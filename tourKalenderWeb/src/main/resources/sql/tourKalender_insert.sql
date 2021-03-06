truncate table track;
INSERT INTO track VALUES (1,'user3', TIMESTAMP '2016-06-03 08:23:00', null, '');

truncate table trackpoint;
INSERT INTO trackpoint VALUES (1,3,'user3', 7.109564,50.732451,30.0,TIMESTAMP '2016-06-03 08:23:00', '');
INSERT INTO trackpoint VALUES (2,3,'user3', 7.110401,50.730563,30.0,TIMESTAMP '2016-06-03 08:23:20', '');
INSERT INTO trackpoint VALUES (3,3,'user3', 7.116461,50.724480,30.0,TIMESTAMP '2016-06-03 08:23:40', '');
INSERT INTO trackpoint VALUES (4,3,'user3', 7.123864,50.720540,30.0,TIMESTAMP '2016-06-03 08:24:00', '');
INSERT INTO trackpoint VALUES (5,3,'user3', 7.141137,50.716274,30.0,TIMESTAMP '2016-06-03 08:24:20', '');
INSERT INTO trackpoint VALUES (6,3,'user3', 7.148540,50.714182,30.0,TIMESTAMP '2016-06-03 08:24:40', '');
INSERT INTO trackpoint VALUES (7,3,'user3', 7.156097,50.710639,30.0,TIMESTAMP '2016-06-03 08:25:00', '');
INSERT INTO trackpoint VALUES (8,3,'user3', 7.161397,50.706929,30.0,TIMESTAMP '2016-06-03 08:25:20', '');


truncate table userstatus;
-- ONLINE_STARTED , ONLINE_LAST_PING , RECORD_STARTED , RECORD_LAST_PING

-- User 1 started the app
INSERT INTO userstatus VALUES (1,'user1', TIMESTAMP '2016-06-02 07:00:00', TIMESTAMP '2016-06-02 07:00:05', null , null );
-- User 2 started the app
INSERT INTO userstatus VALUES (2,'user2', TIMESTAMP '2016-06-01 09:30:00', TIMESTAMP '2016-06-01 09:34:05', null , null );
-- User 3 started the app and started the recording of a track
INSERT INTO userstatus VALUES (3,'user3', TIMESTAMP '2016-06-03 08:20:00', TIMESTAMP '2016-06-03 08:25:00', TIMESTAMP '2016-06-03 08:23:00',TIMESTAMP '2016-06-03 08:25:20');
commit;