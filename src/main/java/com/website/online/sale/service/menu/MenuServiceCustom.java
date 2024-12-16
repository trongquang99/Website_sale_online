package com.website.online.sale.service.menu;

import com.website.online.sale.dtos.request.menu.WarrantyRequest;
import com.website.online.sale.model.SanPham;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
@Service
public class MenuServiceCustom {
    private final EntityManager entityManager;

    public MenuServiceCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<SanPham> getWarrantyByFilter(WarrantyRequest warrantyRequest) {
        StringBuilder queryBuilder = new StringBuilder("""
                select * from sanpham
                """);
        var params = new HashMap<String, Object>();

        if (Objects.nonNull(warrantyRequest.getImeiCode())) {
            queryBuilder.append("""
                    where id in(select id_product from warranty where imei = :imeiCode )
                    """);
            params.put("imeiCode", warrantyRequest.getImeiCode());
        }

        if (Objects.nonNull(warrantyRequest.getPhone())) {
            if (params.isEmpty()) queryBuilder.append("where ");
            else {
                queryBuilder.append("and ");
            }
            queryBuilder.append("""
                    id in (
                    select id_sp from giohangsanpham where id_gh in(
                    select id from giohang where id_kh in(
                    select id from khachhang where sdt = :phone)) )
                    """);
            params.put("phone", warrantyRequest.getPhone());
        }

        var query = entityManager.createNativeQuery(queryBuilder.toString(), SanPham.class);
        params.forEach(query::setParameter);
        List<SanPham> rs = (List<SanPham>) query.getResultList();
        return rs;
    }
}
