import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ArccosCalculatorTest {

    private static void runArccosTestWithPrecision(double arg, double precision) {
        ArccosCalculator calculator = new ArccosCalculator();
        assertEquals(Math.acos(arg), calculator.calcArccos(arg), precision);
    }

    @ParameterizedTest
    @Tag("low")
    @DisplayName(value = "Arccos with low precision test")
    @MethodSource("getDataSet")
    public void testArccosLowPrec(Double value) {
        runArccosTestWithPrecision(value, 0.01);
    }

    @ParameterizedTest
    @Tag("medium")
    @DisplayName(value = "Arccos with med precision test")
    @MethodSource("getDataSet")
    public void testArccosMedPrec(double value) {
        runArccosTestWithPrecision(value, 0.0001);
    }

    @ParameterizedTest
    @Tag("high")
    @DisplayName(value = "Arccos with high precision test")
    @MethodSource("getDataSet")
    public void testArccosHighPrec(double value) {
        runArccosTestWithPrecision(value, 0.000001);
    }

    @Test
    @DisplayName(value = "Invalid argument test")
    public void testIncorrectArgument() {
        ArccosCalculator calculator = new ArccosCalculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.calcArccos(-1.1));
        assertThrows(IllegalArgumentException.class, () -> calculator.calcArccos(1.1));
        assertThrows(IllegalArgumentException.class, () -> calculator.calcArccos(-100));
        assertThrows(IllegalArgumentException.class, () -> calculator.calcArccos(-100));
    }

    public static Stream<Double> getDataSet() {
        return Stream.of(-1d, -0.9999, -0.9998, -0.95, -0.9, -0.85, -0.75, -0.7, -0.1, -0.005, -0.000000001,
                0d, 0.000000001, 0.005, 0.1, 0.7, 0.75, 0.85, 0.9, 0.95, 0.9998, 0.9999, 1d);
    }

}
