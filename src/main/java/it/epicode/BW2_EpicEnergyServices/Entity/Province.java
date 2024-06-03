package it.epicode.BW2_EpicEnergyServices.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Province {

    @Id
    @GeneratedValue
    private int provinceId;

    @OneToOne
    @JoinColumn(name = "town_id")
    private Town town;

    private String acronym;

    private String name;

}
