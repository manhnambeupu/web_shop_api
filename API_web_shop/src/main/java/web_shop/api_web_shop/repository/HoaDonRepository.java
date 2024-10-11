package web_shop.api_web_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_shop.api_web_shop.entity.HoaDon;

import java.util.Optional;

public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    Optional<HoaDon> findByIdAndTrangThai(Long id, Integer trangThai);
}
