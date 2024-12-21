package com.ecommerce.tests.contact;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pom.components.Header;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactUsTest extends BaseTest {
    private static final String EMAIL = "askomdch@gmail.com";
    private static final String CURRENT_URL = "https://askomdch.com/contact-us/";

    @Test
    public void testCheckUrlPage() {
        String currentUrl = new Header(driver)
                .goToContactUsPage()
                .getDriver().getCurrentUrl();

        Assert.assertEquals(currentUrl, CURRENT_URL);

    }

    @Test
    public void testVerifyContactEmail() {
        String email = new Header(driver)
                .goToContactUsPage()
                .showContactEmail();
        Assert.assertEquals(email, EMAIL);
    }
}
