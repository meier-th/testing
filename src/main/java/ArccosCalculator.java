import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class ArccosCalculator {

    private final static int MAX_SERIES_ELEMENTS = 1000;

    private BigInteger nFactorial = BigInteger.ONE;
    private BigInteger twonFactorial = BigInteger.ONE;

    private double calcSeriesElement(int n, double x) {
        nFactorial = n == 0 ? BigInteger.ONE : nFactorial.multiply(BigInteger.valueOf(n));
        twonFactorial = n == 0 ? BigInteger.ONE : twonFactorial.multiply(BigInteger.valueOf(2*n-1)).multiply(BigInteger.valueOf(2*n));
        return new BigDecimal(twonFactorial)
                .divide(BigDecimal.valueOf(2).pow(2*n)
                        .multiply(new BigDecimal(nFactorial.pow(2))),
                        MathContext.DECIMAL128)
                .multiply(BigDecimal.valueOf(x).pow(2*n+1)
                        .divide(BigDecimal.valueOf(2*n+1),
                                MathContext.DECIMAL128)).doubleValue();
    }

    public double calcArccos(double arg) {
        if (arg > 1 || arg < -1)
            throw new IllegalArgumentException(String.format("Arccos function is only defined with argument's value in [-1; 1]. Provided: %f", arg));
        if (arg == 1)
            return 0;
        if (arg == -1)
            return Math.PI;
        double sum = Math.PI/2;
        for (int i = 0; i < MAX_SERIES_ELEMENTS; ++i)
            sum -= calcSeriesElement(i, arg);
        return sum;
    }

}
