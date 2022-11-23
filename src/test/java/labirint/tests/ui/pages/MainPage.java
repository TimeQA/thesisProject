package labirint.tests.ui.pages;


import com.codeborne.selenide.*;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement searchField = $("#search-field");

    private final SelenideElement basketLink = $(".cart-icon-js");
    private final SelenideElement shopsLink = $$(".header-menu__item")
            .findBy(Condition.text("Адреса магазинов"));
    private final SelenideElement souvenirsLink = $$(".nav__item.nav__item_popup")
            .findBy(Condition.text("Сувениры"));

    private final SelenideElement quantityInBasket = $("li.ui-corner-top:nth-child(1)").shouldBe(Condition.text("1"));

    private final SelenideElement quantityInFavourites  = $("li.ui-corner-top:nth-child(1)").shouldBe(Condition.text("1"));

//    Search
    private final ElementsCollection resultSearch = $$("div .genres-carousel__item");

    public final MainPage openPage() {
        open(baseUrl);

        return this;
    }

    public final MainPage searchBook(final String bookName) {
        searchField.setValue(bookName).pressEnter();

        return this;
    }

    public final MainPage openShopsPage() {
        shopsLink.click();

        return this;
    }

    public final MainPage openSouvenirsPage() {
        souvenirsLink.click();

        return this;
    }

    public final MainPage openBasketPage() {
        basketLink.click();

        return this;
    }

    public final MainPage checkResultSearch() {
        resultSearch.shouldBe(CollectionCondition.sizeGreaterThan(0));

        return this;
    }
}