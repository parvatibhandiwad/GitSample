package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.landingPage;
import rahulshettyacademy.pageobjects.productCatalog;

public class SubmitOrderTest extends BaseTest
{
	String productName="ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		productCatalog productcatalog=landingpage.loginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> products=productcatalog.getProductList();
		productcatalog.addProductToCart(input.get("product"));
		CartPage cartpage=productcatalog.goToCartPage();
		
		Boolean match=cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckOut();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		String confirmMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		productCatalog productcatalog=landingpage.loginApplication("parvati.sb96@gmail.com", "Paru@1996");
		OrderPage orderpage=productcatalog.goToOdersPage();
		Assert.assertTrue(orderpage.verifyOderDisplay(productName));	
	}
	
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")
				+"//src//test//java//rehulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}



/*HashMap<String,String> map=new HashMap<String,String>();
map.put("email", "parvati.sb96@gmail.com");
map.put("password", "Paru@1996");
map.put("product", "ZARA COAT 3");

HashMap<String,String> map1=new HashMap<String,String>();
map1.put("email", "parvatibhandiwad123@gmail.com");
map1.put("password", "Paru@1996");
map1.put("product", "ADIDAS ORIGINAL");*/
