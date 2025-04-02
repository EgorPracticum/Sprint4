package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;

    // Локаторы для первой формы
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    private By metroDropdownOption = By.xpath("//div[@class='select-search__select']//button");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[contains(text(), 'Далее')]");

    // Локаторы для второй формы
    private By deliveryDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodField = By.className("Dropdown-placeholder");
    private By rentalPeriodOption = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");
    private By colorCheckbox = By.xpath("//input[@id='black']");
    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.xpath("//button[contains(text(), 'Заказать') and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    private By confirmButton = By.xpath("//button[contains(text(), 'Да')]");
    private By successMessage = By.className("Order_ModalHeader__3FDaJ");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для заполнения первой формы
    public void fillFirstForm(String name, String surname, String address, String metro, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);

        // Поле "Метро"
        WebElement metroInput = driver.findElement(metroField);
        metroInput.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement metroOption = wait.until(ExpectedConditions.elementToBeClickable(metroDropdownOption));
        metroOption.click();

        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    // Методы для заполнения второй формы
    public void fillSecondForm(String deliveryDate, String rentalPeriod, String color, String comment) {
        WebElement dateInput = driver.findElement(deliveryDateField);
        dateInput.sendKeys(deliveryDate);
        dateInput.sendKeys(org.openqa.selenium.Keys.ENTER);

        // Срок аренды
        driver.findElement(rentalPeriodField).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement rentalOption = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption));
        rentalOption.click();

        // Цвет самоката
        driver.findElement(colorCheckbox).click();

        // Комментарий
        driver.findElement(commentField).sendKeys(comment);

        // Заказать
        driver.findElement(orderButton).click();

        // Подтверждение заказа
        driver.findElement(confirmButton).click();
    }

    // Метод для получения сообщения об успешном заказе
    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return successElement.getText();
    }
}