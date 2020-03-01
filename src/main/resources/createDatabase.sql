DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS instruction;
DROP TABLE IF EXISTS favorite_recipes_users;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS image;

CREATE TABLE users (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `username` varchar(25) NOT NULL,
    `password` varchar(50) NOT NULL,
    `email` varchar(320) NOT NULL,
    `first_name` varchar(50),
    `last_name` varchar(50),
    `users_role` varchar(25) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE image (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `filepath` varchar(300) NOT NULL,
    `description` varchar(50),
    PRIMARY KEY (`id`)
);

CREATE TABLE recipe (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `description` varchar(300),
    `category` varchar(50),
    `users_id` int(10) NOT NULL,
    `image_id` int(10),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`users_id`)
        REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`image_id`)
        REFERENCES `image` (`id`)
);

CREATE TABLE favorite_recipes_users (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `users_id` int(10) NOT NULL,
    `recipe_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`users_id`)
        REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`recipe_id`)
        REFERENCES `recipe` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ingredient (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(25) NOT NULL,
    `unit_of_measurement` varchar(25) NOT NULL,
    `quantity_numerator` int(2) NOT NULL,
    `quantity_denominator` int(2) NOT NULL,
    `recipe_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`recipe_id`)
        REFERENCES `recipe` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE instruction (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `rank`int(2) NOT NULL,
    `description` varchar(500) NOT NULL,
    `image_id` int(10),
    `recipe_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`image_id`)
        REFERENCES `image` (`id`)
        ON UPDATE CASCADE,
    FOREIGN KEY (`recipe_id`)
        REFERENCES `recipe` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);