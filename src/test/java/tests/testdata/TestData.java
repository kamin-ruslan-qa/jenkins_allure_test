package tests.testdata;

import net.datafaker.Faker;
import utils.RandomUtils;
import static utils.RandomUtils.getMonthNumber;


public class TestData
{

 private final Faker faker = new Faker();
 private final RandomUtils randomUtils = new RandomUtils();
 public String repository = "kamin-ruslan-qa/Java_Automatoin_QA";
 public String url = "https://github.com";

       public final String firstName = faker.name().firstName();
       public final String lastName = faker.name().lastName();
       public final String userEmail = faker.internet().emailAddress();
       public final String userNumber = faker.phoneNumber().subscriberNumber(10);
       public final String gender = faker.options().option("Male", "Female", "Other");
       public final String year = String.valueOf(faker.number().numberBetween(1994, 2012));
       public final String month = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
       public final String day = String.valueOf(faker.number().numberBetween(1, 28));
       public final String subjects = faker.options().option("Maths", "Accounting", "Arts", "Biology", "Physics", "Chemistry", "Commerce", "History");
       public final String hobbies = faker.options().option("Sports", "Reading", "Music");
       public final String upload = faker.options().option("Test1.jpg", "Test2.jpg", "Test3.jpg");
       public final String currentAddress = faker.address().fullAddress();
       public final String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
       public final String city = randomUtils.getRandomCity(state);
       public final String userName = firstName + " " + lastName;
       public final String permanentAddress = faker.address().secondaryAddress();
       public final String dateOfBirth = String.format("%s-%02d-%02d", year, getMonthNumber(month), Integer.parseInt(day));
       public final String inCorrectEmail = firstName.toLowerCase() + "." + lastName.toLowerCase() + "ru";
       public final String incorrectPhoneNumber = faker.phoneNumber().subscriberNumber(8);
       public String issueName;
}