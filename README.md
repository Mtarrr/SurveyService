# SurveyService
Данный проект демонстрирует реализацию Spring Security в связке с JWT токенами и предоставляет собой сервис опроса о любимых вещах с регистрацией и аутентификацией.

# Реализованные функции
* Регистрация пользователя и вход в систему с JWT-аутентификацией
* Шифрование паролей с помощью BCrypt
* Авторизация на основе ролей с помощью Spring Security
* Заполнение форм опроса, сохранение сущностей в БД и валидация полей
* Обработка ошибок
* Логирование

# Используемые технологии
* Spring Boot 2.7.10
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
* Docker
* PostgreSQL

# Сборка и запуск проекта
1. Скопировать репозиторий на свой компьютер: git clone https://github.com/Mtarrr/SurveyService.git
2. Перейти в папку проекта: cd SurveyService
3. Собрать проект: mvn clean install -DskipTests
4. Собрать проект в Docker: docker-compose build
5. Запустить проект в Docker: docker-compose up -d

Приложение будет доступно по адресу http://localhost:8090.

