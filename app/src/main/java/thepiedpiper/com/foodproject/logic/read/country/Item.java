package thepiedpiper.com.foodproject.logic.read.country;

public class Item {

    public static int UNIT = 1000;
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
}
