package it.epicode.BW2_EpicEnergyServices.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Town {
    @Id
    @GeneratedValue
    private int townId;
    @OneToOne(mappedBy = "town")
    private Province province;
    private String townName;


    @OneToMany(mappedBy = "town")
    private List<Address> addressList;
}
