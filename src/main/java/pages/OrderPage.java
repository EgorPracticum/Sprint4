package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы первой формы
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By metroOption = By.xpath("//div[@class='select-search__select']//button");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Локаторы второй формы
    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.className("Dropdown-placeholder");
    private final By rentalOption = By.xpath("//div[text()='двое суток']");
    private final By blackColor = By.id("black");
    private final By greyColor = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(text(), 'Заказать')]");
    private final By confirmButton = By.xpath("//button[text()='Да']");
    private final By successMessage = By.xpath("//div[contains(@class, 'Order_ModalHeader')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void fillFirstForm(String name, String surname, String address, String metroStation, String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);

        // метро
        WebElement metroInput = wait.until(ExpectedConditions.elementToBeClickable(metroField));
        metroInput.click();
        wait.until(ExpectedConditions.elementToBeClickable(metroOption)).click();

        driver.findElement(phoneField).sendKeys(phone);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void fillSecondForm(String date, String period, String color, String comment) {
        // Дата
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.RETURN);

        // Срок аренды
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriod)).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalOption)).click();

        // Цвет
        if ("black".equals(color)) {
            driver.findElement(blackColor).click();
        } else {
            driver.findElement(greyColor).click();
        }

        // Комментарий
        driver.findElement(commentField).sendKeys(comment);

        // Подтверждение заказа
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }
}