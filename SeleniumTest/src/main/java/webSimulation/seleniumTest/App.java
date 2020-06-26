package webSimulation.seleniumTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver","geckodriver");
		WebDriver driver = new FirefoxDriver();

		// Getting the initial page
		driver.get("http://techfios.com/test/101/");
		String category = "TestCategoryOne";
		WebElement advanceControls = driver.findElement(By.className("advance-controls"));
		WebElement field = advanceControls.findElement(By.name("data"));
		field.sendKeys(category);
		advanceControls.findElement(By.name("submit")).click();
		WebElement categoriesDiv = driver.findElement(By.id("todos-content"));
		List<WebElement> elements = categoriesDiv.findElements(By.tagName("li"));
		boolean exists = false;
		for(WebElement element: elements) {
			
			if(element.getText().contains(category)) {
				exists = true;
				break;
			}
			
		}
		System.out.println("\n\nValidating Add Category: "+exists);
		
		//
		advanceControls = driver.findElement(By.className("advance-controls"));
		field = advanceControls.findElement(By.name("data"));
		field.sendKeys(category);
		advanceControls.findElement(By.name("submit")).click();
		System.out.println("\n\nValidating Same Category: "+
				driver.getCurrentUrl().equals("http://techfios.com/test/101/todo.php"));
		
		//
		driver.get("http://techfios.com/test/101/");
		advanceControls = driver.findElement(By.className("advance-controls"));
		List<String> months = new ArrayList<>(Arrays.asList(new String[] 
				{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"}));
		List<WebElement> monthElements = advanceControls.findElement(By.id("extra"))
				.findElement(By.name("due_month")).findElements(By.tagName("option"));
		for(WebElement element: monthElements) {
			
			if(months.contains(element.getText().trim())) {
				months.remove(element.getText().trim());
			}
			
		}
		System.out.println("\n\nValidating All Month Names: "+months.isEmpty());
		
	}
}
