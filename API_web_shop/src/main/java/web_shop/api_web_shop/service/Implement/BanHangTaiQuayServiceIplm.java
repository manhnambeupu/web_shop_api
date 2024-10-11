package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.HoaDonChiTietTaiQuayDTO;
import web_shop.api_web_shop.entity.HoaDon;
import web_shop.api_web_shop.entity.HoaDonChiTiet;
import web_shop.api_web_shop.entity.SanPham;
import web_shop.api_web_shop.entity.SanPhamChiTiet;
import web_shop.api_web_shop.repository.HoaDonChiTietRepository;
import web_shop.api_web_shop.repository.HoaDonRepository;
import web_shop.api_web_shop.repository.SanPhamChiTietRepository;
import web_shop.api_web_shop.repository.SanPhamRepository;
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
        HoaDon hoaDon = hoaDonRepository.findById(dto.getGioHangId()).orElseThrow(() -> new RuntimeException("Bill not found"));
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


}
