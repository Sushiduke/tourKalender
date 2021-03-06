
CREATE TABLE gpxtrack (
  ID        INTEGER      NOT NULL IDENTITY,
  USERID    VARCHAR(100) NOT NULL,
  CREATED   TIMESTAMP    NOT NULL,
  DATA      CLOB(1024K),
  COMMENT   VARCHAR(100)
);


CREATE TABLE trackpoint (
  ID        INTEGER      NOT NULL IDENTITY,
  TRACKID   INTEGER      NOT NULL,
  USERID    VARCHAR(100) NOT NULL,
  LONGITUDE DOUBLE       NOT NULL,
  LATITUDE  DOUBLE       NOT NULL,
  ELEVATION DOUBLE,
  CREATED   TIMESTAMP    NOT NULL,
  COMMENT   VARCHAR(100)
);


CREATE TABLE userstatus (
  ID                 INTEGER      NOT NULL IDENTITY,
  USERID             VARCHAR(100) NOT NULL,
  ONLINE_STARTED     TIMESTAMP,
  ONLINE_LAST_PING   TIMESTAMP,
  RECORD_STARTED     TIMESTAMP,
  RECORD_LAST_PING   TIMESTAMP
);

CREATE TABLE position (
  ID        INTEGER      NOT NULL IDENTITY,
  LONGITUDE DOUBLE       NOT NULL,
  LATITUDE  DOUBLE       NOT NULL,
  ELEVATION DOUBLE,
  DATETIME  TIMESTAMP    NOT NULL,
  COMMENT   VARCHAR(100)
);

CREATE TABLE message (
  ID        INTEGER      NOT NULL IDENTITY,
  TITLE     VARCHAR(100) NOT NULL,
  TEXT      VARCHAR(100) NOT NULL,
  created   TIMESTAMP    NOT NULL
);