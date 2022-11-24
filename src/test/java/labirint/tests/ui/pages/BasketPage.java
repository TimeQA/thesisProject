package labirint.tests.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasketPage {

    private final SelenideElement itemName = $(".basket-item__link");

    public final BasketPage checkBookInBasket(final String bookName) {
        itemName.shouldHave(Condition.text(bookName));

        return this;
    }
}