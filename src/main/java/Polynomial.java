import java.util.ArrayList;

public class Polynomial {
    private int maxEqLength; // 最长长度 实例化之后用作多项式最大次幂
    private int[] polynomial;
    private static String NUMBER;
    private static String SYMBOL;
    private static String X;
    private static String OPERATOR;
    static {
        NUMBER = "0123456789";
        SYMBOL = "^";
        OPERATOR = "+-";
        X = "xX";
    }
    public Polynomial() {
        maxEqLength = 0;
    }

    public int getMaxEqLength() {
        return maxEqLength;
    }

    public boolean handlePolynomial(String polynomial) { // todo 此处需要测试，字符串为一个多项式
        if (polynomial == null || polynomial.length() < 1) { // 多项式最少一项，最短应该一个数字
            return false;
        }
        char[] cPolynomial = polynomial.toCharArray();
        ArrayList<int []> formulas = new ArrayList<int[]>();
        for (int i = 0; i < cPolynomial.length; i++) { // 每一次循环读出一个项式
            int symbol = 1;
            int ratio = 0;
            int power = 0;
            if (OPERATOR.indexOf(cPolynomial[i]) != -1){ // 检查第一个项式系数是否为负数
                symbol = cPolynomial[i] == '-' ? -1 : 1;
                i++;
            }
            while (NUMBER.indexOf(cPolynomial[i]) != -1){
                ratio = ratio * 10 + (cPolynomial[i] - '0');
                i++;
            }
            if (X.indexOf(cPolynomial[i]) != -1 && SYMBOL.indexOf(cPolynomial[i]) != -1) {
                i += 2; // 跳过"X^"
            } else if (OPERATOR.indexOf(cPolynomial[i]) != -1){ // 如果系数之后是操作符，说明这是个零次幂的项式
                int[] formula = new int[2];
                formula[0] = symbol * ratio;
                formulas.add(formula);
            } else { // 识别错误
                return false;
            }
            while (NUMBER.indexOf(cPolynomial[i]) != -1) {
                power = power * 10 + (cPolynomial[i] - '0');
                i++;
            }
            int[] formula = new int[2];
            formula[0] = symbol * ratio; // 将符号加入
            formula[i] = power;
            if (maxEqLength < power) {
                maxEqLength = power;
            }
            formulas.add(formula);
        }
        this.polynomial = new int[maxEqLength];
        for (int[] f : formulas
             ) {
            this.polynomial[f[1]] = f[0] + this.polynomial[f[1]];
        }
        return true;
    }

    public int[] getPolynomial() {
        int[] returnValue = new int[polynomial.length];
        System.arraycopy(polynomial, 0, returnValue, 0, polynomial.length);
        return returnValue;
    }
}
