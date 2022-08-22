package AmazonSearchTest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProductSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("Webdriver.Chrome.Driver", "Chromedriver");
		WebDriver driver = new ChromeDriver();
		
		//Launch Amazon.in
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        
		//Search for Samsung
		WebElement SearchText = driver.findElement(By.id("twotabsearchtextbox"));
		SearchText.sendKeys("samsung");

		 //Click Search Button
		WebElement SearchButton = driver.findElement(By.id("nav-search-submit-button"));
		SearchButton.click();

		//Print Product Details and Price
		
		List<WebElement> Productname = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		List<WebElement> ProductPrice = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		System.out.println("Total Numbers of Products found " +Productname.size());
		for(int i=0;i<Productname.size();i++) {
			System.out.println(Productname.get(i).getText()+" "+ ProductPrice.get(i).getText());
		}

		//Getting Parent Window Handler
		String ParentWinH= driver.getWindowHandle();
		String ExpectedValue = Productname.get(0).getText();

		// Click on First Product Link
		Productname.get(0).click();

		//Switching to 2nd window
		Set<String> AllWindowHandler =	driver.getWindowHandles();
		for(String win : AllWindowHandler ) {
			System.out.println(win);

			if(!win.equals(ParentWinH)) {
				driver.switchTo().window(win);

			}
		}

		WebElement title = driver.findElement(By.id("productTitle"));

		String str = title.getText();

		//Validation for parent and child windows
		if(str.equals(ExpectedValue)) {
			System.out.println("TC passed : Both Titles are matching");
		}else {
			System.out.println("TC Failed : Title  not matching");
		}
		driver.quit();

	}

}