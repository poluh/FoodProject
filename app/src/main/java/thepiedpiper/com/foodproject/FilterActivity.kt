package thepiedpiper.com.foodproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Spinner
import android.widget.Switch
import thepiedpiper.com.foodproject.logic.read.CSVOpener

class FilterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        CSVOpener().read(applicationContext)

        var spinner = findViewById<Spinner>(R.id.filter_spinner_item)
        spinner.adapter = ItemsAdapter(this, CSVOpener.ALL_FOOD)

        findViewById<android.support.v7.widget.SwitchCompat>(R.id.filter_switch).setOnCheckedChangeListener{
            buttonView, isChecked -> spinner.adapter = ItemsAdapter(this, if (isChecked) CSVOpener.ALL_FEED else CSVOpener.ALL_FOOD) }
    }
}