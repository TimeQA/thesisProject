package labirint.tests.ui;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class LabirintWeb {

//    @Test
//    void addBookInBasketAndFavourites(final TestInfo info) {
//        if (info.getDisplayName().equals("Проверка отображения инфо-текста на странице Закладки")) {
//            return;
//        }
//
//        step(String.format("Открытие главной страницы %s", "https://www.labirint.ru/"), () -> {
//            mainPage.openPage();
//        });
//    }

//    @ValueSource(strings = {"Берсерк", "Дэн Браун"})
//    @ParameterizedTest(name = "Результаты поиска не пустые для запроса {0}")
//    void searchBookNotEmpty(String bookName) {
//        open("https://www.labirint.ru/");
//        step(String.format("Поиск книг %s", bookName), () -> {
//            mainPage.searchBook(bookName);
//        });
//
//        step(String.format("Проверка наличия книг по запросу %s", bookName), () -> {
//            mainPage.checkResultSearch();
//        });
//    }

//    @Disabled
//    @Tag("UI")
//    @ValueSource(strings = {"Death Note", "Dead Guilty", "The Art of War"})
//    @ParameterizedTest(name = "Поиск книги по названию {0}")
//    void searchBookTest(final String bookName) {
//
//        step(String.format("Поиск книги %s", bookName), () -> {
//            mainPage.searchBook(bookName);
//        });
//
//        step(String.format("Проверка отображения книги %s", bookName), () -> {
//            searchPage.checkIfBookExist(bookName);
//        });
//    }
}
