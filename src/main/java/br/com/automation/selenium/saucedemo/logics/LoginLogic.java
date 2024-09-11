package br.com.automation.selenium.saucedemo.logics;

import br.com.automation.selenium.saucedemo.pages.LoginPage;
import lombok.extern.log4j.Log4j2;

import static br.com.automation.configs.BrowserFactory.web;
import static br.com.automation.configs.PDFGenerator.pdfGenerator;
import static br.com.automation.utils.Utilities.*;

@Log4j2
public class LoginLogic {
    private String step;
    private final LoginPage page;

    public LoginLogic() {
        page = new LoginPage();
    }

    public void enterIntoSauceDemo() {
        step = "Entrando na página de login do sauce demo";
        web().get("https://www.saucedemo.com/v1/");
        web().manage().window().maximize();

        log.info(step);

        pdfGenerator().takeScreenshot(web(), step);
    }

    public void insertFormsInformation() {
        step = "Preenchendo informações do login";
        log.info(step);

        sleep();
        page.getInputUser().sendKeys("standard_user");

        sleep();
        page.getInputPassword().sendKeys("secret_sauce");

        pdfGenerator().takeScreenshot(web(), step);

        sleep();
        page.getInputUser().submit();
    }


}
