import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import io.qameta.allure.selenide.AllureSelenide;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SelenideTest {
    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест с Listener")
    @Tag("test")
    @Owner("kamin-ruslan-qa")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");

        $x("//button[@data-action='click:qbsearch-input#handleExpand']").click();
        $x("//input[@id='query-builder-test']").sendKeys("kamin-ruslan-qa/Java_Automatoin_QA");
        $x("//input[@id='query-builder-test']").submit();

        $x("//a[@href='/kamin-ruslan-qa/Java_Automatoin_QA']").click();
        $("#issues-tab").click();
        $(withText("NewTest")).should(Condition.exist);
    }

}