package bm.petclinic;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class PetClinic {
	public static WebDriver driver;
	@BeforeSuite

	public void setup(){
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver_win32\\chromedriver.exe");
		//driver=new ChromeDriver();
		driver=new FirefoxDriver();
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
	PetClinic p1 =new PetClinic();
	List<String> data=p1.readExcel();
	driver.findElement(By.linkText("FIND OWNERS")).click();
		 driver.findElement(By.linkText("Add Owner")).click();
		  driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='firstName']")).sendKeys(data.get(0));
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='lastName']")).sendKeys(data.get(1));
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='address']")).sendKeys(data.get(2));
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='city']")).sendKeys(data.get(3));
		 driver.findElement(By.xpath("//form[@class='form-horizontal']//input[@name='telephone']")).sendKeys(data.get(4));
		 
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
	
	public List<String> readExcel() {
		List<String> data = new ArrayList<String>();
		File file=new File("/root/.jenkins/jobs/TDM_Data/workspace/Petclinic.xlsx");
		try {
			FileInputStream inputStream = new FileInputStream(file);
			Workbook tdmWorkBook=new XSSFWorkbook(inputStream);
			org.apache.poi.ss.usermodel.Sheet tdmSheet=tdmWorkBook.getSheet("PetClinic");
			int rowCount=tdmSheet.getLastRowNum()-tdmSheet.getFirstRowNum();
			for (int i = 1; i < rowCount+1; i++) {

		        Row row = tdmSheet.getRow(i);
		        for (int j = 0; j < row.getLastCellNum(); j++) {
		        	String field=row.getCell(j).getStringCellValue();
					data.add(field);
			}
			}
			/*for(String data1:data) {
				System.out.println(data1);
			}*/
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
}
