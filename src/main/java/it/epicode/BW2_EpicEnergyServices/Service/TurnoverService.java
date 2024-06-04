package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.TurnoverDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Turnover;
import it.epicode.BW2_EpicEnergyServices.Exceptions.TurnoverNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.TurnoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoverService {

    @Autowired
    private TurnoverRepository turnoverRepository;

    public String saveTurnover(TurnoverDto turnoverDto) {
        Turnover turnover = new Turnover();
        turnover.setTurnoverCode(turnoverDto.getTurnoverCode());
        turnover.setDate(turnoverDto.getDate());
        turnover.setTurnoverState(turnoverDto.getTurnoverState());
        turnover.setTotal(turnoverDto.getTotal());
        turnoverRepository.save(turnover);

        return "Turnover with id " + turnover.getTurnoverCode() + " correctly saved!";
    }

    public List<Turnover> getAllTurnover() {
        return turnoverRepository.findAll();
    }

    public Optional<Turnover> getTurnoverById(int id) {
        return turnoverRepository.findById(id);
    }

    public String updateTurnover(int id, TurnoverDto turnoverDto){
        Optional<Turnover> turnoverOptional = getTurnoverById(id);
        if (turnoverOptional.isPresent()) {
            Turnover turnover = new Turnover();
            turnover.setTurnoverCode(turnoverDto.getTurnoverCode());
            turnover.setDate(turnoverDto.getDate());
            turnover.setTurnoverState(turnoverDto.getTurnoverState());
            turnover.setTotal(turnoverDto.getTotal());
            turnoverRepository.save(turnover);

            return "Turnover with id " + turnover.getId() + " correctly saved!";
        } else {
            throw new TurnoverNotFoundException("Turnover with id=" + id + " not found!");
        }
    }

    public String deleteTurnover(int id) {
        Optional<Turnover> turnoverOptional = getTurnoverById(id);
        if (turnoverOptional.isPresent()) {
            turnoverRepository.deleteById(id);
            return "Turnover with id=" + id + " correctly deleted!";
        } else {
            throw new TurnoverNotFoundException("Turnover with id=" + id + " not found!");
        }
    }
}
