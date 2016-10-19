package webdriver.elements;
import org.codehaus.jackson.JsonFactory;
import org.openqa.selenium.By;
import webdriver.Logger;

/**
 * Created by Администратор on 16.10.2016.
 */
public class CheckBox extends BaseElement {

    public CheckBox(final By locator, final String name) {
        super(locator, name);
    }

    public CheckBox(String string, String name) {
        super(string, name);
    }


    protected String getElementType() {
        return Logger.getLoc("loc.checkbox");
    }

    public boolean isEnabled(){
        return this.getElement().isEnabled();
    }

    public CheckBox(By locator) {
        super(locator);
    }


}
