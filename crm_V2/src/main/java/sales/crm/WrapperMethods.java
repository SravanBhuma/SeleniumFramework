package sales.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WrapperMethods
{
	static WebDriverWait wait;
	static Actions action;
	static int waitTime=10;
	public static void click(WebDriver driver,By locator)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		driver.findElement(locator).click();
	}
	
	public static void sendKeys(WebDriver driver,By locator,String keysToSend)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		driver.findElement(locator).sendKeys(keysToSend);
	}
	
	public static void select(WebDriver driver, By locator, String KeysToSelect)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		Select options=new Select(driver.findElement(locator));
		options.selectByVisibleText(KeysToSelect);
	}

	public static void click(WebDriver driver, WebElement element) 
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		element.click();
	}
	
	public static String getText(WebDriver driver, By locator)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		return driver.findElement(locator).getText();
	}
	
	public static String getText(WebDriver driver, WebElement element)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		return element.getText();
	}
	
	public static void moveToElement(WebDriver driver,By locator)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		action=new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
	}
	
	public static void moveToElement(WebDriver driver,WebElement element)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		action=new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}
	
	public static void enter(WebDriver driver,By locator)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		action=new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}
	
	public static Boolean isDisplayed(WebDriver driver,By locator)
	{
		wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(CommonFieldsPage.loading));
		return driver.findElement(locator).isDisplayed();
	}
	
}
