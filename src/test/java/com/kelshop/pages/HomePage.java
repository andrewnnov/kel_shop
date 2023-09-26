package com.kelshop.pages;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Collections.*;

public class HomePage {

    private SelenideElement searchFieldHomePage = $("[placeholder='Find Books, Authors, ISBN...']");
    private SelenideElement searchButtonHomePage = $("label div button");
    //private SelenideElement sortOrderFilter = $(byText("Ordenar por"));
    private SelenideElement sortOrderFilter = $(byText("Más reciente"));
    private List<SelenideElement> listOfElements = $$("article h3");


    public void openHomePage() {
        open("https://www.kelediciones.com/");
        $("[title='Fiction']").should(Condition.appear);
        Assertions.assertEquals("Kel Ediciones", title());
    }

    public void findOneBook(String nameOfBook) {
        searchFieldHomePage.click();
        searchFieldHomePage.sendKeys(nameOfBook);
        searchButtonHomePage.click();
        $("div h1").shouldHave(Condition.text(nameOfBook));
        $("div h3").shouldHave(Condition.text(nameOfBook));
    }

    public void chooseSectionOfBook(String genre) {
        String cssSelector = String.format("[title='%s']", genre);
        $(cssSelector).hover();
    }

    public void chooseItemOfSection(String subgenre) {
        String cssSelector = String.format("[title='%s']", subgenre);
        $(cssSelector).click();
        $("div h1").shouldHave(Condition.text(subgenre));
        String numberOfProductString = $(withText("Productos")).parent().getText();
        String result = numberOfProductString.replaceAll("[^0-9]", "");
        int resultInt = Integer.parseInt(result);
        Assertions.assertTrue(resultInt > 0);
    }

    public void chooseSort(String sortName) {
        sortOrderFilter.click();
        SelenideElement element = $(byText(sortName));
        element.shouldBe(visible, Duration.ofSeconds(5));
        element.click();
        sleep(3000);

        List<String> nameOfBooks = listOfElements
                .stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());

        Assertions.assertTrue(nameOfBooks.get(0).startsWith("A"));
    }


}
