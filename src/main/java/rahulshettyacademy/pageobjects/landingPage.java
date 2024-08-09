package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacdemy.AbstractComponents.AbstractComponent;

public class landingPage extends AbstractComponent
{
	WebDriver driver;
	
	public landingPage(WebDriver driver) 
	{
		super(driver);
		//initialization
		this.driver=driver;
		//PageFactory
		PageFactory.initElements(driver, this);
	}	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	
	public productCatalog loginApplication(String email,String pwd)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();	
		productCatalog productcatalog=new productCatalog(driver);
		return productcatalog;
	}
	
	public String getErrorMessage()
	{
		waitforWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	

	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}