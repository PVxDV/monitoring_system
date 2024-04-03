# Monitoring System

## Назначение проекта
Проект служит для демонстрации работы, запуска и настройки Apache Kafka и состоит из двух микросервисов:
- `Metrics Producer`
- `Metrics Consumer`

## Стек
- `Spring Boot`
- `Spring Data Jpa`
- `Spring Actuator`
- `PostgreSQL`
- `Kafka`
- `Swagger`
- `Docker Compose`

### Metrics Producer
Сервис предоставляет api для отправки списка метрик `Mecric` на кластер `Kafka`.
Так же, сервис каждые `5` секунд автоматически отправляет список метрик о своей работе на кластер `Kafka` в топик `metrics-topic`.
Все действия `Metrics Producer` выводятся в консоль.
Настройки по умолчанию:
- топик `metrics-topic`: `partitions(3)`, `replicas(3)`, `min.insync.replicas = 2`
- доступ к прямым эндпоинтам `Spring Actuator` полностью закрыт;
- по адресу `http://localhost:8081/swagger-ui.html` доступна документация.

### Metrics Consumer
Сервис предоставляет api для получения списка метрик `Mecric` или конкретной метрики по `metric_id`.
Сервис обрабатывает сообщения из кластера `Kafka` топика `metrics-topic` и сохраняет в базу данных `PostgreSQL`.
Настройки по умолчанию:
- по адресу `http://localhost:8080/swagger-ui.html` доступна документация.

### Запуск

Запустить:
```
docker-compose up
```
Порты занимаемые по умолчанию:
- 8080 (`Metrics Consumer`)
- 8081 (`Metrics Producer`)
- 5432 (`PostgreSQL`)
- 2181,3181,4181,9092,9093,9094 (кластер `Kafka`)