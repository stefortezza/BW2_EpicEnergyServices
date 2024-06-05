package it.epicode.BW2_EpicEnergyServices.Entity;


import it.epicode.BW2_EpicEnergyServices.Enums.ClientType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "client")
    private List<Address> address;




}
