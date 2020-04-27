package Test1;
import java.io.File;
import java.io.IOException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase3
{
	public static ExtentReports extent;
	public ExtentTest test;

	@Test
	public void bookstore() throws InterruptedException
	{
		//Set the installation path of chrome
		System.setProperty("webdriver.chrome.driver", "/Users/kirito/Desktop/SeleniumTest/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		//Set size of browser
		driver.get("https://northeastern.bncollege.com/shop/northeastern/products/apparel");

		// Call screenshot function
//		extent = new ExtentReports("/Users/kirito/Desktop/SeleniumTest/bin/Report/Homework4_Scenario4_Report.html", true);
		test = TestCase1.extent.startTest("Scenario4");
		String screenpath1 = captureScreenshot(driver, "book1");
		test.log(LogStatus.PASS, "Scenario 4 : Login to the bookstore", test.addScreenCapture(screenpath1));

		// Dismiss the notice
		driver.findElement(By.xpath("//*[@id=\"flashAnnouncement\"]/div/div/ul/li[2]/input")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/header/section/div[2]/div[2]/ul[2]/li[1]/a[1]/img")).click();
		String screenpath2 = captureScreenshot(driver, "book2");
		test.log(LogStatus.PASS, "Scenario 4 : Select a jacket style", test.addScreenCapture(screenpath2));
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"productSizeList\"]/ul/li[1]")).click();
		String screenpath3 = captureScreenshot(driver, "book3");
		test.log(LogStatus.PASS, "Scenario 4 : Select a jacket size", test.addScreenCapture(screenpath3));
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"addToCartId\"]")).click();
		Thread.sleep(1000);
		String screenpath4 = captureScreenshot(driver, "book4");
		test.log(LogStatus.PASS, "Scenario 4 : Continue to add the jacket to the cart", test.addScreenCapture(screenpath4));
		Thread.sleep(1000);

		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button[2]/span")).click();
		String screenpath5 = captureScreenshot(driver, "book5");
		test.log(LogStatus.PASS, "Scenario 4 : Look at the shopping cart", test.addScreenCapture(screenpath5));

		TestCase1.extent.endTest(test);
		TestCase1.extent.flush();
		driver.quit();
	}

	public static String captureScreenshot (WebDriver driver, String screenshotName)
	{
		try
		{
            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = "/Users/kirito/Desktop/SeleniumTest/bin/ScreenShots/BookStore/" + screenshotName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return dest;
		}

        catch (IOException e)
		{
			return e.getMessage();
		}
	}
}