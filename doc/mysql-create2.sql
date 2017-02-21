
-- ------------------------------------------------------------------------------

DROP TABLE IF EXISTS fav_user;
DROP TABLE IF EXISTS fav_auth_code;
DROP TABLE IF EXISTS fav_auth_token;
DROP TABLE IF EXISTS fav_open_api;
DROP TABLE IF EXISTS fav_open_api_authorize;
DROP TABLE IF EXISTS fav_client_credential;
DROP TABLE IF EXISTS fav_open_account;

-- 最终用户
CREATE TABLE fav_user (
  id  VARCHAR(100) NOT NULL PRIMARY KEY ,
  username VARCHAR(100),
  password VARCHAR(100),
  salt     VARCHAR(100),
  create_at DATETIME(6)
);

-- 开放账户
CREATE TABLE fav_open_account (
  id  VARCHAR(100) NOT NULL PRIMARY KEY ,
  username VARCHAR(100),
  password VARCHAR(100),
  salt     VARCHAR(100),
  create_at DATETIME(6)
);

-- 开放账户授权
CREATE TABLE fav_client_credential (
  id  VARCHAR(100) NOT NULL PRIMARY KEY ,
  name VARCHAR(100) NOT NULL UNIQUE ,
  client_id     VARCHAR(100),
  client_secret VARCHAR(100),
  redirect_uri  VARCHAR(100),
  open_account_id BIGINT,
  create_at DATETIME(6),
  CONSTRAINT FOREIGN KEY (open_account_id) REFERENCES fav_open_account(id)
);

-- 授权code
CREATE TABLE fav_auth_code (
  id VARCHAR(100) NOT NULL PRIMARY KEY,
  code VARCHAR(100) NOT NULL UNIQUE,
  authenticated_userid VARCHAR(100),
  scope VARCHAR(100),
  credential_id VARCHAR(100),
  created_at DATETIME(6),
  CONSTRAINT FOREIGN KEY (credential_id) REFERENCES fav_client_credential(id)
);

-- 授权token
CREATE TABLE fav_auth_token (
  id VARCHAR(100) NOT NULL PRIMARY KEY,
  access_token VARCHAR(100) NOT NULL UNIQUE,
  token_type VARCHAR(100),
  refresh_token VARCHAR(100) NOT NULL UNIQUE,
  expires_in INT,
  authenticated_userid VARCHAR(100),
  scope VARCHAR(100),
  credential_id VARCHAR(100),
  created_at DATETIME(6),
  CONSTRAINT FOREIGN KEY (credential_id) REFERENCES fav_client_credential(id)
);

-- 开放API列表
CREATE TABLE fav_open_api (
  id  VARCHAR(100) NOT NULL PRIMARY KEY ,
  api_code  VARCHAR(100) NOT NULL UNIQUE ,
  api_name VARCHAR(100) NOT NULL UNIQUE ,
  api_profile VARCHAR(100),
  api_category VARCHAR(100),
  api_url VARCHAR(100)
);

-- 开放API授权情况
CREATE TABLE fav_open_api_authorize (
  api_id VARCHAR(100),
  api_state VARCHAR(100), -- 开放状态
  credential_id  VARCHAR(100),
  created_at DATETIME(6),
  PRIMARY KEY (api_id, credential_id),
  CONSTRAINT FOREIGN KEY (credential_id) REFERENCES fav_client_credential(id)
);
