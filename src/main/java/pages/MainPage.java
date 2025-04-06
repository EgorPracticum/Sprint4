package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By cookieButton = By.id("rcc-confirm-button"); // Баннер внизу страницы
    private final By orderButtonTop = By.className("Button_Button__ra12g"); // Кнопка "Заказать" вверху
    private final By orderButtonBottom = By.xpath("//button[contains(text(), 'Заказать')]"); // Кнопка "Заказать" внизу
    private final By accordionSection = By.className("accordion"); //Список "Вопросы о важном"
    private final By accordionQuestion = By.cssSelector("[data-accordion-component='AccordionItemButton']"); // Список вопросов внизу страницы
    private final By accordionAnswer = By.cssSelector("[data-accordion-component='AccordionItemPanel']"); // Ответы к списку вопросов

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30); //
    }

    // Методы для взаимодействия с элементами
    public void closeCookieBanner() {
        driver.findElement(cookieButton).click();
    }

    public void clickOrderButtonTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }

    public void clickOrderButtonBottom() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    public void scrollToAccordion() {
        WebElement accordion = driver.findElement(accordionSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", accordion);
    }

    public void clickAccordionQuestion(int index) {
        List<WebElement> questions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(accordionQuestion));
        WebElement question = questions.get(index);
        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
    }

    public String getAccordionAnswer(int index) {
        List<WebElement> answers = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(accordionAnswer));
        return wait.until(ExpectedConditions.visibilityOf(answers.get(index))).getText();
    }
}