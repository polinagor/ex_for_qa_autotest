package tests.lesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.bidi.script.LocalValue.setValue;

public class TestFormRegistration {
    @BeforeAll
    static void setup(){
        Configuration.browser= "chrome";
        Configuration.browserSize="1920x1080";
        System.setProperty("selenide.headless","false");
    }

    @Test
    void successfulFillFormTest() {
        open("https://demoqa.com/automation-practice-form");
    $("[id=firstName]").setValue("Polina");
    $("[id=lastName]").setValue("Gor");
    $("[id=userEmail]").setValue("polina@test.com");
    $("[id=userNumber]").setValue("7906909090");
    $("[id=currentAddress]").setValue("Москва, Вавилова улица");

    //ковыряюсь с датой
    $("#dateOfBirthInput").click();
    $(byText("23")).click();

    //ковыряюсь с другими полями
    $("[id=gender-radio-2]").click();
    $("[id=subjectsInput]").setValue("English").pressEnter();

    //чекбоксы чтобы их
    $("#hobbies-checkbox-1").parent().click();
    $("#hobbies-checkbox-2").parent().click();
    $("#state").click();
    $("#react-select-3-input")
                .shouldBe(Condition.visible);
    $(byText("NCR")).click();
    $("#state").shouldHave(text("NCR"));
    $("#city").click();
    $("#react-select-4-input")
                .shouldBe(Condition.visible);
    $(byText("Delhi")).click();
    $("#city").shouldHave(text("Delhi"));

    //загрузка картинки как вообще у меня не видно
    $("#uploadPicture").uploadFromClasspath("Кот.jpg");
    $("[id=submit]").click();

    //и что там назаполняли
    $(".modal-content").shouldHave(text("Student Name Polina Gor"));
    $(".modal-content").shouldHave(text("Student Email polina@test.com"));
    $(".modal-content").shouldHave(text("Gender Female"));
    $(".modal-content").shouldHave(text("Mobile 7906909090"));
    $(".modal-content").shouldHave(text("Date of Birth 23 July,2026"));
    $(".modal-content").shouldHave(text("Subjects English"));
    $(".modal-content").shouldHave(text("Hobbies Sports, Reading"));
    $(".modal-content").shouldHave(text("Picture Кот.jpg"));
    $(".modal-content").shouldHave(text("Address Москва, Вавилова улица"));
    $(".modal-content").shouldHave(text("State and City NCR Delhi"));
    }
}