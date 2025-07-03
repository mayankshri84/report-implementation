package com.example.stepdefinitions;

import com.example.utils.CommonUtils;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import net.serenitybdd.screenplay.Actor;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.core.Serenity.getDriver;


public class StepDef  {

    @Steps
    BasePage basePage;

    @Managed(driver = "chrome")
    WebDriver driver;


    Actor actor = new Actor("Mayank");


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
        CommonUtils commonUtils = new CommonUtils();
        By by = commonUtils.getBy(commonUtils.getObjectProperty(field));
        basePage.typeText(actor);
        driver.findElement(by).sendKeys(text);

    }

}
