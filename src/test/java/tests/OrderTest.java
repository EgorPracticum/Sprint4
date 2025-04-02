package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    // Параметры для теста
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String name, String surname, String address, String metro, String phone,
                     String deliveryDate, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва, ул. Ленина, 1", "Черкизовская", "89152002020",
                        "23.03.2025", "сутки", "black", "Комментарий для курьера"},
                {"Алла", "Сидорова", "Москва, улица Строителей, 12а, кв.27", "Парк Победы", "84950000101",
                        "25.03.2025", "двое суток", "grey", "Не звоните, оставьте у двери"}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testOrder() {
        mainPage.clickOrderButtonTop();
        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(deliveryDate, rentalPeriod, color, comment);
        String successMessage = orderPage.getSuccessMessage();
        assertEquals("Заказ не оформлен", "Заказ оформлен", successMessage);
    }

    @After
    public void tearDown() {
        {
            driver.quit();
        }
    }
}