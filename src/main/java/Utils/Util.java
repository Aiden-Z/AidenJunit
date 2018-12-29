package Utils;

import org.apache.commons.lang.StringUtils;

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

    public static String[] splitEquation(String equation) {
        return null;
    }
}
