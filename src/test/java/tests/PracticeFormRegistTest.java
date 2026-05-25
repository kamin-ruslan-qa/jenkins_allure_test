package tests;
import org.junit.jupiter.api.Test;
import pages.PracticeFormRegistPage;
import pages.components.FinalComponent;
import tests.testdata.TestData;


public class PracticeFormRegistTest extends TestBase {


PracticeFormRegistPage practiceFormRegistPage = new PracticeFormRegistPage();
FinalComponent finalComponent = new FinalComponent();
TestData testData = new TestData();

    @Test
    void registrationFormTest()
    // Полная форма регистрации
    {
        useQaGuru();
        practiceFormRegistPage.openPage()
                .closeBanner()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeEmail(testData.userEmail)
                .setGender(testData.gender)
                .typeUserNumber(testData.userNumber)
                .setDateOfBirth(testData.day,testData.month,testData.year)
                .typeSubjects(testData.subjects)
                .setHobbies(testData.hobbies)
                .uploadPicture(testData.upload)
                .typecurrentAddress(testData.currentAddress)
                .setStateAndCity(testData.state, testData.city)
                .submitForm();

        finalComponent.chekResult("Student Name", testData.firstName + " " + testData.lastName)
                .chekResult("Student Email", testData.userEmail)
                .chekResult("Gender", testData.gender)
                .chekResult("Mobile", testData.userNumber)
                .chekResult("Date of Birth", testData.dateOfBirth)
                .chekResult("Subjects", testData.subjects)
                .chekResult("Hobbies", testData.hobbies)
                .chekResult("Picture", testData.upload)
                .chekResult("Address", testData.currentAddress)
                .chekResult("State and City", testData.state + " " + testData.city);

    }
    @Test
    void onlyRequiredFieldsTest()
    // регистрация с обязательными полями
    {
        useQaGuru();
        practiceFormRegistPage.openPage()
                .closeBanner()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .setGender(testData.gender)
                .typeUserNumber(testData.userNumber)
                .submitForm();

        finalComponent.chekResult("Student Name", testData.firstName + " " + testData.lastName)
                .chekResult("Gender", testData.gender)
                .chekResult("Mobile", testData.userNumber);

    }
    @Test
    void negativeScriptOnTheNameTest() {
        //негативный сценарий 1
        useQaGuru();
        practiceFormRegistPage.openPage()
                .closeBanner()
                .typeFirstName(testData.firstName)
                .submitForm();

        finalComponent.modalShouldNotBeVisible();
    }

    @Test
    void negativeScriptOnTheEmailTest() {
        //негативный сценарий 2
        useQaGuru();
        practiceFormRegistPage.openPage()
                .closeBanner()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeEmail(testData.inCorrectEmail)
                .submitForm();

        finalComponent.modalShouldNotBeVisible();
    }

    @Test
    void negativeScriptOnTheCheckingEmailTest()

    {
        //негативный сценарий 3
        useQaGuru();
        practiceFormRegistPage.openPage()
                .closeBanner()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeEmail(testData.inCorrectEmail)
                .submitForm();

        finalComponent.modalShouldNotBeVisible();
    }


    @Test
    void negativeScriptOnTheMobile()
    {
        //негативный сценарий 4
        useQaGuru();
        practiceFormRegistPage.openPage()
                .closeBanner()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeEmail(testData.userEmail)
                .typeUserNumber(testData.incorrectPhoneNumber)
                .submitForm();

        finalComponent.modalShouldNotBeVisible();
    }
}
