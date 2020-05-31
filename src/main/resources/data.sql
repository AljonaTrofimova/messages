drop table IF EXISTS message;

create TABLE message (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  text VARCHAR(250) NOT NULL,
  created TIMESTAMP NOT NULL
);

insert into message (text, created) values
  ('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', now());