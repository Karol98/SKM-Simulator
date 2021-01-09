        CREATE TABLE trains (
        id                          INT AUTO_INCREMENT NOT NULL,
        currentStation              INT UNSIGNED,
        goingToGdansk               BOOLEAN,
        currentPauseTime            INT UNSIGNED,
        PRIMARY KEY                 (id)
    );

    CREATE TABLE compartments (
        id                          INT AUTO_INCREMENT NOT NULL,
        capacity                    INT UNSIGNED,
        train_id                    INT NOT NULL,
        PRIMARY KEY                 (id),
        FOREIGN KEY(train_id)       REFERENCES trains(id)
    );

    CREATE TABLE persons (
        id                          INT AUTO_INCREMENT NOT NULL,
        firstName                   VARCHAR(20),
        lastName                    VARCHAR(30),
        destination                 INT UNSIGNED,
        compartment_id              INT NOT NULL,
        PRIMARY KEY                 (id),
        FOREIGN KEY(compartment_id) REFERENCES compartments(id)
    );

    INSERT INTO trains(currentStation, goingToGdansk, currentPauseTime) VALUES (4,true,2);
    INSERT INTO trains(currentStation, goingToGdansk, currentPauseTime) VALUES (7,true,0);
    INSERT INTO compartments(capacity, train_id) VALUES (20,1);
    INSERT INTO compartments(capacity, train_id) VALUES (20,2);
    INSERT INTO compartments(capacity, train_id) VALUES (20,2);
    INSERT INTO persons(firstName, lastName, destination, compartment_id) VALUES ("Karol","Skwierawski",8,1);