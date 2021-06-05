package io.emqx.mqtt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends BaseFragment {
    public GoogleMap mMap;
    private String[] deneme;
    private MapsRecyclerViewAdapter mAdapter;
    private List<Maps> mList = new ArrayList<>();
    public MapsFragment() {
    }

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_message_list;
    }

    @Override
    protected void setUpView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.message_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(fragmentActivity, DividerItemDecoration.VERTICAL));
        mAdapter = new MapsRecyclerViewAdapter(mList);
        recyclerView.setAdapter(mAdapter);
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng sydney = new LatLng(-0, 0);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(34.0f));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    public void updateMessage1(MqttMessage message) {
        mMap.clear();
        deneme = message.toString().split(",");
        LatLng lastLocation = new LatLng(Float.parseFloat(deneme[0]), Float.parseFloat(deneme[1]));
        mMap.addMarker(new MarkerOptions().position(lastLocation).title(deneme[5]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lastLocation));
    }
}