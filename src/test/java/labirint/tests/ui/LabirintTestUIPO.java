package labirint.tests.ui;

import com.codeborne.selenide.Condition;
import labirint.helpers.RandomUtils;
import labirint.tests.TestBase;
import labirint.tests.ui.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LabirintTestUIPO extends TestBase {

    MainPage mainPage = new MainPage();


    @Test
    @DisplayName("Проверка главной страницы и наличие на ней элементов")
    void mainPageNotEmptyPO() {
        open("https://www.labirint.ru/");
        step("Проверка наличия элементов товаров на главной страцице", () -> {
            mainPage.healthCheck();
        });
    }

    @Test
    @DisplayName("Появление кнопки \"ОФОРМИТЬ\"")
    void appearanceButtonForOrderPO() {
        open("https://www.labirint.ru/");

        step("Добавление товара в корзину", () -> {
            mainPage.selectProduct();
        });

        step("Переход на страницу оформления товара по кнопке \"ОФОРМИТЬ\"", () -> {
            mainPage.clickButtonFormaliseOrder();
        });
    }


    @DisplayName("Проверка добавления книги в раздел \"Отложено\"")
    @ValueSource(strings = {"Огненный поток", "1984"})
    @ParameterizedTest(name = "Проверка добавления книги в раздел \"Отложено\" {0}")
    void addBookBasketAndFavouritesPO(String bookName) {
        open("https://www.labirint.ru/");
        step(String.format("Поиск книги %s", bookName), () -> {
            mainPage.searchBook(bookName);
        });

        step("Нажать на кнопку \"Отложить\"", () -> {
            mainPage.addFirstProductInFavorites();
        });

        step("Переход на страницу \"Отложено\"", () -> {
            mainPage.goFavoritesPage();
        });

        step(String.format("Проверка наличия книги в разделе  %s \"Отложено\"" , bookName), () -> {
            mainPage.checkProductOnBasketOrFavoritesPage(bookName);
        });
    }
    @DisplayName("Проверка добавления книги в корзину")
    @ValueSource(strings = {"Огненный поток", "1984"})
    @ParameterizedTest(name = "Проверка добавления книги в корзину {0}")
    void checkAddBookBasketPO(String bookName) {
        open("https://www.labirint.ru/");

        step(String.format("Поиск книги %s", bookName), () -> {
            mainPage.searchBook(bookName);
        });

        step(String.format("Поиск книги %s", bookName), () -> {
            mainPage.clickButtonAddedProductBasket();
        });


        step("Переходим в корзину", () -> {
            mainPage.goBasketPage();
        });

        step(String.format("Проверка наличия книги в разделе  %s \"Отложено\"" , bookName), () -> {
            mainPage.checkProductOnBasketOrFavoritesPage(bookName);
        });
    }

    static Stream<Arguments> actualCommonComplexAvtoRuDropMenuTestPO() {
        return Stream.of(
                Arguments.of("Игрушки", List.of( "Все игрушки", "Детское творчество", "Игры и Игрушки",
                        "Скидки", "Отзывы", "Новинки", "Рейтинг", "Производители", "Серии")),
                Arguments.of("Еще", List.of("CD/DVD", "Сувениры", "Журналы", "Товары для дома"))
        );
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
