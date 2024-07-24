package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.*;
import web_shop.api_web_shop.entity.*;
import web_shop.api_web_shop.repository.KichCoRepository;
import web_shop.api_web_shop.repository.MauSacRepository;
import web_shop.api_web_shop.repository.SanPhamChiTietRepository;
import web_shop.api_web_shop.repository.SanPhamRepository;
import web_shop.api_web_shop.service.SanPhamChiTietService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamChiTietServiceIplm implements SanPhamChiTietService {

    private final SanPhamChiTietRepository spctRepository;

    private final MauSacRepository mauSacRepository;

    private final KichCoRepository kichCoRepository;
    private final SanPhamRepository sanPhamRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public List<SanPhamChiTietDTO> getAll() {
      return mapToDTO(spctRepository.findAll());
    }

    @Override
    public SanPhamChiTietDTO getById(Long id) throws IOException{
        SanPhamChiTiet spct = spctRepository.findById(id).orElseThrow( ()->new RuntimeException("khong tim thay id san pham chi tiet") );

        return new ModelMapper().map(spct, SanPhamChiTietDTO.class);
    }

    @Override
    public void create(SanPhamChiTietDTO sanPhamChiTietDTO) {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        maptoEnity(spct, sanPhamChiTietDTO);
        spctRepository.save(spct);
    }

    @Override
    public void update(SanPhamChiTietDTO sanPhamChiTietDTO, Long id)  throws IOException {
        SanPhamChiTiet spct = sanPhamChiTietRepository.findById(id).orElseThrow( ()->new RuntimeException("khong co san pham voi id"+ id) ) ;
        maptoEnity(spct, sanPhamChiTietDTO);
    }

    @Override
    public void delete(Long id) {
        spctRepository.deleteById(id);
    }

    private void maptoEnity (SanPhamChiTiet enity , SanPhamChiTietDTO dto) {
        enity.setTen(dto.getTen());
        enity.setMa(dto.getMa());
        enity.setGia(dto.getGia());
        enity.setSoLuongTonKho(dto.getSoLuongTonKho());
        enity.setTrangThai(dto.getTrangThai());

        enity.setSanPham(sanPhamRepository.findById(dto.getSanPham().getId())
                                          .orElseThrow( () -> new RuntimeException("Khong tim thay Id") ) );

        enity.setMauSac(mauSacRepository.findById(dto.getMauSac().getId())
                        .orElseThrow( () -> new RuntimeException("Khong tim thay id mausac") ) );

        enity.setKichCo(kichCoRepository.findById(dto.getKichCo().getId())
                .orElseThrow( () -> new RuntimeException("Khong tim thay id kich co") ) );

    }

    private List <SanPhamChiTietDTO>  mapToDTO (List <SanPhamChiTiet> listEnity){
                List<SanPhamChiTietDTO> temp = new ArrayList<>();
                for (SanPhamChiTiet spct : listEnity) {
                    SanPhamChiTietDTO spctDTO = new SanPhamChiTietDTO();
                    spctDTO.setId(spct.getId());
                    spctDTO.setMa(spct.getMa());
                    spctDTO.setTen(spct.getTen());
                    spctDTO.setGia(spct.getGia());
                    spctDTO.setSoLuongTonKho(spct.getSoLuongTonKho());
                    spctDTO.setSoLuongDaBan(spct.getSoLuongDaBan());
                    spctDTO.setTrangThai(spct.getTrangThai());

                    // mapping sanpham sang san pham DTO de lam viec
                    SanPham sanPham = spct.getSanPham();
                    LoaiSanPham loaiSanPham = sanPham.getLoaiSanPham();
                    LoaiSanPhamDTO loaiSanPhamDTO = new LoaiSanPhamDTO(loaiSanPham.getId(), loaiSanPham.getTen());
                    SanPhamDTO sanPhamDTO  = new SanPhamDTO(
                                            sanPham.getId(), sanPham.getMa(),
                                            sanPham.getTen(), sanPham.getGia(),
                                            sanPham.getSoLuongTonKho(), sanPham.getSoLuongDaBan(),
                                            sanPham.getMoTa(), sanPham.getTrangThai() ,
                                            loaiSanPhamDTO, sanPham.getImages(), null);
                    spctDTO.setSanPham(sanPhamDTO);

                    //mapping mau sac sang mau sac DTO de lam viec
                    MauSac mauSac = spct.getMauSac();
                    MauSacDTO mauSacDTO = new MauSacDTO(mauSac.getId(), mauSac.getTen());
                    spctDTO.setMauSac(mauSacDTO);

                    //mapping kich co sang kich co DTO de lam viec
                    KichCo kichCo = spct.getKichCo();
                    KichCoDTO kichCoDTO = new KichCoDTO(kichCo.getId(), kichCo.getTen());
                    spctDTO.setKichCo(kichCoDTO);

                    temp.add(spctDTO);

                }
                return temp;

    }
}
