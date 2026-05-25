import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.WebSteps;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;


public class StepTest {
    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест с Steps")
    @Tag("test")
    @Owner("kamin-ruslan-qa")
    public void testIssueSearchSteps() {
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());

        steps.openMainPage();
        steps.clickSearchButton();
        steps.searchRepository();
        steps.clickIssues();
        steps.existIssues();
    }
}