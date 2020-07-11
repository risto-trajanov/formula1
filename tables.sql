CREATE TABLE IF NOT EXISTS driver(
    id varchar(20),
    version timestamp,
    name_of_driver varchar ,
    deleted bit,
    team_id varchar FOREIGN KEY REFERENCES team(id),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS team(
    id varchar,
    version timestamp,
    name_of_team varchar(50),
    deleted bit,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS season(
    id varchar,
    version timestamp,
    start_date date,
    end_date date,
    deleted bit,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS race(
    id varchar,
    version timestamp,
    name_of_race varchar(50),
    date_of_race date,
    deleted bit,
    season_id varchar FOREIGN KEY REFERENCES season(id),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS team_placement(
    id varchar,
    version timestamp,
    deleted bit,
    race_id varchar FOREIGN KEY REFERENCES race(id),
    team_id varchar FOREIGN KEY REFERENCES team(id),
    place integer,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS driver_placement(
    id varchar,
    version timestamp,
    deleted bit,
    race_id varchar FOREIGN KEY REFERENCES race(id),
    driver_id varchar FOREIGN KEY REFERENCES driver(id),
    place integer,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS leaderboard_teams(
    id varchar,
    version timestamp,
    deleted bit,
    season_id varchar FOREIGN KEY REFERENCES season(id),
    team_id varchar FOREIGN KEY REFERENCES team(id),
    place integer,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS leaderboard_drivers(
    id varchar,
    version timestamp,
    deleted bit,
    season_id varchar FOREIGN KEY REFERENCES season(id),
    driver_id varchar FOREIGN KEY REFERENCES driver(id),
    place integer,
    PRIMARY KEY(id)
);