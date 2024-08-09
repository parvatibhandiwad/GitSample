package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.landingPage;
import rahulshettyacademy.pageobjects.productCatalog;

public class ErrorValidationsTest extends BaseTest
{

	@Test
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
	
		String productName="ZARA COAT 3";
		landingpage.loginApplication("parvati.sb96@gmail.com", "Paru@1996");
		AssertJUnit.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	
		String productName="ZARA COAT 3";
		productCatalog productcatalog=landingpage.loginApplication("parvati.sb96@gmail.com", "Paru@1996");
		List<WebElement> products=productcatalog.getProductList();
		productcatalog.addProductToCart(productName);
		CartPage cartpage=productcatalog.goToCartPage();
		Boolean match=cartpage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
