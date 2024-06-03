package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Entity.Town;
import it.epicode.BW2_EpicEnergyServices.Repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;

    public String saveTown(TownDto townDto) {
        Town town = new Town();
        town.setTownName(townDto.getTownName());
        town.setRegion(townDto.getRegion());

        townRepository.save(town);

        return "Town with id " + town.getId() + " correctly saved!";
    }

    public List<Town> getAllTown() {
        return townRepository.findAll();
    }

    public Optional<Town> getTownById(int id) {
        return townRepository.findById(id);
    }

    public Town updateTown(int id, TownDto townDto) {
        Optional<Town> townOptional = getTownById(id);
        if (townOptional.isPresent()) {
            Town town = new Town();
            town.setTownName(townDto.getTownName());
            town.setRegion(townDto.getRegion());

            townRepository.save(town);

            return "Town with id " + town.getId() + " correctly saved!";

        } else {
            throw new TownNotFoundException("Town with id=" + id + " not found!");
        }
    }

    public String deleteTown(int id) {
        Optional<Town> townOptional = getTownById(id);
        if (townOptional.isPresent()) {
            townRepository.deleteById(id);
            return "Town with id=" + id + " correctly deleted!";
        } else {
            throw new TownNotFoundException("Town with id=" + id + " not found!");
        }
    }
}
