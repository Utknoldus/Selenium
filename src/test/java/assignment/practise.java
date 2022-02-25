package assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class practise {
    //making driver a global variable

    WebDriver driver;
    //Demosite link
    String baseUrl = "https://demoqa.com/login";


    //testing chrome browser

    @Test
    public void Chrome_browser() throws InterruptedException {

        //link external input source
        ExcelConf excel = new ExcelConf("src/test/resources/Data.xlsx");
        //set the chrome path
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //wait is use for control the selenium flow
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //send emailId from excel_sheet
        driver.findElement(By.cssSelector("input#userName")).sendKeys(excel.getData(0, 0, 0));
        driver.findElement(By.cssSelector("input#password")).sendKeys(excel.getData(0, 0, 1));
        driver.findElement(By.id("login")).click();
        Reporter.log("Done");
        driver.quit();
    }

    @Test
    public void Firefox_browser() throws InterruptedException {
        //link external input source
        ExcelConf excel = new ExcelConf("src/test/resources/Data.xlsx");
        //set the chrome path

        System.setProperty("webdriver.gecko.driver", "driver/geckodriver");

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        //send emailId from excel_sheet
        driver.findElement(By.cssSelector("input#userName")).sendKeys(excel.getData(0, 0, 0));
        driver.findElement(By.cssSelector("input#password")).sendKeys(excel.getData(0, 0, 1));
        driver.findElement(By.id("login")).click();
        Reporter.log("Done");
    }

    @Test
    public void headless() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        // Maximize browser
        driver.manage().window().maximize();

        //implict Timeout
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(baseUrl);
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                try {
                    FileHandler.copy(source, new File("Driver" + result.getName() + ".png"));
                    Reporter.log("Screenshot taken");
                } catch (Exception e) {
                    Reporter.log("Exception while taking screenshot " + e.getMessage());
                }
            } catch (Exception e) {
                Reporter.log("Exception while taking screenshot " + e.getMessage());
            }
        }

    }
}

