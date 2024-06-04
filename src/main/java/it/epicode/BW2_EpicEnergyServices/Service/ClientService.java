package it.epicode.BW2_EpicEnergyServices.Service;

import it.epicode.BW2_EpicEnergyServices.Dto.ClientDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Client;
import it.epicode.BW2_EpicEnergyServices.Exceptions.ClientNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public String saveClient(ClientDto clientDto) {
        Client client = new Client();
        client.setSocietyName(clientDto.getSocietyName());
        client.setClientType(clientDto.getClientType());
        client.setEmail(clientDto.getEmail());
        client.setVat(clientDto.getVat());
        client.setAddDate(clientDto.getAddDate());
        client.setLastContact(clientDto.getLastContact());
        client.setTotalSales(clientDto.getTotalSales());
        client.setPec(clientDto.getPec());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setContactEmail(clientDto.getContactEmail());
        client.setContactName(clientDto.getContactName());
        client.setContactSurname(clientDto.getContactSurname());
        client.setContactPhone(clientDto.getContactPhone());
        client.setSocietyLogo(clientDto.getSocietyLogo());
        client.setLegalAddress(clientDto.getLegaleAddress());
        client.setHeadquartesAddress(clientDto.getHeadquartesAddress());

        clientRepository.save(client);

        return "Client with id " + client.getClientId() + " correctly saved!";
    }

    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }

    public Client getClientByEmail(String email) {
        Optional<Client> clientOptional = clientRepository.findByEmail(email);

        if (clientOptional.isPresent()) {
            return clientOptional.get();
        } else {
            throw new ClientNotFoundException("Client with email=" + email + " not found!");
        }
    }

    public Client updateClient(int id, ClientDto clientDto) {
        Optional<Client> clientOptional = getClientById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setSocietyName(clientDto.getSocietyName());
            client.setClientType(clientDto.getClientType());
            client.setEmail(clientDto.getEmail());
            client.setVat(clientDto.getVat());
            client.setAddDate(clientDto.getAddDate());
            client.setLastContact(clientDto.getLastContact());
            client.setTotalSales(clientDto.getTotalSales());
            client.setPec(clientDto.getPec());
            client.setPhoneNumber(clientDto.getPhoneNumber());
            client.setContactEmail(clientDto.getContactEmail());
            client.setContactName(clientDto.getContactName());
            client.setContactSurname(clientDto.getContactSurname());
            client.setContactPhone(clientDto.getContactPhone());
            client.setSocietyLogo(clientDto.getSocietyLogo());
            client.setLegalAddress(clientDto.getLegaleAddress());
            client.setHeadquartesAddress(clientDto.getHeadquartesAddress());
            clientRepository.save(client);
            return client;
        } else {
            throw new ClientNotFoundException("Client with id=" + id + " not found!");
        }
    }

    public String deleteClient(int id) {
        Optional<Client> clientOptional = getClientById(id);
        if (clientOptional.isPresent()) {
            clientRepository.deleteById(id);
            return "Client with id=" + id + " correctly deleted!";
        } else {
            throw new ClientNotFoundException("Client with id=" + id + " not found!");
        }
    }
}
