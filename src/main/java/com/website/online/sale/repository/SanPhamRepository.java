package com.website.online.sale.repository;

import com.website.online.sale.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    List<SanPham> findByTenContaining(String ten);
    List<SanPham> findByIdIn(List<Long> id);

    @Query(value = "select * from sanpham where gia = :x" , nativeQuery = true)
    List<SanPham> productPriceList(@Param("x") Long gia);

    List<SanPham> findByIdDmc(Long idDmc);

}
