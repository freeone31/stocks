1) Установить JDK 8+.
2) Указать JAVA_HOME.
3) Распаковать архив.
4) Перейти в корень проекта. Собрать проект Maven Wrapper-ом. Для этого:
- в Linux: `mvnw clean install`
- в Windows: `mvnw.cmd clean install`
5) Перейти в папку target.
6) Выполнить `java -jar stocks-0.0.1.jar`.
7) Добавить исключение в фаерволл, если будет мешать.
8) http://localhost:8080/greeting
