package sven.daug.com.daugcam;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFrag extends Fragment {

    GoogleApiClient mLocClient;
    Location mLastLocation;
    Location mCurrentLocation;
    LocationRequest mLocationRequest;
    TextView disp;

    public InfoFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment InfoFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFrag newInstance() {
        InfoFrag fragment = new InfoFrag();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        /*view.findViewById(R.id.picture).setOnClickListener(this);
        view.findViewById(R.id.info).setOnClickListener(this);*/
        disp = (TextView) view.findViewById(R.id.disp);
        disp.setText("View created");
    }




}
