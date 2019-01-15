package Utils;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Util {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        String ipt = scanner.next();
        if (StringUtils.isNotEmpty(ipt)) {
            return ipt;
        } else {
            return null;
        }
    }

    public class Test{
        public Test(@NotNull String input, @NotNull String validate, @NotNull boolean handleValidate) {
            this.input = input;
            String validates[] = validate.equals("") ? new String[0] : validate.split(",");
            int[] v = new int[validates.length];
            for (int i = 0; i < validates.length; i++) {
                if (validates[i].length() == 0) {
                    continue;
                }
                v[i] = (Integer.parseInt(validates[i]));
            }
            this.validate = v;
            this.handleValidate = handleValidate;
        }
        private String input;
        private int[] validate;
        private boolean handleValidate;

        public boolean isHandleValidate() {
            return handleValidate;
        }

        public void setHandleValidate(boolean handleValidate) {
            this.handleValidate = handleValidate;
        }

        public String getInput() {
            return input;
        }

        public int[] getValidate() {
            return validate;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public void setValidate(int[] validate) {
            this.validate = validate;
        }
    }

    public class CalculatorTest {
        private int[] lh;
        private int[] rh;
        private int[] validate;
        public CalculatorTest(@NotNull String lh, @NotNull String rh, @NotNull String validate) {
            String[] validates = validate.split(",");
            String[] rhs = rh.split(",");
            String[] lhs = lh.split(",");
            this.validate = new int[validates.length];
            this.lh = new int[lhs.length];
            this.rh = new int[rhs.length];
            for (int i = 0; i < validates.length; i++) {
                this.validate[i] = (Integer.parseInt(validates[i]));
            }
            for (int i = 0; i < lhs.length; i++) {
                this.lh[i] = (Integer.parseInt(lhs[i]));
            }
            for (int i = 0; i < rhs.length; i++) {
                this.rh[i] = (Integer.parseInt(rhs[i]));
            }
        }

        public int[] getLh() {
            return lh;
        }

        public int[] getRh() {
            return rh;
        }

        public int[] getValidate() {
            return validate;
        }
    }
}
