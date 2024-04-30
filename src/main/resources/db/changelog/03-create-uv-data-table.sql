CREATE TABLE uv_data (
                      id BIGSERIAL PRIMARY KEY NOT NULL,
                      uv_index FLOAT NOT NULL,
                      longitude FLOAT NOT NULL,
                      latitude FLOAT NOT NULL,
                      skin_type INTEGER NOT NULL,
                      minutes INTEGER NOT NULL
);