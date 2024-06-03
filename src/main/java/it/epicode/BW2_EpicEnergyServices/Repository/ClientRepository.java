package it.epicode.BW2_EpicEnergyServices.Repository;

import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Optional<Client> findByEmail(String email);

}
