package com.fcunam.convexhullfordiscs.math.geometry;

import com.fcunam.convexhullfordiscs.math.structs.Disc;
import com.fcunam.convexhullfordiscs.math.structs.Line;
import com.fcunam.convexhullfordiscs.math.structs.OrderedPair;
import org.jetbrains.annotations.NotNull;

/**
 *
 * */
public class GeometryUtils {

    /**
     * */
    public static Line calculateCommonSupportLine(@NotNull Disc disc1, @NotNull Disc disc2) {

        double distance = calculateDistance(disc1.dot(), disc2.dot());
        double radiusDiff = Math.abs(disc1.radius() - disc2.radius());

        // If one disk is inside the other or, they are the same center, there is no common support.
        if (distance <= radiusDiff + OrderedPair.EPSILON) {
            return null;
        }

        double theta = calculateAngle(disc1.dot(), disc2.dot());
        double alpha = Math.acos((disc1.radius() - disc2.radius()) / distance);
        double tangent_angle = theta + alpha;

        double x1 = disc1.dot().x() + disc1.radius() * Math.cos(tangent_angle);
        double y1 = disc1.dot().y() + disc1.radius() * Math.sin(tangent_angle);

        double x2 = disc2.dot().x() + disc2.radius() * Math.cos(tangent_angle);
        double y2 = disc2.dot().y() + disc2.radius() * Math.sin(tangent_angle);

        return Line.fromDirectedPoints(new OrderedPair(x1,y1), new OrderedPair(x2,y2));
    }

    /**
     * @param point1
     * @param point2
     * @return
     * */
    public static double calculateAngle(@NotNull OrderedPair point1, @NotNull OrderedPair point2) {
        return Math.atan2(point2.y() - point1.y(), point2.x() - point1.x());
    }

    /**
     * @param point1
     * @param point2
     * @return
     * */
    public static double calculateDistance(@NotNull OrderedPair point1, @NotNull OrderedPair point2) {
        double x = point2.x() - point1.x();
        double y = point2.y() - point1.y();
        return Math.sqrt(x * x + y * y);
    }

}
