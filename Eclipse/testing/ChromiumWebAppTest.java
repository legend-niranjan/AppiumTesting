package testing;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
 
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
 
public class ChromiumWebAppTest {
 
    public static void main(String[] args) throws MalformedURLException {
		
    	DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "vivo V2334");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
   
        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
 
 
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 
            // Open the web application
            driver.get("https://www.apple.com/in");
 
            // Find the 'Learn More' button by its XPath or another locator
            WebElement learnMoreButton =  driver.findElement(By.xpath("//a[contains(text(),'Learn more')]"));
 
            // Click on the 'Learn More' button
            learnMoreButton.click();
 
 
            // Print the page title
            System.out.println("Page Title: " + driver.getTitle());
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
 