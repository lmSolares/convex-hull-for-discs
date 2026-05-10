package com.fcunam.convexhullfordiscs.math.structs;

import org.jetbrains.annotations.NotNull;

public record Disc(OrderedPair dot, double radius) {

    // Tolerance constant
    public static final double EPSILON = 1e-9;

    /**
     * Determines whether a point is inside or on the edge of the disc.
     *
     * @param point Point to determine whether it is located inside the disk or not
     * @return true if it is inside or on the edge of the disk, otherwise false
     * */
    public boolean contains(@NotNull OrderedPair point) {
        double dx = this.dot.x() - point.x();
        double dy = this.dot.y() - point.y();
        double distanceSquared = (dx * dx) + (dy * dy);

        return distanceSquared <= (this.radius * this.radius) + EPSILON; // We compare the squares so as not to use Math.sqrt()
    }

}
