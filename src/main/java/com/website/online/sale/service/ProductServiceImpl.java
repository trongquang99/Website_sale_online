package com.website.online.sale.service;

import com.website.online.sale.dtos.response.SanPhamResponse;
import com.website.online.sale.model.SanPham;
import com.website.online.sale.repository.SanPhamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final SanPhamRepository sanPhamRepository;

    public ProductServiceImpl(SanPhamRepository sanPhamRepository) {
        this.sanPhamRepository = sanPhamRepository;
    }


    @Override
    public List<SanPham> layDanhSachSanPham(String text) {
        return sanPhamRepository.findByTenContaining(text);
    }

    @Override
    public SanPhamResponse laySanPhamChiTiet(Long id) {
        Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);
        if (optionalSanPham.isPresent()) {
            SanPham sanPhamById = optionalSanPham.get();

            SanPhamResponse sanPhamResponse = new SanPhamResponse();
            sanPhamResponse.setId(sanPhamById.getId());
            sanPhamResponse.setTen(sanPhamById.getTen());
            sanPhamResponse.setGia(sanPhamById.getGia());
            sanPhamResponse.setSoLuong(sanPhamById.getSoLuong());
            sanPhamResponse.setMoTa(sanPhamById.getMoTa());
            sanPhamResponse.setImage(sanPhamById.getImage());
            return sanPhamResponse;
        }
        return null;
    }
}
