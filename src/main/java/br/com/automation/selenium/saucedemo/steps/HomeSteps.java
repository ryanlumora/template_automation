package br.com.automation.selenium.saucedemo.steps;

import br.com.automation.selenium.saucedemo.logics.HomeLogic;
import io.cucumber.java.en.Then;

public class HomeSteps {

    private final HomeLogic homeLogic;

    public HomeSteps() {
        homeLogic = new HomeLogic();
    }

    @Then("podera validar que esta logado")
    public void podera_validar_que_esta_logado(){
        homeLogic.validateInventoryEntrance();
    }
}
