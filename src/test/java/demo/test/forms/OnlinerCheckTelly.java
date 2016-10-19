package demo.test.forms;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import webdriver.BaseForm;
import webdriver.elements.Label;

import static java.lang.Integer.*;
import static java.lang.System.out;
import static org.apache.commons.lang.StringUtils.contains;

/**
 * Created by Администратор on 16.10.2016.
 */
public class OnlinerCheckTelly extends BaseForm {
        private Label descrMaker = new Label(By.xpath("//div[@class='product-header']//h2"),"descrMaker");
        private Label descrPrice = new Label(By.xpath("//div[@class='b-offers-desc__info-sub']/a[1]"),"descrPrice");
        private Label descrYear = new Label(By.xpath("//td[contains(text(),'Дата выхода')]//following-sibling::td/span"),"descrYear");
        private Label descrDiagonal = new Label(By.xpath("//td[contains(text(),'Диагональ экрана')]//following-sibling::td/span"),"descrDiagonal");


   public OnlinerCheckTelly() {
   super(By.xpath("//div[@class='product-specs__top']/h3"), "Description");
    }
        public void searchCorrectInfo(int i,String makerName,String maxPrice,String fromYear,String fromDiagonal,String toDiagonal)
                {
                    SoftAssert sa=new SoftAssert();

                    descrYear.waitForIsElementPresent();
                    sa.assertTrue(descrYear.isPresent(),"descrYear");
                    sa.assertTrue(Integer.parseInt((descrYear.getText().substring(0,descrYear.getText().indexOf(' '))))>=(Integer.parseInt(fromYear)),"descrYear");

                    descrMaker.waitForIsElementPresent();
                    sa.assertTrue(descrMaker.isPresent(),"descrMaker");
                    sa.assertTrue(contains(descrMaker.getText().toLowerCase(),makerName),"descrMaker");

                    descrPrice.waitForIsElementPresent();
                    sa.assertTrue(descrPrice.isPresent(),"descrPrice");
                   String str =descrPrice.getText().replaceAll(",",".");;
                     //String str2=str.substring((str.indexOf('–')+1),str.indexOf('р'));
                    String str2=str.substring(0,(str.indexOf(' ')));
                    sa.assertTrue((Float.parseFloat(str2))<=(Float.parseFloat(maxPrice)),"descrPrice");

                    descrDiagonal.waitForIsElementPresent();
                    sa.assertTrue(descrDiagonal.isPresent(),"descrDiagonal");
                    sa.assertTrue(Integer.parseInt((descrDiagonal.getText().replaceAll("\"","")))>=(Integer.parseInt(fromDiagonal)),"descrFromDiagonal");
                    sa.assertTrue(Integer.parseInt((descrDiagonal.getText().replaceAll("\"","")))<=(Integer.parseInt(toDiagonal)),"descrtoDiagonal");

                    sa.assertAll();

                    System.out.println(i+") "+descrMaker.getText()+"; Цена: "+descrPrice.getText()+"; Диагональ: "+descrDiagonal.getText()+"; Год выпуска: "+descrYear.getText());

        }

    }



