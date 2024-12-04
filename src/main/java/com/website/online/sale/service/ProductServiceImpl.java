package com.website.online.sale.service;

import com.website.online.sale.dtos.request.product.ListProductReq;
import com.website.online.sale.dtos.response.SanPhamResponse;
import com.website.online.sale.dtos.response.SpecificationProductDto;
import com.website.online.sale.dtos.response.product.ListProductResponse;
import com.website.online.sale.model.Discount;
import com.website.online.sale.model.ProductDiscount;
import com.website.online.sale.model.ProductSpecification;
import com.website.online.sale.model.SanPham;
import com.website.online.sale.repository.DiscountRepository;
import com.website.online.sale.repository.ProductDiscountRepository;
import com.website.online.sale.repository.ProductSpecificationRepository;
import com.website.online.sale.repository.SanPhamRepository;
import com.website.online.sale.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{
    private final SanPhamRepository sanPhamRepository;
    private final ProductServiceCustom productServiceCustom;
    private final ProductDiscountRepository productDiscountRepository;
    private final DiscountRepository discountRepository;
    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductServiceImpl(SanPhamRepository sanPhamRepository,
                              ProductServiceCustom productServiceCustom, ProductDiscountRepository productDiscountRepository, DiscountRepository discountRepository, ProductSpecificationRepository productSpecificationRepository
    ) {
        this.sanPhamRepository = sanPhamRepository;
        this.productServiceCustom = productServiceCustom;
        this.productDiscountRepository = productDiscountRepository;
        this.discountRepository = discountRepository;
        this.productSpecificationRepository = productSpecificationRepository;
    }


    @Override
    public List<SanPham> layDanhSachSanPham(String text) {
        return sanPhamRepository.findByTenContaining(text);
    }

    @Override
    public SanPhamResponse laySanPhamChiTiet(Long id) {
        Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);
        if (optionalSanPham.isPresent()) {
            SanPham sanPhamById = optionalSanPham.get();

            SanPhamResponse sanPhamResponse = new SanPhamResponse();
            sanPhamResponse.setId(sanPhamById.getId());
            sanPhamResponse.setTen(sanPhamById.getTen());
            sanPhamResponse.setGia(sanPhamById.getGia());
            sanPhamResponse.setSoLuong(sanPhamById.getSoLuong());
            sanPhamResponse.setMoTa(sanPhamById.getMoTa());
            sanPhamResponse.setImage(sanPhamById.getImage());
            return sanPhamResponse;
        }
        return null;
    }

    @Override
    public List<ListProductResponse> getListProducts(ListProductReq request) {
        List<ListProductResponse> responses = new ArrayList<>();
        List<SanPham> sanPhams = productServiceCustom.getListByFilter(request);
        // Từ list sp => list chi tiết
        sanPhams.forEach(sanPham -> {
            ListProductResponse listProductResponse = new ListProductResponse();
            listProductResponse.setId(sanPham.getId());
            listProductResponse.setName(sanPham.getTen());
            listProductResponse.setQuantity(sanPham.getSoLuong());
            listProductResponse.setPrice(sanPham.getGia());
            listProductResponse.setImage(sanPham.getImage());

            //set giam gia
            ProductDiscount productDiscount = productDiscountRepository.findFirstByIdSp(sanPham.getId());
            if (Objects.nonNull(productDiscount)) {
                Discount discount = discountRepository.findFirstById(productDiscount.getIdGg());
                listProductResponse.setDiscount(Objects.nonNull(discount) ? discount.getGiaTri() : null);
            }
            //Set thong so
            ProductSpecification productSpecification = productSpecificationRepository.findFirstByIdProduct(sanPham.getId());
            if (Objects.nonNull(productSpecification)) {
                SpecificationProductDto dto = JsonUtils.parse(productSpecification.getConfig(), SpecificationProductDto.class);
                listProductResponse.setSpecification(dto);
            }
            responses.add(listProductResponse);
        });
        return responses;
    }

}
