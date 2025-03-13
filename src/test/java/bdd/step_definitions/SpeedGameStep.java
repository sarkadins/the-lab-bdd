package bdd.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;

public class SpeedGameStep {
    WebDriver driver;
    Wait<WebDriver> wait;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I navigate to {string}")
    public void i_navigate_to_speed_game(String url) {
        driver.get(url);
    }

    @When("I start the game")
    public void i_start_the_game() {
        WebElement startBtn = driver.findElement(By.xpath("//div[@class='game_space']/button[@data-testid='startBtn']"));
        wait.until(ExpectedConditions.visibilityOf(startBtn)).click();
    }

    @And("I end the game")
    public void i_end_the_game() {
        WebElement endGameBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='game_space']/button[@class='form_btn delete']")));
        wait.until(ExpectedConditions.visibilityOf(endGameBtn));
        wait.until(ExpectedConditions.elementToBeClickable(endGameBtn)).click();
    }

    @Then("a success message should be shown")
    public void a_success_message_should_be_shown() {
        String expected = "Success";
        String actual = driver.findElement(By.xpath("//div[@class='game_space']/div/p[@class='success_message ']")).getText();
        assertThat(expected, Matchers.is(actual));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
