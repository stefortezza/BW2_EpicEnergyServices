package it.epicode.BW2_EpicEnergyServices.Service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import it.epicode.BW2_EpicEnergyServices.Dto.TownDto;
import it.epicode.BW2_EpicEnergyServices.Entity.Province;
import it.epicode.BW2_EpicEnergyServices.Entity.Town;
import it.epicode.BW2_EpicEnergyServices.Exceptions.TownNotFoundException;
import it.epicode.BW2_EpicEnergyServices.Repository.TownRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TownService {

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private ProvinceService provinceService;

    public String saveTown(TownDto townDto) {
        Town town = new Town();
        town.setTownName(townDto.getTownName());
        town.setProvince(townDto.getProvince());

        townRepository.save(town);

        return "Town with id " + town.getTownId() + " correctly saved!";
    }

    public List<Town> getAllTown() {
        return townRepository.findAll();
    }

    public Optional<Town> getTownById(int id) {
        return townRepository.findById(id);
    }

    public String updateTown(int id, TownDto townDto) {
        Optional<Town> townOptional = getTownById(id);
        if (townOptional.isPresent()) {
            Town town = new Town();
            town.setTownName(townDto.getTownName());
            town.setProvince(townDto.getProvince());

            townRepository.save(town);

            return "Town with id " + town.getTownId() + " correctly saved!";

        } else {
            throw new TownNotFoundException("Town with id=" + id + " not found!");
        }
    }

    public String deleteTown(int id) {
        Optional<Town> townOptional = getTownById(id);
        if (townOptional.isPresent()) {
            townRepository.deleteById(id);
            return "Town with id=" + id + " correctly deleted!";
        } else {
            throw new TownNotFoundException("Town with id=" + id + " not found!");
        }
    }

    @Transactional
    public void importTownsFromCSV(String filePath) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            csvReader.skip(1);

            List<String[]> rows = csvReader.readAll();

            for (String[] row : rows) {
                System.out.println("Raw row length: " + row.length);
                System.out.println("Raw row: " + Arrays.toString(row));

                // Rimuovi gli spazi bianchi da ciascun elemento dell'array
                for (int i = 0; i < row.length; i++) {
                    row[i] = row[i].trim();
                }

                System.out.println("Trimmed row length: " + row.length);
                System.out.println("Trimmed row: " + Arrays.toString(row));

                if (row.length >= 3) { // Assicurati che ci siano abbastanza colonne
                    String townName = row[2];
                    String provinceName = row[3];

                    System.out.println("TownName: " + townName);
                    System.out.println("ProvinceName: " + provinceName);

                    Province province = provinceService.getProvinceByName(provinceName);
                    if (province != null) {
                        Town town = new Town();
                        town.setTownName(townName);
                        town.setProvince(province);

                        // Salva la città nel repository
                        townRepository.save(town);
                    } else {
                        System.out.println("Province not found for row: " + Arrays.toString(row));
                    }
                } else {
                    System.out.println("Skipped row due to insufficient length: " + Arrays.toString(row));
                }
            }
        }
    }

}
