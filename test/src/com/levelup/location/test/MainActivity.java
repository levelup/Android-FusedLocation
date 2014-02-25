package com.levelup.location.test;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.levelup.location.LocationManager;

public class MainActivity extends Activity implements LocationListener {

	private static final String TAG = "FusedLocation-test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LocationManager locationManager = LocationManager.getInstance(getApplicationContext());
		List<String> providers = locationManager.getAllProviders();
		boolean hasFused = false;
		for (String provider : providers) {
			if (LocationManager.FUSED_PROVIDER.equals(provider)) {
				hasFused = true;
				break;
			}
		}

		if (hasFused) {
			try {
				locationManager.requestSingleUpdate(LocationManager.FUSED_PROVIDER, this, Looper.getMainLooper());
			} catch (IllegalArgumentException e) {
				Log.i(TAG, "failed to request a location with fused provider", e);
				hasFused = false;
			}
		}

		if (!hasFused) {
			((TextView) findViewById(R.id.text1)).setText(R.string.fused_not_supported);
			findViewById(R.id.fused).setVisibility(View.GONE);
			findViewById(R.id.network).setVisibility(View.GONE);
			findViewById(R.id.gps).setVisibility(View.GONE);
		} else {
			locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, Looper.getMainLooper());
			try {
				locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, Looper.getMainLooper());
			} catch (IllegalArgumentException e) {
				Log.i(TAG, "failed to request a location with network provider", e);
				hasFused = false;
			}
		}
	}

	private static final List<Location> locations = new ArrayList<Location>(3);

	@Override
	public void onLocationChanged(Location location) {
		locations.add(location);
		if (LocationManager.FUSED_PROVIDER.equals(location.getProvider())) {
			((TextView) findViewById(R.id.textFused)).setText(String.format("%f, %f", location.getLatitude(), location.getLongitude()));
		} else if (LocationManager.GPS_PROVIDER.equals(location.getProvider())) {
			((TextView) findViewById(R.id.textGps)).setText(String.format("%f, %f", location.getLatitude(), location.getLongitude()));
		} else if (LocationManager.NETWORK_PROVIDER.equals(location.getProvider())) {
			((TextView) findViewById(R.id.textNetwork)).setText(String.format("%f, %f", location.getLatitude(), location.getLongitude()));
		}

		if (locations.size()==3) {
			((TextView) findViewById(R.id.text1)).setText(R.string.fused_supported);
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}
}
