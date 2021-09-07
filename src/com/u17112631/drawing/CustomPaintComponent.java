package com.u17112631.drawing;

import com.u17112631.models.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomPaintComponent extends Component {

    private final List<Polygon> polygons;
    private boolean drawBorder;

    private int xOrigin;
    private int xEnd;
    private int yOrigin;
    private int yEnd;

    public CustomPaintComponent() {
        this.polygons = new ArrayList<>();
        drawBorder = true;

        xOrigin = 50;
        yOrigin = 50;
    }

    public Polygon createPolygon(Piece p){
        Polygon polygon = new Polygon();

        for (var coord : p.getVertices()){
            var xCoord = coord.getX() + xOrigin; //Left shift
            var yCoord = coord.getY() + yOrigin; //Down shift

            yCoord = yEnd - yCoord;

            polygon.addPoint(xCoord,yCoord );
        }

        return polygon;

    }

    public void paint(Graphics g) {

        // Retrieve the graphics context; this object is used to paint shapes

        Graphics2D window = (Graphics2D) g;

        if(drawBorder)
            drawBorder(window);

        for (var polygon : polygons){
            window.fillPolygon(polygon);
        }

    }

    public CustomPaintComponent addPoly(Polygon p){
        polygons.add(p);
        return this;
    }

    public CustomPaintComponent addPoly(Piece piece) {
        return addPoly(createPolygon(piece));
    }

    public CustomPaintComponent drawBorder(Graphics2D window){

        /**
         * The coordinate system of a graphics context is such that the origin is at the
         * northwest corner and x-axis increases toward the right while the y-axis increases
         * toward the bottom.
         */

        window.drawLine(xOrigin,yEnd,xEnd,yEnd); //top
        window.drawLine(xOrigin,yOrigin,xEnd,yOrigin);//bottom
        window.drawLine(xEnd,yOrigin,xEnd,yEnd);//right
        window.drawLine(xOrigin,yOrigin,xOrigin,yEnd); //left

        return this;
    }

    public void setEnds() {
        xEnd = getSize().width - 50;

        yEnd = getSize().height - 50;
    }
//    public void paint(Graphics g) {
//
//
//        // A start angle of 0 represents a 3 o'clock position, 90 represents a 12 o'clock position,
//
//        // and -90 (or 270) represents a 6 o'clock position
//
//        int startAngle = 45;
//
//        int arcAngle = -60;
//
//        // to draw a filled arc use : g2d.fillArc(x, y, w, h, startAngle, arcAngle) instead
//
//        g2d.drawArc(x, y, w / 2, h / 2, startAngle, arcAngle);
//
//        // to draw a filled round rectangle use : g2d.fillRoundRect(x, y, w, h, arcWidth, arcHeight) instead
//
//        g2d.drawRoundRect(x, y, w, h, w / 2, h / 2);
//
//        Polygon polygon = new Polygon();
//
//        polygon.addPoint(w / 4, h / 2);
//
//        polygon.addPoint(0, h / 2);
//
//        polygon.addPoint(w / 4, 3 * h / 4);
//
//        polygon.addPoint(w / 2, 3 * h / 4);
//
//        // Add more points...
//
//        // to draw a filled round rectangle use : g2d.fillPolygon(polygon) instead
//
//        g2d.drawPolygon(polygon);
//
//    }
}
