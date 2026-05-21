package statisticker;

import java.util.List;

public class Statistics 
{
    public static class Stats {
        public final float average;
        public final float min;
        public final float max;

        public Stats(float average, float min, float max) {
            this.average = average;
            this.min = min;
            this.max = max;
        }
    }

    public static Stats getStatistics(List<Float> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("temperature list cannot be null");
        }

        if (numbers.isEmpty()) {
            return new Stats(Float.NaN, Float.NaN, Float.NaN);
        }

        if (isLikelyCelsiusHumanTemperature(numbers)) {
            throw new IllegalArgumentException("expected Fahrenheit temperatures, but input looks like Celsius");
        }

        float sum = 0.0f;
        float min = Float.POSITIVE_INFINITY;
        float max = Float.NEGATIVE_INFINITY;

        for (Float temperatureFahrenheit : numbers) {
            if (temperatureFahrenheit == null) {
                throw new IllegalArgumentException("temperature value cannot be null");
            }

            sum += temperatureFahrenheit;
            min = Math.min(min, temperatureFahrenheit);
            max = Math.max(max, temperatureFahrenheit);
        }

        return new Stats(sum / numbers.size(), min, max);
    }

    private static boolean isLikelyCelsiusHumanTemperature(List<Float> numbers) {
        float min = Float.POSITIVE_INFINITY;
        float max = Float.NEGATIVE_INFINITY;

        for (Float temperature : numbers) {
            if (temperature == null) {
                throw new IllegalArgumentException("temperature value cannot be null");
            }

            min = Math.min(min, temperature);
            max = Math.max(max, temperature);
        }

        // Human body temperatures in Celsius are typically around 30-45,
        // while Fahrenheit body temperatures are usually around 90-110.
        return min >= 30.0f && max <= 45.0f;
    }
}
