CREATE TABLE trip (
    trip_id    SERIAL     NOT NULL PRIMARY KEY ,
    title     VARCHAR(100) NOT NULL ,
    destination VARCHAR(100) NOT NULL ,
    descption VARCHAR(100) NOT NULL ,
    start_date      DATE    ,
    end_date      DATE     ,
    user_id   INT     NOT NULL,

    CONSTRAINT fk_trip_user
        FOREIGN KEY(user_id)
            REFERENCES "user"(user_id
)