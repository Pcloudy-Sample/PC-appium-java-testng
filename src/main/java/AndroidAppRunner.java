import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidAppRunner {

    AppiumDriver<WebElement> driver;
    String folder_name;
    DateFormat df;

    String userName = "anjali.y@opkey.com";
    String apiKey = "r2s7km88f328xrzqz58rtk5c";
    String hubUrl = "https://device.pcloudy.com/appiumcloud/wd/hub";

    @BeforeMethod
    public void prepareTest() throws IOException, InterruptedException {
        // No-op: actual capabilities are set in the test method
    }

    @Test
    @Parameters({"manufacturer", "version", "platform"})
    public void AndroidApp1(String manufacturer, String version, String platform) throws IOException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:newCommandTimeout", 600);
        capabilities.setCapability("appium:launchTimeout", 90000);
        capabilities.setCapability("appium:platformVersion", version);
        capabilities.setCapability("appium:platformName", platform);
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:appPackage", "com.pcloudy.appiumdemo");
        capabilities.setCapability("appium:appActivity", "com.ba.mobile.LaunchActivity");
        capabilities.setCapability("appium:pCloudy_ApplicationName", "pCloudyAppiumDemo.apk");

        HashMap<String, Object> pcloudyOptions = new HashMap<String, Object>();
        pcloudyOptions.put("pCloudy_Username", userName);
        pcloudyOptions.put("pCloudy_ApiKey", apiKey);
        pcloudyOptions.put("pCloudy_DeviceManufacturer", manufacturer);
        pcloudyOptions.put("pCloudy_DevicePlatformVersion", version);
        pcloudyOptions.put("pCloudy_WildNet", false);
        pcloudyOptions.put("pCloudy_EnableVideo", true);
        pcloudyOptions.put("pCloudy_EnablePerformanceData", false);
        pcloudyOptions.put("pCloudy_EnableDeviceLogs", false);
        pcloudyOptions.put("appiumVersion", "3.1.1");
        capabilities.setCapability("pcloudy:options", pcloudyOptions);

        driver = new AndroidDriver(new URL(hubUrl), capabilities);

        driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.pcloudy.appiumdemo:id/accept']")).click();
        captureScreenShots();

        driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.pcloudy.appiumdemo:id/flightButton']")).click();
        captureScreenShots();

        driver.findElement(By.xpath("//android.widget.Spinner[@resource-id='com.pcloudy.appiumdemo:id/spinnerfrom']")).click();
        captureScreenShots();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Bangalore, India (BLR)']")).click();
        captureScreenShots();

        driver.findElement(By.xpath("//android.widget.Spinner[@resource-id='com.pcloudy.appiumdemo:id/spinnerto']")).click();
        captureScreenShots();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Pune, India (PNQ)']")).click();
        captureScreenShots();

        driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id='com.pcloudy.appiumdemo:id/singleTrip']")).click();

        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.pcloudy.appiumdemo:id/txtdepart']")).click();
        captureScreenShots();
        driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1' and @text='OK']")).click();
        captureScreenShots();

        driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.pcloudy.appiumdemo:id/searchFlights']")).click();
        captureScreenShots();
    }

    @AfterMethod
    public void endTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void captureScreenShots() throws IOException {
        folder_name = "screenshot";
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        new File(folder_name).mkdir();
        String file_name = df.format(new Date()) + ".png";
        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
    }
}
