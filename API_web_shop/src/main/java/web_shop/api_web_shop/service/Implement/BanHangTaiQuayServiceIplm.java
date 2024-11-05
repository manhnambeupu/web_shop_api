package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.HoaDonChiTietTaiQuayDTO;
import web_shop.api_web_shop.dto.PayInStoreRequestDTO;
import web_shop.api_web_shop.entity.*;
import web_shop.api_web_shop.repository.*;
import web_shop.api_web_shop.service.BanHangTaiQuayService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BanHangTaiQuayServiceIplm implements BanHangTaiQuayService {

    private final HoaDonRepository hoaDonRepository;
    private final SanPhamChiTietRepository spctRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final SanPhamRepository sanPhamRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final GioHangRepository gioHangRepository;

    @Override
    public ResponseEntity<String> genBill() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongSanPham(0L);
        hoaDon.setTongSoTien(0D);
        hoaDon.setDate(LocalDate.now());
        hoaDon.setTrangThai(1);
        hoaDon.setLoaiHoaDon(2);
        hoaDonRepository.save(hoaDon);
        return ResponseEntity.ok("Generate Bill Success");
    }

    @Override
    public ResponseEntity<String> addToCart(HoaDonChiTietTaiQuayDTO dto) {
        HoaDon hoaDon = hoaDonRepository.findById(dto.getHoadonId()).orElseThrow(() -> new RuntimeException("Bill not found"));
        SanPhamChiTiet spct = spctRepository.findById(dto.getSanPhamChiTietId()).orElseThrow(()->new RuntimeException("Product not found"));

        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setHoaDon(hoaDon);
        hdct.setSanPhamChiTiet(spct);
        hdct.setSoLuong(dto.getSoLuong());
        hdct.setDonGia(spct.getGia());
        hdct.setThanhTien(spct.getGia() * dto.getSoLuong());
        if(spct.getSoLuongTonKho() < dto.getSoLuong()){
            throw new RuntimeException("Not enough Product in WareHouse");
        }
        hoaDonChiTietRepository.save(hdct);

        spct.setSoLuongTonKho(spct.getSoLuongTonKho() - dto.getSoLuong());
        spct.setSoLuongDaBan(spct.getSoLuongDaBan() + dto.getSoLuong());
        spct.setTrangThai(spct.getSoLuongTonKho() == 0 ? 0:1);

        SanPham sanPham = spct.getSanPham();
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - dto.getSoLuong());
        sanPham.setSoLuongDaBan(sanPham.getSoLuongDaBan() + dto.getSoLuong());
        sanPhamChiTietRepository.save(spct);
        sanPhamRepository.save(sanPham);

        hoaDon.setTongSanPham(hoaDon.getTongSanPham() + 1);
        hoaDon.setTongSoTien(hoaDon.getTongSoTien() + hdct.getThanhTien());
        hoaDonRepository.save(hoaDon);
        return ResponseEntity.ok("Add Product in Cart Success ");
    }

    @Override
    public ResponseEntity<String> addToCartQR(String numProduct, Long idBill){
        HoaDon hoaDon = hoaDonRepository.findById(idBill).orElseThrow(() -> new RuntimeException("Bill not found"));
        SanPhamChiTiet spct = spctRepository.findByMa(numProduct).orElseThrow(()->new RuntimeException("Product not found"));

        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setHoaDon(hoaDon);
        hdct.setSanPhamChiTiet(spct);
        hdct.setSoLuong(1L);
        hdct.setDonGia(spct.getGia());
        hdct.setThanhTien(spct.getGia() * 1);
        if(spct.getSoLuongTonKho() < 1){
            throw new RuntimeException("Not enough Product in WareHouse");
        }
        hoaDonChiTietRepository.save(hdct);

        spct.setSoLuongTonKho(spct.getSoLuongTonKho() -1);
        spct.setSoLuongDaBan(spct.getSoLuongDaBan() + 1);
        spct.setTrangThai(spct.getSoLuongTonKho() == 0 ? 0:1);

        SanPham sanPham = spct.getSanPham();
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - 1);
        sanPham.setSoLuongDaBan(sanPham.getSoLuongDaBan() + 1);
        sanPhamChiTietRepository.save(spct);
        sanPhamRepository.save(sanPham);

        hoaDon.setTongSanPham(hoaDon.getTongSanPham() + 1);
        hoaDon.setTongSoTien(hoaDon.getTongSoTien() + hdct.getThanhTien());
        hoaDonRepository.save(hoaDon);
        return ResponseEntity.ok("Succes");
    }

    @Override
    public ResponseEntity<String> payBill(PayInStoreRequestDTO dto) {
        HoaDon hoaDon = hoaDonRepository.findById(dto.getBillId()).orElseThrow(() -> new RuntimeException("Bill not found"));
        TaiKhoan taiKhoan = taiKhoanRepository.findByPhone(dto.getPhone()).orElseThrow(() -> new RuntimeException("Phone not found"));

        if (taiKhoan == null && dto.getPhone() == null){
            hoaDon.setHoVaTen("New Guest");
        }else if (taiKhoan == null && dto.getPhone()!= null ) {
            hoaDon.setSoDienThoai(dto.getPhone());
            hoaDon.setHoVaTen("Old Guest");
            taiKhoan = new TaiKhoan();
            taiKhoan.setPhone(dto.getPhone());
            taiKhoan.setStatus(1);
            taiKhoan.setTongTien(hoaDon.getTongSoTien());
            taiKhoan.setHangTaiKhoan(1);
            taiKhoan.setRole("KhanhHang");
            taiKhoan = taiKhoanRepository.save(taiKhoan);
            GioHang gioHang = new GioHang(0L,0D,taiKhoan);
            gioHangRepository.save(gioHang);
        }else{
            hoaDon.setSoDienThoai(dto.getPhone());
            hoaDon.setHoVaTen(taiKhoan.getHoVaTen());
            taiKhoan.setTongHoaDon(taiKhoan.getTongHoaDon() + 1);
            taiKhoan.setTongTien(taiKhoan.getTongTien() + hoaDon.getTongSoTien());
            taiKhoan.setHangTaiKhoan(taiKhoan.getTongTien() > 1000 && taiKhoan.getTongHoaDon() > 10 ? 2 : 1);
            taiKhoanRepository.save(taiKhoan);
        }


        hoaDon.setNgayHoanThanh(LocalDate.now());
        hoaDon.setTrangThai(4);
        hoaDonRepository.save(hoaDon);
        return ResponseEntity.ok("PayBill Success");
    }


}
