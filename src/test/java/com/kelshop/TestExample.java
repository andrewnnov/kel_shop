package com.kelshop;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class TestExample {


    @Test
    public void findOneBook() {
        Selenide.open("https://www.kelediciones.com/");
        $("[placeholder='Find Books, Authors, ISBN...']").click();
        $("[placeholder='Find Books, Authors, ISBN...']").sendKeys("ANIMALS - Very First Words Library");
        $("label div button").click();
        //$("[placeholder='Find Books, Authors, ISBN...']").submit();
        $("div h1").shouldHave(Condition.text("ANIMALS - Very First Words Library"));
        $("div h3").shouldHave(Condition.text("ANIMALS - Very First Words Library"));
    }

    @Test
    public void findFantasy() {
        Selenide.open("https://www.kelediciones.com/");
        $("[title='Fiction']").hover();
        $("[title='Fantasy']").click();
        $("div h1").shouldHave(Condition.text("FANTASY"));
        String numberOfProductString = $(withText("Productos")).parent().getText();
        System.out.println(numberOfProductString);
        String result = numberOfProductString.replaceAll("[^0-9]", "");
        int resultInt = Integer.parseInt(result);
        Assertions.assertTrue(resultInt > 0);

    }


}
