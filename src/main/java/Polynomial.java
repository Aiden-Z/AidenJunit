import com.sun.istack.internal.NotNull;

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

    public boolean handlePolynomial(@NotNull String polynomial) { // todo 此处需要测试，字符串为一个多项式
        this.polynomial = new int[0];
        if (polynomial.length() < 1) { // 多项式最少一项，最短应该一个数字
            return false;
        }
        char[] cPolynomial = polynomial.toCharArray();
        ArrayList<int []> formulas = new ArrayList<int[]>();
        for (int i = 0; i < cPolynomial.length;) { // 每一次循环读出一个项式
            int symbol = 1;
            int powerSym = 1;
            int ratio = 0;
            int power = 0;
            if (OPERATOR.indexOf(cPolynomial[i]) != -1){ // 检查第一个项式系数是否为负数
                symbol = cPolynomial[i] == '-' ? -1 : 1;
                i++;
            }
            if (X.indexOf(cPolynomial[i]) != -1) {
                ratio = 1;
            }
            if (SYMBOL.indexOf(cPolynomial[i]) != -1) {
                return false;
            }
            while (i < cPolynomial.length && NUMBER.indexOf(cPolynomial[i]) != -1){
                ratio = ratio * 10 + (cPolynomial[i] - '0');
                i++;
            }
            if (i < cPolynomial.length && X.indexOf(cPolynomial[i]) != -1) { // 如果是非零次幂则跳过"X"
                i += 1;
                if (i < cPolynomial.length && SYMBOL.indexOf(cPolynomial[i]) != -1){ // 如果幂次不为1则跳过"^"
                    i += 1;
                    if (OPERATOR.indexOf(cPolynomial[i]) != -1){ // 检查指数是否为负数
                        powerSym = cPolynomial[i] == '-' ? -1 : 1;
                        i++;
                    }
                } else if (i == cPolynomial.length || OPERATOR.indexOf(cPolynomial[i]) != -1){ // 如果幂次为1
                    int[] formula = new int[2];
                    formula[0] = symbol * ratio;
                    formula[1] = 1;
                    if (maxEqLength < 1) {
                        maxEqLength = 1;
                    }
                    formulas.add(formula);
                    continue;
                } else {
                    return false;
                }
            } else if (i == cPolynomial.length || OPERATOR.indexOf(cPolynomial[i]) != -1){ // 如果系数之后是操作符或者系数之后没有字符，说明这是个零次幂的项式
                int[] formula = new int[2];
                formula[0] = symbol * ratio;
                formulas.add(formula);
                continue;
            } else { // 识别错误
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            while (i < cPolynomial.length && NUMBER.indexOf(cPolynomial[i]) != -1) {
                stringBuilder.append(cPolynomial[i]);
                i++;
            }
            try {
                power = Integer.parseInt(stringBuilder.toString());
            } catch (Exception e) {
                return false;
            }
            power *= powerSym;
            int[] formula = new int[2];
            formula[0] = symbol * ratio; // 将符号加入
            formula[1] = power;
            if (maxEqLength < Math.abs(power)) {
                maxEqLength = Math.abs(power);
            }
            formulas.add(formula);
        }
        this.polynomial = new int[maxEqLength * 2 + 1];
        for (int[] f : formulas
             ) {
            this.polynomial[f[1] + maxEqLength] = f[0] + this.polynomial[f[1] + maxEqLength];
        }
        return true;
    }

    public int[] getPolynomial() {
        int[] returnValue = new int[polynomial.length];
        System.arraycopy(polynomial, 0, returnValue, 0, polynomial.length);
        return returnValue;
    }
}
