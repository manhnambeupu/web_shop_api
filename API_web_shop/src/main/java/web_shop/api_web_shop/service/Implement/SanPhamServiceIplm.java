package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web_shop.api_web_shop.dto.LoaiSanPhamDTO;
import web_shop.api_web_shop.dto.SanPhamDTO;
import web_shop.api_web_shop.entity.LoaiSanPham;
import web_shop.api_web_shop.entity.SanPham;
import web_shop.api_web_shop.repository.LoaiSanPhamRepository;
import web_shop.api_web_shop.repository.SanPhamRepository;
import web_shop.api_web_shop.service.SanPhamService;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor // automatisch Inject dependency with Constructor
public class SanPhamServiceIplm implements SanPhamService {

    private final SanPhamRepository sanPhamRepository;
    
    private final LoaiSanPhamRepository loaiSanPhamRepository;

    @Override
    public List<SanPhamDTO> getAll() {
        return mapToDto(sanPhamRepository.findAll());
    }

    @Override
    public List<SanPhamDTO> search (String ten , String ma, Long idLoaiSanPham, Integer page, Integer size) {
        page = page == null ? 0 : page; // vi tri trang
        size = size == null ? 5 : size; // so phan tu tren mot trang
        Page<SanPham> pageEnity = sanPhamRepository.getAll( ten, ma, idLoaiSanPham, PageRequest.of( page, size) ) ;

        List<SanPhamDTO> listDTO = mapToDto( pageEnity.getContent() );

        return listDTO;
    }

        @Override
        public SanPhamDTO getById (Long id){
            return null;
        }

        @Override
        public void create (SanPhamDTO dto) throws IOException {
            // Tao mot list chua ten file anh
            List<String> images = new ArrayList<>();


            // LUU File ANH VAO DATA BASE
            // Kiem tra xem file co ton tai hay khong, neu co thi lay ten file va luu vao List <String> images
            // neu khong thi throw exception
            if (dto.getFiles() != null) {

                //xet tung file trong  List<MultipartFile> files
                for (MultipartFile multipartFile : dto.getFiles()) {
                    // lay ten file
                    String name = multipartFile.getOriginalFilename();
                    // luu ten file vao List<String> images
                    images.add(name);
                    // duong dan toi file
                    String path = "C:\\Users\\Admin\\Desktop\\web_shop\\src\\main\\resources\\static\\images\\" + name;
                    // tao file
                    File folder = new File(path);
                    // kiem tra xem folder co ton tai hay khong, neu khong thi tao folder
                    if (!folder.exists()) {
                        folder.mkdirs();//tao folder
                    }
                    // tao file sao cho ten file la name
                    File file = new File(path + "/" + name);
                    // luu file cua khach hang da upload vao file vua tao
                    multipartFile.transferTo(file);
                }
            } else {
                throw new RuntimeException("File is null");
            }

            SanPham sanPham = new SanPham(); // chuyen SanPhamDTO sang dang SanPham() de luu vao database
            mapToEntitySave(sanPham, dto);
            sanPham.setImages(images);
            sanPham.setSoLuongDaBan(0L);
            sanPham.setTrangThai(1);
            sanPhamRepository.save(sanPham);
        }

        @Override
        public void update (SanPhamDTO sanPhamDTO, Long id) throws IOException {
            SanPham sanPham = sanPhamRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Not found san pham id" + id));

            List<String> images = new ArrayList<>();

            // LUU File ANH VAO DATA BASE
            // Kiem tra xem file co ton tai hay khong, neu co thi lay ten file va luu vao List <String> images
            // neu khong thi throw exception
            if (sanPhamDTO.getFiles() != null) {

                //xet tung file trong  List<MultipartFile> files
                for (MultipartFile multipartFile : sanPhamDTO.getFiles()) {
                    // lay ten file
                    String name = multipartFile.getOriginalFilename();
                    // luu ten file vao List<String> images
                    images.add(name);
                    // duong dan toi file
                    String path = "C:\\Users\\Admin\\Desktop\\web_shop\\src\\main\\resources\\static\\images\\" + name;
                    // tao file
                    File folder = new File(path);
                    // kiem tra xem folder co ton tai hay khong, neu khong thi tao folder
                    if (!folder.exists()) {
                        folder.mkdirs();//tao folder
                    }
                    // tao file sao cho ten file la name
                    File file = new File(path + "/" + name);
                    // luu file cua khach hang da upload vao file vua tao
                    multipartFile.transferTo(file);
                }
                sanPham.setImages(images);
            }
            mapToEntitySave(sanPham, sanPhamDTO);
            sanPham.setTrangThai(sanPhamDTO.getTrangThai());
            sanPhamRepository.save(sanPham);

        }

        @Override
        public void delete (Long id){
            sanPhamRepository.deleteById(id);
        }

        /**
         * chuyen SanPhamDTO sang dang SanPham() de luu vao database
         * @param dto SanPhamDTO day du thong tin
         */
        private void mapToEntitySave (SanPham sanPham, SanPhamDTO dto){
            sanPham.setMa(dto.getMa());
            sanPham.setTen(dto.getTen());
            sanPham.setGia(dto.getGia());
            sanPham.setSoLuongTonKho((dto.getSoLuongTonKho()));
            sanPham.setMoTa(dto.getMoTa());

            // tim loai san pham giua tren id cua san pham
            // neu khong co se tra ra Exception
            LoaiSanPham loaiSanPham = loaiSanPhamRepository
                    .findById(dto.getLoaiSanPham().getId())
                    .orElseThrow(() -> new RuntimeException("ko tim thay loai san pham"));
            //sau khi tim duoc loai san pham dua tren id san pham thi ta phai setLoaiSanPham()
            sanPham.setLoaiSanPham(loaiSanPham);
        /*
        //cach 2 dung model mapper
        return new ModelMapper().map(dto, SanPham.class);
         */
        }

        /**
         * chuyen list SanPham sang dang SanPhamDTO() de tra ve cho client
         * @param listEntity list SanPham day du thong tin
         */
        private List<SanPhamDTO> mapToDto (List < SanPham > listEntity) {
            List<SanPhamDTO> temp = new ArrayList<>();

            for (SanPham enity : listEntity) {
                SanPhamDTO sanPhamDTO = new SanPhamDTO();
                sanPhamDTO.setId(enity.getId());
                sanPhamDTO.setMa(enity.getMa());
                sanPhamDTO.setTen(enity.getTen());
                sanPhamDTO.setGia(enity.getGia());
                sanPhamDTO.setSoLuongTonKho(enity.getSoLuongTonKho());
                sanPhamDTO.setSoLuongDaBan(enity.getSoLuongDaBan());
                sanPhamDTO.setMoTa(enity.getMoTa());
                sanPhamDTO.setTrangThai(enity.getTrangThai());

                // bang phu thi khong can chuyen doi sang DTO vi no khong co Enity
                //loai san pham tam thoi
                LoaiSanPham loaiSanPham = enity.getLoaiSanPham();
                // chuyen doi du lieu tu Enity sang DTO
                LoaiSanPhamDTO enityToDto = new LoaiSanPhamDTO(loaiSanPham.getId(), loaiSanPham.getTen());

                //boi vi setLoaiSanPham referenz duoc SanPhamDTO nen phai qua hai buoc tren
                sanPhamDTO.setLoaiSanPham(enityToDto);

                //add vao list
                temp.add(sanPhamDTO);

            }
            //cach 2
        /*for(SanPham entity: listEntity) {
            temp.add(new ModelMapper().map(entity, SanPhamDTO.class));
        }*/
            return temp;
        }


}
