package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.KichCoDTO;
import web_shop.api_web_shop.entity.KichCo;
import web_shop.api_web_shop.repository.KichCoRepository;
import web_shop.api_web_shop.service.KichCoService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KichCoServiceIplm implements KichCoService {

    private final KichCoRepository kichCoRepository;

    public List<KichCoDTO> getAll() {
        List<KichCo> listEntity = kichCoRepository.findAll();
        List<KichCoDTO> listDto = mapToDto(listEntity);
        return listDto;
    }

    public void create(KichCoDTO kichCoDTO) {
        KichCo kichCo = new KichCo();
        kichCo.setTen(kichCoDTO.getTen());
        //jpa setTen chi mapping voi obhject co Id nen can phai luu qua KichCo
        // save := insert into KichCo values (kichCoDTO.getTen())
        kichCoRepository.save(kichCo);
    }

    public void update(KichCoDTO kichCoDTO, Long id) {
        KichCo kichCo = new KichCo();
        kichCo.setId(id);
        kichCo.setTen(kichCoDTO.getTen());
        // save := update KichCo set ten = kichCoDTO.getTen() where id = id
        kichCoRepository.save(kichCo);
    }

    public void delete(Long id) {
        KichCo kichCo = kichCoRepository.findById(id).orElse(null);
        if(kichCo == null){
            throw new RuntimeException( "Not Found id" + id);
        }

        // delete := delete from KichCo where id = id
        kichCoRepository.deleteById(id);
    }

    public KichCoDTO getById(Long id) {
        KichCo kichCo = kichCoRepository.findById(id).orElse(null);

        if (kichCo == null) {
            throw new RuntimeException("Not Found id" + id);
        }

        return new KichCoDTO(kichCo.getId(), kichCo.getTen());
    }

    /**
     * umwandeln list Kich Co zu KichCoDTO() um client zu geben
     * Sicherheit fur Datenbank
     * @param listEntity list Kich Co vollstaendige Informationen
     * @return List<KichCoDTO>
     */
    private List<KichCoDTO> mapToDto(List<KichCo> listEntity) {
       List<KichCoDTO> listDto = new ArrayList<>();
         for (KichCo kichCo : listEntity) {
              listDto.add(new KichCoDTO(kichCo.getId(), kichCo.getTen()));
         }
         return listDto;
    }

}
