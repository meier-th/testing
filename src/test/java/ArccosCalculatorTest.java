import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArccosCalculatorTest {

    private static final Set<Double> arccosArgs = Set.of(-1d, -0.95, -0.9, -0.85, -0.75, -0.7, 0d, 0.7, 0.75, 0.85, 0.9, 0.95, 1d);

    private static boolean equalsWithPrecision(double value1, double value2, double precision) {
        return Math.abs(value1 - value2) <= precision;
    }

    private static void runArccosTestWithPrecision(double precision) {
        ArccosCalculator calculator = new ArccosCalculator();
        for (Double arg : arccosArgs)
            assertTrue(equalsWithPrecision(Math.acos(arg), calculator.calcArccos(arg), precision));
    }

    @Test
    @Tag("low")
    @DisplayName(value = "Arccos with low precision test")
    public void testArccosLowPrec() {
        runArccosTestWithPrecision(0.01);
    }

    @Test
    @Tag("medium")
    @DisplayName(value = "Arccos with med precision test")
    public void testArccosMedPrec() {
        runArccosTestWithPrecision(0.0001);
    }

    @Test
    @Tag("high")
    @DisplayName(value = "Arccos with high precision test")
    public void testArccosHighPrec() {
        runArccosTestWithPrecision(0.000001);
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

}
