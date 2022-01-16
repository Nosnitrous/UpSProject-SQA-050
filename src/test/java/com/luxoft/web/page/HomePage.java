package com.luxoft.web.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.awt.image.ImageFormatException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.By.className;

public class HomePage {
    public static WebDriver wd;
    WebDriverWait waiter;

    /// -------------- First approach - USE BY ------------
//    public HomePage(WebDriver wd) {
//        this.wd = wd;
//    }
//
//    By searchButton = By.id("menu-search");
//    By searchField = By.id("query-search");
//    By resultLink = By.partialLinkText("https://www.dxc.technology/covid-19/");
//
//    public void searchFor(String text){
//        wd.findElement(searchButton).click();
//        wd.findElement(searchField).sendKeys(text + Keys.ENTER);
//    }
//    public void isResultLinkDisplaed(){
//        try {
////            wd.wait(4000);
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Assertions.assertTrue(wd.findElement(resultLink).isDisplayed());
//    }

    /// -------------- Second approach - USE PageFactory ------------
    public HomePage(WebDriver wd, WebDriverWait waiter) {
        this.wd = wd;
        this.waiter = waiter;
        PageFactory.initElements(wd, this);
    }

            //    By searchButton = By.id("menu-search");
    @FindBy(id = "menu-search")
    WebElement searchButton;
            //    By searchField = By.id("query-search");
    @FindBy(id = "query-search")
    WebElement searchField;
            //    By resultLink = By.partialLinkText("https://www.dxc.technology/covid-19/");
    @FindBy(linkText = "https://www.dxc.technology/covid-19/")
    WebElement resultLink;
    @FindBy(className = "lux-insights__item-img-container")
    List<WebElement> insights;

    public void searchFor(String text){
        searchButton.click();
        searchField.sendKeys(text + Keys.ENTER);
        assertEquals(searchField.getAttribute("type"), "text");
    }

    public void isResultLinkDisplayed() throws InterruptedException {
//        Thread.sleep(3000);
        waiter.until(ExpectedConditions.presenceOfElementLocated(linkText("https://www.dxc.technology/covid-19/")));
        Assertions.assertTrue(resultLink.isDisplayed());
    }

    public void clickLinkByText(String text){
        wd.findElement(linkText(text)).click();
    }

    public void moveMouseToElementNum(int num) throws InterruptedException {
        Actions builder = new Actions(wd);
        builder.moveToElement(insights.get(1)).build().perform();
        Thread.sleep(5000);
    }

    public void checkArrowsSize(int num){
        List<WebElement> arrows = insights.get(num).findElements(By.xpath("./following-sibling::div[2]/i"));
                // ByXPath("./following-sibling::div[2]/i"));
                //.findElements(By.tagName("i"));
        assertEquals(arrows.size(), 3);
    }

    public void openNewTab() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) wd;
//        jse.executeScript("alert('Hello Brother!')");
//        wd.switchTo().alert().dismiss();

        jse.executeScript("window.open()");
        List<String> tabs = new ArrayList<String>(wd.getWindowHandles());
        wd.switchTo().window(tabs.get(1));
        wd.get("lhttps://uxoft-training.ru/");
        Thread.sleep(5000);
        TakesScreenshot scrcht = (TakesScreenshot) wd;
//        scrcht.getScreenshotAs("/homePage", ImageFormatException);
    }

    //-------------- 3-d aproach ----------------------------
    public WebElement getElement(String name){
        WebElement obj = null;
        switch (name){          // Здесь перебираем все элементы страницы
            case "searchButton":
                obj = wd.findElement(By.id("...")); break;
            case "HomeLink":
                obj = wd.findElement(); break;
        }
        return obj;
    }
    public void searchButton (String name){
        getElement(name).click();
    }

}
