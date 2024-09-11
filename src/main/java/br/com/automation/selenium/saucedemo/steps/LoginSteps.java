package br.com.automation.selenium.saucedemo.steps;

import br.com.automation.selenium.saucedemo.logics.LoginLogic;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class LoginSteps {

    private final LoginLogic logic;

    public LoginSteps() {
        logic = new LoginLogic();
    }

    @Given("que o usuario esta pagina inicial do sauce demo")
    public void que_o_usuario_esta_pagina_inicial_do_sauce_demo(){
        logic.enterIntoSauceDemo();
    }

    @When("preencher usuario e senha corretos")
    public void preencher_usuario_e_senha_corretos() throws IOException {
        logic.insertFormsInformation();
    }

    @Then("podera validar que esta logado")
    public void podera_validar_que_esta_logado() throws InterruptedException {
        logic.validateInventoryPage();
    }
}
