package com.fcunam.convexhullfordiscs.controller;

import com.fcunam.convexhullfordiscs.model.math.geometry.GeometryUtils;
import com.fcunam.convexhullfordiscs.model.math.structs.Disc;
import com.fcunam.convexhullfordiscs.model.math.structs.Line;
import com.fcunam.convexhullfordiscs.model.math.structs.OrderedPair;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private Canvas drawingCanvas;

    @FXML
    protected void onDrawButtonClick() {
        GraphicsContext gc = drawingCanvas.getGraphicsContext2D();

        gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

        Disc disc1 = new Disc(new OrderedPair(150, 200), 60);
        Disc disc2 = new Disc(new OrderedPair(400, 250), 90);

        drawDisc(gc, disc1, Color.WHITE);
        drawDisc(gc, disc2, Color.WHITE);

        Line tangentLine = GeometryUtils.calculateCommonSupportLine(disc1, disc2);

        if (tangentLine != null) {
            drawInfiniteLine(gc, tangentLine, Color.BLACK);
        }
    }

    private void drawDisc(GraphicsContext gc, Disc disc, Color color) {
        gc.setFill(color);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.5);

        double startX = disc.dot().x() - disc.radius();
        double startY = disc.dot().y() - disc.radius();
        double diameter = disc.radius() * 2;

        gc.fillOval(startX, startY, diameter, diameter);
        gc.strokeOval(startX, startY, diameter, diameter);
    }

    private void drawInfiniteLine(GraphicsContext gc, Line line, Color color) {
        gc.setStroke(color);
        gc.setLineWidth(2.0);

        double canvasWidth = drawingCanvas.getWidth();
        double canvasHeight = drawingCanvas.getHeight();

        if (Math.abs(line.b()) > Line.EPSILON) {

            double y1 = -(line.a() * 0 + line.c()) / line.b();
            double y2 = -(line.a() * canvasWidth + line.c()) / line.b();

            gc.strokeLine(0, y1, canvasWidth, y2);
        } else {
            double x = -line.c() / line.a();
            gc.strokeLine(x, 0, x, canvasHeight);
        }
    }
}