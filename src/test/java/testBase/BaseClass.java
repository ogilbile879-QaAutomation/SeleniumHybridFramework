package testBase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;//Log4j
import org.apache.logging.log4j.Logger;//Log4J
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	public WebDriver driver;
	public Logger logger;//Log4j
	
	@BeforeClass
	public void setup() {
		
		logger=LogManager.getLogger(this.getClass());
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://tutorialsninja.com/demo/index.php");
		driver.manage().window().maximize();
		
		
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
		
	}
	
	public String randomString() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
	    LocalDateTime now = LocalDateTime.now();
	    return now.format(formatter);
	}

}
