package it.epicode.BW2_EpicEnergyServices.Entity;

import it.epicode.BW2_EpicEnergyServices.Enums.TurnoverState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Turnover {

    private Long turnoverCode;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TurnoverState turnoverState;

    private double total;
}
