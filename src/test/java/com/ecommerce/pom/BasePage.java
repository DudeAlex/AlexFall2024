package com.ecommerce.pom;

import com.ecommerce.pom.components.Footer;
import com.ecommerce.pom.components.Header;
import org.openqa.selenium.WebDriver;

public abstract class BasePage extends BaseModel {

    private Header header;

    private Footer footer;


    public BasePage(WebDriver driver){
        super(driver);
        header = new Header(driver);
        footer = new Footer(driver);
    }

    public Header getHeader() {
        return header;
    }

    public Footer getFooter() {

        return footer;
    }

}
