package it.epicode.BW2_EpicEnergyServices.Dto;

import it.epicode.BW2_EpicEnergyServices.Entity.Address;
import it.epicode.BW2_EpicEnergyServices.Enums.ClientType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientDto {
    @NotBlank
    @Size(min = 4, max = 15)
    private String societyName;
    @NotBlank
    private ClientType clientType;
    @NotBlank
    @Size(min = 11, max = 11)
    private String vat;
    @NotBlank
    @Email
    @Size(min = 2, max = 20)
    private String email;
    @NotNull
    private LocalDate addDate;
    @NotNull
    private LocalDate lastContact;
    @NotNull
    @Positive
    @Min(1)
    @Max(50)
    private double totalSales;
    @NotBlank
    @Email
    @Size(min = 2, max = 20)
    private String pec;
    @NotNull
    @Positive
    @Min(10)
    @Max(10)
    private long phoneNumber;
    @NotBlank
    @Email
    @Size(min = 2, max = 20)
    private String contactEmail;
    @NotBlank
    @Size(min = 1, max = 20)
    private String contactName;
    @NotBlank
    @Size(min = 1, max = 20)
    private String contactSurname;
    @NotBlank
    @Min(10)
    @Max(10)
    private long contactPhone;

    private String societyLogo;

    @NotNull
    private List<Address> address;


}
