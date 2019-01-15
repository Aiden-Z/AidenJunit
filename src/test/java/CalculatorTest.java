import Utils.Util;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CalculatorTest {
    private static List<Util.CalculatorTest> addTests;
    private static List<Util.CalculatorTest> multiTests;
    private static Calculator calculator;
    @BeforeClass
    public static void getTestGroup() {
        calculator = new Calculator();
        InputStream is = PolynomialTest.class.getResourceAsStream("/Test/Calculator/TestGroup.XML");
        SAXReader saxReader = new SAXReader();
        addTests = new ArrayList<Util.CalculatorTest>();
        multiTests = new ArrayList<Util.CalculatorTest>();
        Util util = new Util();
        try {
            Document document = saxReader.read(is);
            Element adds = document.getRootElement().element("Add");
            Element multis = document.getRootElement().element("Multi");
            Iterator addIt = adds.elementIterator();
            Iterator multiIt = multis.elementIterator();
            String input1;
            String input2;
            String validate;
            while (addIt.hasNext()) {
                Element testElement = (Element) addIt.next();
                input1 = testElement.elementText("Input1");
                input2 = testElement.elementText("Input2");
                validate = testElement.elementText("Validate");
                addTests.add(util.new CalculatorTest(input1, input2, validate));
            }
            while (multiIt.hasNext()) {
                Element testElement = (Element) multiIt.next();
                input1 = testElement.elementText("Input1");
                input2 = testElement.elementText("Input2");
                validate = testElement.elementText("Validate");
                multiTests.add(util.new CalculatorTest(input1, input2, validate));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void polynomialAddTest() {
        System.out.println("开始单元测试");
        for (Util.CalculatorTest test: addTests
        ) {
            System.out.println("开始第 " + addTests.indexOf(test) + " 次测试");
            try {
                Assert.assertArrayEquals(test.getValidate(), calculator.polynomialAdd(test.getLh(), test.getRh()));
            } catch (Exception e) {
                System.out.println("第 " + addTests.indexOf(test) + " 次测试不通过");
                continue;
            }
            System.out.println("测试结果为: " + "通过");
        }
    }

    @Test
    public void polynomialMultiTest() {
        System.out.println("开始单元测试");
        for (Util.CalculatorTest test: multiTests
        ) {
            System.out.println("开始第 " + multiTests.indexOf(test) + " 次测试");
            try {
                Assert.assertArrayEquals(test.getValidate(), calculator.polynomialMulti(test.getLh(), test.getRh()));
            } catch (Exception e) {
                System.out.println("第 " + multiTests.indexOf(test) + " 次测试不通过");
                continue;
            }
            System.out.println("测试结果为: " + "通过");
        }
    }
}
