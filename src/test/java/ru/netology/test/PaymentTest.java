package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DbHelper;
import ru.netology.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;


public class PaymentTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @DisplayName("Успешная покупка тура")
    @Test
    void shouldPaymentApprovedCard() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var approvedCardInformation = DataHelper.getValidCard();
        goToPayment.payment(approvedCardInformation);
        goToPayment.verifySuccessNotificationCard();
        var statusPayment = DbHelper.getStatusPayment();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());

    }
    @DisplayName("Покупка тура отклоненной картой")
    @Test
    void shouldPaymentDeclinedCard() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var declinedCardInformation = DataHelper.getDeclinedCard();
        goToPayment.payment(declinedCardInformation);
        goToPayment.verifyErrorNotificationCard();
        var statusPayment = DbHelper.getStatusPayment();
        Assertions.assertEquals("DECLINED", DbHelper.getStatusPayment());

    }
    @DisplayName("Успешная покупка тура с текущей датой")
    @Test
    void shouldPaymentApprovedWithCurrentDate() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        goToPayment.payment(validCardInformation);
        goToPayment.verifySuccessNotificationCard();
        var statusPayment = DbHelper.getStatusPayment();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Покупка тура с пустыми полями в форме")
    @Test
    void shouldGetNotificationEmptyFields() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        goToPayment.payment(emptyCardInformation);
        goToPayment.verifyInvalidFormatCard();

    }
    @DisplayName("Покупка тура с пустым полем номера карты")
    @Test
    void shouldGetNotificationEmptyCardNumber() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var emptyCardInformation = DataHelper.getCardNumberEmpty();
        goToPayment.payment(emptyCardInformation);
        goToPayment.verifyInvalidFormatCard();
    }
    @DisplayName("Покупка тура с пустым полем номера месяца")
    @Test
    void shouldGetNotificationEmptyMonth() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var emptyMonth = DataHelper.getMonthEmpty();
        goToPayment.payment(emptyMonth);
        goToPayment.verifyInvalidFormatCard();
    }
    @DisplayName("Покупка тура с пустым полем года")
    @Test
    void shouldGetNotificationEmptyYear() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var emptyYear = DataHelper.getYearEmpty();
        goToPayment.payment(emptyYear);
        goToPayment.verifyInvalidFormatCard();
    }
    @DisplayName("Покупка тура с пустым полем Владелец")
    @Test
    void shouldGetNotificationEmptyCardOwnerField() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var cardOwnerEmpty = DataHelper.getHolderEmpty();
        goToPayment.payment(cardOwnerEmpty);
        goToPayment.verifyRequiredFieldCard();
    }
    @DisplayName("Покупка тура с пустым полем CVV")
    @Test
    void shouldGetNotificationEmptyCvv() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var CvvEmpty = DataHelper.getCVVEmpty();
        goToPayment.payment(CvvEmpty);
        goToPayment.verifyInvalidFormatCard();
    }
    @DisplayName("Покупка тура с неверным номером карты")
    @Test
    void shouldGetNotificationInvalidCardNumber() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var InvalidNumber = DataHelper.getInvalidNumber();
        goToPayment.payment(InvalidNumber);
        goToPayment.verifyInvalidFormatCard();
    }
    @DisplayName("Покупка тура с неверной датой (Месяц)")
    @Test
    void shouldGetNotificationInvalidMonth() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var InvalidDate = DataHelper.getInvalidMonth();
        goToPayment.payment(InvalidDate);
        goToPayment.verifyInvalidDateCard();
    }
    @DisplayName("Покупка тура с неверной датой (Год)")
    @Test
    void shouldGetNotificationInvalidYear() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var InvalidDate = DataHelper.getWrongYear();
        goToPayment.payment(InvalidDate);
        goToPayment.verifyInvalidDateCard();
    }
    @DisplayName("Покупка тура с истекшим сроком действия карты (Месяц)")
    @Test
    void shouldGetNotificationExpiredMonth() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var ExpiredMonth = DataHelper.getExpiredMonth();
        goToPayment.payment(ExpiredMonth);
        goToPayment.verifyInvalidDateCard();
    }
    @DisplayName("Покупатка тура с истекшим сроком действия карты (Год)")
    @Test
    void shouldGetNotificationExpiredYear() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var ExpiredYear = DataHelper.getExpiredYear();
        goToPayment.payment(ExpiredYear);
        goToPayment.expiredCardYear();
    }
    @DisplayName("Покупка тура с неверным кодом CVV")
    @Test
    void shouldGetNotificationInvalidCvv() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var InvalidCvv = DataHelper.getInvalidCVV();
        goToPayment.payment(InvalidCvv);
        goToPayment.verifyInvalidFormatCard();
    }
    @DisplayName("Покупка тура с Номером карты в виде нулей")
    @Test
    void shouldGetNotificationZeroCardNumber() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var ZeroCardNumber = DataHelper.getZeroCard();
        goToPayment.payment(ZeroCardNumber);
        goToPayment.verifyInvalidFormatCard();
    }
    @DisplayName("Покупка тура с нулевым месяцем")
    @Test
    void shouldGetNotificationZeroMonth() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var ZeroMonth = DataHelper.getZeroMonth();
        goToPayment.payment(ZeroMonth);
        goToPayment.verifyInvalidFormatCard();
    }
    @DisplayName("Покупка тура с нулевыми CVV")
    @Test
    void shouldGetNotificationZeroCvv() {
        var orderPage = new OrderPage();
        var goToPayment = OrderPage.goToPayment();
        var ZeroCvv = DataHelper.getZeroCVV();
        goToPayment.payment(ZeroCvv);
        goToPayment.verifyInvalidFormatCard();
    }
}

