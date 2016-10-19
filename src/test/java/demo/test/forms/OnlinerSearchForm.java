package demo.test.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import webdriver.BaseForm;
import webdriver.PropertiesResourceManager;
import webdriver.elements.CheckBox;
import webdriver.elements.ComboBox;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.List;

import static org.testng.Assert.assertTrue;


public class OnlinerSearchForm extends BaseForm {
    static final String PROPERTIES_FILE = "testdata.properties";
    public static PropertiesResourceManager props = new PropertiesResourceManager(PROPERTIES_FILE);

       String makerName = props.getProperty("makerName");
       String maxPrice = props.getProperty("maxPrice");
       String fromYear = props.getProperty("fromYear");
       String fromDiagonal = props.getProperty("fromDiagonal");
       String toDiagonal = props.getProperty("toDiagonal");

    private CheckBox chMaker = new CheckBox(By.xpath("//li//input[@value='"+makerName+"']/following-sibling::span"), "Maker");
    private TextBox tbMaxPrice = new TextBox(By.xpath("//input[@placeholder='до']"), "Max Price");
    private TextBox tbFromYear = new TextBox(By.xpath("//input[@placeholder='2010']"), "Min Year");
    private ComboBox coFromDiagonal = new ComboBox(By.xpath("//select[contains(@data-bind,'facet.value.from')]"), "Min Diagonal");
    private ComboBox coToDiagonal = new ComboBox(By.xpath("//select[contains(@data-bind,'facet.value.to')]"), "Max Diagonal");
    private Label lblTesting = new Label(By.xpath("//div[@class='schema-product__title']//a[contains(@href,'"+makerName+"')]"),"Result (Simple Check Result by Maker)");
    private Label tagMaxPrice = new Label(By.xpath("//*[@id='schema-tags']//div[@title='Минимальная цена, после деноминации']"),"tagMaxPrice");
    private Label tagFromYear = new Label(By.xpath("//*[@id='schema-tags']//div[@title='Дата выхода на рынок']"),"tagFromYear");
    private Label tagDiagonal = new Label(By.xpath("//*[@id='schema-tags']//div[@title='Диагональ']"),"tagDiagonal");
    private Label tagMaker = new Label(By.xpath("//*[@id='schema-tags']//div[@title='Производитель']"),"tagMaker");

    public OnlinerSearchForm() {
         super(By.xpath("//div[@class='schema-header']//h1"), "TVSets Header");
           }

    public void FillingForm() throws InterruptedException {
        chMaker.click();
        tbMaxPrice.setText(maxPrice);
        tbFromYear.setText(fromYear);
        coFromDiagonal.setValue(fromDiagonal);
        coToDiagonal.setValue(toDiagonal);
        Thread.currentThread().sleep(1000) ;
    }

    public void assertMakerString(){
        lblTesting.waitForIsElementPresent();
        assertTrue(lblTesting.isPresent());

    }
// "лишняя"/дополнительная проверка значений установленных фильтров, добавила проверку парметров по каждому телевизору searchCorrectInfo
    public void assertTags(){
        SoftAssert sa=new SoftAssert();

        tagFromYear.waitForIsElementPresent();
        sa.assertTrue(tagFromYear.isPresent(),"tagFromYear");

        tagMaker.waitForIsElementPresent();
        sa.assertTrue(tagMaker.isPresent(),"tagMaker");
        sa.assertEquals(tagMaker.getText().toLowerCase(),makerName,"tagMaker");

        tagMaxPrice.waitForIsElementPresent();
        sa.assertTrue(tagMaxPrice.isPresent(),"tagMaxPrice");
        sa.assertTrue(tagMaxPrice.getText().replaceAll(" ","").endsWith(maxPrice),"tagMaxPrice");

        tagDiagonal.waitForIsElementPresent();
        sa.assertTrue(tagDiagonal.isPresent(),"tagDiagonal");
        sa.assertTrue(tagDiagonal.getText().startsWith(fromDiagonal),"tagFromDiagonal");
        sa.assertTrue(tagDiagonal.getText().endsWith(toDiagonal+'"'),"tagToDiagonal");

       sa.assertAll();

    }

public void analysisSearchResults() throws InterruptedException {
    List<WebElement> elements;
     elements=browser.getDriver().findElements(By.xpath("//div[@class='schema-product__title']//a[contains(@href,'"+makerName+"')]"));
     System.out.println(elements.size());

        int j=0;
    for (int i=0; i<elements.size();i++,j++)
    {
        //div[3] - отсутствует элемент
        if (j==2)     j++;
        Label lblTemp = new Label(By.xpath("//*[@id='schema-products']/div[" +(j+1)+ "]//div[@class='schema-product__title']/a/span"), "TV"+(i+1));

        lblTemp.click();
        browser.waitForPageToLoad();
        OnlinerCheckTelly oct=new OnlinerCheckTelly();
        oct.searchCorrectInfo(i+1,makerName,maxPrice,fromYear,fromDiagonal,toDiagonal);
        browser.getDriver().navigate().back();
        browser.waitForPageToLoad();
    }

}
}
