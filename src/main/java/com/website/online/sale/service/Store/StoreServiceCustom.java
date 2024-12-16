package com.website.online.sale.service.Store;

import com.website.online.sale.dtos.request.StoreRequest;
import com.website.online.sale.model.Cuahang;
import com.website.online.sale.model.SanPham;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class StoreServiceCustom {
    private final EntityManager entityManager;

    public StoreServiceCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Cuahang> getListStoreByFilter(StoreRequest storeRequest) {
        StringBuilder queryBuilder = new StringBuilder("""
                select * from cuahang
                """);
        var params = new HashMap<String, Object>();

        if (Objects.nonNull(storeRequest.getName())) {
            queryBuilder.append("""
                    where ten like concat('%', :name, '%')
                    """);
            params.put("name", storeRequest.getName());
        }

        if (Objects.nonNull(storeRequest.getAddress())) {
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("""
                    dia_chi like concat('%', :address, '%')
                    """);
            params.put("address", storeRequest.getAddress());
        }

        var query = entityManager.createNativeQuery(queryBuilder.toString(), Cuahang.class);
        params.forEach(query::setParameter);
        List<Cuahang> rs = (List<Cuahang>) query.getResultList();
        return rs;
    }
}
