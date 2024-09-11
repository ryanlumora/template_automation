package br.com.automation.configs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.Getter;

import static br.com.automation.configs.BrowserFactory.*;
import static br.com.automation.configs.PDFGenerator.pdfGenerator;

public class Hooks{

    @Getter
    private static String scenarioName;

    @Before
    public void setup(Scenario scenario) {
        scenarioName = scenario.getName();
        System.out.println("Nome do Cenário: " + scenarioName);
    }

    @After
    public void tearDown(){
        pdfGenerator().createPDF();
        closeBrowser();
    }

}
