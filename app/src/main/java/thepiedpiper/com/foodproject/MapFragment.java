package thepiedpiper.com.foodproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    public MapFragment() {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        super.onCreateView(inflater, container, bundle);

        this.getMapAsync(this);
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap == null) return;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.map_style));

            if (!success) {
                Log.e("MAP_STYLE", "Style parsing failed.");
            }
        } catch (Exception e) {
            Log.e("MAP_STYLE", "Can't find style. Error: ", e);
        }
    }
}
