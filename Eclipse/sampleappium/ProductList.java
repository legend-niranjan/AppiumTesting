package sampleappium;

import java.net.MalformedURLException; 


import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ProductList {
	public static void main(String[] args) throws InterruptedException {
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
	            
	             List<WebElement> products=driver.findElements(MobileBy.className("android.widget.TextView"));
	             if(!products.isEmpty()) {
	            	 System.out.println("Product List:");
		             for (WebElement product : products) {
		                 System.out.println(product.getText());
		             }
	            	 products.get(0).click();
	            	 System.out.println("Successfully clicked on first product and navigated to detail");
	             }else {
	            	 System.out.println("failed to click, not navigated to detail");
	             }
	        	   driver.quit();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }

	}

}
