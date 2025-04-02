package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AccordionTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testAccordion() {
        By accordionQuestion = By.className("accordion__button");
        By accordionAnswer = By.className("accordion__panel");

        List<WebElement> questions = driver.findElements(accordionQuestion);

        for (int i = 0; i < questions.size(); i++) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questions.get(i));

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(questions.get(i))).click();

            WebElement answer = driver.findElements(accordionAnswer).get(i);
            wait.until(ExpectedConditions.visibilityOf(answer));

            String answerText = answer.getText();
            System.out.println("Ответ на вопрос " + (i + 1) + ": " + answerText);
            assertEquals("Ответ не должен быть пустым", true, !answerText.isEmpty());
        }
    }

    @After
    public void tearDown() {
        {
            driver.quit();
        }
    }
}