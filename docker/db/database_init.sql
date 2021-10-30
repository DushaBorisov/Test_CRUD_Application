
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INTEGER,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users ( name, surname, age, login, password)
    VALUES ('Andrey', 'Borisov', 19, 'andrey', '123456');