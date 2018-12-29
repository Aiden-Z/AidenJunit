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
        int maxPower = lPolynomial.length > rPolynomial.length ? lPolynomial.length : rPolynomial.length;
        int[] resultPolynomial = new int[maxPower];
        if (lPolynomial.length > rPolynomial.length) {
            int index = 0;
            for (; index < rPolynomial.length; index++) {
                resultPolynomial[index] = lPolynomial[index] + rPolynomial[index];
            }
            for (; index < lPolynomial.length; index++) {
                resultPolynomial[index] = lPolynomial[index];
            }
        } else {
            int index = 0;
            for (; index < lPolynomial.length; index++) {
                resultPolynomial[index] = lPolynomial[index] +rPolynomial[index];
            }
            for (; index < rPolynomial.length; index++) {
                resultPolynomial[index] = rPolynomial[index];
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
        int[] resultPolynomial = new int[rPolynomial.length + lPolynomial.length];
        for (int i = 0; i < lPolynomial.length; i++) {
            for (int j = 0; j < rPolynomial.length; j++) {
                resultPolynomial[i + j] += lPolynomial[i] * rPolynomial[j];
            }
        }
        return resultPolynomial;
    }
}
