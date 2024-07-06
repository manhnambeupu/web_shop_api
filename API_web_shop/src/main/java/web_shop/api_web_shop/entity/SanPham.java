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
public class SanPham{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma;
    private String ten;
    private Double gia;
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private String moTa;
    private Integer trangThai;

    @ManyToOne // vd 1 loaij san pham thi se co nhieu san pham, chi nen dung @ManyToOne vi no de quan ly
    @JoinColumn(name = "loai_san_pham_id") // ten cot khoa ngoai
    private LoaiSanPham loaiSanPham;
                                                // khi dung EAGER thi no se tu dong load
    @ElementCollection(fetch = FetchType.EAGER) // EAGER: dung trong truowng hop bang phu khong co khoa chinh nhu bang anh san pham
    @CollectionTable(name =  "anh_san_pham")  // Mapping voi bang anh_san_pham
    private List<String> images;

}
