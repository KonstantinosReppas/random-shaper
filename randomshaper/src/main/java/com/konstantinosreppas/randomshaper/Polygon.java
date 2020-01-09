package com.konstantinosreppas.randomshaper;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polygon {

    // Polygon coodinates.
    private final int[] polyY, polyX;

    // Number of sides in the polygon.
    private final int polySides;

    /**
     * Default constructor.
     * @param px Polygon y coods.
     * @param py Polygon x coods.
     * @param ps Polygon sides count.
     */
    public Polygon( final int[] px, final int[] py, final int ps ) {
        polyX = px;
        polyY = py;
        polySides = ps;
    }

    /**
     * Checks if the Polygon contains a point.
     * @see "http://alienryderflex.com/polygon/"
     * @param x Point horizontal pos.
     * @param y Point vertical pos.
     * @return Point is in Poly flag.
     */
    public boolean contains( final float x, final float y ) {

        boolean oddTransitions = false;
        for( int i = 0, j = polySides -1; i < polySides; j = i++ ) {
            if( ( polyY[ i ] < y && polyY[ j ] >= y ) || ( polyY[ j ] < y && polyY[ i ] >= y ) ) {
                if( polyX[ i ] + ( y - polyY[ i ] ) / ( polyY[ j ] - polyY[ i ] ) * ( polyX[ j ] - polyX[ i ] ) < x ) {
                    oddTransitions = !oddTransitions;
                }
            }
        }
        return oddTransitions;
    }

    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x=" + x + " y=" + y;
        }
    }

    public static Point findCentroid(ArrayList<Point> points) {
        int x = 0;
        int y = 0;
        for (Point p : points) {
            x += p.x;
            y += p.y;
        }
        Point center = new Point(0, 0);
        center.x = x / points.size();
        center.y = y / points.size();
        return center;
    }

    public static ArrayList<Point> sortVertices(ArrayList<Point> points) {
        // get centroid
        final Point center = findCentroid(points);
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                double a1 = (Math.toDegrees(Math.atan2(a.x - center.x, a.y - center.y)) + 360) % 360;
                double a2 = (Math.toDegrees(Math.atan2(b.x - center.x, b.y - center.y)) + 360) % 360;
                return (int) (a1 - a2);
            }
        });
        return points;
    }


}