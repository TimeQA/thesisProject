package labirint.tests.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    private final SelenideElement bookTitle = $(".product-card__title");
    private final SelenideElement buyButton = $$(".js__card_button_text").first();
    private final SelenideElement purchaseButton = $$(".js__card_button_text")
            .findBy(Condition.text("Оформить"));

    public final SearchPage checkIfBookExist(final String bookName) {
        bookTitle.shouldHave(Condition.text(bookName));

        return this;
    }

    public final SearchPage buyButtonClick(final String buyText) {
        buyButton.shouldBe(Condition.text(buyText));
        buyButton.click();

        return this;
    }

    public final SearchPage checkIfTextChanged(final String purchaseText) {
        buyButton.shouldBe(Condition.text(purchaseText));

        return this;
    }

    public final SearchPage purchaseButtonClick() {
        purchaseButton.click();

        return this;
    }
}