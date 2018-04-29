package thepiedpiper.com.foodproject.logic.read.country;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Item implements Comparable<Item> {

    public static int UNIT = 1000;
    public static int START_YEAR = 1961;
    public static int END_YEAR = 2014;
    public static int AMONG = END_YEAR - START_YEAR;
    private int itemCode;
    private String itemName;
    private Element element;
    private Country linkToCountry;
    private int[] ofDates;

    public static List<Integer> allYears() {
        List<Integer> years = new ArrayList<>();
        for (int year = Item.START_YEAR; year < Item.END_YEAR; year++) {
            years.add(year);
        }
        return years;

    }

    public Item(int[] ofDates, Country country, String... values) {
        this.itemCode = Integer.parseInt(values[0]);
        this.itemName = values[1];
        this.element = values[2].equals("Feed") ? Element.FEED : Element.FOOD;
        this.ofDates = ofDates;
        this.linkToCountry = country;
    }

    public Country getLinkToCountry() {
        return linkToCountry;
    }

    public int getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public int[] getOfDates() {
        return ofDates;
    }

    public Element getElement() {
        return element;
    }

    public int getOfDate(int year) {
        return ofDates[year - START_YEAR];
    }

    public int gerMaxAmount() {
        int max = -1;
        for (int currentAmount : ofDates) {
            max = Math.max(currentAmount, max);
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(itemName).append("\n");
        int year = START_YEAR;
        for (int amount : ofDates) {
            sb.append("\tin ").append(year).append(" year = ").append(amount).append("\n");
            year++;
        }
        return sb.toString();
    }

    @Override
    public int compareTo(@NonNull Item other) {
        return this.getItemName().compareTo(other.itemName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Item o = (Item) obj;
        return this.getItemName().equals(o.getItemName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(itemCode, itemName, element, linkToCountry);
        result = 31 * result + Arrays.hashCode(ofDates);
        return result;
    }
}
