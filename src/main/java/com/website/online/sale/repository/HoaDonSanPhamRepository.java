package com.website.online.sale.repository;

import com.website.online.sale.model.HoaDon;
import com.website.online.sale.model.HoaDonSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonSanPhamRepository extends JpaRepository<HoaDonSanPham, Long> {
    List<HoaDonSanPham> findByIdHdIn(List<Long> idHd);

}
