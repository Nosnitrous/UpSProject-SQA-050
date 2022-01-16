package com.luxoft.web;
// Запуск теста(ов) из командной строки (Terminal'а) через мавен:
// mvn clean -Dgroups="web -Dtest=WebTest test site
// -Dgroups="web" - для тэгов web (@Tag("web")
// -Dtest=WebTest - тестовый класс полностью
// Для просмотра результатов через ALLURE следует поднять FTP SERVER командой:
// mvn allure:serve
import com.luxoft.web.page.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
@ExtendWith(TestWatcher.class)
@Epic("Some Epic")
@Story("Story")
public class WebTest {
    static WebDriver wd;
    static WebDriverWait waiter;
    @BeforeAll
    static void setUp(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
//        System.setProperty("Webdriver.gecko.driver", "lib/geckodriver.exe");
        ChromeOptions opti = new ChromeOptions();
        opti.setAcceptInsecureCerts(true);              // irnore sertificate error
//        opti.setHeadless(true);                       // фоновый режим
        wd = new ChromeDriver(opti);       // с выбранными опциями
//        ChromeDriver wd = new ChromeDriver();
        waiter = new WebDriverWait(wd, 4);  // Включили имплисит ожидание (неявное
//        wd.manage().timeouts().implicitlyWait("5000", TimeUnit.MILLISECONDS); // General TIMEOUT

//        wd.get("http://luxoft.com/");
        wd.navigate().to("http://luxoft.com/");
        wd.manage().window().fullscreen();
    }
    @Test
    @Tag("HomeP")
    void openHomePage(){

//        wd.navigate().back();
//        wd.navigate().refresh();
//        wd.navigate().forward();
//        wd.close();
//        ChromeDriver wd = new ChromeDriver();
        wd.navigate().to("http://luxoft.com/");
//        wd.findElements(By.className("actions").get(3).click());
        wd.findElement(By.id("menu-search")).click();
        Assertions.assertTrue(wd.findElement(By.id("menu-search"))
                .findElements(By.className("search-switcher__text")).toString().contains("search"));
        wd.findElement(By.id("query-search")).sendKeys("covid-19");
        wd.findElement(By.id("query-search")).sendKeys(Keys.ENTER);
    }

    @Test
    @Description("Some description")
    Step("Search")
    void searchTest() throws InterruptedException {
        HomePage hP = new HomePage(wd, waiter);
        hP.searchFor("covid-19");
        hP.isResultLinkDisplayed();
    }

    @Test
    @Step("Arrow Test")
    void arrowTest() throws InterruptedException {
        HomePage hP = new HomePage(wd, waiter);
        hP.clickLinkByText("INSIGHTS");
        hP.moveMouseToElementNum(1);
        hP.checkArrowsSize(1);
    }

    @Test
    Step("New Tab")
    void newTabTest() throws InterruptedException {
        HomePage hP = new HomePage(wd, waiter);
        hP.openNewTab();

    }

    @AfterAll
    static void tearDown(){
        wd.quit();
    }
}
