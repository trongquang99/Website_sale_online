package com.website.online.sale.service;

import com.website.online.sale.dtos.response.CheckLoginResponse;
import com.website.online.sale.dtos.response.CheckSignupResponse;
import com.website.online.sale.model.*;
import com.website.online.sale.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final KhachHangRepository khachHangRepository;
    private final SanPhamRepository sanPhamRepository;
    private final HoaDonRepository hoaDonRepository;
    private final HoaDonSanPhamRepository hoaDonSanPhamRepository;

    public UserServiceImpl(UserRepository userRepository, KhachHangRepository khachHangRepository, SanPhamRepository sanPhamRepository, HoaDonRepository hoaDonRepository, HoaDonSanPhamRepository hoaDonSanPhamRepository) {
        this.userRepository = userRepository;
        this.khachHangRepository = khachHangRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.hoaDonSanPhamRepository = hoaDonSanPhamRepository;
    }

    @Override
    public Object getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public CheckLoginResponse checkLogin(String username, String pass) {
        CheckLoginResponse res = new CheckLoginResponse();
        res.setUserName(username);
        res.setPassWord(pass);
        User user = userRepository.findFirstByUserNameAndPass(username, pass);
        if (Objects.isNull(user)){
            res.setStatus(false);
            res.setError("Sai mật khẩu hoặc tài khoản");
        }else res.setStatus(true);
        return res;
    }

    @Override
    public CheckSignupResponse checkSignup(String ten, String username, String password, String sdt, String diaChi, Date ngaySinh, String email) {
        CheckSignupResponse res = new CheckSignupResponse();

        User user =  userRepository.findByUserNameAndPass(username, password);
        if(Objects.nonNull(user)){
            res.setStatus(false);
            res.setError("mật khẩu hoặc tài khoản đã tồn tại");
            return res;
        }
        KhachHang khachHang = khachHangRepository.findByEmail(email);
        if (Objects.nonNull(khachHang)){
            res.setStatus(false);
            res.setError("email đã tồn tại");
            return res;
        }

        KhachHang khachHang2 = khachHangRepository.findBySdt(sdt);
        if (Objects.nonNull(khachHang2)){
            res.setStatus(false);
            res.setError("sdt đã tồn tại");
            return res;
        }

        User newuser = new User();
        newuser.setUserName(username);
        newuser.setPass(password);
        newuser = userRepository.save(newuser);

        KhachHang newKhachHang = new KhachHang();
        newKhachHang.setIdUser(newuser.getId());
        newKhachHang.setTen(ten);
        newKhachHang.setSdt(sdt);
        newKhachHang.setDiaChi(diaChi);
        newKhachHang.setNgaySinh(ngaySinh);
        newKhachHang.setEmail(email);
        khachHangRepository.save(newKhachHang);

        res.setTen(ten);
        res.setSdt(sdt);
        res.setDiaChi(diaChi);
        res.setNgaySinh(ngaySinh);
        res.setEmail(email);
        res.setPassWord(password);
        res.setStatus(true);

        return res;
    }

    @Override
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    @Override
    public List<SanPham> layDanhSachSanPham(String tenSp, String tenKh) {
        List<SanPham> sanPhams = new ArrayList<>();
        if(tenSp == null && tenKh == null){
            sanPhams = sanPhamRepository.findAll();
        }

        if(tenSp != null && tenKh == null){
            sanPhams = sanPhamRepository.findByTenContaining(tenSp);
        }

        if(tenSp == null && tenKh != null){
            List<KhachHang> dSKHTheoTen = khachHangRepository.findByTenContaining(tenKh);
            List<Long> dSIdKh = new ArrayList<>();
            dSKHTheoTen.forEach(khachHang -> {
                dSIdKh.add(khachHang.getId());
            });
            List<HoaDon> hoaDonDSIDKH = hoaDonRepository.findByIdKhIn(dSIdKh);
            List<Long> dSIdHd = new ArrayList<>();
            hoaDonDSIDKH.forEach(hoaDon -> {
                dSIdHd.add(hoaDon.getId());
            });
            List<HoaDonSanPham> hoaDonSanPhamdSIdHd = hoaDonSanPhamRepository.findByIdHdIn(dSIdHd);
            List<Long> dSIdSp = new ArrayList<>();
            hoaDonSanPhamdSIdHd.forEach(hoaDonSanPham -> {
                dSIdSp.add(hoaDonSanPham.getId());
            });
            sanPhams = sanPhamRepository.findByIdIn(dSIdSp);
        }
        return sanPhams;
    }

}


