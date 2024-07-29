package web_shop.api_web_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_shop.api_web_shop.entity.GioHangChiTiet;

import java.util.Optional;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {
    // Dung Optional de tu dong bat exception null
    // findByGioHangIdAndSanPhamChiTietId := // select * from gio_hang_chi_tiet where gio_hang_id = ? and san_pham_chi_tiet_id = ?
    Optional<GioHangChiTiet> findByGioHangIdAndSanPhamChiTietId(Long gioHangId, Long sanPhamChiTietId);
}
