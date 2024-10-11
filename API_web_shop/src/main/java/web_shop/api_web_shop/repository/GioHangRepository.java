package web_shop.api_web_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_shop.api_web_shop.entity.GioHang;

import java.util.Optional;

public interface GioHangRepository extends JpaRepository<GioHang, Long>{
    Optional<GioHang> findByTaiKhoanId(Long id);
}
