package web_shop.api_web_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Double donGia;
    private Long soLuong;
    private Double thanhTien;

    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;
}
