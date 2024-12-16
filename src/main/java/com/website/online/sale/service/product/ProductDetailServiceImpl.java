package com.website.online.sale.service.product;

import com.website.online.sale.dtos.response.ProductDetailResponse;
import com.website.online.sale.dtos.response.SpecificationProductDto;
import com.website.online.sale.handlers.CustomBadRequestException;
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

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService{
    private final SanPhamRepository sanPhamRepository;
    private final ProductDiscountRepository productDiscountRepository;
    private final DiscountRepository discountRepository;
    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductDetailServiceImpl(SanPhamRepository sanPhamRepository, ProductDiscountRepository productDiscountRepository, DiscountRepository discountRepository, ProductSpecificationRepository productSpecificationRepository) {
        this.sanPhamRepository = sanPhamRepository;
        this.productDiscountRepository = productDiscountRepository;
        this.discountRepository = discountRepository;
        this.productSpecificationRepository = productSpecificationRepository;
    }

    @Override
    public ProductDetailResponse getProductDetail(Long id) {
        ProductDetailResponse productDetailResponse = new ProductDetailResponse();
        SanPham sanPhamById = sanPhamRepository.findById(id).orElse(null);

        if(Objects.isNull(sanPhamById)){
            throw new CustomBadRequestException("khong tim thay san pham");
        }

        productDetailResponse.setId(sanPhamById.getId());
        productDetailResponse.setName(sanPhamById.getTen());
        productDetailResponse.setQuantity(sanPhamById.getSoLuong());
        productDetailResponse.setPrice(sanPhamById.getGia());
        productDetailResponse.setDescription(sanPhamById.getMoTa());
        productDetailResponse.setImage(sanPhamById.getImage());

        ProductDiscount productDiscount = productDiscountRepository.findByIdSp(id);
        if (Objects.nonNull(productDiscount)) {
            Discount optionalDiscount = discountRepository.findById(productDiscount.getIdGg()).orElse(null);
            if (Objects.nonNull(optionalDiscount)) {
                productDetailResponse.setPromotion(optionalDiscount.getTen());
                productDetailResponse.setDiscount(optionalDiscount.getGiaTri());
            }
        }

        // lay thong so
        //B1: tim bang ts_sp => check co ton tai ko
        //B2: lay config => pare ra dto
        //b3: set dto vào specifications

        ProductSpecification productSpecification = productSpecificationRepository.findByIdProduct(id);
        if(Objects.nonNull(productSpecification)){
            SpecificationProductDto dto = JsonUtils.parse(productSpecification.getConfig(), SpecificationProductDto.class);
            productDetailResponse.setSpecifications(dto);
        }
        return productDetailResponse;
    }
}
