package com.ecommerce.pom.components;

import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LeftSidebar extends BaseComponent {

    // "Search Products" section
    By searchInputField = By.xpath("//input[@type='search']");
    By searchButton = By.xpath("//button[@value='Search']");

    // "Our Best Sellers" section

    // "Browse By Categories" section
    By browseByCategoryInputField = By.xpath("//select[@id='product_cat']");
    By noCategory = By.xpath("//option[contains(text(),'Select a category')]");
    By accessories = By.xpath("//option[@value='accessories']");
    By men = By.xpath("//option[@value='men']");
    By mensJeans = By.xpath("//option[@value='mens-jeans']");
    By mensShirts = By.xpath("//option[@value='mens-shirts']");
    By mensShoes = By.xpath("//option[@value='mens-shoes']");
    By pursesAndHandbags = By.xpath("//option[@value='purses-and-handbags']");
    By women = By.xpath("//option[@value='women']");
    By womensJeans = By.xpath("//option[@value='womens-jeans']");
    By womensShirts = By.xpath("//option[@value='womens-shirts']");
    By womensShoes = By.xpath("//option[@value='womens-shoes']");
    By clearSelectionIcon = By.xpath("//span[@class='select2-selection__clear' and text()='×']");

    // "Filter By Price" section

    public LeftSidebar(WebDriver driver) {
        super(driver);
    }

    public <T> T searchProduct(String item, T page) {
        WaitUtils.visibilityOfElementLocated(getDriver(), searchInputField).sendKeys(item);
        WaitUtils.presenceOfElementLocated(getDriver(), searchButton).click();

        return page;
    }

    public void browseByCategory(String category) {
        WebElement selectCategoryField = getDriver().findElement(browseByCategoryInputField);
        Select select = new Select(selectCategoryField);
        List<WebElement> list = select.getOptions();
        for (WebElement option : list) {
            if (option.getText().contains(category)) {
                option.click();
                return;
            }
        }
    }

    public String getSelectedCategoryText(){
        WebElement selectCategoryField = getDriver().findElement(browseByCategoryInputField);
        Select select = new Select(selectCategoryField);
        WebElement selectedOption = select.getFirstSelectedOption();
        String option = selectedOption.getText();
        return option;
    }

    public StorePage clearCategory() {
        WaitUtils.presenceOfElementLocated(getDriver(), clearSelectionIcon).click();
        return new StorePage(getDriver());
    }


}
