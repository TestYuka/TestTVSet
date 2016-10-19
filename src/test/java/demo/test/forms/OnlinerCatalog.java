package demo.test.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by Администратор on 16.10.2016.
 */
public class OnlinerCatalog extends BaseForm {
    private Label lbTv = new Label(By.xpath("//div [@class='catalog-bar-main']//a[@href='https://catalog.onliner.by/tv']"), "TV");
    public OnlinerCatalog() {
        super(By.xpath("//input[@name='query']"), "Catalog page");
    }
/*
    public void assertLbl(){
        assert(lbTv.isPresent());
    }
*/
    public void clickOnLabel(){
        lbTv.click();
        browser.waitForPageToLoad();
            }

}
