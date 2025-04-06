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

    @Parameterized.Parameter
    public boolean useTopButton;

    @Parameterized.Parameter(1)
    public String name;

    @Parameterized.Parameter(2)
    public String surname;

    @Parameterized.Parameter(3)
    public String address;

    @Parameterized.Parameter(4)
    public String metro;

    @Parameterized.Parameter(5)
    public String phone;

    @Parameterized.Parameter(6)
    public String date;

    @Parameterized.Parameter(7)
    public String period;

    @Parameterized.Parameter(8)
    public String color;

    @Parameterized.Parameter(9)
    public String comment;

    @Parameterized.Parameters(name = "Тест заказа: кнопка={0}, имя={1}")
    public static Object[][] getData() {
        return new Object[][]{
                {true, "Иван", "Иванов", "Москва, ул. Ленина, 1", "Черкизовская",
                        "89152002020", "23.03.2025", "сутки", "black", "Позвоните за час"},

                {false, "Анна", "Петрова", "Москва, ул. Пушкина, 15", "Сокольники",
                        "89261234567", "25.03.2025", "двое суток", "grey", "Оставить у двери"}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        mainPage.closeCookieBanner();
    }

    @Test
    public void testOrderCreation() {
        if (useTopButton) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        orderPage.fillFirstForm(name, surname, address, metro, phone);
        orderPage.fillSecondForm(date, period, color, comment);

        assertEquals("Не отобразилось сообщение об успешном заказе",
                "Заказ оформлен",
                orderPage.getSuccessMessage());
    }

    @After
    public void tearDown() {
        {
            driver.quit();
        }
    }
}