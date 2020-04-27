package Test1;
import java.io.File;
import java.io.IOException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase2
{
    public ExtentTest test;

    @Test
    public void browseClasses() throws InterruptedException
    {
        System.setProperty("webdriver.chrome.driver", "/Users/kirito/Desktop/SeleniumTest/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

//        extent = new ExtentReports("/Users/kirito/Desktop/SeleniumTest/bin/Report/Homework4_Scenario3_Report.html", true);
        test = TestCase1.extent.startTest("Scenario3");
        driver.get("https://my.northeastern.edu/c/portal/login");
        String screenpath1 = captureScreenshot(driver, "browse1");
        test.log(LogStatus.PASS, "Scenario 3 : Login to myNEU", test.addScreenCapture(screenpath1));

        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("zhu.mib");
        driver.findElement(By.id("password")).sendKeys("ZMBin2018#");
        String screenpath2 = captureScreenshot(driver, "browse2");
        test.log(LogStatus.PASS, "Scenario 3 : Type in account name and password", test.addScreenCapture(screenpath2));

        driver.findElements(By.className("SSO__elementWrapper")).get(2).click();
        Thread.sleep(2000);

        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//*[@id=\"auth_methods\"]/fieldset/div[1]/button")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(15000);
        String screenpath3 = captureScreenshot(driver, "browse3");
        test.log(LogStatus.PASS, "Scenario 3 : Login at main panel", test.addScreenCapture(screenpath3));

        driver.findElement(By.xpath("//*[@id=\"app-search-list\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"app-search-list\"]/div/input")).sendKeys("Course Registration");
        Thread.sleep(2000);
        String screenpath4 = captureScreenshot(driver, "browse4");
        test.log(LogStatus.PASS, "Scenario 3 : Type in course registration at search part", test.addScreenCapture(screenpath4));

        driver.findElement(By.xpath("//*[@id=\"app-search-list\"]/div/div/div/div[1]/div/div[2]/a")).click();
        Thread.sleep(2000);
        String screenpath5 = captureScreenshot(driver, "browse5");
        test.log(LogStatus.PASS, "Scenario 3 : Click on the course registration", test.addScreenCapture(screenpath5));
    
        String newWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles())
        {
            newWindow = handle;
        }
        driver.switchTo().window(newWindow);
      
        driver.findElement(By.xpath("//*[@id=\"classSearch\"]/a")).click();
        Thread.sleep(2000);
      
        WebElement termdropdown=driver.findElement(By.id("s2id_txt_term"));
        termdropdown.click();
        Thread.sleep(2000);
  
        driver.findElement(By.xpath("//*[@id=\"select2-results-1\"]/li[5]")).click();
        String screenpath6 = captureScreenshot(driver, "browse6");
        test.log(LogStatus.PASS, "Scenario 3 : Select Summer Full 2020", test.addScreenCapture(screenpath6));

        driver.findElement(By.id("term-go")).click();
        Thread.sleep(2000);
        String screenpath7 = captureScreenshot(driver, "browse7");
        test.log(LogStatus.PASS, "Scenario 3 : Before selecting IS", test.addScreenCapture(screenpath7));

        WebElement subDropdown = driver.findElement(By.xpath("//*[@id=\"s2id_txt_subject\"]/ul"));
        subDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[11]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[21]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[31]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li[38]")).click();
        Thread.sleep(2000);
        String screenpath8 = captureScreenshot(driver, "browse8");
        test.log(LogStatus.PASS, "Scenario 3 : After selecting IS", test.addScreenCapture(screenpath8));

        driver.findElement(By.id("search-go")).click();
        Thread.sleep(5000);
        String screenpath9 = captureScreenshot(driver, "browse9");
        test.log(LogStatus.PASS, "Scenario 3 : Show the class information", test.addScreenCapture(screenpath9));

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
            String dest = "/Users/kirito/Desktop/SeleniumTest/bin/ScreenShots/BrowseClasses/" + screenshotName + ".png";
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