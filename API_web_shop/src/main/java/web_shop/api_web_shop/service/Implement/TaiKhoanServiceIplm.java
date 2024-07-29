package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.TaiKhoanDTO;
import web_shop.api_web_shop.entity.GioHang;
import web_shop.api_web_shop.entity.TaiKhoan;
import web_shop.api_web_shop.repository.GioHangRepository;
import web_shop.api_web_shop.repository.TaiKhoanRepository;
import web_shop.api_web_shop.service.TaiKhoanService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaiKhoanServiceIplm implements TaiKhoanService {

    private final TaiKhoanRepository tkRepository;
    private  final GioHangRepository gioHangRepository;
    private final EmailService emailService;


    @Override
    public List<TaiKhoanDTO> getAll() {
        List<TaiKhoan> listEnity = tkRepository.findAll();
        List<TaiKhoanDTO> listDto = new ArrayList<>();
        for (TaiKhoan tk : listEnity) {
            listDto.add(new ModelMapper().map(tk, TaiKhoanDTO.class));
        }
        return listDto;
    }

    @Override
    public TaiKhoanDTO getById(Long id) {
        TaiKhoan tk = tkRepository.findById(id).orElseThrow( ()->new RuntimeException("Keine ID Konto") );
        return new ModelMapper().map(tk, TaiKhoanDTO.class);
    }

    @Override
    public void create(TaiKhoanDTO taiKhoanDTO) {
        TaiKhoan enity = new TaiKhoan();
        mapToEnity(enity, taiKhoanDTO);
        //moi tao nen chua co cai nao
        enity.setTongHoaDon(0L);
        enity.setTongTien(0L);
        enity.setHangTaiKhoan(1);
        enity.setTrangThai(1);
        TaiKhoan tkSave = tkRepository.save(enity);
        GioHang giohang = new GioHang(0L, 0D, tkSave);
        gioHangRepository.save(giohang);
        emailService.sendEmail(

                enity.getEmail(),
                "dang ky User",
                "Hello" + enity.getHoVaTen()

        );
    }

    @Override
    public void update(TaiKhoanDTO taiKhoanDTO, Long id) {
        TaiKhoan enity = tkRepository.findById(id).orElseThrow( ()->new RuntimeException("Keine ID Konto") );
        mapToEnity(enity, taiKhoanDTO);
        tkRepository.save(enity);
    }


    private void mapToEnity(TaiKhoan enity, TaiKhoanDTO dto) {
        enity.setId(dto.getId());
        enity.setMa(dto.getMa());
        enity.setEmail(dto.getEmail());
        enity.setMatKhau(dto.getMatKhau());
        enity.setHoVaTen(dto.getHoVaTen());
        enity.setRole(dto.getRole());
        enity.setTongHoaDon(dto.getTongHoaDon());
        enity.setTongTien(dto.getTongTien());
        enity.setHangTaiKhoan(dto.getHangTaiKhoan());
        enity.setTrangThai(dto.getTrangThai());
    }
}
