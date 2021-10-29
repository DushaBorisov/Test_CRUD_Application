CREATE TABLE IF NOT EXISTS public.users
(
    id SERIAL PRIMARY KEY,
    name character varying(255) ,
    surname character varying(255),
    age integer,
    login character varying(255),
    password character varying(255)
 );
