package web_shop.api_web_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web_shop.api_web_shop.entity.LoaiSanPham;

public interface MauSacRepository extends JpaRepository<LoaiSanPham, Long> {
}
