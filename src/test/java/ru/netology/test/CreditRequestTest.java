package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DbHelper;
import ru.netology.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;




public class CreditRequestTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @DisplayName("Успешная покупка тура в кредит")
    @Test
    void shouldCreditApprovedCard() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var approvedCardInformation = DataHelper.getValidCard();
        goToCredit.paymentCredit(approvedCardInformation);
        goToCredit.verifySuccessNotificationCreditCard();

        var statusPayment = DbHelper.getStatusCredit();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Покупка тура отклоненной картой")
    @Test
    void shouldCreditPaymentDeclinedCard() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var declinedCard = DataHelper.getDeclinedCard();
        goToCredit.paymentCredit(declinedCard);
        goToCredit.verifyErrorNotificationCreditCard();
        Assertions.assertEquals("DECLINED", DbHelper.getStatusCredit());
    }

    @DisplayName("Покупка тура в кредит с пустыми полями в форме")
    @Test
    void shouldGetNotificationEmptyFields() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        goToCredit.paymentCredit(emptyCardInformation);
        goToCredit.verifyInvalidFormatCreditCard();
    }

    @DisplayName("Успешная покупка тура в кредит с текущей датой")
    @Test
    void shouldCreditPaymentWithCurrentDate() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var validCardInformation = DataHelper.getCurrentMonthAndYear();
        goToCredit.paymentCredit(validCardInformation);
        goToCredit.verifySuccessNotificationCreditCard();
        var statusPayment = DbHelper.getStatusCredit();
        Assertions.assertEquals("APPROVED", statusPayment.getStatus());
    }

    @DisplayName("Покупка тура в кредит с пустым полем номера карты")
    @Test
    void shouldGetNotificationEmptyCardNumber() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        goToCredit.paymentCredit(fieldCardEmpty);
        goToCredit.verifyInvalidFormatCreditCard();
    }
    @DisplayName("Покупка тура в кредит с пустым полем номера месяца")
    @Test
    void shouldGetNotificationEmptyMonth() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var monthEmpty = DataHelper.getMonthEmpty();
        goToCredit.paymentCredit(monthEmpty);
        goToCredit.verifyInvalidFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с пустым полем года")
    @Test
    void shouldGetNotificationEmptyYear() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var yearEmpty = DataHelper.getYearEmpty();
        goToCredit.paymentCredit(yearEmpty);
        goToCredit.verifyInvalidFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с пустым полем Владелец")
    @Test
    void shouldGetNotificationEmptyCardOwnerField() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var cardOwnerEmpty = DataHelper.getHolderEmpty();
        goToCredit.paymentCredit(cardOwnerEmpty);
        goToCredit.verifyRequiredFieldCreditCard();
    }

    @DisplayName("Покупка тура в кредит с пустым полем CVV")
    @Test
    void shouldGetNotificationEmptyCvv() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var CvvEmpty = DataHelper.getCVVEmpty();
        goToCredit.paymentCredit(CvvEmpty);
        goToCredit.verifyInvalidFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с неверным номером карты")
    @Test
    void shouldGetNotificationInvalidCardNumber() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var invalidNumber = DataHelper.getInvalidNumber();
        goToCredit.paymentCredit(invalidNumber);
        goToCredit.verifyInvalidFormatCreditCard();
    }

    @DisplayName("Покупка тура в кредит с неверной датой (Месяц)")
    @Test
    void shouldGetNotificationInvalidMonth() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var invalidDate = DataHelper.getInvalidMonth();
        goToCredit.paymentCredit(invalidDate);
        goToCredit.verifyInvalidDateCreditCard();
    }
    @DisplayName("Покупка тура в кредит с неверной датой (Год)")
    @Test
    void shouldGetNotificationInvalidYear() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var invalidDate = DataHelper.getWrongYear();
        goToCredit.paymentCredit(invalidDate);
        goToCredit.verifyInvalidDateCreditCard();
    }

    @DisplayName("Покупка тура в кредит с истекшим сроком действия карты (Месяц)")
    @Test
    void shouldGetNotificationExpiredMonth() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var expiredMonth = DataHelper.getExpiredMonth();
        goToCredit.paymentCredit(expiredMonth);
        goToCredit.verifyInvalidDateCreditCard();
    }
    @DisplayName("Покупка тура в кредит с истекшим сроком действия карты (Год)")
    @Test
    void shouldGetNotificationExpiredYear() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var expiredYear = DataHelper.getExpiredYear();
        goToCredit.paymentCredit(expiredYear);
        goToCredit.expiredCreditCardYear();
    }
    @DisplayName("Покупка тура в кредит с неверным кодом CVV")
    @Test
    void shouldGetNotificationInvalidCvv() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var invalidCvv = DataHelper.getInvalidCVV();
        goToCredit.paymentCredit(invalidCvv);
        goToCredit.verifyInvalidFormatCreditCard();
    }
    @DisplayName("Покупка тура в кредит с номером карты в виде нулей")
    @Test
    void shouldGetNotificationZeroCardNumber() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var zeroCardNumber = DataHelper.getZeroCard();
        goToCredit.paymentCredit(zeroCardNumber);
        goToCredit.verifyInvalidFormatCreditCard();
    }
    @DisplayName("Покупка тура в кредит с нулевым месяцем")
    @Test
    void shouldGetNotificationZeroMonth() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var zeroMonth = DataHelper.getZeroMonth();
        goToCredit.paymentCredit(zeroMonth);
        goToCredit.verifyInvalidFormatCreditCard();
    }
    @DisplayName("Покупка тура в кредит с нулевым CVV")
    @Test
    void shouldGetNotificationZeroCvv() {
        var orderPage = new OrderPage();
        var goToCredit = OrderPage.goToCredit();
        var zeroCvv = DataHelper.getZeroCVV();
        goToCredit.paymentCredit(zeroCvv);
        goToCredit.verifyInvalidFormatCreditCard();
    }


}
