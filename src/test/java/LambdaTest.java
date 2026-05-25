import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.testdata.TestData;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class LambdaTest {
    TestData testData = new TestData();
    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест с Lambda")
    @Tag("test")
    @Owner("kamin-ruslan-qa")
    public void testIssueSearchLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Нажимаем на поиск", () -> {
            $x("//button[@data-action='click:qbsearch-input#handleExpand']").click();
        });

        step("Ищем репозиторий", () -> {
            $x("//input[@id='query-builder-test']").sendKeys(testData.repository);
            $x("//input[@id='query-builder-test']").submit();
        });

        step("Нажимаем на Issues", () -> {
            $x("//a[@href='/kamin-ruslan-qa/Java_Automatoin_QA']").click();
            $("#issues-tab").click();
        });

        step("Проверка наличия Issue", () -> {
            $(withText("NewTest")).should(Condition.exist);
        });
    }
}