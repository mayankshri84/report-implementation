package org.sarb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {


    @FindBy(xpath = "//*[@placeholder='Email address or phone number']")
    public WebElement userName;
}
