package thepiedpiper.com.foodproject.logic.read;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;

public class CheckParser {

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        CSVOpener csvOpener = new CSVOpener("/Users/sergey/Desktop/FAO.csv");
        try {
            csvOpener.read();
        } catch (IOException ignored) {
        }
        csvOpener.getCountries().forEach(country -> {
            System.out.println(country.toString());
        });
    }
}
