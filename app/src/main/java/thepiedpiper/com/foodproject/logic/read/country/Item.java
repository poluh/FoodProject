package thepiedpiper.com.foodproject.logic.read.country;

public class Item {

    public static int UNIT = 1000;
    public static int START_YEAR = 1961;
    public static int END_YEAR = 2013;
    public static int AMONG = 53;
    private int itemCode;
    private String itemName;
    private Element element;
    private Country linkToCountry;
    private int[] ofDates;

    enum Element {
        FOOD,
        FEED;

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
}
