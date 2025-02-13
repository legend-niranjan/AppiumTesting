package sampleappium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DetailPage {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "vivo V2334");
	        caps.setCapability("appPackage", "com.example.sampleappium");
	        caps.setCapability("appActivity", "com.example.sampleappium.LoginActivity");
	        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
	        
	        try {
	             AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
	             Thread.sleep(3000);
	             driver.findElement(By.id("username")).sendKeys("Niranjan");
	             driver.findElement(By.id("password")).sendKeys("1234");
	             driver.findElement(By.id("button")).click();
	             Thread.sleep(2000);
	             
	             List<WebElement> products=driver.findElements(MobileBy.className("android.widget.TextView"));
	             if(!products.isEmpty()) {
	            	 products.get(0).click();
	             }
	             
	             Thread.sleep(2000);
	             WebElement name= driver.findElement(By.id("name"));
	             System.out.println("Product name: "+name.getText());
	             WebElement price= driver.findElement(By.id("price"));
	             System.out.println("Product price: "+price.getText());
	             WebElement detail= driver.findElement(By.id("detail"));
	             System.out.println("Product detail: "+detail.getText());
	             
	        	   driver.quit();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }
	}

}
