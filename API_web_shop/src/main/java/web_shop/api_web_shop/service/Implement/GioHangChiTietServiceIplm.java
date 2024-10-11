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
public class GioHangChiTietServiceIplm implements GioHangChiTietService {
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
    public void update(Long soLuong, Long id) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findById(id).orElseThrow(  ()->new RuntimeException("Keine ID giohangchitiet gefunden") );
        gioHangChiTiet.setSoLuong(soLuong);
        GioHang gioHang = gioHangChiTiet.getGioHang();
        Double ThanhTienSauUpdate = soLuong * gioHangChiTiet.getSanPhamChiTiet().getGia() ;
        gioHang.setTongSoTien(gioHang.getTongSoTien() - gioHangChiTiet.getThanhTien() + ThanhTienSauUpdate);
        gioHangChiTiet.setThanhTien(ThanhTienSauUpdate);
        gioHangRepository.save(gioHang);
        gioHangChiTietRepository.save(gioHangChiTiet);
    }

    /**
     * Deletes a GioHangChiTiet entity by its ID.
     *
     * @param id the ID of the GioHangChiTiet entity to be deleted
     */
    @Override
    public void delete(Long id) {

        GioHangChiTiet ghct = gioHangChiTietRepository.findById(id).orElseThrow( ()->new RuntimeException("Not Found id GioHang" + id) );
        GioHang gioHang = ghct.getGioHang();

        // trong logic này sẽ Tổng Sản Phẩm trong giở hàng sẽ không đếm số Lượng sản phẩm chi tiết giống nhau
        // mà chỉ đếm số lượng sản pẩm chi tiết khác nhau nên không sẽ không .... - ghct.getSoLuong
        gioHang.setTongSanPham( gioHang.getTongSanPham() - 1 );
        gioHang.setTongSoTien( gioHang.getTongSoTien() - ghct.getThanhTien() );
        gioHangRepository.save(gioHang);
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

        entity.setSanPhamChiTiet(sanPhamChiTiet);


        GioHang gioHang = gioHangRepository.findById( dto.getGioHangId() )
                .orElseThrow( ()->new RuntimeException("Khong tim thay gio hang")  );


        entity.setGioHang(gioHang);


        Double gia = sanPhamChiTiet.getGia();
        Double thanhTien = gia * dto.getSoLuong();
        //vd: trong gio hang co san san pham va khi ta them san pham khac vao gio hang thi phai cong them tien
        gioHang.setTongSoTien( gioHang.getTongSoTien() + thanhTien );
        //Kiem tra xem gio hang da co san pham nay chua
        // kiem tra xem Trong gio hang chi tiet do da co san pham chi tiet ma minh muon them vao,
        // san pham chi tiet do dã ton tai trong gio hang chi tiet hay chua
        GioHangChiTiet isExist = gioHangChiTietRepository.findByGioHangIdAndSanPhamChiTietId( dto.getGioHangId(), sanPhamChiTiet.getId() ).orElse(null);
        // isExist là giỏ hàng chi tiết chứa sản phẩm chi tiết hiện đang sẵn ở trong giỏ hàng
        if (isExist != null) {
            //neu ton tai trong gio hang thi ta se cong don vao
            //cộng dồn sản phẩm vào
            entity.setId(  isExist.getId() ); // vì đang mapping từ DTO sang Enity
            entity.setSoLuong( isExist.getSoLuong() + dto.getSoLuong() );
            entity.setThanhTien( isExist.getThanhTien() + thanhTien );

            // trong logic này  Tổng Sản Phẩm trong giở hàng sẽ không đếm số Lượng sản phẩm chi tiết giống nhau
            // mà chỉ đếm số lượng sản pẩm chi tiết khác nhau nên  sẽ không SET là :
            // gioHang.setTongSanPham( gioHang.getTongSanPham() +  isExist.getSoLuong()

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
