package Test1;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Main
{
    public static ExtentReports extent;
    public ExtentTest test;

    public static void main(String[] args)
    {
        TestCase1 testCase1 = new TestCase1();
        TestCase2 testCase2 = new TestCase2();
        TestCase3 testCase3 = new TestCase3();

        try {
        	testCase1.addFavoLink();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            testCase2.browseClasses();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            testCase3.bookstore();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}