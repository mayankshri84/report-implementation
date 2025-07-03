package com.example.stepdefinitions;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

public class BasePage extends PageObject {


    @Step("User is typing text on textbox")
    public void typeText(Actor actor){

    }
}
