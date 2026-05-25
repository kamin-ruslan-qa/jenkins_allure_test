package pages.components;

import com.codeborne.selenide.SelenideElement;
import pages.PracticeFormRegistPage;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class FinalComponent {

    public final SelenideElement chekResultComponent = $("#resultBody");
    public final SelenideElement resultModal = $("#resultModal");
    private final SelenideElement fieldError = $(".field-error");


    public FinalComponent chekResult (String key, String value){
        chekResultComponent
                .$$("tr")
                .findBy(text(key))
                .$$("td")
                .get(1)
                .shouldHave(text(value));

        return this;
    }
    public FinalComponent modalShouldNotBeVisible() {
        resultModal.shouldNotBe(visible);
        return this;
    }
        public FinalComponent fieldErrorShouldBeVisible() {
            fieldError.shouldNotBe(visible);
        return this;

}
}
