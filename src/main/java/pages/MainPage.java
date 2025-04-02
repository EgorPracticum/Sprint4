package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Локаторы
    private By orderButtonTop = By.className("Button_Button__ra12g"); // Кнопка "Заказать" вверху
    private By orderButtonBottom = By.xpath("//button[contains(text(), 'Заказать')]"); // Кнопка "Заказать" внизу
    private By accordionQuestion = By.className("accordion__button"); // Список вопросов внизу страницы
    private By accordionAnswer = By.className("accordion__panel"); // Ответы к списку вопросов

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для взаимодействия с элементами
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickAccordionQuestion(int index) {
        driver.findElements(accordionQuestion).get(index).click();
    }

    public String getAccordionAnswer(int index) {
        return driver.findElements(accordionAnswer).get(index).getText();
    }
}