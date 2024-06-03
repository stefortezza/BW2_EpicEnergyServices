package it.epicode.BW2_EpicEnergyServices.Entity;


import it.epicode.BW2_EpicEnergyServices.Enums.ClientType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue
    private int  clientId;
    private String societyName;
    private ClientType clientType;
    private String vat;
    private String email;
    private LocalDate addDate;
    private LocalDate lastContact;
    private double totalSales;
    private String pec;
    private long phoneNumber;
    private String contactEmail;
    private String contactName;
    private String contactSurname;
    private long contactPhone;
    private String societyLogo;
    @ManyToOne
    @JoinColumn(name="legal_address_id")
    private Address legalAddress;
    @ManyToOne
    @JoinColumn(name="headquartes_address_id")
    private Address headquartesAddress;


}
