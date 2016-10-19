package demo.test;

import webdriver.BaseTest;
import demo.test.forms.*;

public class DemoTest extends BaseTest {

	
	public void runTest() throws InterruptedException {

		logger.step(1);
		OnlinerMain om = new OnlinerMain();
		om.assertLogo();
		om.clickOnLabel();

		logger.step(2);

		OnlinerCatalog oc = new OnlinerCatalog();
		//oc.assertLbl();
		oc.clickOnLabel();

		logger.step(3);
		OnlinerSearchForm osf=new OnlinerSearchForm();
		osf.FillingForm();

        logger.step(4);
        osf.assertMakerString();

        logger.step(5);
        osf.assertTags();

		logger.step(6);
		osf.analysisSearchResults();




	}
}
