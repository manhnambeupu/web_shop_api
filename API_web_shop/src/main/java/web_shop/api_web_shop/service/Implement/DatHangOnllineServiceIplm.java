package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.HoaDonChiTietDTO;
import web_shop.api_web_shop.dto.HoaDonDTO;
import web_shop.api_web_shop.dto.ThongTinHoaDonDTO;
import web_shop.api_web_shop.entity.GioHang;
import web_shop.api_web_shop.entity.GioHangChiTiet;
import web_shop.api_web_shop.entity.HoaDon;
import web_shop.api_web_shop.entity.HoaDonChiTiet;
import web_shop.api_web_shop.repository.*;
import web_shop.api_web_shop.service.DatHangOnlineService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatHangOnllineServiceIplm implements DatHangOnlineService {

    private final DatHangOnlineRepository datHangOnlineRepository;
    private  final TaiKhoanRepository taiKhoanRepository;
    private final GioHangRepository gioHangRepository;
    private final GioHangChiTietRepository gioHangChiTietRepository;
    private final HoaDonRepository hoaDonRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public ResponseEntity<String> create(HoaDonDTO hoaDonDTO) {
        GioHang gioHang =  gioHangRepository.findByTaiKhoanId( hoaDonDTO.getTaiKhoanId() ).orElseThrow(()->new RuntimeException("..."));
        return ResponseEntity.ok("is ordered"); // doi voi ResponseEnity thi tri co tra ve Status va Body
    }

    @Override
    public ResponseEntity<String> updateStatus(Long id, Integer trangThai) {
        return ResponseEntity.ok("change status");
    }

    @Override
    public ResponseEntity<ThongTinHoaDonDTO> getById(Long id) {
        HoaDon hoaDon = hoaDonRepository.findById(id).orElseThrow( () -> new RuntimeException("Not found") );
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepository.findByHoaDonId(id).orElse( new ArrayList<>() );// not found thi tra ve 1 list rong
        return maptoDTO(hoaDon, hoaDonChiTietList);
    }


    private ResponseEntity<ThongTinHoaDonDTO> maptoDTO(HoaDon hoadon, List<HoaDonChiTiet> listHoaDonCT){
        ThongTinHoaDonDTO tthdDTO = new ThongTinHoaDonDTO();

        HoaDonDTO hoaDonDTO = new ModelMapper().map(hoadon, HoaDonDTO.class);
        List<HoaDonChiTietDTO> listhdct = listHoaDonCT.stream().map( x -> new ModelMapper().map(x, HoaDonChiTietDTO.class) ).collect(Collectors.toList());

        tthdDTO.setHoaDonDTO(hoaDonDTO);
        tthdDTO.setChiTietHoaDonDTOList(listhdct);
        return ResponseEntity.ok(tthdDTO);
    }


    private void mapGioHangToHoaDon(GioHang gioHang, HoaDon hoaDon){
        hoaDon.setTongSoTien( gioHang.getTongSoTien() );
        gioHang.setTongSoTien( 0D );

        hoaDon.setTongSanPham( gioHang.getTongSanPham() );
        gioHang.setTongSanPham(0L);

        hoaDon.setTaiKhoan( gioHang.getTaiKhoan() );
        hoaDon.setTrangThai(1);

        hoaDon.setDate(LocalDate.now());

        HoaDon hoaDonSave   = hoaDonRepository.save(hoaDon);
        gioHangRepository.save(gioHang);


        List<GioHangChiTiet> listGioHangCT = gioHangChiTietRepository.findByGioHangId(gioHang.getId());
        List<HoaDonChiTiet> listHoaDonCT = new ArrayList<>();


        for (GioHangChiTiet gioHangChiTiet: listGioHangCT){
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setDonGia( gioHangChiTiet.getSanPhamChiTiet().getGia() );
            hoaDonChiTiet.setHoaDon(hoaDonSave);
            hoaDonChiTiet.setSanPhamChiTiet(gioHangChiTiet.getSanPhamChiTiet());
            hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());
            hoaDonChiTiet.setThanhTien(  gioHangChiTiet.getSoLuong() * gioHangChiTiet.getSanPhamChiTiet().getGia() );
            listHoaDonCT.add(hoaDonChiTiet);
            gioHangRepository.deleteById(gioHangChiTiet.getId());
        }
        hoaDonChiTietRepository.saveAll(listHoaDonCT);

    }

    public void checkStatus(Long id, Integer trangThai){
       if (trangThai != 0) {
           HoaDon hoaDon = hoaDonRepository.findByIdAndTrangThai(id, trangThai - 1).orElseThrow(() -> new RuntimeException("Status is not waiting"));
           hoaDon.setTrangThai(trangThai);
           if(trangThai == 4){
               hoaDon.setNgayHoanThanh(LocalDate.now());
           }
           hoaDonRepository.save(hoaDon);
       }
    }

}
