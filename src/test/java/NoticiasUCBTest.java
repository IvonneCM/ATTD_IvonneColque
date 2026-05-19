import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

/****************************************/
//Historia de Usuario: Como estudiante nuevo quiero buscar relatos en noticias de la UCB
//
//Prueba de Aceptacion / Caso de Prueba
//TC:125: Verificar que el buscador de noticias encuentre "relatos"
//
//PASO 1. Ingresar a la pagina de la UCB https://lpz.ucb.edu.bo/
//PASO 2. Hacer click en el boton noticias
//PASO 3. Abrir el buscador con el icono de busqueda
//PASO 4. Insertar "relatos" y presionar ENTER
//
//Resultado Esperado:
//El resultado de busqueda debe mostrar un articulo relacionado con "relatos"
/****************************************/

//Para ejecutar en la linea de comando:
//mvn clean compile test -Dtest=BuscadorUCBTest

public class NoticiasUCBTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() throws Exception {

        String path = "/Users/gustavo/apps/chromedriver-mac-x64-148/chromedriver";

        System.setProperty("webdriver.chrome.driver", path);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void buscarRelatosTest() {

        /********** Preparacion de la prueba **********/

        //PASO 1. Ingresar a la pagina de la UCB
        String url = "https://lpz.ucb.edu.bo/";
        driver.get(url);

        //Esperamos para que cargue la pagina
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Esperando a la pagina.....");

        /*********** Logica de la prueba ***********/

        //PASO 2. Hacer click en el boton noticias

        WebElement noticias = driver.findElement(
                By.xpath("//*[@id=\"et-secondary-nav\"]/li[5]/a"));

        System.out.println("Se muestra el texto del boton: " + noticias.getText());

        noticias.click();

        //Esperamos para que cargue la pagina de noticias
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //PASO 3. Abrir el buscador con el icono et_search_icon

        //PASO 3. Abrir el buscador con el icono et_search_icon

WebElement iconoBusqueda = driver.findElement(By.id("et_search_icon"));

//Hacemos click con JavaScript porque el span a veces no es interactuable
JavascriptExecutor js = (JavascriptExecutor) driver;

js.executeScript("arguments[0].click();", iconoBusqueda);

System.out.println("Se hizo click en el icono de busqueda");

//Esperamos a que aparezca el input
try {
    TimeUnit.SECONDS.sleep(5);
}
catch (InterruptedException e) {
    e.printStackTrace();
}

        //Esperamos a que aparezca el input de busqueda
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //PASO 4. Insertar "relatos" y presionar ENTER

        WebElement inputBusqueda = driver.findElement(
                By.xpath("//*[@id=\"main-header\"]/div[2]/div/form/input"));

        inputBusqueda.sendKeys("relatos");

        //Presionamos ENTER porque no existe boton buscar
        inputBusqueda.sendKeys(Keys.ENTER);

        //Esperamos a que carguen los resultados
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /************ Verificacion - Assert ***************/

        //Resultado encontrado en la pagina de resultados
        WebElement resultado = driver.findElement(
                By.xpath("//*[@id=\"post-256550\"]/h2/a"));

        boolean estaPresente = resultado.getText().toLowerCase().contains("relatos");

        Assert.assertEquals(true, estaPresente);

        System.out.println("Resultado encontrado: " + resultado.getText());

    }

    @AfterTest
    public void closeDriver() throws Exception {
        driver.quit();
    }

}