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

public class VehicleInsuranceAppTest {

	public static void main(String[] args) {
		
		try {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "vivo V2334");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
			capabilities.setCapability("appPackage", "com.example.vehicleinsuranceapplication");
			capabilities.setCapability("appActivity", ".SnapActivity");
			
			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			WebElement loginButton = driver.findElement(By.id("btnlogin"));
			loginButton.click();
			
			WebElement sinupText = driver.findElement(By.id("sinupText"));
			sinupText.click();
			
			WebElement registerTitle = driver.findElement(By.xpath("//android.widget.EditText[@hint='Full Name']"));
			
			if(registerTitle.getText().equals("Full Name")) {
				System.out.println("Successfully navigated to Register Page");
			}
			
			WebElement fullname = driver.findElement(By.xpath("//android.widget.EditText[@hint='Full Name']"));
			WebElement registerUsername = driver.findElement(By.xpath("//android.widget.EditText[@hint='set username']"));
			WebElement registerEmail = driver.findElement(By.xpath("//android.widget.EditText[@hint='Email Address']"));
			WebElement registerPassword = driver.findElement(By.xpath("//android.widget.EditText[@hint='set password']"));
			WebElement registerButton = driver.findElement(By.id("register"));
			
			fullname.sendKeys("Niranjan Suryawanshi");
			registerUsername.sendKeys("Niranjan");
			registerEmail.sendKeys("niranjansurywanshi318@gmail.com");
			registerPassword.sendKeys("Niranjan123");
			registerButton.click();
			
			System.out.println("Registration Successfull!");
			
			WebElement loginUsernameField = driver.findElement(By.xpath("//android.widget.EditText[@hint='username']"));
			WebElement loginPasswordField = driver.findElement(By.xpath("//android.widget.EditText[@hint='password']"));
			WebElement loginButtonElement = driver.findElement(By.id("Login"));
			
			loginUsernameField.sendKeys("Niranjan");
			loginPasswordField.sendKeys("Niranjan123");
			
			loginButtonElement.click();
			
			WebElement startClaimButtonElement = driver.findElement(By.id("cardView"));
			
			if(startClaimButtonElement.isDisplayed()) {
				System.out.println("Login successfull!");
				System.out.println("Navigated to the DashboardActivity");
			}
			driver.quit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		}

}
