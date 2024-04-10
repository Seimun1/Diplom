package ru.netology.page;

import static com.codeborne.selenide.Condition.visible;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class OrderPage {
    private final SelenideElement heading = $(byText("Путешествие дня"));
    private static final SelenideElement paymentButton = $(byText("Купить"));
    private static final SelenideElement creditButton = $(byText("Купить в кредит"));

    public OrderPage() {

        heading.shouldBe(visible);
    }

    public static PaymentPage goToPayment() {
        paymentButton.click();
        return new PaymentPage();
    }

    public static CreditPage goToCredit() {
        creditButton.click();
        return new CreditPage();
    }
}

