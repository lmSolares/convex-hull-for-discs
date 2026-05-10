package com.fcunam.convexhullfordiscs.model.math.structs;

import org.jetbrains.annotations.NotNull;

public record Line(double a, double b, double c) {

    // Tolerance constant
    public static final double EPSILON = 1e-9;

    /**
     * Construct a directed line that goes from p1 to p2.
     *
     * @param p1 Starting point
     * @param p2 Ending point
     * @return Directed line that goes from p1 to p2.
     * */
    public static Line fromDirectedPoints(@NotNull OrderedPair p1, @NotNull OrderedPair p2) {
        double a = -(p2.y() - p1.y());
        double b = (p2.x() - p1.x());
        double c = (p1.x() * p2.y()) - (p2.x() * p1.y());
        return new Line(a, b, c);
    }

    /**
     * Determine if a point belongs to the line.
     *
     * @param point Point to determine if it belongs to the line
     * @return true if the point belongs to the line, otherwise false
     * */
    public boolean contains(@NotNull OrderedPair point) {
        return Math.abs(evaluatePoint(point)) < EPSILON;
    }

    /**
     * Determine on which side of the line a point is located.
     *
     * @param point point to determine its direction
     * @return <0 if the point is to the right, >0 if it is to the left, and 0 if it is collinear
     * */
    public double evaluatePoint(@NotNull OrderedPair point) {
        return (this.a * point.x()) + (this.b * point.y()) + this.c;
    }

}
