package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormRegistPage {
    CalendarComponent calendar = new CalendarComponent();


    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genderContainer = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement currentAddress = $("#currentAddress");
    private final SelenideElement stateSelect = $("#state");
    private final SelenideElement citySelect = $("#city");
    private final SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private final SelenideElement submitButton = $("#submit");

    public PracticeFormRegistPage openPage() {
        open("/one-page-form/automation-practice-form.html");

        return this;
    }
    public PracticeFormRegistPage closeBanner(){
            executeJavaScript("""
            document.getElementById('fixedban')?.remove();
            document.querySelector('footer')?.remove();
            document.querySelector('.banner')?.remove();
            document.querySelector('[class*="banner"]')?.remove();
            """);
            return this;
    }

    public PracticeFormRegistPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormRegistPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormRegistPage typeEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormRegistPage setGender(String value) {
        genderContainer.$(byText(value)).click();

        return this;
    }

    public PracticeFormRegistPage typeUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public PracticeFormRegistPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);

        return this;
    }

    public PracticeFormRegistPage typeSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public PracticeFormRegistPage setHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public PracticeFormRegistPage uploadPicture(String value) {
        uploadPictureInput.uploadFromClasspath(value);

        return this;
    }

    public PracticeFormRegistPage typecurrentAddress(String value) {
        currentAddress.setValue(value);

        return this;
    }

    public PracticeFormRegistPage setState(String value) {
        stateSelect.click();
        stateCityContainer.$(byText(value)).click();
        return this;
    }

    public PracticeFormRegistPage setCity(String value) {
        citySelect.click();
        stateCityContainer.$(byText(value)).click();
        return this;
    }

    public PracticeFormRegistPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);
        return this;
    }

    public PracticeFormRegistPage submitForm() {
        submitButton.click();
        return this;
    }

}
