package br.com.automation.selenium.saucedemo.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static br.com.automation.configs.BrowserFactory.web;

@Getter
public class HomePage {

    public HomePage() {
        PageFactory.initElements(web(), this);
    }

    @FindBy(id = "inventory_container")
    private WebElement inventory;
}
