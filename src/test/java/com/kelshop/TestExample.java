package com.kelshop;

import com.kelshop.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


public class TestExample {

    HomePage homePage;

    @BeforeEach
    public void openHomePageTest() {
        homePage = new HomePage();
        homePage.openHomePage();
    }

    @Test
    public void findOneBookTest() {
        homePage.findOneBook("ANIMALS - Very First Words Library");
    }

    @Test
    public void chooseSectionOfBookTest() {
        homePage.chooseSectionOfBook("Fiction");
        homePage.chooseItemOfSection("Fantasy");
    }

    @Test
    public void sortListOfFoundBookTest() {
        homePage.chooseSectionOfBook("Fiction");
        homePage.chooseItemOfSection("Fantasy");
        homePage.chooseSort("Nombre A-Z");
    }

}
