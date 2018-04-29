package thepiedpiper.com.foodproject.logic.read;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import thepiedpiper.com.foodproject.logic.read.country.Country;
import thepiedpiper.com.foodproject.logic.read.country.Item;

public class CSVOpener {

    private String path;
    private List<Country> countries;

    CSVOpener(String path) {
        this.path = path;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void read() throws IOException {
        CSVReader reader = new CSVReader(Files.newBufferedReader(Paths.get(path)));

        countries = new ArrayList<>();

        String[] line;
        reader.readNext();
        while ((line = reader.readNext()) != null) {
            Country country = new Country(line[0], line[1], line[2], line[8], line[9]);
            int[] ofDates = new int[53];
            for (int column = 10; column < 63; column++) {
                ofDates[column - 10] = !line[column].isEmpty() ? Integer.parseInt(line[column]) : 0;
            }
            Item item = new Item(ofDates, country, line[3], line[4], line[5]);
            if (countries.contains(country)) {
                countries.get(countries.indexOf(country)).addItem(item);
            } else {
                countries.add(country);
                country.addItem(item);
            }
        }
    }

    public List<Country> getCountries() {
        return countries;
    }
}
