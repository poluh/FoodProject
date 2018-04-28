package thepiedpiper.com.foodproject.logic.read;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import thepiedpiper.com.foodproject.logic.read.country.Country;
import thepiedpiper.com.foodproject.logic.read.country.Item;

public class CSVOpener {

    private String path;

    CSVOpener(String path) {
        this.path = path;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void read() throws IOException {
        CSVReader reader = new CSVReader(Files.newBufferedReader(Paths.get(path)));

        List<Country> countries = new ArrayList<>();

        String[] line;
        while ((line = reader.readNext()) != null) {
            Country country = new Country(line[0], line[1], line[2], line[8], line[9]);
            int[] ofDates = new int[52];
            for (int column = 10; column < 62; column++) {
                ofDates[0] = Integer.parseInt(line[column]);
            }
            Item item = new Item(ofDates, line[3], line[4]);
            if (countries.contains(country)) {
                country.addItem(item);
            } else {
                countries.add(country);
                country.addItem(item);
            }
        }
    }

}
