package demo.test.forms;

import org.openqa.selenium.By;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

/**
 * Created by Администратор on 16.10.2016.
 */
public class OnlinerMain  extends BaseForm {

    private Label lbLogo = new Label(By.xpath("//img[@class='onliner_logo retina-off']"),"onliner.by logo");
    private Label lblCatalog = new Label(By.xpath("//a[@href='https://catalog.onliner.by/']"),"Catalog");
    public OnlinerMain() {
        super(By.xpath("//input[@name='query']"), "Onliner by");
    }

       public void assertLogo(){
        assert(lbLogo.isPresent());
    }

    public void clickOnLabel(){
        lblCatalog.click();
        browser.waitForPageToLoad();
    }


}
