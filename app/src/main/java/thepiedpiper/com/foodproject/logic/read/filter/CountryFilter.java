package thepiedpiper.com.foodproject.logic.read.filter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import thepiedpiper.com.foodproject.logic.read.country.Country;
import thepiedpiper.com.foodproject.logic.read.country.Item;

public class CountryFilter implements Filter<Country, Item> {

    @Override
    public Country searchOfName(List<Country> countries, String countryName) {
        for (Country country: countries) {
            if (country.getArea().contains(countryName)) {
                return country;
            }
        }
        throw new IllegalArgumentException("Unknown country name");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Item max(Country country) throws NullPointerException {
        return max(country, -1);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Item max(List<Country> countries, String countryName) {
        return max(searchOfName(countries, countryName));
    }

    @Override
    public List<Country> sorted(List<Country> countries, Item itemName) {
        return sorted(countries, itemName, -1);
    }

    @Override
    public List<Country> sorted(List<Country> countries, Item itemName, int year) {
        List<Country> sortedList = new ArrayList<>();
        List<Country> countriesBuff = new ArrayList<>(countries);
        while (!countriesBuff.isEmpty()) {
            // TODO After create filters for Items
        }
        return sortedList;
    }

    @Override
    public Item max(Country country, int year) {
        Item resultItem = null;
        int maxAmount = -1;
        for (Item item : country.getItems()) {
            int currentYear = Item.START_YEAR;
            for (int itemAmount : item.getOfDates()) {
                if (year == currentYear || year == -1) {
                    if (itemAmount > maxAmount) {
                        maxAmount = itemAmount;
                        resultItem = item;
                    }
                }
                currentYear++;
            }
        }
        return resultItem;
    }

    @Override
    public Item max(List<Country> countries) throws NullPointerException {
        return max(countries, -1);
    }

    @Override
    public Item max(List<Country> countries, int year) {
        int amountMax = -1;
        Item resultItem = null;
        for (Country country : countries) {
            for (Item item : country.getItems()) {
                int currentYear = Item.START_YEAR;
                for (int itemAmount : item.getOfDates()) {
                    if (year == currentYear || year == -1) {
                        if (itemAmount > amountMax) {
                            amountMax = itemAmount;
                            resultItem = item;
                        }
                    }
                    currentYear++;
                }
            }
        }
        return resultItem;
    }
}
