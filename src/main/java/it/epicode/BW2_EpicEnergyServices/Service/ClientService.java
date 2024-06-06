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
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());

        clientRepository.save(client);

        return "Client with id " + client.getClientId() + " correctly saved!";
    }

    public Page<Client> getAllClients(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id " + id));
    }

    public String updateClient(int id, ClientDto clientDto) {
        Client client = getClientById(id);
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());

        clientRepository.save(client);

        return "Client with id " + client.getClientId() + " correctly saved!";
    }

    public String deleteClient(int id) {
        Client client = getClientById(id);
        clientRepository.deleteById(id);
        return "Client with id=" + id + " correctly deleted!";
    }

    @Transactional
    public void importClientsFromCSV(String filePath) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            csvReader.skip(1);

            List<String[]> rows = csvReader.readAll();

            for (String[] row : rows) {
                System.out.println("Raw row length: " + row.length);
                System.out.println("Raw row: " + Arrays.toString(row));

                for (int i = 0; i < row.length; i++) {
                    row[i] = row[i].trim();
                }

                System.out.println("Trimmed row length: " + row.length);
                System.out.println("Trimmed row: " + Arrays.toString(row));

                if (row.length >= 2) {
                    String firstName = row[0];
                    String lastName = row[1];

                    System.out.println("FirstName: " + firstName);
                    System.out.println("LastName: " + lastName);

                    Client client = new Client();
                    client.setFirstName(firstName);
                    client.setLastName(lastName);

                    clientRepository.save(client);
                } else {
                    System.out.println("Skipped row due to insufficient length: " + Arrays.toString(row));
                }
            }
        }
    }

    public Client getClientByLastName(String lastName) {
        Optional<Client> optionalClient = clientRepository.findByLastName(lastName);
        return optionalClient.orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
