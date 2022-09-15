drop table IF EXISTS term_topic;
drop table IF EXISTS description;
drop table IF EXISTS term;
drop table IF EXISTS topic;
drop table IF EXISTS vote;

create table users (
       id  serial not null,
        email varchar(255) not null,
        password varchar(255),
        status boolean,
        username varchar(255) not null,
        primary key (id)
    );


   create table user_role_assignment (
       id  serial not null,
        role varchar(255),
        users_id int4,
        primary key (id),
              CONSTRAINT fk_user
              FOREIGN KEY(users_id)
        	  REFERENCES users
    );

create TABLE term (
  id serial PRIMARY KEY,
  name varchar NOT NULL UNIQUE
);

create TABLE topic (
  id serial PRIMARY KEY,
  name varchar NOT NULL UNIQUE,
  color varchar
);

create TABLE term_topic (
  id serial PRIMARY KEY,
  term_id int NOT NULL,
  topic_id int NOT NULL,
  CONSTRAINT fk_term
      FOREIGN KEY(term_id)
	  REFERENCES term(id),
  CONSTRAINT fk_topic
      FOREIGN KEY(topic_id)
	  REFERENCES topic(id)
);


create TABLE description (
    id serial PRIMARY KEY,
    content varchar(1000) NOT NULL,
    create_date date NOT NULL,
    user_id int,
    term_id int,
    vote_point int NOT NULL,

    CONSTRAINT fk_user_description
      FOREIGN KEY(user_id)
	  REFERENCES users(id),
	   CONSTRAINT fk_term
      FOREIGN KEY(term_id)
	  REFERENCES term(id)

);

create TABLE vote (
    id serial PRIMARY KEY,
    description_id int,

    CONSTRAINT fk_description
    FOREIGN KEY(description_id)
    REFERENCES description(id)
);


