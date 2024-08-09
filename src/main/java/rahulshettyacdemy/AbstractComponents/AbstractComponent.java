package rahulshettyacdemy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent 
{
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	
	public void waitforElementToAppear(By FindBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitforWebElementToAppear(WebElement FindBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public OrderPage goToOdersPage()
	{
		//waitforElementToAppear(cartHeader);
		orderHeader.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
	
	public CartPage goToCartPage()
	{
		//waitforElementToAppear(cartHeader);
		cartHeader.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	
	public void waitforElementToDisapper(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitforElementToBeClickable(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void scrollElementIntoView(By locator)
	{
		WebElement element=driver.findElement(locator);
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
	}
	
}
