import Utils.Util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolynomialTest {
    private static List<Util.Test> tests;
    private static Polynomial polynomial;
    @BeforeClass
    public static void getTestGroup() {
        InputStream is = PolynomialTest.class.getResourceAsStream("/Test/Polynomial/TestGroup.XML");
        SAXReader saxReader = new SAXReader();
        tests = new ArrayList<Util.Test>();
        Util util = new Util();
        try {
            Document document = saxReader.read(is);
            Element Tests = document.getRootElement();
            Iterator it = Tests.elementIterator();
            String input;
            String validate;
            boolean handleValidate;
            while (it.hasNext()) {
                Element testElement = (Element) it.next();
                input = testElement.elementText("Input");
                validate = testElement.elementText("Validate");
                handleValidate = Boolean.parseBoolean(testElement.elementText("HandleValidate"));
                tests.add(util.new Test(input, validate, handleValidate));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void handlePolynomialTest() {
        System.out.println("开始单元测试");
        for (Util.Test test: tests
             ) {
            System.out.println("开始第 " + tests.indexOf(test) + " 次测试");
            polynomial = new Polynomial();
            boolean result;
            result = polynomial.handlePolynomial(test.getInput()) == test.isHandleValidate();
            try {
                if (result && test.isHandleValidate()) {
                    Assert.assertArrayEquals(test.getValidate(), polynomial.getPolynomial());
                }
            } catch (Exception e) {
                System.out.println("第 " + tests.indexOf(test) + " 次测试不通过");
                continue;
            }
            System.out.println("测试结果为: " + (result ? "通过" : "不通过"));
        }
    }
}
