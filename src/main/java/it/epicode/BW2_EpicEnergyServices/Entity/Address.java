package it.epicode.BW2_EpicEnergyServices.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private int addressId;
    private String street;
    private String number;
    private int cap;
    @OneToMany(mappedBy = "legalAddress")
    private List<Address> legalAddressList;
    @OneToMany(mappedBy = "headquartesAddress")
    private List<Address> headquartesAddressList;
    @ManyToOne
    @JoinColumn(name="town_id")
    private Town town;


}
