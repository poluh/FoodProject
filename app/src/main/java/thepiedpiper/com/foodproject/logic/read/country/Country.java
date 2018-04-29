package thepiedpiper.com.foodproject.logic.read.country;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Country implements Comparable<Country> {

    private String areaAbbreviation;
    private int areaCode;
    private String area;
    private LatLng coordinates;
    private List<Item> items;
    private static List<Country> countries;

    public Country(String areaAb, int areaCode, String area, LatLng latLng) {
        this.areaAbbreviation = areaAb;
        this.areaCode = areaCode;
        this.area = area;
        this.coordinates = latLng;
        items = new ArrayList<>();
    }

    public static Map<String, Integer> getItemAmountByYear(Item item, int year) {
        Map<String, Integer> answer = new HashMap<>();
        for (Country country : countries) {
            for (Item currentItem : country.getItems()) {
                if (currentItem.getItemName().equals(item.getItemName())) {
                    answer.put(country.getAreaAbbreviation(), item.getOfDate(year));
                }
            }
        }
        return answer;
    }

    public Country(String... values) {
        this.areaAbbreviation = values[0];
        this.areaCode = Integer.parseInt(values[1]);
        this.area = values[2];
        this.coordinates = new LatLng(Double.valueOf(values[3]), Double.valueOf(values[4]));
        items = new ArrayList<>();
    }

    public static void setCountries(List<Country> countries) {
        Country.countries = countries;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String getAreaAbbreviation() {
        return areaAbbreviation;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public String getArea() {
        return area;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public List<Item> getItems() {
        return items;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return areaCode == country.areaCode &&
                Objects.equals(areaAbbreviation, country.areaAbbreviation) &&
                Objects.equals(area, country.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaAbbreviation, areaCode, area, coordinates, items);
    }

    @Override
    public String toString() {
        return areaAbbreviation + " areaCode = " + areaCode + " area = " + area +
                " coordinates = " + coordinates + " all items = " + items;
    }

    @Override
    public int compareTo(@NonNull Country other) {
        return this.areaAbbreviation.compareTo(other.areaAbbreviation);
    }
}
