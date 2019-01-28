import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumWebDriver {

	public static void main(String[] args) throws IOException {

		boolean isMobile = false;
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com.tr");

		if (driver.getPageSource().contains("Türkiye")) {

			driver.findElement(By.name("q")).sendKeys("Hipo Labs");
			driver.findElement(By.name("q")).sendKeys(Keys.RETURN);

			if (driver.getPageSource().contains("Hipo - iOS, Android, Web, Hardware Development ")) {
				driver.findElement(By.className("LC20lb")).click();

				org.openqa.selenium.Dimension windows_size = driver.manage().window().getSize();
				int width = windows_size.getWidth();

				if (width <= 800) {
					isMobile = true;
				}
				if (isMobile) {
					WebElement element = driver.findElement(By.id("menuMinimized"));
					Actions actions = new Actions(driver);
					actions.moveToElement(element);
					actions.perform();

					driver.findElement(By.id("hamburger")).click();
					driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
					driver.findElement(By.id("menuMinimizedButtonTeam")).click();

				}

				else {
					WebElement element = driver.findElement(By.id("menuMaximizedButtonTeam"));
					Actions actions = new Actions(driver);
					actions.moveToElement(element);
					actions.perform();
					driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
					driver.findElement(By.id("menuMaximizedButtonTeam")).click();

				}

			}
			driver.manage().timeouts().implicitlyWait(2500, TimeUnit.MILLISECONDS);

			if (driver.getPageSource().contains("APPLY FOR INTERNSHIP")) {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("c:\\screens\\screenshot.png"));
			}

		} else {
			System.out.println("You dont use Google TR");

		}
	}

}
