import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/****************************************/
//Historia de Usuario: Como usuario nuevo quiero registrar mis datos en Dzone.com
//
//Prueba de Aceptacion: Verificar que se muestren alertas para los campos obligatorios
//
//1. Ingresar a la pagina de DZone  www.dzone.com
//2. Hacer en el link Join
//3. Presionar el boton Join
//
//Resultado Esperado: Se deben mostrar mensajes de alerta para los campos obligatorios que no fueron llenados
/****************************************/

//Para ejecutar en la linea de comando: mvn clean compile test -Dtest=RegistroInfoQTest

public class RegistroInfoQTest {

    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception{
        
    	 String path = "/Users/gustavo/apps/chromedriver-mac-x64-148/chromedriver";
         
         System.setProperty("webdriver.chrome.driver", path);
         
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
        
    }
    
    @Test
    public void verificarMensajeErrorAlRegistrar(){
        
        /********** Preparacion de la prueba **********/
    	
    	//PASO 1. Ingresar a la pagina de InfoQ
        String dzoneUrl = "https://lpz.ucb.edu.bo/";
        driver.get(dzoneUrl);
        

        //Esperamos 3 segundos para que se renderice la siguiente pagina
        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e){
            e.printStackTrace();
     
        }
        System.out.println("Esperando a la pagina.....");

        /*********** Logica de la prueba***********/
        
        //PASO 2. Hacer en el link Join
        
        WebElement joinLink = driver.findElement(By.xpath("//*[@id=\"et-secondary-nav\"]/li[6]/a"));
        
        System.out.println("Se muestra el texto del boton: "+joinLink.getText());

        joinLink.click();;
        
        //Esperamos 3 segundos para que se renderice la siguiente pagina
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
     
        }
        
        
        //PASO 3. Presionar el boton CREATE ACCOUNT
        WebElement buscador = driver.findElement(By.xpath("//*[@id=\"tbTextoBuscar\"]"));                                
        
        buscador.sendKeys("Duran");

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        
       
        
        /************Verificacion de la situacion esperada - Assert ***************/
        
        /* 
        WebElement iconAlert = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/div[1]/div[2]/form/div[1]/span[2]/i"));
        Assert.assertEquals(true, iconAlert.isDisplayed());
        
        
        
        //Validar el mensaje del correo
        WebElement 	emailErrorMessage = driver.findElement(
        		By.xpath("//div[@data-validate=\"Please enter a valid email address\"]"));
        
        String attribute = emailErrorMessage.getAttribute("data-validate");
        System.out.println("Valor del attribute::: "+attribute);
        
        Assert.assertEquals("Please enter a valid email address", attribute);
        */
        
    }
    
    
    
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
    
    
}
