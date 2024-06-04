package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.ProvinceDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Province;
import it.epicode.BW2_EpicEnergyServices.Exceptions.ProvinceNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public String saveProvince(ProvinceDto provinceDto) {
        Province province = new Province();
        province.setAcronym(provinceDto.getAcronym());
        province.setName(provinceDto.getName());

        provinceRepository.save(province);

        return "Province with id " + province.getProvinceId() + " correctly saved!";
    }

    public List<Province> getAllProvince() {
        return provinceRepository.findAll();
    }

    public Optional<Province> getProvinceById(int id) {
        return provinceRepository.findById(id);
    }

    public String updateProvince(int id, ProvinceDto provinceDto) {
        Optional<Province> provinceOptional = getProvinceById(id);
        if (provinceOptional.isPresent()) {
            Province province = new Province();
            province.setAcronym(provinceDto.getAcronym());
            province.setName(provinceDto.getName());

            provinceRepository.save(province);

            return "Province with id " + province.getProvinceId() + " correctly saved!";

        } else {
            throw new ProvinceNotFoundException("Province with id=" + id + " not found!");
        }
    }

    public String deleteProvince(int id) {
        Optional<Province> provinceOptional = getProvinceById(id);
        if (provinceOptional.isPresent()) {
            provinceRepository.deleteById(id);
            return "Province with id=" + id + " correctly deleted!";
        } else {
            throw new ProvinceNotFoundException("Province with id=" + id + " not found!");
        }
    }
}
