CREATE TABLE IF NOT EXISTS country
(
    id               serial PRIMARY KEY,
    country          varchar(120),
    country_iso_code varchar(2)
);

CREATE TABLE IF NOT EXISTS region
(
    id             serial PRIMARY KEY,
    region_fias_id uuid,
    region         varchar(120),
    time_zone      varchar(50),
    country_id     integer NOT NULL references country (id)
);

CREATE TABLE IF NOT EXISTS locality
(
    id                serial PRIMARY KEY,
    locality_fias_id  uuid,
    locality_kladr_id char(19),
    locality_type     varchar(10),
    locality          varchar(120),
    region_id         integer NOT NULL references region (id)
);

CREATE TABLE IF NOT EXISTS settlement
(
    id                 serial PRIMARY KEY,
    settlement_fias_id uuid,
    settlement_type    varchar(10),
    settlement         varchar(120),
    locality_id        integer NOT NULL references locality (id)
);

CREATE TABLE IF NOT EXISTS street
(
    id             serial PRIMARY KEY,
    street_fias_id uuid,
    street_type    varchar(10),
    street         varchar(120),
    settlement_id  integer references settlement (id),
    locality_id    integer references locality (id)
);

CREATE TABLE IF NOT EXISTS house
(
    id            serial PRIMARY KEY,
    house_fias_id uuid,
    house_type    varchar(10),
    house         varchar(50),
    block_type    varchar(10),
    block         varchar(50),
    street_id     integer references street (id)

);

CREATE TABLE IF NOT EXISTS result
(
    id             serial PRIMARY KEY,
    fias_level     integer,
    porches_number integer,
    address        varchar(120)
);

CREATE TABLE IF NOT EXISTS entrance
(
    id        serial PRIMARY KEY,
    number    integer,
    from_en   integer,
    to_en     integer,
    result_id integer
);