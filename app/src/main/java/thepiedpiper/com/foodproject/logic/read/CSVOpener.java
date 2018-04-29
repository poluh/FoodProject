package thepiedpiper.com.foodproject.logic.read;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import thepiedpiper.com.foodproject.logic.read.country.Country;
import thepiedpiper.com.foodproject.logic.read.country.Element;
import thepiedpiper.com.foodproject.logic.read.country.Item;

public class CSVOpener {

    private String path;
    private List<Country> countries;
    public static List<Item> ALL_ITEMS = new ArrayList<>();
    public static List<Item> ALL_FEED = new ArrayList<>();
    public static List<Item> ALL_FOOD = new ArrayList<>();

    CSVOpener(String path) {
        this.path = path;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void read() throws IOException {
        CSVReader reader = new CSVReader(Files.newBufferedReader(Paths.get(path), Charset.forName("windows-1251")));
        countries = new ArrayList<>();

        String[] line;
        while ((line = reader.readNext()) != null) {
            if (!line[1].contains("Area Code")) {
                Country country = new Country(line[0], line[1], line[2], line[8], line[9]);
                int[] ofDates = new int[53];
                for (int column = 10; column < 63; column++) {
                    ofDates[column - 10] = !line[column].isEmpty() ? Integer.parseInt(line[column]) : 0;
                }
                Item item = new Item(ofDates, country, line[3], line[4], line[5]);
                if (!ALL_ITEMS.contains(item)) {
                    ALL_ITEMS.add(item);
                    if (item.getElement() == Element.FEED) {
                        ALL_FEED.add(item);
                    } else {
                        ALL_FOOD.add(item);
                    }
                }
                if (countries.contains(country)) {
                    countries.get(countries.indexOf(country)).addItem(item);
                } else {
                    countries.add(country);
                    country.addItem(item);
                }
            }
        }
    }

    public List<Country> getCountries() {
        return countries;
    }
}
