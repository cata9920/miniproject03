import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions fo = new FirefoxOptions();
      //  fo.addArguments("--headless");
      //  fo.addArguments("--disable-gpu");
      //  fo.addArguments("--window-size=500,500");

        WebDriver driver = new FirefoxDriver(fo);
        driver.manage().timeouts().implicitlyWait(Duration.ofHours(100));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofHours(100));
        driver.manage().timeouts().scriptTimeout(Duration.ofHours(100));

        driver.get("https://www.barnesandnoble.com/");

        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofHours(100));


        webDriverWait.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));


        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);

        WebElement bookele = driver.findElement(By.xpath("//a[contains(@class,'image-container carousel-image-link')]"));
        String f = bookele.getAttribute("href");
        System.out.println(f);

        //rbt-input-main form-control rbt-input user-success

        String url = f;

        // Use JavaScript to open in a new tab
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open(arguments[0], '_blank');", url);

        //The Book of Bill (B&N Exclusive Edition)


        String originalhandle = driver.getWindowHandle();

        Set<String> wh  = driver.getWindowHandles();

        for (String g : wh) {
            if (!g.equals(originalhandle)) {
                driver.switchTo().window(g);
            }
        }
        System.out.println(wh.size());

        WebDriverWait ww = new WebDriverWait(driver,Duration.ofHours(100));

        //ww.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return Document.readyState").equals("complete"));
        ww.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));
        ww.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("*")));
       System.out.println(driver.getTitle());

       WebElement ele2 = driver.findElement(By.xpath("//button[contains(@class,'onetrust-close-btn-handler onetrust-close-btn-ui banner-close-button ot-close-icon')]"));

       ele2.click();

        driver.quit();
    }
}