package br.com.automation.selenium.saucedemo.logics;

import br.com.automation.selenium.saucedemo.pages.HomePage;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;

import static br.com.automation.configs.BrowserFactory.web;
import static br.com.automation.configs.PDFGenerator.pdfGenerator;
import static br.com.automation.utils.Utilities.sleep;

@Log4j2
public class HomeLogic {
    private String step;
    private final HomePage page;

    public HomeLogic() {page = new HomePage();}

    public void validateInventoryEntrance() {
        step = "Validando que está na tela de pós login";

        log.info(step);
        sleep();

        Assert.assertTrue(page.getInventory().isDisplayed());
        Assert.assertTrue(web().getCurrentUrl().endsWith("/inventory.html"));

        pdfGenerator().takeScreenshot(web(), "Validando que está na tela de pós login");
    }
}
