import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class stepDefination {
	
	public RemoteWebDriver driver;
	public WebDriverWait wait;
	
	@Override
	public RemoteWebDriver startApp(String url) {
		return startApp("chrome", url);
	}

	@Override
	public RemoteWebDriver startApp(String browser, String url) {
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if(browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if(browser.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		} catch (Exception e) {
			reportStep("The Browser Could not be Launched. Hence Failed", "fail");
			throw new RuntimeException();
		} 
		return driver;

	}
	
	@Given I am :userName logging with :password credential
	public void login(String userName, String password) 
	{
		this.startApp("https://sanity-stage-csm.symplicity.com/manager");
		driver.findElement(By.xpath("//input[@class='input-text' and @name='username']")).sendKeys("vramanathan@sympdebug");
		driver.findElement(By.xpath("//input[@class='input-password' and @name='password']")).sendKeys("Vkyking@123a");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Sign In']")).click();
	}

}
