package com.levelup.location.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.test.AndroidTestCase;
import android.text.format.DateUtils;

import com.levelup.location.LocationManager;

public class FusedCriteriaTest extends AndroidTestCase {

	private static final long MIN_TIME_IN_MS = 5 * DateUtils.SECOND_IN_MILLIS;
	private static final long MIN_DISTANCE_IN_M = 30;

	private LocationManager locationManager;

	@Override
	public void setContext(Context context) {
		super.setContext(context);
		locationManager = LocationManager.getInstance(context);
	}
	
	public void testDefaultCriteria() throws InterruptedException {
		final CountDownLatch signal = new CountDownLatch(1);
		Criteria criteria = new Criteria();
		locationManager.requestLocationUpdates(MIN_TIME_IN_MS, MIN_DISTANCE_IN_M, criteria, new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onProviderDisabled(String provider) {
			}

			@Override
			public void onLocationChanged(Location location) {
				assertNotNull(location);
				signal.countDown();
			}
			
		}, null);
		
		signal.await(MIN_TIME_IN_MS, TimeUnit.MILLISECONDS);
	}

}
