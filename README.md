1) Установить JDK 8+.
2) Указать переменную окружения JAVA_HOME.
3) В удобном месте (где позже будет находиться папка с проектом) создать файл config.json со следующим содержимым:

{
  "iextoken": "<YOUR_PUBLISHABLE_TOKEN>"
}

, где вместо <YOUR_PUBLISHABLE_TOKEN> указать ваш открытый токен для IEX Cloud API.
4) Распаковать архив с проектом рядом с config.json (в той же папке).
5) Открыть консоль, перейти в корень проекта. Собрать проект Maven Wrapper-ом. Для этого выполнить без кавычек:
- в Linux: "mvnw clean install";
- в Windows: "mvnw.cmd clean install".
6) Перейти в папку target.
7) Запустить проект, выполнив "java -jar stocks-x.x.x.jar" без кавычек, где x.x.x - текущая версия сборки.
8) Добавить исключение в фаерволл, если будет блокировать работу приложения.
9) Отправить HTTP POST-запрос на адрес http://localhost:8080/portfolio, указав в заголовке "content-type: application/json" без кавычек, а в теле - информацию об интересующих компаниях и количестве акций в формате json, например:

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
