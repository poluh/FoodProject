package thepiedpiper.com.foodproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Spinner
import thepiedpiper.com.foodproject.logic.read.CSVOpener
import thepiedpiper.com.foodproject.logic.read.country.Item

class FilterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val food = CSVOpener.ALL_FOOD
        val feed = CSVOpener.ALL_FEED
        setContentView(R.layout.activity_filter)

        val spinner_item = findViewById<Spinner>(R.id.filter_spinner_item)
        spinner_item.adapter = ItemsAdapter(this, food)

        val spinner_year = findViewById<Spinner>(R.id.filter_spinner_year)
        spinner_year.adapter = YearAdapter(this, Item.allYears())

        findViewById<android.support.v7.widget.SwitchCompat>(R.id.filter_switch).setOnCheckedChangeListener {
            buttonView, isChecked -> spinner_item.adapter = ItemsAdapter(this, if (isChecked) feed else food) }
    }
}