package com.fcunam.convexhullfordiscs.model.math.structs;

import org.jetbrains.annotations.NotNull;

public record OrderedPair(double x, double y) {

    // Tolerance constant
    public static final double EPSILON = 1e-9;

    /**
     * Compares whether two points are equal, avoiding floating-point errors.
     * @param pair point to compare
     * @return true if they are equal, otherwise false
     * */
    public boolean isEqual(@NotNull OrderedPair pair) {
        return Math.abs(this.x - pair.x) < EPSILON && Math.abs(this.y - pair.y) < EPSILON;
    }

}
