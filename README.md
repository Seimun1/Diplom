# Дипломная работа

### Документация: 

1. [План автоматизации](https://github.com/AlexSashaNik/QA_Diploma/blob/main/Plan.md)
2. [Отчет по итогам тестирования](https://github.com/AlexSashaNik/QA_Diploma/blob/main/Report.md)
3. [Отчет по итогам автоматизации](https://github.com/AlexSashaNik/QA_Diploma/blob/main/Summary.md)

### Запуск тестов:

#### Работа с базой данных MySQl:

1. 'docker-compose up -d' - запуск контейнеров
2. 'java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:3306/app' - запуск приложения
3. './gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app' - запуск автотестов
4. './gradlew allureReport','./gradlew allureServe' - создание отчета Allure

#### Работа с базой данных PostgreSQl:

1. 'docker-compose up -d' - запуск контейнеров
2. 'java -jar ./artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app' - запуск приложения
3. './gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app'- запуск автотестов
4. './gradlew allureReport', './gradlew allureServe' - создание отчета Allure