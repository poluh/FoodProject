package thepiedpiper.com.foodproject.logic.read;

import java.io.IOException;

public class CheckParser {

    public static void main(String[] args) {
        CSVOpener csvOpener = new CSVOpener();
/*
        try {
            csvOpener.read();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
*/

        System.out.println(CSVOpener.ALL_ITEMS);
    }
}
