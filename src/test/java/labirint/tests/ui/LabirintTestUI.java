package labirint.tests.ui;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import labirint.helpers.RandomUtils;
import labirint.tests.ui.pages.UITestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LabirintTestUI extends UITestBase {

    @Test
    @DisplayName("Проверка главной страницы и наличие на ней элементов")
    void mainPageNotEmpty() {
        open("https://www.labirint.ru/");
        step("Проверка наличия элементов товаров на главной страцице", () -> {
            mainPage.healthCheck();
        });
    }

    @Test
    @DisplayName("Появление кнопки \"ОФОРМИТЬ\"")
    void appearanceButtonForOrder() {
        open("https://www.labirint.ru/");
        $(".product_labeled:nth-child(3)").$(byText("В КОРЗИНУ")).click();
        $(".product_labeled:nth-child(3)").$(byText("ОФОРМИТЬ")).click();
    }

    @Test
    @DisplayName("Появление кнопки \"ОФОРМИТЬ\"")
    void appearanceButtonForOrderPO() {
        open("https://www.labirint.ru/");

        step("Проверка наличия элементов товаров на главной страцице", () -> {
            mainPage.clickButtonAddedProductBasket();
        });

        step("Проверка наличия элементов товаров на главной страцице", () -> {
            mainPage.clickButtonFormaliseOrder();
        });
    }


    @ValueSource(strings = {"Огненный поток", "1984"})
    @ParameterizedTest(name = "Проверка добавления книги в раздел \"Отложено\" {0}")
    void addBookBasketAndFavourites(String bookName) {
        open("https://www.labirint.ru/");
        $("#search-field").setValue(bookName).pressEnter();
        $(".buy-link").click();
        $(".icon-fave:nth-child(1)").click();
        $(".cart-icon-js").click();
        $("li.ui-corner-top:nth-child(1)").shouldBe(Condition.text("1"));
        $("li.ui-corner-top:nth-child(2)").shouldBe(Condition.text("1"));
    }

    @ValueSource(strings = {"Огненный поток", "1984"})
    @ParameterizedTest(name = "Проверка добавления книги в раздел \"Отложено\" {0}")
    void addBookBasketAndFavouritesPO(String bookName) {
        open("https://www.labirint.ru/");
        step(String.format("Поиск книги %s", bookName), () -> {
            mainPage.searchBook(bookName);
        });

        step("Переход на страницу \"Отложено\"", () -> {
            mainPage.goFavoritesPage();
        });

        step(String.format("Проверка наличия книги в разделе  %s \"Отложено\"" , bookName), () -> {
            mainPage.goFavoritesPage();
        });
    }

    @ValueSource(strings = {"Огненный поток", "1984"})
    @ParameterizedTest(name = "Проверка добавления книги в корзину {0}")
    void checkAddBookBasket(String bookName) {
        open("https://www.labirint.ru/");
        $("#search-field").setValue(bookName).pressEnter();
        $(".buy-link").click();
        $(".cart-icon-js").click();
        $(".need-watch").shouldBe(Condition.text(bookName));
    }

    @Test
    @DisplayName("Регистрация")
    void registerUser() {
        open("https://www.labirint.ru/");
        $(".b-header-b-personal-e-list-item_cabinet").click();
        $(".full-input__input.formvalidate-error").setValue(RandomUtils.getEmail());
        $("#g-recap-0-btn").click();
    }


    static Stream<Arguments> actualCommonComplexAvtoRuDropMenuTest() {
        return Stream.of(
                Arguments.of("Игрушки", List.of( "Все игрушки", "Детское творчество", "Игры и Игрушки",
                        "Скидки", "Отзывы", "Новинки", "Рейтинг", "Производители", "Серии")),
                Arguments.of("Еще", List.of("CD/DVD", "Сувениры", "Журналы", "Товары для дома"))
        );
    }

    @DisplayName("Проверка drop-down menu на наличие разделов подменю")
    @MethodSource
    @ParameterizedTest(name = "Для меню \"{0}\" отображаются разделы \"{1}\"")
    void actualCommonComplexAvtoRuDropMenuTest (String typeDevice, List<String> expectedTypeDevice) {
        open("https://www.labirint.ru/");
        $(".b-header-b-menu-wrapper").$(byText(typeDevice)).hover();
        $$("ul li.b-menu-second-item").contains(expectedTypeDevice);
    }

    @DisplayName("Проверка drop-down menu на наличие разделов подменю")
    @MethodSource
    @ParameterizedTest(name = "Для меню \"{0}\" отображаются разделы \"{1}\"")
    void actualCommonComplexAvtoRuDropMenuTestPO (String typeDevice, List<String> expectedTypeDevice) {
        open("https://www.labirint.ru/");

        step("Проверка наличия элементов товаров на главной страцице", () -> {
            mainPage.selectItemHeaderMenu(typeDevice);
        });

        step("Проверка наличия элементов товаров на главной страцице", () -> {
            mainPage.checkSubitemDropDownMenu(expectedTypeDevice);
        });

    }
}
