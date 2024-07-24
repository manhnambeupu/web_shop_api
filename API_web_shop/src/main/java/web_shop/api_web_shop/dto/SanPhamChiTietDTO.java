package web_shop.api_web_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO {

    private Long id;
    private String ma;
    private String ten;
    private Double gia;
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private Integer trangThai;

    private SanPhamDTO sanPham;

    private MauSacDTO mauSac;

    private KichCoDTO kichCo;
}
