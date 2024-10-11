package web_shop.api_web_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_shop.api_web_shop.entity.HoaDonChiTiet;

import java.util.List;
import java.util.Optional;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    Optional<List<HoaDonChiTiet>> findByHoaDonId(Long id);
}
