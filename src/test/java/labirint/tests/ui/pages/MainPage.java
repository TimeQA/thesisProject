package labirint.tests.ui.pages;


import com.codeborne.selenide.*;

import java.util.List;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {


    private final SelenideElement searchField = $("#search-field");

    private final SelenideElement basketLink = $(".cart-icon-js");

    private final SelenideElement favoritesLink = $(".b-header-b-personal-e-icon-count-m-putorder");

//    top-link-main_putorder
    private final SelenideElement headerMenu = $(".b-header-b-menu-wrapper");

    private final SelenideElement addBasketSomeProduct = $(".product_labeled:nth-child(3)");

    private final SelenideElement formaliseButtonSomeProduct = $(".product_labeled:nth-child(3)");

    private final SelenideElement selectedProductInBasketOrFavorites = $(".need-watch");

    private final SelenideElement quantityInBasket = $("li.ui-corner-top:nth-child(1)")
            .shouldBe(Condition.text("1"));

    private final SelenideElement quantityInFavourites  = $("li.ui-corner-top:nth-child(1)")
            .shouldBe(Condition.text("1"));

    private final ElementsCollection itemDropDownMenu = $$("ul li.b-menu-second-item");

//    Search
    private final ElementsCollection elementsOnPage = $$("div .genres-carousel__item");

//    Basket

    private final SelenideElement buttonAddBasket = $(".buy-link");

    private final SelenideElement myBasketTitle = $(".basket-page__title");

    public final MainPage openPage() {
        open(baseUrl);

        return this;
    }

    public final MainPage healthCheck() {
        elementsOnPage.shouldBe(CollectionCondition.sizeGreaterThan(0));

        return this;
    }

    public final MainPage clickButtonAddedProductBasket() {
        addBasketSomeProduct.$(byText("В КОРЗИНУ")).click();
        return this;
    }

    public final MainPage clickButtonFormaliseOrder() {
        formaliseButtonSomeProduct.shouldBe(Condition.text("ОФОРМИТЬ")).click();

        return this;
    }

    public final MainPage searchBook(final String bookName) {
        searchField.setValue(bookName).pressEnter();

        return this;
    }


    public final MainPage openBasketPage() {
        basketLink.click();

        return this;
    }


    public final MainPage selectItemHeaderMenu(String typeDevice) {
        headerMenu.$(byText(typeDevice)).hover();

        return this;
    }

    public final MainPage checkSubitemDropDownMenu(List<String> expectedTypeDevice) {
        itemDropDownMenu.contains(expectedTypeDevice);

        return this;
    }

    public final MainPage goFavoritesPage() {
        favoritesLink.click();

        return this;
    }

    public final MainPage goBasketPage() {
        basketLink.click();

        return this;

    }


    public final MainPage checkProductOnBasketOrFavoritesPage(String bookName) {
        selectedProductInBasketOrFavorites.shouldBe(Condition.text(bookName));

        return this;
    }



}