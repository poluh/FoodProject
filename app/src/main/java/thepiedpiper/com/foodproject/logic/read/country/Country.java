package thepiedpiper.com.foodproject.logic.read.country;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Country {

    private String areaAbbreviation;
    private int areaCode;
    private String area;
    private LatLng coordinates;
    private List<Item> items;

    public Country(String... values) {
        this.areaAbbreviation = values[0];
        this.areaCode = Integer.parseInt(values[1]);
        this.area = values[2];
        this.coordinates = new LatLng(Double.valueOf(values[3]), Double.valueOf(values[4]));
        items = new ArrayList<>();
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
                Objects.equals(area, country.area) &&
                Objects.equals(coordinates, country.coordinates) &&
                Objects.equals(items, country.items);
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
}
