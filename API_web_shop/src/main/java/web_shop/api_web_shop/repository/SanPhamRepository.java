package web_shop.api_web_shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web_shop.api_web_shop.entity.SanPham;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query("""
    select s from SanPham s
    where (:ten is null or s.ten like concat('%', :ten, '%') )
    and ( :ma is null or s.ma like concat('%', :ma, '%') )
    and ( :idLoaiSanPham is null or s.loaiSanPham.id = :idLoaiSanPham )
    """)
    Page<SanPham> getAll(@Param("ten") String ten,
                         @Param("ma") String ma,
                         @Param("idLoaiSanPham") Long idLoaiSanPham,
                         Pageable pageable);



}
