package com.example.geofencing;

import android.app.NotificationManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.snatik.polygon.*;

import com.mapbox.geojson.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyLocationListener implements LocationListener {
    private Context context;
    private Polygons polygons;
    private boolean insidePolygon;
    private Polygon defaultPolygon;
    private NotificationManagerCompat notificationManager;
    private NotificationManager mNotifyManager;

    private final String FENCING_NOTIFICATION_STATUS = "Fencing Change !";
    private final int FENCE_NOTIFICATION_ID = 0;

    private static final Map<Boolean, String> insidePolygonStateMap = new HashMap<>();

    static {
        insidePolygonStateMap.put(true, "You are INSIDE of the polygon");
        insidePolygonStateMap.put(false, "You are OUTSIDE of the polygon");
    }

    public MyLocationListener(Context context, NotificationManager notificationManager) {
        this.context = context;

        polygons = new Polygons();
        defaultPolygon = generatePolygon(polygons.DEFAULT_OUTER_POINTS);
        mNotifyManager = notificationManager;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("Message: ", "Location changed, " + location.getAccuracy() +
                " , " + location.getLatitude() + "," +
                location.getLongitude());

        boolean currentInsidePolygonState = isInsidePolygon(location);

        if (insidePolygon != currentInsidePolygonState) {
            Log.i("Message: ", "Inside polygon, " + location.getAccuracy() +
                    " , " + location.getLatitude() + "," +
                    location.getLongitude());

            insidePolygon = currentInsidePolygonState;
            notifyInsidePolygonChange(insidePolygon);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    private boolean isInsidePolygon(Location location) {
        com.snatik.polygon.Point point = new com.snatik.polygon.Point(location.getLongitude(),
                location.getLatitude());

        return defaultPolygon.contains(point);
    }

    private Polygon generatePolygon(List<Point> points) {
        Polygon.Builder polygonBuilder = Polygon.Builder();
        for (Point point : points) {
            polygonBuilder.addVertex(new com.snatik.polygon.Point(point.longitude(), point.latitude()));
        }

        return polygonBuilder.build();
    }

    private void notifyInsidePolygonChange(boolean insidePolygon) {
//        Toast.makeText(
//                context,
//                notificationMessage,
//                Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder builder = generateNotificationBuilder(insidePolygon);

        mNotifyManager.notify(FENCE_NOTIFICATION_ID, builder.build());
    }

    private NotificationCompat.Builder generateNotificationBuilder(boolean insidePolygon) {
        String notificationMessage = insidePolygonStateMap.get(insidePolygon);

        return new NotificationCompat.Builder(context, MainActivity.FENCE_CHANNEL_ID)
                    .setContentTitle(FENCING_NOTIFICATION_STATUS)
                    .setContentText(notificationMessage)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setSmallIcon(R.mipmap.ic_android);
    }
}