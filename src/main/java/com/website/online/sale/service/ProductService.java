package com.website.online.sale.service;

import com.website.online.sale.dtos.response.SanPhamResponse;
import com.website.online.sale.model.SanPham;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {

    List<SanPham> layDanhSachSanPham(String text);

    SanPhamResponse laySanPhamChiTiet(Long id);
}
