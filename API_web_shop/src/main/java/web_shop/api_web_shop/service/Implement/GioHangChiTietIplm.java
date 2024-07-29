package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.GioHangChiTietDTO;
import web_shop.api_web_shop.dto.SanPhamChiTietDTO;
import web_shop.api_web_shop.entity.GioHang;
import web_shop.api_web_shop.entity.GioHangChiTiet;
import web_shop.api_web_shop.entity.SanPhamChiTiet;
import web_shop.api_web_shop.repository.GioHangChiTietRepository;
import web_shop.api_web_shop.repository.GioHangRepository;
import web_shop.api_web_shop.repository.SanPhamChiTietRepository;
import web_shop.api_web_shop.service.GioHangChiTietService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GioHangChiTietIplm implements GioHangChiTietService {

    private final GioHangRepository gioHangRepository;
    private final GioHangChiTietRepository gioHangChiTietRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public List<GioHangChiTietDTO> getAll() {
        List<GioHangChiTiet> listEntity = gioHangChiTietRepository.findAll();
        return mapToDto(listEntity);
    }

    @Override
    public void create(GioHangChiTietDTO dto) {
        GioHangChiTiet gioHangChiTietSave = new GioHangChiTiet();
        mapToEntity(dto, gioHangChiTietSave);
        gioHangChiTietRepository.save(gioHangChiTietSave);
    }

    @Override
    public void update(GioHangChiTietDTO dto, Long id) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findById(id).orElseThrow(  ()->new RuntimeException("Keine ID giohangchitiet gefunden") );
        mapToEntity(dto, gioHangChiTiet);
        gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public void delete(Long id) {
        gioHangChiTietRepository.deleteById(id);
    }

    @Override
    public GioHangChiTietDTO getById(Long id) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findById(id).orElseThrow(  ()->new RuntimeException("Keine ID giohangchitiet gefunden") );
        return new ModelMapper().map(gioHangChiTiet, GioHangChiTietDTO.class);
    }

    /**
     * Maps a GioHangChiTietDTO to a GioHangChiTiet entity for saving to the database.
     * Also updates the total price of the GioHang entity. that we use when we create a new GioHangChiTiet.(add to cart)
     * @param dto GioHangChiTietDTO containing the details.
     * @param entity GioHangChiTiet entity to be populated.
     */
    private void mapToEntity(GioHangChiTietDTO dto , GioHangChiTiet entity) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById( dto.getSanPhamChiTiet().getId() )
                .orElseThrow( ()->new RuntimeException("Khong tim thay san Pham")  );
        GioHang gioHang = gioHangRepository.findById( dto.getGioHangId() )
                .orElseThrow( ()->new RuntimeException("Khong tim thay gio hang")  );

        entity.setSanPhamChiTiet(sanPhamChiTiet);
        Double gia = sanPhamChiTiet.getGia();
        Double thanhTien = gia * dto.getSoLuong();
        //vd: trong gio hang co san san pham va khi ta them san pham khac vao gio hang thi phai cong them tien
        gioHang.setTongSoTien( gioHang.getTongSoTien() + thanhTien );
        //Kiem tra xem gio hang da co san pham nay chua                                                                                                                  //ohne Code-Dupilcate
        GioHangChiTiet isExist = gioHangChiTietRepository.findByGioHangIdAndSanPhamChiTietId( dto.getGioHangId(), sanPhamChiTiet.getId() ).orElse(null);

        if (isExist != null) {
            //neu ton tai trong gio hang thi ta se cong don vao

            entity.setId(  isExist.getId() );
            entity.setSoLuong( isExist.getSoLuong() + dto.getSoLuong() );
            entity.setThanhTien( isExist.getThanhTien() + thanhTien );

        }else {
            //neu chua ton tai trong gio hang thi them moi
            entity.setSoLuong( dto.getSoLuong() );
            gioHang.setTongSanPham( gioHang.getTongSanPham() + 1 );
            entity.setThanhTien( thanhTien );
        }
    }

    /**
     * Maps a list of GioHangChiTiet entities to a list of GioHangChiTietDTOs.
     * we don't need to update the total price hier , just get the data from database and show it to the user.
     * @param listEntity List of GioHangChiTiet entities.
     * @return List of GioHangChiTietDTOs.
     */
    private  List<GioHangChiTietDTO> mapToDto(List<GioHangChiTiet> listEntity) {
        List<GioHangChiTietDTO> listDto = new ArrayList<>();
        for (GioHangChiTiet entity : listEntity) {
            GioHangChiTietDTO dto = new GioHangChiTietDTO();
            dto.setSoLuong(entity.getSoLuong());
            dto.setThanhTien(entity.getThanhTien());
            dto.setSanPhamChiTiet(new ModelMapper().map(entity.getSanPhamChiTiet(), SanPhamChiTietDTO.class));
            dto.setGioHangId(entity.getGioHang().getId());
            listDto.add(dto);
        }
        return listDto;
    }
}
