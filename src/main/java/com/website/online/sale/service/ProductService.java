package com.website.online.sale.service;

import com.website.online.sale.dtos.request.product.ListProductReq;
import com.website.online.sale.dtos.response.SanPhamResponse;
import com.website.online.sale.dtos.response.product.ListProductResponse;
import com.website.online.sale.model.SanPham;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {

    List<SanPham> layDanhSachSanPham(String text);

    SanPhamResponse laySanPhamChiTiet(Long id);

    List<ListProductResponse> getListProducts(ListProductReq request);
}
