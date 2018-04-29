package thepiedpiper.com.foodproject

import android.app.ProgressDialog
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle
import org.json.JSONException
import thepiedpiper.com.foodproject.logic.read.CSVOpener
import thepiedpiper.com.foodproject.logic.read.country.Country
import thepiedpiper.com.foodproject.logic.read.country.Item
import java.io.IOException


/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
class MainActivity :
        AppCompatActivity(),
        OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    companion object {
        @JvmStatic
        var year: Int? = null
        var item: Item? = null
    }

    var progresssDialog: ProgressDialog? = null

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val DEFAULT_POS = LatLng(60.0, 30.0) // SPB
    val DEFAULT_ZOOM_LEVEL = 3f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        openMap()
        if (!CSVOpener.isSorted) ParseTask().execute()
    }

    override fun onResume() {
        super.onResume()
        openMap()
    }

    fun openMap() {
        val mapFragment: SupportMapFragment? =
                supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        if (item!!.itemId == R.id.filter) {
            startActivity(Intent(this, FilterActivity::class.java))
        }
        return true
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just move the camera to Sydney and add a marker in Sydney.
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap ?: return

        // set styles
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style))

            if (!success) {
                Log.e("MAP_STYLE", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("MAP_STYLE", "Can't find style. Error: ", e)
        }

        with(googleMap.uiSettings) {
            isCompassEnabled = false
            isRotateGesturesEnabled = false
        }

        // draw polygons
        try {
            var layer = GeoJsonLayer(googleMap, R.raw.countries_geo, getApplicationContext())
            var style = layer.getDefaultPolygonStyle()

            style.setStrokeWidth(0F)

            layer.setOnFeatureClickListener({ Toast.makeText(this@MainActivity, it.properties.toString(), Toast.LENGTH_LONG).show() })

            if (item != null && year != null) {
                val countries = Country.getItemAmountByYear(item, year)
                var max = Country.maxAmount(countries)
                if (max == 0) max = 100000

                for (country in layer.features) {
                    if (countries.containsKey(country.id)){
                        val polygonStyle = GeoJsonPolygonStyle()
                        val kkk = countries[country.id]!!
                        polygonStyle.fillColor = Color.argb((255 * (countries[country.id]!!.toDouble() / max.toDouble())).toInt(),  255, 0, 0)
                        polygonStyle.setStrokeWidth(0F)
                        country.polygonStyle = polygonStyle
                    }
                }
            }

            layer.addLayerToMap()

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_POS, DEFAULT_ZOOM_LEVEL))

        } catch (e: IOException) {
            Log.e("IOException", e.getLocalizedMessage())
        } catch (e: JSONException) {
            Log.e("JSONException", e.getLocalizedMessage())
        }
    }

    private inner class ParseTask : AsyncTask<Void, Void?, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progresssDialog = ProgressDialog.show(this@MainActivity, "",
                    "Parsing data. Please wait...", true)
        }

        override fun doInBackground(vararg params: Void): Void? {
            try {
                CSVOpener().read(applicationContext)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progresssDialog?.cancel();
        }
    }
}