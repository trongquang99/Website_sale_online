package com.website.online.sale.service.product;

import com.website.online.sale.dtos.request.ProductListRequest;
import com.website.online.sale.model.SanPham;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ProductListBuilderService {
    private final EntityManager entityManager;

    public ProductListBuilderService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<SanPham> getProductListBuilder(ProductListRequest request) {
        StringBuilder queryBuilder = new StringBuilder("""
                select * from sanpham
                """);
        var params = new HashMap<String, Object>();

        if(Objects.nonNull(request.getName())) {
            queryBuilder.append("where ten like concat('%', :name, '%') ");
            params.put("name", request.getName());
        }

        if(Objects.nonNull(request.getPrice())) {
            if(params.isEmpty()){
                queryBuilder.append("where ");
            }
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("gia = :price ");
            params.put("price", request.getPrice());
        }

        if(Objects.nonNull(request.getMemory())) {
            if(params.isEmpty()){
                queryBuilder.append("where ");
            }
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("""
                    id in(
                    select id_product from product_specifications where config like concat('%', :memory, '%') )
                    """);
            params.put("memory", request.getMemory());
        }

        String cmd = queryBuilder.toString();
        var query = entityManager.createNativeQuery(cmd, SanPham.class);
        params.forEach(query::setParameter);
        List<SanPham> rs = (List<SanPham>) query.getResultList();
        return rs;
    }

}
