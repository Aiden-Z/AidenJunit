import com.sun.istack.internal.NotNull;

public class Calculator {
    public int[] polynomialAdd(String lh, String rh) { // todo 两个串分别为两个不同的多项式，这是加法
        int[] lPolynomial, rPolynomial;
        Polynomial polynomial = new Polynomial();
        if (polynomial.handlePolynomial(lh)) {
            lPolynomial = polynomial.getPolynomial();
        } else {
            return null;
        }
        polynomial = new Polynomial();
        if (polynomial.handlePolynomial(rh)) {
            rPolynomial = polynomial.getPolynomial();
        } else {
            return null;
        }
        return polynomialAdd(lPolynomial, rPolynomial);
    }

    public int[] polynomialAdd(@NotNull int[] lPolynomial,@NotNull int[] rPolynomial) { // todo 两个串分别为两个不同的多项式，这是加法
        int maxPower = lPolynomial.length > rPolynomial.length ? lPolynomial.length : rPolynomial.length;
        int[] resultPolynomial = new int[maxPower];
        if (lPolynomial.length > rPolynomial.length) {
            int index = 0;
            for (; index * 2 < rPolynomial.length; index++) {
                resultPolynomial[resultPolynomial.length / 2 + index] = lPolynomial[lPolynomial.length / 2 + index] + rPolynomial[rPolynomial.length / 2 + index];
                resultPolynomial[resultPolynomial.length / 2 - index ] = lPolynomial[lPolynomial.length / 2 - index] + rPolynomial[rPolynomial.length / 2 - index];
            }
            for (; index * 2 < lPolynomial.length; index++) {
                resultPolynomial[resultPolynomial.length / 2 + index] = lPolynomial[lPolynomial.length / 2 + index];
                resultPolynomial[resultPolynomial.length / 2 - index ] = lPolynomial[lPolynomial.length / 2 - index];
            }
        } else {
            int index = 0;
            for (; index * 2 < lPolynomial.length; index++) {
                resultPolynomial[resultPolynomial.length / 2 + index] = lPolynomial[lPolynomial.length / 2 + index] + rPolynomial[rPolynomial.length / 2 + index];
                resultPolynomial[resultPolynomial.length / 2 - index] = lPolynomial[lPolynomial.length / 2 - index] + rPolynomial[rPolynomial.length / 2 - index];
            }
            for (; index * 2 < rPolynomial.length; index++) {
                resultPolynomial[resultPolynomial.length / 2 + index] = rPolynomial[rPolynomial.length / 2 + index];
                resultPolynomial[resultPolynomial.length / 2 - index] = rPolynomial[rPolynomial.length / 2 - index];
            }
        }
        return resultPolynomial;
    }

    public int[] polynomialMulti(String lh, String rh) { // todo 两个串分别为两个不同的多项式，这是乘法
        int[] lPolynomial, rPolynomial;
        Polynomial polynomial = new Polynomial();
        if (polynomial.handlePolynomial(lh)) {
            lPolynomial = polynomial.getPolynomial();
        } else {
            return null;
        }
        polynomial = new Polynomial();
        if (polynomial.handlePolynomial(rh)) {
            rPolynomial = polynomial.getPolynomial();
        } else {
            return null;
        }
        return polynomialMulti(lPolynomial, rPolynomial);
    }

    public int[] polynomialMulti(@NotNull int[] lPolynomial,@NotNull int[] rPolynomial) { // todo 两个串分别为两个不同的多项式，这是乘法
        int[] resultPolynomial = new int[(lPolynomial.length / 2 + rPolynomial.length / 2) * 2 + 1];
        for (int i = 0; i < lPolynomial.length; i++) {
            for (int j = 0; j < rPolynomial.length; j++) {
                int ratio = lPolynomial[i] * rPolynomial[j];
                int power = (i - lPolynomial.length / 2) + (j - rPolynomial.length / 2);
                resultPolynomial[resultPolynomial.length / 2 + power] += ratio;
            }
        }
        return resultPolynomial;
    }
}
