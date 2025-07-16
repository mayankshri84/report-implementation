package org.sarb.stepdefinitions;

import io.cucumber.java.After;
import org.openqa.selenium.support.PageFactory;
import org.sarb.pages.HomePage;
import org.sarb.utils.CommonUtils;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.screenplay.Actor;
import java.util.concurrent.TimeUnit;


public class StepDef  {

    @Steps
    BasePage basePage;

    @Managed(driver = "chrome")
    WebDriver driver;


    Actor actor = new Actor("Mayank");

    @After
    public void tearDown(){
        System.out.println();
    }

    @Given("user is opening {string} browser")
    public void user_is_opening_browser(String string) {
        basePage.typeText(actor);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @Given("navigating to {string}")
    public void navigating_to(String string) {
        basePage.typeText(actor);
        driver.get(string);

    }

    @Given("type {string} in {string} field")
    public void type_in_field(String text, String field) {
        /*CommonUtils commonUtils = new CommonUtils();
        By by = commonUtils.getBy(commonUtils.getObjectProperty(field));
        basePage.typeText(actor);
        driver.findElement(by).sendKeys(text);*/
        basePage.openingTheBrowser(actor);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.userName.sendKeys(text);

    }

}
