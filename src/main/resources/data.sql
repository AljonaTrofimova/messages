DROP TABLE IF EXISTS message;

CREATE TABLE message (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  text VARCHAR(250) NOT NULL,
  created VARCHAR(250) NOT NULL
);

INSERT INTO message (text, created) VALUES
  ('Text test', '2019-02-20');
  --('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  --('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');