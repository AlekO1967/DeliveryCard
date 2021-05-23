package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeliveryCardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    LocalDate today = LocalDate.now();
    LocalDate newDate = today.plusDays(4);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    void shouldEnterValidData() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").sendKeys("\b\b\b\b\b\b\b\b");
        $("[data-test-id='date'] input").setValue(formatter.format(newDate));
        $("[data-test-id='name'] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id='phone'] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldEnterNotValidCity() {
        $("[data-test-id='city'] input").setValue("Wendell");
        $("[data-test-id='date'] input").sendKeys("\b\b\b\b\b\b\b\b");
        $("[data-test-id='date'] input").setValue(formatter.format(newDate));
        $("[data-test-id='name'] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id='phone'] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".input_invalid[data-test-id='city']").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldNotEnterCity() {
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").sendKeys("\b\b\b\b\b\b\b\b");
        $("[data-test-id='date'] input").setValue(formatter.format(newDate));
        $("[data-test-id='name'] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id='phone'] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".input_invalid[data-test-id='city']").shouldHave(text("Поле обязательно для заполнения"));
    }

        @Test
    void shouldEnterNotValidName() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").sendKeys("\b\b\b\b\b\b\b\b");
        $("[data-test-id='date'] input").setValue(formatter.format(newDate));
        $("[data-test-id='name'] input").setValue("Aleksandr Osipov");
        $("[data-test-id='phone'] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".input_invalid[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotEnterName() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").sendKeys("\b\b\b\b\b\b\b\b");
        $("[data-test-id='date'] input").setValue(formatter.format(newDate));
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".input_invalid[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEnterNotValidPhone() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").sendKeys("\b\b\b\b\b\b\b\b");
        $("[data-test-id='date'] input").setValue(formatter.format(newDate));
        $("[data-test-id='name'] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id='phone'] input").setValue("+7980123456");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotEnterPhone() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").sendKeys("\b\b\b\b\b\b\b\b");
        $("[data-test-id='date'] input").setValue(formatter.format(newDate));
        $("[data-test-id='name'] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".input_invalid[data-test-id='phone']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldEmptyCheckBox() {
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='date'] input").sendKeys("\b\b\b\b\b\b\b\b");
        $("[data-test-id='date'] input").setValue(formatter.format(newDate));
        $("[data-test-id='name'] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id='phone'] input").setValue("+79801234567");
        //$("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".input_invalid[data-test-id='agreement']").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }


}
