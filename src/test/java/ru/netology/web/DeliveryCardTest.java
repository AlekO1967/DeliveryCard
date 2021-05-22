package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class DeliveryCardTest {

    @Test
    void shouldEnterValidData() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Воронеж");
        $("[data-test-id='name'] input").setValue("Александр Сергеевич Осипов");
        $("[data-test-id='phone'] input").setValue("+79801234567");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}