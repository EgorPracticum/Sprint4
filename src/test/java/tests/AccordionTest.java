package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class AccordionTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Parameterized.Parameter
    public int questionIndex;

    @Parameterized.Parameters(name = "Тест вопроса №{0}")
    public static Object[][] data() {
        return new Object[][]{{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}};
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.closeCookieBanner();
        mainPage.scrollToAccordion();
    }

    @Test
    public void shouldShowAnswerWhenClickOnQuestion() {
        mainPage.clickAccordionQuestion(questionIndex);
        String answer = mainPage.getAccordionAnswer(questionIndex);
        assertNotNull("Ответ на вопрос №" + questionIndex + " не должен быть null", answer);
        assertFalse("Ответ на вопрос №" + questionIndex + " не должен быть пустым", answer.isEmpty());
    }

    @After
    public void tearDown() {
        {
            driver.quit();
        }
    }
}