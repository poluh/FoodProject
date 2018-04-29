package thepiedpiper.com.foodproject.logic.read;

import android.content.Context;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import thepiedpiper.com.foodproject.logic.read.country.Country;
import thepiedpiper.com.foodproject.logic.read.country.Element;
import thepiedpiper.com.foodproject.logic.read.country.Item;

public class CSVOpener {

    private List<Country> countries;
    public static List<Item> ALL_ITEMS = new ArrayList<>();
    public static List<Item> ALL_FEED = new ArrayList<>();
    public static List<Item> ALL_FOOD = new ArrayList<>();
    public static boolean isSorted = false;

    public CSVOpener() {}

    public CSVOpener read(Context context) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open("fao.csv")));
        countries = new ArrayList<>();

        String[] line;
        while ((line = reader.readNext()) != null) {
            if (!line[1].contains("Area Code")) {
                Country country = new Country(line[0], line[1], line[2], line[8], line[9]);
                int[] ofDates = new int[53];
                for (int column = 10; column < 63; column++) {
                    ofDates[column - 10] = !line[column].isEmpty() ? Integer.parseInt(line[column]) : 0;
                }
                Item item = new Item(ofDates, country, line[3], line[4], line[6]);
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
        Collections.sort(ALL_ITEMS);
        Collections.sort(ALL_FEED);
        Collections.sort(ALL_FOOD);
        Country.setCountries(countries);
        isSorted = true;
        return this;
    }

    public List<Country> getCountries() {
        return countries;
    }
}
