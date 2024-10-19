package com.website.online.sale.controller;

import com.website.online.sale.dtos.request.UserLoginRequest;
import com.website.online.sale.dtos.request.UserSignupRequest;
import com.website.online.sale.dtos.response.CheckLoginResponse;
import com.website.online.sale.dtos.response.CheckSignupResponse;
import com.website.online.sale.model.KhachHang;
import com.website.online.sale.model.SanPham;
import org.springframework.web.bind.annotation.*;
import com.website.online.sale.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/user")
    public Object getAllUser (){
        return userService.getAllUser();
    }

    @PostMapping("/api/v1/check_login")
    public CheckLoginResponse checkLogin (
            @RequestBody UserLoginRequest request
    ){
        return userService.checkLogin(request.getUsername(), request.getPassword());
    }

    @PostMapping("/api/v2/check_signup")
    public CheckSignupResponse checkSignup (
            @RequestBody UserSignupRequest request
    ){
        return userService.checkSignup(request.getTen(), request.getUsername(), request.getPassword(), request.getSdt(), request.getDiaChi(), request.getNgaySinh(), request.getEmail());
    }

    @GetMapping("/api/v1/sanpham")
    public List<SanPham> getSanPhamList(){
        List<SanPham> response = userService.getAllSanPham();
        return response;
    }

    @GetMapping("/api/danhsachsanpham")
    public List<SanPham> layDanhSachSanPhamTheoTen(
            @RequestParam(name = "ten_sp", required = false) String tenSp,
            @RequestParam(name = "ten_kh", required = false) String tenKh
    ){
        List<SanPham> response = userService.layDanhSachSanPham(tenSp, tenKh);
        return response;
    }
}
