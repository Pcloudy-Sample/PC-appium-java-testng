import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class IOSAppRunner {

    String userName = "anjali.y@opkey.com";
    String accessKey = "r2s7km88f328xrzqz58rtk5c";
    String hubUrl = "https://device.pcloudy.com/appiumcloud/wd/hub";

    AppiumDriver<WebElement> driver;

    @Test
    @Parameters(value = {"manufacturer", "version", "platform"})
    public void iOSApp1(String manufacturer, String version, String platform) throws IOException, InterruptedException {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium:newCommandTimeout", 600);
            capabilities.setCapability("appium:launchTimeout", 90000);
            capabilities.setCapability("appium:platformVersion", version);
            capabilities.setCapability("appium:platformName", platform);
            capabilities.setCapability("appium:acceptAlerts", true);
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("appium:bundleId", "com.pcloudy.TestmunkDemo");
            capabilities.setCapability("appium:pCloudy_ApplicationName", "pCloudy_Appium_Demo-1782572132_Resigned1782583101.ipa");

            HashMap<String, Object> pcloudyOptions = new HashMap<String, Object>();
            pcloudyOptions.put("pCloudy_Username", userName);
            pcloudyOptions.put("pCloudy_ApiKey", accessKey);
            pcloudyOptions.put("pCloudy_DeviceManufacturer", manufacturer);
            pcloudyOptions.put("pCloudy_DevicePlatformVersion", version);
            pcloudyOptions.put("pCloudy_WildNet", false);
            pcloudyOptions.put("pCloudy_EnableVideo", false);
            pcloudyOptions.put("pCloudy_EnablePerformanceData", false);
            pcloudyOptions.put("pCloudy_EnableDeviceLogs", false);
            pcloudyOptions.put("appiumVersion", "3.1.1");
            capabilities.setCapability("pcloudy:options", pcloudyOptions);

            String hub = hubUrl;
            driver = new IOSDriver<>(new URL(hub), capabilities);

            System.out.println("execution started");
            driver.findElement(By.xpath(
                    "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField[1]"))
                    .sendKeys("test@testname.com");
            captureScreenShots();
            Thread.sleep(3000);

            // Enter Password
            driver.findElement(By.xpath(
                    "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeSecureTextField[1]"))
                    .sendKeys("testmunk");
            captureScreenShots();
            Thread.sleep(8000);

            // Click on login button
            driver.findElement(By.xpath(
                    "//XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]"))
                    .click();
            captureScreenShots();

            // Click Skip
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Skip']")).click();
            Thread.sleep(1800);

            // Click Show view button
            driver.findElement(By.xpath("//*[contains(@name,'SHOW ALERT VIEW')]")).click();
            captureScreenShots();

        } catch (AssertionError a) {
            a.printStackTrace();
        }

        driver.quit();
    }

    //Capture screenshot
    public void captureScreenShots() throws IOException {
        String folder_name = "screenshot";
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //Date format for screenshot file name
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        //create dir with given folder name
        new File(folder_name).mkdir();
        //Setting file name
        String file_name = df.format(new Date()) + ".png";
        //copy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
    }
}
