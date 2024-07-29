package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.LoaiSanPhamDTO;
import web_shop.api_web_shop.entity.LoaiSanPham;
import web_shop.api_web_shop.repository.LoaiSanPhamRepository;
import web_shop.api_web_shop.service.LoaiSanPhamService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoaiSanPhamServiceIplm implements LoaiSanPhamService {

    private final LoaiSanPhamRepository loaiSanPhamRepository;

    public List<LoaiSanPhamDTO> getAll() {
        List<LoaiSanPham> listEntity = loaiSanPhamRepository.findAll();
        return mapToDto(listEntity);
    }

    public void create(LoaiSanPhamDTO loaiSanPhamDTO) {
        LoaiSanPham loaiSanPham = new LoaiSanPham();
        loaiSanPham.setTen(loaiSanPhamDTO.getTen());
        loaiSanPhamRepository.save(loaiSanPham);
    }

    public void update(LoaiSanPhamDTO loaiSanPhamDTO, Long id) {
        LoaiSanPham loaiSanPham = new LoaiSanPham();
        loaiSanPham.setId(id);
        loaiSanPham.setTen(loaiSanPhamDTO.getTen());
        loaiSanPhamRepository.save(loaiSanPham);
    }

    public void delete(Long id) {

        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(id).orElse(null);
        if (loaiSanPham == null) {
            throw new RuntimeException("Not Found loai san pham id" + id);
        }
        loaiSanPhamRepository.deleteById(id);
    }

    public LoaiSanPhamDTO getById(Long id) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepository.findById(id).orElse(null);
        if(loaiSanPham == null){
            throw new RuntimeException( "Not Found loai san pham id" + id);
        }

        return new LoaiSanPhamDTO(loaiSanPham.getId(), loaiSanPham.getTen());
    }

    private List<LoaiSanPhamDTO> mapToDto(List<LoaiSanPham> listEntity) {
        List<LoaiSanPhamDTO> listDto = new ArrayList<>();
        for (LoaiSanPham loaiSanPham : listEntity) {
            listDto.add(new LoaiSanPhamDTO(loaiSanPham.getId(), loaiSanPham.getTen()));
        }
        return listDto;
    }
}
