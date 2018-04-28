package thepiedpiper.com.foodproject

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import org.json.JSONException
import java.io.IOException


/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
class MapFragmentС :
        SupportMapFragment(),
        OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {


    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val SPB = LatLng(60.0, 30.0)
    val ZOOM_LEVEL = 3f

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val rootView = inflater?.inflate(R.layout.fragment_maps, container, false)
        this.getMapAsync(this)

        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap ?: return

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            context, R.raw.map_style))

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

        try {
            // Drawing Polygons from JSON
            var layer = GeoJsonLayer(googleMap, R.raw.countries_geo, context)
            var style = layer.getDefaultPolygonStyle()
            style.setStrokeWidth(0F)

            layer.setOnFeatureClickListener({ Toast.makeText(context, it.properties.toString(), Toast.LENGTH_LONG).show() })
            layer.addLayerToMap()

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SPB, ZOOM_LEVEL))

        } catch (e: IOException) {
            Log.e("IOException", e.getLocalizedMessage())
        } catch (e: JSONException) {
            Log.e("JSONException", e.getLocalizedMessage())
        }
    }
}