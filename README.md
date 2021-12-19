# Distance calculator

### Web service (REST) application for distance calculation
Приложение считает дистанцию между двумя городами. Для вычисления используются два способа.
* CrowFlight - с помощью формулы вычисления дистанции на сфере.
* DistanceMatrix - с помощью матрицы дистанций из базы.

### Стек:
* Spring + Tomcat
* Maven
* MySQL DB
* Liquibase
* Java 11

### Использование:
1. Запустить приложение.


2. Импорт данных в БД. 

   В программе "Postman" выполнить POST-запрос. Тип тела запроса - "form-data".
    
    Key - "document"

    Value - .xml файл с данными (Например src/main/resources/input.xml)
    
    Адрес запроса:

        http://localhost:8080/input 
   Пример:
   ![offers](https://github.com/ahsel21/distance-calculator/blob/master/src/main/resources/docs/input.png)


3. Просмотр списка городов из БД.

   Для просмотра списка всех городов выполнить GET-запрос на

        http://localhost:8080/city
   
   Пример:
   ![offers](https://github.com/ahsel21/distance-calculator/blob/master/src/main/resources/docs/city.png)


4. Вычисление дистанции.

   В программе "Postman" выполнить POST-запрос. Тип тела запроса - "raw".

   Пример запроса:

        {
        "from" : ["Самара", "Москва"],
        "to" : ["Тольятти"],
        "type" : "ALL"
        }

   Адрес запроса:

        http://localhost:8080/distance  

   Графа "type" может принимать конкретный тип вычисления (Например "CROWFLIGHT" или "DISTANCE_MATRIX")

   Пример:
   ![offers](https://github.com/ahsel21/distance-calculator/blob/master/src/main/resources/docs/distance.png)
