package web_shop.api_web_shop.service.Implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web_shop.api_web_shop.dto.MauSacDTO;
import web_shop.api_web_shop.entity.MauSac;
import web_shop.api_web_shop.repository.MauSacRepository;
import web_shop.api_web_shop.service.MauSacService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor // auto create constructor with final properties, dont need @Autowired
public class MauSacServiceIplm implements MauSacService {

    // This means to get the bean called mauSacRepository, dont need Instanz (auto) (lam viec voi database)
    private final MauSacRepository mauSacRepository;

    @Override
    public List<MauSacDTO> getAll() {
        // find all := select * from MauSac
        List<MauSac> listEntity = mauSacRepository.findAll();
        List<MauSacDTO> listDto = mapToDto(listEntity);
        return listDto;
    }

    @Override
    public void create(MauSacDTO mauSacDTO) {
        MauSac mauSac = new MauSac();
        mauSac.setTen(mauSacDTO.getTen());
        //jpa setTen chi mapping voi obhject co Id nen can phai luu qua MauSac
        // save := insert into MauSac values (mauSacDTO.getTen())
        mauSacRepository.save(mauSac);
    }

    @Override
    public void update(MauSacDTO mauSacDTO, Long id) {
        MauSac mauSac = new MauSac();
        mauSac.setTen(mauSacDTO.getTen());
        mauSac.setId(id);
        // save := update MauSac set ten = mauSacDTO.getTen() where id = id
        mauSacRepository.save(mauSac);
    }

    @Override
    public void delete(Long id) {
        // delete := delete from MauSac where id = id
        mauSacRepository.deleteById(id);
    }

    @Override
    public MauSacDTO getById(Long id) {
        MauSac mauSac = mauSacRepository.findById(id).orElse(null);
        return new MauSacDTO(mauSac.getId(), mauSac.getTen());
    }

    /**
     * chuyen list Mau Sac sang dang MauSacDTO() de tra ve cho client
     * @param listEntity list Mau Sac day du thong tin
     */
    private List<MauSacDTO> mapToDto(List<MauSac> listEntity){
        List<MauSacDTO> temp = new ArrayList<>();
        for(MauSac entity: listEntity) {
            temp.add(new MauSacDTO(entity.getId(), entity.getTen()));
        }
        return temp;        }
    }
