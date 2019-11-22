1. Установить JDK 8+.
2. Указать переменную окружения JAVA_HOME.
3. Для работы с приложением необходимо получить открытый токен с IEX Cloud API, и подставлять его в командах ниже вместо <YOUR_PUBLISHABLE_TOKEN>.
4. Распаковать архив с проектом.
5. Открыть консоль, перейти в корень проекта. Собрать проект Maven Wrapper-ом. Для этого выполнить:
* в Linux: `./mvnw clean install -Diextoken=<YOUR_PUBLISHABLE_TOKEN>`
* в Windows: `./mvnw.cmd clean install -Diextoken=<YOUR_PUBLISHABLE_TOKEN>`

, где вместо <YOUR_PUBLISHABLE_TOKEN> указать ваш открытый токен для IEX Cloud API.

6. Перейти в папку target.
7. Запустить проект, выполнив `java -Diextoken=<YOUR_PUBLISHABLE_TOKEN> -jar stocks-x.x.x.jar`, где x.x.x - текущая версия сборки.
8. Добавить исключение в фаерволл, если будет блокировать работу приложения.
9. Отправить HTTP POST-запрос на адрес http://localhost:8080/portfolio, указав в заголовке `content-type: application/json`, а в теле - информацию об интересующих компаниях и количестве акций в формате json, например:

```json
{
  "stocks": [
    {
      "symbol": "AAPL",
      "volume": 50
    },
    {
      "symbol": "HOG",
      "volume": 10
    },
    {
      "symbol": "MDSO",
      "volume": 1
    },
    {
      "symbol": "IDRA",
      "volume": 1
    },
    {
      "symbol": "MRSN",
      "volume": 1
    }
  ]
}
```

10. Для удобства можно создать файл config.json со следующим содержимым:

```json
{
  "iextoken": "<YOUR_PUBLISHABLE_TOKEN>"
}
```

, и поместить его рядом с папкой, в которой лежит исполняемый файл stocks-x.x.x.jar, например:


```
config.json
folder
`-- stocks-x.x.x.jar
```

В этом случае токен будет браться из конфига, и его можно не передавать в свойствах, а приложение будет запускаться командой `java -jar stocks-x.x.x.jar`.

Если сборка и запуск проекта осуществляются посредством IDE, то файл config.json должен находиться рядом с папкой проекта.