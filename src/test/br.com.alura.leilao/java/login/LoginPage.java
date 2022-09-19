package login;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    public static final String HTTP_LOCALHOST_8080_LOGIN = "http://localhost:8080/login";
    private ChromeDriver browser;

    public LoginPage(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(HTTP_LOCALHOST_8080_LOGIN);
    }


    public void fechar() {
        this.browser.quit();
    }

    public void preencheLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void efetuaLogin() {
        browser.findElement(By.id("login-form")).submit();
    }

    public boolean isLoginPage() {
        return browser.getCurrentUrl().equals(HTTP_LOCALHOST_8080_LOGIN);
    }

    public Object getNomeUsuarioLogado() {
        try {
            return  browser.findElement(By.id("usuario-logado")).getText();
        }catch (NoSuchElementException e){
            return null;
        }

    }

    public void navegaParaPaginaLance() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemTexto(String texto) {
        return browser.getPageSource().contains(texto);
    }

    public boolean isLoginPageComDadosInvalidos() {
        return browser.getCurrentUrl().equals(HTTP_LOCALHOST_8080_LOGIN + "?erro");
    }
}
