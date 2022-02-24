package assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class practise {
    //making driver a global variable
    WebDriver driver;
    //Demosite link
    String baseUrl = "https://demoqa.com/login";


    @BeforeMethod
    public void beforeMethod() {

        System.out.println("Starting Test On Chrome Browser First");
        System.out.println("then starting test on firefox");

    }

    //testing chrome browser
    @Test
    public void Chrome_browser() throws InterruptedException {

        //link external input source
        ExcelConf excel = new ExcelConf("/home/knoldus/IdeaProjects/Login/src/test/resources/Data.xlsx");
        //set the chrome path
        System.setProperty("webdriver.chrome.driver", "/home/knoldus/IdeaProjects/Login/driver/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //wait is use for control the selenium flow
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(baseUrl);

        //send emailId from excel_sheet
        driver.findElement(By.cssSelector("input#userName")).sendKeys(excel.getData(0, 1, 0));
        driver.findElement(By.cssSelector("input#password")).sendKeys(excel.getData(0, 1, 1));
        driver.findElement(By.id("login")).click();
    }
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                try{
                    FileHandler.copy(source, new File("Driver"+result.getName()+".png"));
                    System.out.println("Screenshot taken");
                }
                catch (Exception e)
                {
                    System.out.println("Exception while taking screenshot "+e.getMessage());
                }
            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
    }
}
