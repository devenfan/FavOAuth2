DROP TABLE IF EXISTS fav_client;
DROP TABLE IF EXISTS fav_user;
DROP TABLE IF EXISTS fav_web_app;

CREATE TABLE fav_client (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY ,
  client_name   VARCHAR(100),
  client_id     VARCHAR(100),
  client_secret VARCHAR(100)
);

CREATE TABLE fav_user (
  id       BIGINT AUTO_INCREMENT PRIMARY KEY ,
  username VARCHAR(100),
  password VARCHAR(100),
  salt     VARCHAR(100)
);


CREATE TABLE fav_web_app (
  webId       BIGINT AUTO_INCREMENT PRIMARY KEY ,
  webKey      VARCHAR(100),
  webName     VARCHAR(100),
  webProfile  VARCHAR(100),
  webCategory VARCHAR(100),
  webUrl      VARCHAR(100),
  webState    VARCHAR(100)
);
