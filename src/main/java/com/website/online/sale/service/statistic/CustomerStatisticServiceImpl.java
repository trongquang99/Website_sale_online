package com.website.online.sale.service.statistic;

import com.website.online.sale.dtos.response.statistic.CustomerStatisticResponse;
import com.website.online.sale.dtos.response.statistic.ProductStatisticResponse;
import com.website.online.sale.dtos.response.statistic.PurchasedProductType;
import com.website.online.sale.model.*;
import com.website.online.sale.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class CustomerStatisticServiceImpl implements CustomerStatisticService {
    private final SanPhamRepository sanPhamRepository;
    private final MenuRepository menuRepository;
    private final DanhmucconRepository danhmucconRepository;
    private final KhachHangRepository khachHangRepository;
    private final HoaDonRepository hoaDonRepository;
    private final HoaDonSanPhamRepository hoaDonSanPhamRepository;
    private final DiscountRepository discountRepository;
    private final ProductDiscountRepository productDiscountRepository;

    public CustomerStatisticServiceImpl(SanPhamRepository sanPhamRepository, MenuRepository menuRepository, DanhmucconRepository danhmucconRepository, KhachHangRepository khachHangRepository, HoaDonRepository hoaDonRepository, HoaDonSanPhamRepository hoaDonSanPhamRepository, DiscountRepository discountRepository, ProductDiscountRepository productDiscountRepository) {
        this.sanPhamRepository = sanPhamRepository;
        this.menuRepository = menuRepository;
        this.danhmucconRepository = danhmucconRepository;
        this.khachHangRepository = khachHangRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.hoaDonSanPhamRepository = hoaDonSanPhamRepository;
        this.discountRepository = discountRepository;
        this.productDiscountRepository = productDiscountRepository;
    }

    @Override
    public List<CustomerStatisticResponse> getCustomerStatistic() {
        //Khoi tao 1 list<dtoResponse>
        List<CustomerStatisticResponse> customerStatisticResponseList = new ArrayList<>();

        //Tim all KH
        List<KhachHang> khachHangs = khachHangRepository.findAll();

        //for qua tung thang KH để( mỗi 1 KH tương ứng 1 dtoResponse)
        // TH1: KH đã mua sp
        // TH2: chưa từng mua => để số luong mua và so luong loai sp = 0 ( có thể bỏ)
        khachHangs.forEach(khachHang -> {
            // Khởi tạo 1 dtoResponse tương ứng vs khachHang
            CustomerStatisticResponse customerStatisticResponse = new CustomerStatisticResponse();

            // add 1 số thuộc tính đã có từ đối Khachhang
            customerStatisticResponse.setId(khachHang.getId());
            customerStatisticResponse.setName(khachHang.getTen());
            customerStatisticResponse.setPhone(khachHang.getSdt());
            customerStatisticResponse.setAddress(khachHang.getDiaChi());

            //Khoi tao thuoc tinh 2 list
            List<CustomerStatisticResponse.PurchasedProduct> purchasedProducts = new ArrayList<>();
            Set<PurchasedProductType> purchasedProductTypeSet = new HashSet<>();

            /// tim sp của KH => xử lý 2 thuộc tính list cho dtoResponse
            List<HoaDon> hoaDons = hoaDonRepository.findByIdKh(khachHang.getId());
            List<Long> hoaDonsId = hoaDons.stream().map(HoaDon::getId).toList();
            List<HoaDonSanPham> hoaDonSanPhams = hoaDonSanPhamRepository.findByIdHdIn(hoaDonsId);

            hoaDonSanPhams.forEach(hoaDonSanPham -> {
                Optional<SanPham> optionalSanPham = sanPhamRepository.findById(hoaDonSanPham.getIdSp());
                if (optionalSanPham.isPresent()) {
                    SanPham sanPham = optionalSanPham.get();

                    // Kiểm tra sản phẩm đã tồn tại trong danh sách chưa
                    CustomerStatisticResponse.PurchasedProduct existingProduct = null;
                    for (CustomerStatisticResponse.PurchasedProduct purchasedProduct : purchasedProducts) {
                        if (purchasedProduct.getId().equals(sanPham.getId())) {
                            existingProduct = purchasedProduct;
                            break; // Dừng lại nếu tìm thấy
                        }
                    }

                    if (existingProduct != null) {
                        // Nếu đã tồn tại, tăng số lượng
                        existingProduct.setQuantity(existingProduct.getQuantity() + hoaDonSanPham.getSoLuong());
                    } else {
                        // Nếu chưa tồn tại, thêm mới
                        CustomerStatisticResponse.PurchasedProduct purchasedProduct = new CustomerStatisticResponse.PurchasedProduct();
                        purchasedProduct.setId(sanPham.getId());
                        purchasedProduct.setName(sanPham.getTen());
                        purchasedProduct.setQuantity(sanPham.getSoLuong());
                        purchasedProducts.add(purchasedProduct);
                    }
                    // Xử lý sp đã mua
//                    CustomerStatisticResponse.PurchasedProduct purchasedProduct = new CustomerStatisticResponse.PurchasedProduct();
//                    purchasedProduct.setId(sanPham.getId());
//                    purchasedProduct.setName(sanPham.getTen());
//                    purchasedProduct.setQuantity(sanPham.getSoLuong());
//
//                    // check san pham da có chua => viết check
//                    // TH1: chua có thì adđ luôn: purchasedProducts.add(purchasedProduct);
//                    // TH2: đã có => tăng quantity của sp đó > xử lý thêm
//                    purchasedProducts.add(purchasedProduct);

                    //Xử lý loai sp da mua => xử lý lại menu
//                    PurchasedProductType purchasedProductType = new PurchasedProductType();
//                    purchasedProductType.setName(sanPham.getTen()); // tên menu
                    List<Danhmuccon> danhmuccons = danhmucconRepository.findByIdDm(sanPham.getIdDmc());
                    danhmuccons.forEach(danhmuccon -> {
                        Optional<Menu> menuOptional = menuRepository.findById(danhmuccon.getIdDm());
                        if(menuOptional.isPresent()){
                            Menu menu = menuOptional.get();
                            PurchasedProductType purchasedProductType = new PurchasedProductType();
                            purchasedProductType.setId(menu.getId());
                            purchasedProductType.setName(menu.getTen());

                            purchasedProductTypeSet.add(purchasedProductType);
                        }
                    });
                }
            });

            //Add 2 thuộc tính list
            customerStatisticResponse.setPurchasedProducts(purchasedProducts);
            customerStatisticResponse.setPurchasedProductTypes(purchasedProductTypeSet.stream().toList());

            //add hai số lượng

            // Tính số lượng sản phẩm và loại sản phẩm
            long totalQuantity = 0L;
            for (CustomerStatisticResponse.PurchasedProduct purchasedProduct : purchasedProducts) {
                totalQuantity = totalQuantity + purchasedProduct.getQuantity();
            }
            customerStatisticResponse.setNumberPurchasedProduct(totalQuantity);

            customerStatisticResponse.setNumberPurchasedProductType(purchasedProductTypeSet.size());

            // dtoResponse đủ thuộc tính mới add vào list<dtoResponse> => count list
            customerStatisticResponseList.add(customerStatisticResponse);
        });

        return customerStatisticResponseList;
    }

    @Override
    public List<ProductStatisticResponse> getProductStatistic() {
        List<ProductStatisticResponse> productStatisticResponses = new ArrayList<>();
        List<SanPham> sanPhams = sanPhamRepository.findAll();
        if (CollectionUtils.isEmpty(sanPhams)) return null;
        sanPhams.forEach(sanPham -> {
            ProductStatisticResponse productStatisticResponse = new ProductStatisticResponse();
            productStatisticResponse.setId(sanPham.getId());
            productStatisticResponse.setName(sanPham.getTen());
            productStatisticResponse.setPrice(sanPham.getGia());

            ProductDiscount productDiscount = productDiscountRepository.findFirstByIdSp(sanPham.getId());

            double discountValue = 0.0;
            if (Objects.nonNull(productDiscount)) {
                Discount discount = discountRepository.findFirstById(productDiscount.getIdGg());
                if (Objects.nonNull(discount)) {
                    discountValue = discount.getGiaTri();
                    productStatisticResponse.setDiscount(discountValue);
                }
            }
            // Giá bán sau khi áp dụng giảm giá
            double discountPrice = sanPham.getGia();
            if (discountValue > 0) {
                discountPrice = sanPham.getGia() * (1 - discountValue / 100);
            }
            
            // Lấy số lượng bán ra (xử lý null)
            int saleCount = Objects.requireNonNullElse(sanPham.getSaleCount(),0);

            // Lấy giá nhập (xử lý null)
            long purchasePrice = Objects.requireNonNullElse(sanPham.getPurchasePrice(), 0L);

            // Tính Doanh thu
            double totalRevenue = discountPrice * saleCount;
            productStatisticResponse.setTotalRevenue(totalRevenue);

            // Tính totalProfit Lợi nhuận
            double totalProfit = totalRevenue - (purchasePrice * saleCount);
            productStatisticResponse.setTotalProfit(totalProfit);

            productStatisticResponses.add(productStatisticResponse);

        });
        return productStatisticResponses;
    }
}
