package com.website.online.sale.repository;

import com.website.online.sale.model.HoaDon;
import com.website.online.sale.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    KhachHang findByEmail(String email);
    KhachHang findBySdt(String sdt);
    List<KhachHang> findByTenContaining(String ten);
}
