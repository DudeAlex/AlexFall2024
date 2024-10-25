package com.ecommerce.pom.components;

import com.ecommerce.pom.pages.MenPage;
import com.ecommerce.pom.pages.StorePage;
import com.ecommerce.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LeftSidebar extends BaseComponent {

    // "Search Products" section
    By searchInputField = By.xpath("//input[@type='search']");
    By searchButton = By.xpath("//button[@value='Search']");

    // "Our Best Sellers" section
    By bestSellerTitle = By.cssSelector(".widget.woocommerce.widget_top_rated_products >.widget-title");
    By bestSellersItems = By.cssSelector(".product_list_widget>li");

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

    By searchResultPageWithBlue = By.xpath("//h2[@class='woocommerce-loop-product__title']");

    By notFoundMsgLocator = By.xpath("//p[@class='woocommerce-info woocommerce-no-products-found']");
    By menCategorySearchHeader = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");

    By searchResultMenCategory = By.xpath("//span[@class='ast-woo-product-category']");

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

    public String getSelectedCategoryText() {
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

    public String getBestSellerTitle() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), bestSellerTitle).getText();
    }

    public List<WebElement> getBestSellersList() {
        return WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), bestSellersItems);
    }

    //specific method for search result with word "blue"
    public List<String> getExpectedSearchResultListWithBlue() {
        List<String> expectedSearchResultList = new ArrayList<>();
        List<WebElement> searchResult = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), searchResultPageWithBlue);
        for (WebElement search : searchResult) {
            String text = search.getText();
            if (text.toLowerCase().contains("blue")) {
                expectedSearchResultList.add(text);
            }
        }
        return expectedSearchResultList;
    }

    public String getNotFoundMessageText() {
        return WaitUtils.visibilityOfElementLocated(getDriver(), notFoundMsgLocator).getText();

    }

    public <T> T selectCategoryByIndex(int index, T page) {
        WebElement dropdown = WaitUtils.visibilityOf(getDriver(), browseByCategoryInputField,10);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        return page;
    }

    public List<String> getActualSortedListManCategory() {
        WaitUtils.visibilityOfElementLocated(getDriver(), menCategorySearchHeader);
        List<String> actualSortedList = new ArrayList<>();

        List<WebElement> sortedList = WaitUtils.visibilityOfAllElementsLocatedBy(getDriver(), searchResultMenCategory);

        for (WebElement category : sortedList) {
            actualSortedList.add(category.getText());
        }
        return actualSortedList;

    }

}



