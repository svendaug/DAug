package sven.daug.com.daugcam;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.w3c.dom.Text;


public class MainActivity extends FragmentActivity {

    private static final int MY_PERMISSIONS = 1;
    //TextView disp;
    GoogleApiClient mLocClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //disp = (TextView) findViewById(R.id.disp);

        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.WAKE_LOCK};
        if(hasPermissions(MainActivity.this, PERMISSIONS)){
            //disp.setText("we have permissions");
            if (null == savedInstanceState) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Camcontainer, CamFragment.newInstance())
                        .commit();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Loccontainer, InfoFrag.newInstance())
                        .commit();
            }
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, MY_PERMISSIONS);
        }

    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
