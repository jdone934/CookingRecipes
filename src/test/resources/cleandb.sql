DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS instruction;
DROP TABLE IF EXISTS favorite_recipes_users;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `username` varchar(25) NOT NULL,
    `password` varchar(50) NOT NULL,
    `email` varchar(320) NOT NULL,
    `first_name` varchar(50),
    `last_name` varchar(50),
    `user_role` varchar(25) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE recipe
(
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `description` varchar(300),
    `category` varchar(50),
    `created_by_user_id` int(10),
    PRIMARY KEY (`id`),
    foreign key (`created_by_user_id`) references  users(id)
            on update cascade
);

create table instruction
(
    id          int auto_increment primary key,
    recipeRank      int          not null,
    description varchar(500) not null,
    recipe_id   int          not null,
    constraint recipeRank_recipeId_unique
        unique (recipeRank, recipe_id),
    foreign key (recipe_id) references recipe (id)
            on update cascade on delete cascade
);

CREATE TABLE image
(
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `filepath` varchar(300) NOT NULL,
    `recipe_id` int,
    `instruction_id` int,
    `description` varchar(50),
    PRIMARY KEY (`id`),
    foreign key (recipe_id) references recipe (id)
            on update cascade on delete cascade,
    foreign key (instruction_id) references instruction (id)
            on update cascade on delete cascade,
    unique key (recipe_id),
    unique key (instruction_id)
);

CREATE TABLE favorite_recipes_users
(
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `users_id` int(10) NOT NULL,
    `recipe_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    unique key (users_id, recipe_id)
);

CREATE TABLE ingredient
(
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `name` varchar(25) NOT NULL,
    `unit_of_measurement` varchar(25) NOT NULL,
    `quantity_numerator` int(2) NOT NULL,
    `quantity_denominator` int(2) NOT NULL,
    `recipe_id` int(10) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE role (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(25) NOT NULL,
    `username` varchar(15) NOT NULL,
    `user_id` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `role_id_uindex` (`id`),
    KEY `role_user_user_id_fk` (`user_id`),
    CONSTRAINT `role_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO
    users (id, username, password, email, first_name, last_name, user_role)
    VALUES (1, 'jdone934', 'AWESOME PASSWORD', 'jdone934@hotmail.com', 'Jacob', 'Doney', 'admin'),
           (2, 'lktucker', 'SUPER AWESOME PASSWORD', 'lktucker@gmail.com', 'Laura', 'Tucker', 'user'),
           (3, 'zfabry', 'KINDA LAME PASSWORD', 'zfabry@gmail.com', 'Zach', 'Fabry', 'user'),
           (4, 'acollegnon', 'BEST PASSWORD', 'acollegnon@gmail.com', 'Ashley', 'Fabry', 'user');

INSERT INTO
    recipe (id, name, created_by_user_id)
    VALUES (1, 'Doney\'s BBQ Pork', 1),
           (2, 'Laura\'s Panag Curry', 2);

INSERT INTO
    instruction (id, recipeRank, description, recipe_id)
    VALUES (1, 1, 'First, place the roast in the roaster and fill with water until the water covers about half the pork. Cook on LOW for about 10 hours.', 1),
           (2, 2, 'Then, remove the roast form the heat and shred it, discarding as much fat as you can. You can then place the meat back into the roaster.', 1),
           (3, 3, 'Next, mix the bbq sauce, lemon juice adn onion together in a mixing bowl.  The onion needs to be chopped well and sauteed on low-medium heat. When cooking the onion for the roast, make sure it doesn\'t brown (this will take about 30 minutes).', 1),
           (4, 4, 'After mixing the bbq, lemon juice and onion together, pout into the roaster and mix thoroughly into the meat.', 1),
           (5, 5, 'Cook the mixture on LOW for another 3 hours, stirring about once every hour to ensure the meat doesn\'t burn.', 1);

INSERT INTO
    image (id, filepath, description, recipe_id, instruction_id)
    VALUES (1, 'catLightning.png', 'cat shooting lightning from his paws', 1, null),
           (2, 'waterfall.jpeg', 'waterfall from Grand Portage', null, 1);

INSERT INTO
    ingredient (id, name, unit_of_measurement, quantity_numerator, quantity_denominator, recipe_id)
    VALUES (1, 'Pork Roast', 'lbs', 3, 1, 1),
           (2, 'BBQ Sauce', 'oz', 18, 1, 1),
           (3, 'Medium Onion', 'qty', 1, 1, 1),
           (4, 'Lemon Juice', 'tbsp', 3, 1, 1);

INSERT INTO
    favorite_recipes_users (id, users_id, recipe_id)
    VALUES (1, 1, 1),
           (2, 4, 1),
           (3, 3, 1),
           (4, 1, 2),
           (5, 2, 2);

INSERT INTO
    role (id, role_name, username, user_id)
    VALUES (1,'admin','jdone934',1);