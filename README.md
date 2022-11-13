# Java pre-project. Задача 3.1.4

## Информация по запуску приложения

### 1. Запустить приложение
### 2. Нужно добавить роли пользователей в таблицу my_role:
```aidl
INSERT INTO my_role(id, name)
  VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'); 
```
### 3. Регистрируем в базе данных одного пользователя (пароль будет назначен '100' без кавычек)
```aidl
INSERT INTO my_user (username, surname, age, email, password)
    value ('admin', 'admin', 50, 'admin@gmail.com', 
    '$2a$12$KagsNtJ.bquY.g05lLc4ie0kFBSttpSQVkLuVxXE53VlalmMmGYK6');
```

### 4. после регистрации нового пользователя, добавьте в таблицу пользователь-роли запись, дающую эту роль:
```aidl
INSERT INTO my_user_roles(user_id, roles_id)
  VALUES (1, 2);
```
Приложение успешно запущено!