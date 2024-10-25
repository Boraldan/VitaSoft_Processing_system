# Тестовый проект для VitaSoft по управлению заявками

## Общая информация

Данный проект представляет собой систему управления заявками с использованием монолитной архитектуры. 
В системе реализованы роли пользователей: администратор, оператор и обычный пользователь. 
Каждый пользователь имеет определенные права и ограничения, позволяющие им взаимодействовать с системой в зависимости от их роли.

### Основные функции:

- Пользователи могут создавать, редактировать и отправлять заявки.
- Операторы могут просматривать, принимать и отклонять заявки.
- Администраторы могут управлять пользователями и назначать им роли.
- В проекте протестированы несколько методов с тестами, где используется библиотека Mockito.
- 
### Обзор используемых технологий:

- Spring Framework
- Spring Boot
- RestTemplate
- Spring Security
- Spring Data JPA
- Hibernate
- SQL, HQL
- PostgreSQL
- Thymeleaf
- Spring Validation
- Docker, Docker Compose
- Lombok

## Запуск базы данных в Docker

Для работы проекта используется база данных PostgreSQL, которая разворачивается с помощью Docker. Ниже представлены шаги по запуску базы данных:

1. Убедитесь, что у вас установлен Docker.
2. Создайте файл `docker-compose.yml` с следующим содержимым:

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:latest
    container_name: postgres_db
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 123
      POSTGRES_DB: vita_db
    ports:
      - "15432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/vita_db

volumes:
  postgres_data:



