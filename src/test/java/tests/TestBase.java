package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase
{
    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @BeforeAll
    static  void  beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x2080";
//      Configuration.browserVersion= "144.0";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
//        Configuration.browserVersion = "128.0";
    }

    protected static void useQaGuru() {
        Configuration.baseUrl = "https://qa-guru.github.io";
    }
//      Configuration.pageLoadStrategy = "eager";
//      Configuration.timeout = 10000; // default 4000

    @AfterEach
    void afterEach(){
        closeWebDriver();
    }
}

