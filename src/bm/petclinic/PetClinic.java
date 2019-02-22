package bm.petclinic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;
public class PetClinic {
	public static WebDriver driver;
	@BeforeSuite

	public void setup(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		//driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.get("http://54.214.95.28:8081/petclinic/");
		//String url=driver.findElement(By.xpath("//body")).getText();
		//driver.get(url);
		
		//driver.get(url);
		
	}

	@Test
	public void getTitle(){
		String title=driver.getTitle();
		if(title.equals("PetClinic :: a Spring Framework demonstration")){
			System.out.println("Application is working properly");
		}
		
		else{
			System.out.println("Issue with application ");
			Assert.fail();
		}
		
		//driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[3]/a")).click();
		
	}
	
	@Test
	public void addOwner(){
	driver.findElement(By.linkText("FIND OWNERS")).click();
		 driver.findElement(By.linkText("Add Owner")).click();
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='firstName']")).sendKeys("Demo");
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='lastName']")).sendKeys("Test");
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='address']")).sendKeys("Dummy address");
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='city']")).sendKeys("Mumbai");
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='telephone']")).sendKeys("1234567890");
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//button[@class='btn btn-default']")).click();
		 
	}
	
	@Test
	public void findOwner(){
	driver.findElement(By.linkText("FIND OWNERS")).click();
		 driver.findElement(By.xpath("//form[@id='search-owner-form']//input[@name='lastName']")).sendKeys("Test");
		 driver.findElement(By.xpath("//form[@id='search-owner-form']//button[@class='btn btn-default']")).click();
	}
	
	@Test
	public void findVet(){
	 driver.findElement(By.linkText("VETERINARIANS")).click();
		 List<WebElement> list=driver.findElements(By.xpath("//table[@class='table table-striped']//td"));
		 System.out.println("Available Vets with Specialities");
		 for(WebElement iterate:list) {
			 System.out.println(iterate.getText());
		 }
	}
	
}
