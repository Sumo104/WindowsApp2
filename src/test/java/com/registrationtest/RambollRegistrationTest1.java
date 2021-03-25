package com.registrationtest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;

public class RambollRegistrationTest1 {
	
	public static WindowsDriver driver=null;
	
	
	@BeforeMethod
	public void setUp() throws IOException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("app", "C:\\Users\\Sumit\\Downloads\\Ramboll_Testing\\Ramboll_Testing\\bin\\Debug\\Ramboll_Testing.exe");
		cap.setCapability("platformName", "windows");
		cap.setCapability("deviceName", "windowsPC");
		
		
		driver= new WindowsDriver(new URL("http://127.0.0.1:4723"),cap);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void cleanUp() {
		driver.quit();
	}
	
	@Test
	public void completeRegisteration() {
		driver.findElementByAccessibilityId("textBoxFirstName").sendKeys("Sumit");
		driver.findElementByAccessibilityId("textBoxLastName").sendKeys("Ranjan");
		driver.findElementByAccessibilityId("textBoxEmail").sendKeys("ranjan.sumit8@gmail.com");
		driver.findElementByAccessibilityId("passwordBox1").sendKeys("password123");
		driver.findElementByAccessibilityId("passwordBoxConfirm").sendKeys("password123");
		driver.findElementByAccessibilityId("textBoxMobileNo").sendKeys("8050057875");
		driver.findElementByAccessibilityId("textBoxAddress").sendKeys("My Address");
		driver.findElementByAccessibilityId("radioMale").click();
		driver.findElementByAccessibilityId("Submit").click();
	}
	
	
	@Test
	public void validationCheck() {
		driver.findElementByAccessibilityId("Submit").click();
		String message = driver.findElementByAccessibilityId("errormessage").getText();
//		System.out.println(message);
		String msg= "Enter an email.";
//		System.out.println(msg);
		assert  msg == message: "Email Id not provided";	
	}
	
	@Test
	public void emailFormat() {
		driver.findElementByAccessibilityId("textBoxEmail").sendKeys("ranjan.su");
		driver.findElementByAccessibilityId("Submit").click();
		String email=driver.findElementByAccessibilityId("errormessage").getText();
		if(email!="") {
			assert  "Enter a valid email." == email: "Provide valid Email address";			
		}
	
	}
	
	@Test
	public void resetBtnChk() {
		WebElement  radioBtnFemale=driver.findElementByAccessibilityId("radioFemale");
		radioBtnFemale.click();
		driver.findElementByAccessibilityId("button2").click();
		
		assert radioBtnFemale.isSelected()==false:"Reset button is not working";
//		if (radioBtnFemale.isSelected()) {
//			System.out.println("Reset button is not working");
//		}
		
	}
	@Test
	public void passwordCheck() {
		driver.findElementByAccessibilityId("textBoxEmail").sendKeys("ranjan.sumit8@gmail.com");
		driver.findElementByAccessibilityId("passwordBox1").sendKeys("password123");
		driver.findElementByAccessibilityId("passwordBoxConfirm").sendKeys("password123");
		driver.findElementByAccessibilityId("Submit").click();
		try {
			String errormessage= driver.findElementByName("errormessage").getText();
			if(errormessage!="") { 
				System.out.println("Password Mismatch");
				}
		}
		catch(Exception e){
			System.out.println("Password Match");
			
		}
		
	}
	
	

}
