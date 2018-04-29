package thepiedpiper.com.foodproject.logic.read;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;

public class CheckParser {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) throws IOException {
        CSVOpener csvOpener = new CSVOpener();


        System.out.println(CSVOpener.ALL_ITEMS);
    }
}
