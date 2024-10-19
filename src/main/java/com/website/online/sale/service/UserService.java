package com.website.online.sale.service;

import com.website.online.sale.dtos.response.CheckLoginResponse;
import com.website.online.sale.dtos.response.CheckSignupResponse;
import com.website.online.sale.model.KhachHang;
import com.website.online.sale.model.SanPham;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface UserService {
    Object getAllUser();

    CheckLoginResponse checkLogin(String username, String pass);

    List<SanPham> getAllSanPham();

    CheckSignupResponse checkSignup(String ten, String username, String password, String sdt, String diaChi, Date ngaySinh, String email);

    List<SanPham> layDanhSachSanPham(String tenSp, String tenKh);
}
