package SeleniumTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MapMyRunTests {
	private final String USER_NAME="ImaRunner@email.com";
	private final String PASSWORD="NotARealpassword";
	
	@Before
	public void setup(){
		driver = new FirefoxDriver();
		performMapMyRunLogin();		
	}
	
	@After
	public void teardown(){
		driver.get("https://www.mapmyrun.com/auth/logout");
		driver.quit();
	}
	
	WebDriver driver;
		
	@Test
	public void testMapMyRunAppForWorkoutDistanceMonthlyDistance(){
				
		WebDriverWait waitUntilThePageIsDoneLoading = new WebDriverWait(driver,20);
		waitUntilThePageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("feed_end")));
				
		driver.get("https://www.mapmyrun.com/workouts");
		
		//This is what I'm testing for:  Distance=26.1
		//Already setup as a precondition.
		waitUntilThePageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("month_total_miles")));
		Assert.assertEquals("26.1", driver.findElement(By.id("month_total_miles")).getText());
	}
	
		
	@Test
	public void testMapMyRunAppForWorkoutDurationMonthlyTotal(){
			
		WebDriverWait waitUntilTheMainPageIsDoneLoading = new WebDriverWait(driver,20);
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("feed_end")));
					
		driver.get("https://www.mapmyrun.com/workouts");
			
		//This is what I'm testing for:  Duration=3.65hrs
		//Already setup as a precondition.
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("month_total_hours")));
		Assert.assertEquals("3.65", driver.findElement(By.id("month_total_hours")).getText());
	}

		
	@Test
	public void testMapMyRunAppForWorkoutCaloriesMonthlyTotal(){
			
		WebDriverWait waitUntilTheMainPageIsDoneLoading = new WebDriverWait(driver,20);
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("feed_end")));
					
		driver.get("https://www.mapmyrun.com/workouts");
				
		//This is what I'm testing for:  Calories=2650
		//Already setup as a precondition.
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("month_total_calories")));
		Assert.assertEquals("2,650", driver.findElement(By.id("month_total_calories")).getText());
	}
		
		
	@Test
	public void testMapMyRunAppForWorkoutWorkoutsMonthlyTotal(){
			
		WebDriverWait waitUntilTheMainPageIsDoneLoading = new WebDriverWait(driver,20);
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("feed_end")));
					
		driver.get("https://www.mapmyrun.com/workouts");
		
		//This is what I'm testing for:  Workouts=4
		//Already setup as a precondition.
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("month_total_workouts")));
		Assert.assertEquals("4", driver.findElement(By.id("month_total_workouts")).getText());
	}

	
	@Test
	public void testMapMyRunAppUpdateProfileHometown(){
				
		WebDriverWait waitUntilTheMainPageIsDoneLoading = new WebDriverWait(driver,20);
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("feed_end")));
				
		driver.findElement(By.id("my_profile_button")).click();
				
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_hometown")));
		
		WebElement clearHometownBox = driver.findElement(By.id("id_hometown"));
		clearHometownBox.clear();
		clearHometownBox.submit();
		
		driver.get("https://www.mapmyrun.com/account/my_profile");
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("follow_us")));
		
		WebElement hometownBox = driver.findElement(By.id("id_hometown"));
		hometownBox.sendKeys("Phoenix");
		hometownBox.submit();
		
		driver.get("https://www.mapmyrun.com/account/my_profile");
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("follow_us")));
		
		Assert.assertEquals("Phoenix", driver.findElement(By.id("id_hometown")).getAttribute("value"));
		
	}

	@Test
	public void testMapMyRunAppForWorkoutImportLink(){
			
		WebDriverWait waitUntilTheMainPageIsDoneLoading = new WebDriverWait(driver,20);
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("feed_end")));
					
		driver.get("http://www.mapmyrun.com/workout/create");
		
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("integrate_tout_import")));
		driver.findElement(By.id("integrate_tout_import")).click();
		
		WebDriverWait waitUntilTheImportPageIsDoneLoading = new WebDriverWait(driver,20);
		waitUntilTheImportPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("manage_data_sources")));
		
		Assert.assertEquals("DATA PREFERENCES", driver.findElement(By.id("manage_data_sources")).getText());		
		}

	
	@Test
	public void testMapMyRunRouteCreatorPageTitle(){
		
		WebDriverWait waitUntilTheMainPageIsDoneLoading = new WebDriverWait(driver,30);
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("feed_end")));
					
		driver.get("http://www.mapmyrun.com/routes/create/");
		
		waitUntilTheMainPageIsDoneLoading.until(ExpectedConditions.visibilityOfElementLocated(By.id("location-search-text")));
		Assert.assertEquals("MapMyRun run Mapping Editor | MapMyRun", driver.getTitle());
	}
	
	
	private void performMapMyRunLogin() {
		driver.get("https://www.mapmyrun.com");
		WebElement loginBtn = driver.findElement(By.id("login_btn"));
		loginBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_button")));
		
		driver.findElement(By.id("id_email")).sendKeys(USER_NAME);
		driver.findElement(By.id("id_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("login_button")).click();
	}
}