package br.com.automation.selenium.saucedemo.steps;

import br.com.automation.selenium.saucedemo.logics.LoginLogic;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class LoginSteps {

    private final LoginLogic loginLogic;

    public LoginSteps() {
        loginLogic = new LoginLogic();
    }

    @Given("que o usuario esta pagina inicial do sauce demo")
    public void que_o_usuario_esta_pagina_inicial_do_sauce_demo() {
        loginLogic.enterIntoSauceDemo();
    }

    @When("preencher usuario e senha corretos")
    public void preencher_usuario_e_senha_corretos() {
        loginLogic.insertFormsInformation();
    }
}
