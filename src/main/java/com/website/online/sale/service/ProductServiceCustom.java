package com.website.online.sale.service;

import com.website.online.sale.dtos.request.product.ListProductReq;
import com.website.online.sale.model.SanPham;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceCustom {
    private final EntityManager entityManager;

    public ProductServiceCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<SanPham> getListByFilter(ListProductReq request) {
        StringBuilder queryBuilder = new StringBuilder("""
                select * from sanpham
                """);
        var params = new HashMap<String, Object>();

        if (Objects.nonNull(request.getName())) {
            queryBuilder.append("""
                    where ten like concat('%', :name, '%')
                    """);
            params.put("name", request.getName());
        }

        if (Objects.nonNull(request.getPrice())) {
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("""
                    gia = :price
                    """);
            params.put("price", request.getPrice());
        }

        if (Objects.nonNull(request.getStorageRom())) {
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append(""" 
                    id in (select id_product from product_specifications where json_extract(config, '$.storage.storage_rom') like concat('%', :storageRom, '%') )
                    """);
            params.put("storageRom", request.getStorageRom());
        }

        if(Objects.nonNull(request.getPriceFrom())){
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("""
                    gia >= :priceFrom
                    """);
            params.put("priceFrom", request.getPriceFrom());
        }

        if(Objects.nonNull(request.getPriceTo())){
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("""
                    gia <= :priceTo
                    """);
            params.put("priceTo", request.getPriceTo());
        }

        if (Objects.nonNull(request.getBatteryCapacity())) {
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append(""" 
                    id in (select id_product from product_specifications where json_extract(config, '$.battery_info.battery_capacity') like concat('%', :batteryCapacity, '%') )
                    """);
            params.put("batteryCapacity", request.getBatteryCapacity());
        }

        if(Objects.nonNull(request.getDateFrom())){
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("""
                    created >= :dateFrom
                    """);
            params.put("dateFrom", request.getDateFrom());
        }

        if(Objects.nonNull(request.getDateTo())){
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("""
                    updated <= :dateTo
                    """);
            params.put("dateTo", request.getDateTo());
        }

        var query = entityManager.createNativeQuery(queryBuilder.toString(), SanPham.class);
        params.forEach(query::setParameter);
        List<SanPham> rs = (List<SanPham>) query.getResultList();
        return rs;
    }
}
