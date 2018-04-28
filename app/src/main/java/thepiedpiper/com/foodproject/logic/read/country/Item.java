package thepiedpiper.com.foodproject.logic.read.country;

public class Item {

    public static int UNIT = 1000;
    public static int START_YEAR = 1961;
    public static int END_YEAR = 2013;
    public static int AMONG = 53;
    private int itemCode;
    private String itemName;
    private int[] ofDates;


    public Item(int[] ofDates, String... values) {
        this.itemCode = Integer.parseInt(values[0]);
        this.itemName = values[1];
        this.ofDates = ofDates;
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
