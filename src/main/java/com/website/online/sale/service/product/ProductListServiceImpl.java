package com.website.online.sale.service.product;

import com.website.online.sale.dtos.request.ProductListRequest;
import com.website.online.sale.model.ProductSpecification;
import com.website.online.sale.model.SanPham;
import com.website.online.sale.repository.ProductSpecificationRepository;
import com.website.online.sale.repository.SanPhamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductListServiceImpl implements ProductListService {
    private final SanPhamRepository sanPhamRepository;
    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductListServiceImpl(SanPhamRepository sanPhamRepository, ProductSpecificationRepository productSpecificationRepository) {
        this.sanPhamRepository = sanPhamRepository;
        this.productSpecificationRepository = productSpecificationRepository;
    }

//    @Override
//    public List<SanPham> getProductList(String nameSp, Long priceSp, String memorySp) {
//        List<SanPham> sanPhams = new ArrayList<>();
//        if (nameSp == null && priceSp == null && memorySp == null) {
//            sanPhams = sanPhamRepository.findAll();
//        }
//
//        if (nameSp == null && priceSp != null && memorySp == null) {
//            sanPhams = sanPhamRepository.productPriceList(priceSp);
//        }
//        if (nameSp == null && priceSp == null && memorySp != null) {
//            List<ProductSpecification> specificationList = productSpecificationRepository.findByConfigContaining(memorySp);
//            List<Long> dSIdSpecification = new ArrayList<>();
//            specificationList.forEach(specification ->
//                    dSIdSpecification.add(specification.getId()));
//
//            sanPhams = sanPhamRepository.findByIdIn(dSIdSpecification);
//        }
//
//        if (nameSp != null && priceSp == null && memorySp == null) {
//            sanPhams = sanPhamRepository.findByTenContaining(nameSp);
//        }
//
//        return sanPhams;
//    }

//    @Override
//    public List<SanPham> getProductList(ProductListRequest productListRequest) {
//        List<SanPham> sanPhams = new ArrayList<>();
//        String nameSp = productListRequest.getNameSp();
//        Long priceSp = productListRequest.getPriceSp();
//        String memorySp = productListRequest.getMemorySp();
//
//        if (nameSp == null && priceSp == null && memorySp == null) {
//            sanPhams = sanPhamRepository.findAll();
//        }
//
//        if (nameSp == null && priceSp != null && memorySp == null) {
//            sanPhams = sanPhamRepository.productPriceList(priceSp);
//        }
//        if (nameSp == null && priceSp == null && memorySp != null) {
//            List<ProductSpecification> specificationList = productSpecificationRepository.findByConfigContaining(memorySp);
//            List<Long> dSIdSpecification = new ArrayList<>();
//            specificationList.forEach(specification ->
//                    dSIdSpecification.add(specification.getId()));
//
//            sanPhams = sanPhamRepository.findByIdIn(dSIdSpecification);
//        }
//
//        if (nameSp != null && priceSp == null && memorySp == null) {
//            sanPhams = sanPhamRepository.findByTenContaining(nameSp);
//        }
//
//        return sanPhams;
//    }
}
