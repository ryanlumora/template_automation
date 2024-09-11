Feature: Exemplo de feature do template de automacao

  Background:
    Given que o usuario esta pagina inicial do sauce demo

  Scenario: Login na pagina do sauce demo com sucesso
    When preencher usuario e senha corretos
    Then podera validar que esta logado