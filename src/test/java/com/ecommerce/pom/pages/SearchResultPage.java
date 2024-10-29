package com.ecommerce.pom.pages;

public interface SearchResultPage {
    boolean containsSubstringInProductNames(String substring);

    int countItemsOnPage(String category);

    String getSearchHeaderTitle();
}
