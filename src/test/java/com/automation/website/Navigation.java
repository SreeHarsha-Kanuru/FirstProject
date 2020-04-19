package com.automation.website;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Navigation {
	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void browser( String browser) {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url = "http://automationpractice.com/";
		driver.get(url);
		
		System.out.println("Driver started ruuning in chrome/firefox");

	}

	@Parameters({ "username", "password" })
	@Test(groups = { "LogIn", "smokeTest" })
	public void checkout(String username, String password) {
		driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		WebElement name = driver.findElement(By.xpath("//input[@id='email']"));
		name.sendKeys(username);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passwd']")));
		WebElement pswd = driver.findElement(By.xpath("//input[@id='passwd']"));
		pswd.sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='columns']//p//span[1]")));
		WebElement LogIn = driver.findElement(By.xpath("//div[@id='columns']//p//span[1]"));
		LogIn.click();
		driver.findElement(By.xpath("//input[@id='search_query_top']")).sendKeys("Dress");
		driver.findElement(By.xpath("//button[@name='submit_search']")).click();

		driver.findElement(By.partialLinkText("Printed Summer Dress")).click();

		driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
		wait1.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Proceed to checkout')]")));
		WebElement CheckOut = driver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]"));
		CheckOut.click();
		wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Proceed to checkout")));
		WebElement CheckOut1 = driver.findElement(By.partialLinkText("Proceed to checkout"));
		CheckOut1.click();

		wait1.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")));
		WebElement CheckOut2 = driver.findElement(
				By.xpath("//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]"));
		CheckOut2.click();
		//wait1.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@id='cgv']"))));
		WebElement CheckBox = driver.findElement(By.xpath("//input[@id='cgv']"));
		CheckBox.click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")));
		WebElement CheckOutFinal = driver.findElement(
				By.xpath("//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]"));
		CheckOutFinal.click();
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}

}
