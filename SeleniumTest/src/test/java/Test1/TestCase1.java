package Test1;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import junit.framework.Assert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCase1
{
	public String baseUrl = "https://my.northeastern.edu/";
	String driverPath = "/Users/kirito/Desktop/SeleniumTest/bin/chromedriver";
	public WebDriver driver;
	public static ExtentReports extent;
	public ExtentTest test;

	@Test
	public void addFavoLink() throws InterruptedException
	{
		System.out.println("launching chrome browser");
	 	System.setProperty("webdriver.chrome.driver", driverPath);
	 	driver = new ChromeDriver();
	 	driver.get(baseUrl);
	 	Thread.sleep(5000);

		extent = new ExtentReports("/Users/kirito/Desktop/SeleniumTest/bin/Report/Homework4_Report.html", true);
		test = extent.startTest("Scenario1");
	 	driver.manage().window().maximize();
	 	String screenpath1 = captureScreenshot(driver, "browse1");
		test.log(LogStatus.PASS, "Scenario 1 : Open myNEU website", test.addScreenCapture(screenpath1));

	 	//Click the login button
		driver.findElements(By.xpath("//a[@href='/c/portal/login']")).get(1).click();
		Thread.sleep(5000);

		//Enter the user name and the password
		String screenpath2 = captureScreenshot(driver, "browse2");
		test.log(LogStatus.PASS, "Scenario 1 : Login to myNEU", test.addScreenCapture(screenpath2));
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("zhu.mib");
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("ZMBin2018#");
		Thread.sleep(5000);
		String screenpath3 = captureScreenshot(driver, "browse3");
		test.log(LogStatus.PASS, "Scenario 1 : Type in account name and password", test.addScreenCapture(screenpath3));

		//log in the myneu account
		driver.findElement(By.xpath("//button[@name='_eventId_proceed']")).click();
		Thread.sleep(20000);
		String screenpath4 = captureScreenshot(driver, "browse4");
		test.log(LogStatus.PASS, "Scenario 1 : Before clicking the button for favorite", test.addScreenCapture(screenpath4));

		//Find the history element and click the add into favorite button
		String historyText = driver.findElement(By.xpath("//h2[text()='My History']/following-sibling::div[1]/ul[1]/li[1]/a[1]")).getText();
		driver.findElement(By.xpath("//h2[text()='My History']/following-sibling::div[1]/ul[1]/li[1]/i[1]")).click();
		Thread.sleep(5000);
		String screenpath5 = captureScreenshot(driver, "browse5");
		test.log(LogStatus.PASS, "Scenario 1 : After clicking the button for favorite", test.addScreenCapture(screenpath5));

		//Get the text of the last added favorite link
		WebElement favoriteUl = driver.findElement(By.xpath("//h2[text()='My Favorites']/following-sibling::div[1]/ul[1]"));
		List<WebElement> favLinks = favoriteUl.findElements(By.xpath(".//li"));
		int favNum = favLinks.size();
		String favoritesText = favLinks.get(favNum - 1).getText();

		//Check if the text of the history link which just added equals the text of the last added favorite link
		Assert.assertEquals(historyText, favoritesText);
		System.out.println("test finished close the chrome browser");

		extent.endTest(test);
		extent.flush();

		driver.close();
	}
	 
	public static String captureScreenshot (WebDriver driver, String screenshotName)
	{
		try
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = "/Users/kirito/Desktop/SeleniumTest/bin/ScreenShots/AddFavorites/" + screenshotName + ".png";
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