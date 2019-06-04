package com.example.geofencing;

import com.mapbox.geojson.Point;

import java.util.ArrayList;
import java.util.List;

public class Polygons {
    public static final List<List<Point>> DEFAULT_POINTS = new ArrayList<>();
    public static final List<Point> DEFAULT_OUTER_POINTS = new ArrayList<>();

    public Polygons(){
        DEFAULT_OUTER_POINTS.add(Point.fromLngLat(34.794190,32.078599));
        DEFAULT_OUTER_POINTS.add(Point.fromLngLat(34.795000,32.078599));
        DEFAULT_OUTER_POINTS.add(Point.fromLngLat(34.795000,32.079999));
        DEFAULT_OUTER_POINTS.add(Point.fromLngLat(34.794190,32.079999));
        DEFAULT_OUTER_POINTS.add(Point.fromLngLat(34.794190,32.078599));

        DEFAULT_POINTS.add(DEFAULT_OUTER_POINTS);
    }
}
