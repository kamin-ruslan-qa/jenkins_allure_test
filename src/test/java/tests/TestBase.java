package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase
{
    @BeforeAll
    static  void  beforeAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x2080";
//      Configuration.browserVersion= "144.0";
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

