## Отчет по итогам автоматизации
### Что было запланировано и что реализовано:

1. Выполнена настройка SUT и подключение к двум СУБД - MySQl, Postgresql (при помощи Docker).
2. Составлен план тестирования, написано 36 автотестов.
3. Протестирована работа сервиса на двух СУБД (MySQL, Postgresql).
4. Проведено автоматизированное тестирование работы веб-сервиса "Покупка тура Мараккеш".
5. Сгенерирован и проанализирован отчет в Allure.
6. Заведено 8 issues (bug-reports) по найденным в работе веб-сервиса багам.
7. Составлена отчетная документация по итогам тестирования.

**Риски:**
1. Из-за отсутствия полноценного технического задания невозможно оценить правильность выбора сценария автотестирования.
2. Из-за отсутствия уникальных css-селекторов есть риск того, что тесты будут провалены в будущем. Также, это усложнило работу с локаторами и потребовало больше времени на проведение тестирования.


**Не было выполнено:**
1. Не была произведена интеграция с CI Actions и Appveyor.

**Общий итог по времени:**
1. Создание проекта в IntelliJIDEA на базе Gradle, настройка SUT - 24 часа.
2. Составление плана автоматизации - 4 часа.
3. Написание автотестов - 50 часов.
4. Оформление issues - 5 часов.
5. Подготовка отчетных документов - 4 часа.

**ИТОГО:** 87 часов.

*На проект было запланировано 48 часов, а фактически было потрачено времени почти в 2 раза больше.*





