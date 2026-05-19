import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.*;
import org.testng.Assert;

/****************************************/
// Historia de Usuario: Como usuario quiero verificar que el boton "Ingenieria de Sistemas" se despliega
//
// Prueba de Aceptacion: Verificar que el boton de "Ingenieria de Sistemas" este  presente en la pagina de la UCB
//
// Paso 1. Ingresar a la pagina de la UCB: https://lpz.ucb.edu.bo/
// Paso 2. Buscar el boton "Ingenieria  de sistemas"

// 
// Resultado Esperado: El boton "Buscar con Google" debe estar presente y ser visible
/****************************************/


//Para ejecutar en la linea de comando: mvn clean compile test -Dtest=UcbLaPazTest

public class UcbLaPazTest {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception{

        //https://sites.google.com/chromium.org/driver/getting-started
        
    	String path = "/Users/gustavo/apps/chromedriver-mac-x64_119/chromedriver119";
        
        System.setProperty("webdriver.chrome.driver", path);
        
        WebDriverManager.chromedriver().setup();
        
        driver = new ChromeDriver();
        
    }
    
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
    
    @Test
    public void paginaPrincipalUCB(){
        
        /**************  1. Preparacion de la prueba***********/
    	
    	//Paso 1. Ingresar a la pagina de UCB
        String ucbUrl = "https://lpz.ucb.edu.bo/";
        driver.get(ucbUrl);
        
        
        /************** 2. Logica de la prueba***************/
        
        /*Capturar el boton "Ingenieria de Sistemas"*/
        
        WebElement linkSistemas = driver.findElement(By.xpath("//*[@id=\"post-227475\"]/div/div/div/div[6]/div[2]/div[2]/div[5]/div/h4/a"));


        String txtBoton = linkSistemas.getText();
        System.out.println("Texto del boton:: "+txtBoton);

        linkSistemas.click();
        System.out.println("Haciendo click...  ");

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
        	e.printStackTrace();
        }    
       
        
        /************ 3. Verificacion de la situacion esperada - Assert ***************/
        
        Assert.assertEquals(txtBoton,"Ingeniería de Sistemas");
    }
    
   
   
    
    
    
    
    /*
     
        
       
        
        
        

        
        
        
        try{
            TimeUnit.SECONDS.sleep(7);
        }
        catch(InterruptedException e){
        	e.printStackTrace();
        }    
        
        
       

      */
    
}







