package restapi.utils;

import org.springframework.stereotype.Service;

@Service
public class ProductUtils {
	
	 /**
     * Euclidean distance between the two arrays of type integer. No missing
     * value handling in this method.
     */
    public double euclideanDistance(int[] x, int[] y) {
	    if (x.length != y.length)
	        throw new IllegalArgumentException(String.format("Arrays have different length: x[%d], y[%d]", x.length, y.length));
	
	    double dist = 0.0;
	
        for (int i = 0; i < x.length; i++) {
            double d = x[i] - y[i];
            dist += d * d;
        }
	
        return Math.sqrt(dist);
    }

}
