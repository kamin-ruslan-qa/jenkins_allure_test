package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.testdata.CategoreType;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class BookStoreTests {


    @BeforeEach
    void setUp() {
        // Открываем главную страницу интернет-магазина книг
        open("https://bi-bi.ru/");
    }

    @ValueSource(strings = {
            "Масло",
            "Инструмент",
            "Ключ"
    })
    @ParameterizedTest(name = "При поиске товара {0} отображается список с карточками товаров")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String bookName) {
        $(".confirm-city-popup__close-button").click();
        $("input[type='search']").setValue(bookName).pressEnter();
        $$(".card-catalog").shouldBe(sizeGreaterThan(0));

    }

    @CsvSource(value = {
            "Тормозные диски, По вашему запросу “Тормозные диски",
            "Ароматизатор, По вашему запросу “Ароматизатор"
    })
    @ParameterizedTest(name = "При поиске товара {0} в заголовке результатов поиска должно быть название {1}")
    @Tag("BLOCKER")
    void searchResultsMustContainTheTitle(String searchProduct , String expectedDescription) {
        $(".confirm-city-popup__close-button").click();
        $("input[type='search']").setValue(searchProduct).pressEnter();
        $("h1").shouldHave(text(expectedDescription));
    }


    @EnumSource(CategoreType.class)
    @ParameterizedTest(name = "При выборе каегории {0} отображается соответствующий заголовок")
    void testCategory(CategoreType CategoreType) {
        $(".confirm-city-popup__close-button").click();
        $(".header-sticky__catalog-btn").click();
        $$(".catalog-category__title").find(text(CategoreType.name())).click();
        $("h1").shouldHave(text(CategoreType.header));
    }
}