package ru.netology.page;

import ru.netology.data.DataHelper;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;


public class CreditPage {
    private final SelenideElement heading = $(byText("Оплата по карте"));
    private final ElementsCollection fields = $$(".input__control");
    private final SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $("[placeholder='08']");
    private final SelenideElement year = $("[placeholder='22']");
    private final SelenideElement cardOwnerField = fields.get(3);
    private final SelenideElement cvv = $("[placeholder='999']");
    private final SelenideElement proceedButton = $(withText("Продолжить"));
    private final SelenideElement approvedNotification = $(withText("Успешно"));
    private final SelenideElement errorNotification = $(withText("Ошибка. Банк отказал в проведении операции"));
    private final SelenideElement invalidFormat = $(withText("Неверный формат"));
    private final SelenideElement requiredField = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement expiredYearError = $(withText("Истёк срок действия карты"));
    private final SelenideElement invalidDateError = $(withText("Неверно указан срок действия карты"));

    public CreditPage() {

        heading.shouldBe(visible);
    }

    public void paymentCredit(DataHelper.CardInformation cardInformation) {
        cardNumber.setValue(cardInformation.getCardNumber());
        month.setValue(cardInformation.getMonth());
        year.setValue(cardInformation.getYear());
        cardOwnerField.setValue(cardInformation.getHolder());
        cvv.setValue(cardInformation.getCVV());
        proceedButton.click();
    }

        public void verifySuccessNotificationCreditCard() {
            approvedNotification.shouldBe(visible, Duration.ofSeconds(15));
        }

        public void verifyErrorNotificationCreditCard() {
            errorNotification.shouldBe(visible, Duration.ofSeconds(15));
        }

        public void verifyInvalidFormatCreditCard() {
            invalidFormat.shouldBe(visible);
        }

        public void verifyRequiredFieldCreditCard() {
            requiredField.shouldBe(visible);
        }

        public void expiredCreditCardYear () {
            expiredYearError.shouldBe(visible);
        }

        public void verifyInvalidDateCreditCard() {
            invalidDateError.shouldBe(visible);
        }
    }


