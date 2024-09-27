import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class SeleniumTest {


@Test
    void setup() throws InterruptedException {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");

    System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
    WebDriver driver = new ChromeDriver(options);


    // 01_04
    driver.get("http://www.google.com");
    WebElement element = driver.findElement(By.name("q"));
    element.sendKeys("Cheese!");
    element.submit();

    //02_02
    driver.get("https://formy-project.herokuapp.com/keypress");

    WebElement name = driver.findElement(By.id("name"));
    name.click();
    name.sendKeys("Mr.White");

    WebElement button = driver.findElement(By.id("button"));
    button.click();

    //02_03 autocomplete
//    driver.get("https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_autocomplete");
//    Thread.sleep(10000);
//    WebElement autocomplete = driver.findElement(By.id("myInput"));
//    Thread.sleep(10000);
//    autocomplete.sendKeys("Canada");


//    WebElement autocompleteResult = driver.findElement(By.id("myInputautocomplete-list"));
//    autocompleteResult.click();

    //02_04 scroll
    driver.get("https://formy-project.herokuapp.com/scroll");

    WebElement name1 = driver.findElement(By.id("name"));
    Actions actions = new Actions(driver);
    actions.moveToElement(name1);
    name1.sendKeys("Meaghan Lewis");

    WebElement date = driver.findElement(By.id("date"));
    date.sendKeys("01/01/2020");

    //02_05 switch tab
    driver.get("https://formy-project.herokuapp.com/switch-window");

    WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
    newTabButton.click();

    String originalHandle = driver.getWindowHandle();

    for(String handle1: driver.getWindowHandles()) {
        driver.switchTo().window(handle1);
    }

    driver.switchTo().window(originalHandle);

    //02_06 alert
    driver.get("https://formy-project.herokuapp.com/switch-window");

    WebElement alertButton = driver.findElement(By.id("alert-button"));
    alertButton.click();

    Alert alert = driver.switchTo().alert();
    alert.accept();

    //02_07 javascript executer
    driver.get("https://formy-project.herokuapp.com/modal");

    WebElement modalButton = driver.findElement(By.id("modal-button"));
    modalButton.click();

    Thread.sleep(1000);
    WebElement closeButton = driver.findElement(By.id("close-button"));
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].click();", closeButton);

    //02_08 drag and drop
    driver.get("https://formy-project.herokuapp.com/dragdrop");

    WebElement image = driver.findElement(By.id("image"));

    WebElement box = driver.findElement(By.id("box"));

    Actions actions1 = new Actions(driver);
    actions1.dragAndDrop(image,box).build().perform();

    //04_02 radiobutton and checkbox
    driver.get("https://formy-project.herokuapp.com/radiobutton");

    WebElement radioButton1 = driver.findElement(By.id("radio-button-1"));
    radioButton1.click();

    WebElement radioButton2 = driver.findElement(By.cssSelector("input[value='option2']"));
    radioButton2.click();

    WebElement radioButton3 = driver.findElement(By.xpath("/html/body/div/div[3]/input"));
    radioButton3.click();

    //04_03 datepicker
    driver.get("https://formy-project.herokuapp.com/datepicker");

    WebElement dateField = driver.findElement(By.id("datepicker"));
    dateField.sendKeys("03/03/2020");
    dateField.sendKeys(Keys.RETURN);

    //04_04 drop down
    driver.get("https://formy-project.herokuapp.com/dropdown");

    WebElement dropDownMenu = driver.findElement(By.id("dropdownMenuButton"));
    dropDownMenu.click();

    WebElement autocompleteItem = driver.findElement(By.id("autocomplete"));
    autocompleteItem.click();

    //04_05 file upload
    driver.get("https://formy-project.herokuapp.com/fileupload");

    WebElement fileUploadField = driver.findElement(By.id("file-upload-field"));
    fileUploadField.sendKeys("file-to-upload.png");

    //05_02 implicit waits
    driver.get("https://formy-project.herokuapp.com/autocomplete");

    WebElement autocomplete = driver.findElement(By.id("autocomplete"));
    //autocomplete.sendKeys("1555 Park Blvd, Palo Alto, CA");

//    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//    WebElement autocompleteResult = driver.findElement(By.className("pac-item"));
//    autocompleteResult.click();

    //05_03 explicit waits
    driver.get("https://formy-project.herokuapp.com/autocomplete");

//    WebElement autocomplete2 = driver.findElement(By.id("autocomplete"));
//    autocomplete2.sendKeys("1555 Park Blvd, Palo Alto, CA");
//
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10) );  //maximum duration to wait
//    WebElement autocompleteResult1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pac-item"))); // recursively checks after few milli seconds
//
//    autocompleteResult.click();

    //06_04 classes
    driver.get("https://formy-project.herokuapp.com/form");

    FormPage formPage = new FormPage();
    formPage.submitForm(driver);

    ConfirmationPage confirmationPage = new ConfirmationPage();
    confirmationPage.waitForAlertBanner(driver);

    //assertEquals("The form was successfully submitted!", confirmationPage.getAlertBannerText(driver));


    //Close the browser
   // driver.quit();
    }
}
