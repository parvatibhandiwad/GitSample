

	package rahulshettyacademy.tests;

	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;

	import io.github.bonigarcia.wdm.WebDriverManager;
	import rahulshettyacademy.pageobjects.landingPage;

	public class dummy 
	{

		public static void main(String[] args) 
		{
			String productName="ZARA COAT 3";
			//WebDriverManager.chromedriver().setup();
			//WebDriver driver=new ChromeDriver();
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://rahulshettyacademy.com/client");
			
			//landingPage landingpage=new landingPage(driver);
			
			driver.findElement(By.id("userEmail")).sendKeys("parvati.sb96@gmail.com");
			driver.findElement(By.id("userPassword")).sendKeys("Paru@1996");
			driver.findElement(By.id("login")).click();
			
			//Explicit wait
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
			
			List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
			WebElement prod=products.stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			//ng-animating--->loader webelement name
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
			
			List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
			Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
			Assert.assertTrue(match);
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
			driver.findElement(By.cssSelector(".totalRow button")).click();
			
			Actions a=new Actions(driver);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[placeholder='Select Country']")));
			a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
			driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
			//JavascriptExecutor js=(JavascriptExecutor)driver;
			//js.executeScript("window.scrollBy(0,1500)");
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Place Order ']")));
			driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
			
			String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			driver.close();
		}

	}

