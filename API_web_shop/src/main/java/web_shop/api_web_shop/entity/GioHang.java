package web_shop.api_web_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tongSanPham;
    private Double tongSoTien; // tong so tien cua tat ca cac gio hang chi tiet trong gio hang

    @OneToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;

    @OneToMany(mappedBy = "gioHang")
    private List<GioHangChiTiet> gioHangChiTiets;

    public GioHang(Long tongSanPham, Double tongSoTien, TaiKhoan taiKhoan) {
        this.tongSanPham = tongSanPham;
        this.tongSoTien = tongSoTien;
        this.taiKhoan = taiKhoan;
    }
}
