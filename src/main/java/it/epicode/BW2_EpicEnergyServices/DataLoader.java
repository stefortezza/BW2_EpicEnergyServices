package it.epicode.BW2_EpicEnergyServices;

import com.opencsv.exceptions.CsvException;
import it.epicode.BW2_EpicEnergyServices.Service.ProvinceService;
import it.epicode.BW2_EpicEnergyServices.Service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TownService townService;

    @Autowired
    private ProvinceService provinceService;

    @Override
    public void run(String... args) throws Exception {
        try {
            provinceService.importProvincesFromCSV("province-italiane.csv");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}

