package com.website.online.sale.service.shopping_cart;

import com.website.online.sale.dtos.response.ProductDetail;
import com.website.online.sale.dtos.response.ShoppingCartResponse;
import com.website.online.sale.handlers.CustomBadRequestException;
import com.website.online.sale.model.ProductCart;
import com.website.online.sale.model.SanPham;
import com.website.online.sale.model.ShoppingCart;
import com.website.online.sale.repository.ProductCartRepository;
import com.website.online.sale.repository.SanPhamRepository;
import com.website.online.sale.repository.ShoppingCartRepository;
import com.website.online.sale.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductCartRepository productCartRepository;
    private final SanPhamRepository sanPhamRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductCartRepository productCartRepository, SanPhamRepository sanPhamRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productCartRepository = productCartRepository;
        this.sanPhamRepository = sanPhamRepository;
    }

    @Override
    public ShoppingCartResponse getCartDetail(Long id) {
        log.info("getCartDetail starting..... id - {}", id);
        ShoppingCartResponse responseshoppingCart = new ShoppingCartResponse();
        ShoppingCart shoppingCartById = shoppingCartRepository.findByIdKh(id);
        if (Objects.isNull(shoppingCartById)) {
            throw new CustomBadRequestException("Không tìm thấy giỏ hàng");
        }
        responseshoppingCart.setId(id);

        List<ProductCart> productCartList = productCartRepository.findAllByIdGh(shoppingCartById.getId());
        if (CollectionUtils.isEmpty(productCartList)) {
            return responseshoppingCart;
        }
        List<ProductDetail> productDetailList = new ArrayList<>();
        productCartList.forEach(productCart -> {
            ProductDetail productDetail = new ProductDetail();
            Optional<SanPham> optionalSanPham = sanPhamRepository.findById(productCart.getIdSp());
            if (optionalSanPham.isPresent()) {
                SanPham sanPham = optionalSanPham.get();
                productDetail = ProductDetail.builder()
                        .name(sanPham.getTen())
                        .quantity(sanPham.getSoLuong())
                        .price(sanPham.getGia())
                        .image(sanPham.getImage())
                        .build();
            }
            productDetailList.add(productDetail);
        });

        responseshoppingCart.setListProducts(productDetailList);
//        Long total = 0L;
//        for (ProductDetail productDetail : productDetailList) {
//            total = total + productDetail.getPrice() * productDetail.getQuantity();
//        }
        Long total = productDetailList.stream().mapToLong(productDetail -> productDetail.getQuantity() * productDetail.getPrice()).sum();
        responseshoppingCart.setTotalMoney(total);
        log.info("getCartDetail end..... response - {}", JsonUtils.stringify(responseshoppingCart));
        return responseshoppingCart;
    }
}
