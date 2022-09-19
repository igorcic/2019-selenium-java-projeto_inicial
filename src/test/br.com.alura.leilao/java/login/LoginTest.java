package login;

import org.junit.After;
import org.junit.Assert;


import org.junit.Before;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private LoginPage loginPage;


    @BeforeEach
    public void beforeEach(){
       this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        this.loginPage.fechar();
    }


    @Test
    public void deveriaEfetuarLoginComDadosValidos(){

        loginPage.preencheLogin("fulano","pass");
        loginPage.efetuaLogin();

        Assert.assertFalse(loginPage.isLoginPage());
        Assert.assertEquals("fulano",loginPage.getNomeUsuarioLogado());

    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosIvalidos(){

        loginPage.preencheLogin("invalido","123");
        loginPage.efetuaLogin();

        Assert.assertTrue(loginPage.isLoginPage());
        Assert.assertNull(loginPage.getNomeUsuarioLogado());
        Assert.assertTrue(loginPage.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemLogado(){
        loginPage.navegaParaPaginaLance();

        Assert.assertTrue(loginPage.isLoginPage());
        Assert.assertFalse(loginPage.contemTexto("Dados de Login"));
    }
}