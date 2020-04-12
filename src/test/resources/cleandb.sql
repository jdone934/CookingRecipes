DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS instruction;
DROP TABLE IF EXISTS favorite_recipes_users;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS image;
CREATE TABLE users (`id` int(10) NOT NULL AUTO_INCREMENT, `username` varchar(25) NOT NULL, `password` varchar(50) NOT NULL, `email` varchar(320) NOT NULL, `first_name` varchar(50), `last_name` varchar(50), `user_role` varchar(25) NOT NULL, PRIMARY KEY (`id`));
CREATE TABLE image (`id` int(10) NOT NULL AUTO_INCREMENT, `filepath` varchar(300) NOT NULL, `description` varchar(50), PRIMARY KEY (`id`));
CREATE TABLE recipe (`id` int(10) NOT NULL AUTO_INCREMENT, `name` varchar(100) NOT NULL, `description` varchar(300), `category` varchar(50), `created_by_user_id` int(10) NOT NULL, `image_id` int(10), PRIMARY KEY (`id`), FOREIGN KEY (`created_by_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (`image_id`) REFERENCES `image` (`id`));
CREATE TABLE favorite_recipes_users (`id` int(10) NOT NULL AUTO_INCREMENT, `users_id` int(10) NOT NULL, `recipe_id` int(10) NOT NULL, PRIMARY KEY (`id`), FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE);
CREATE TABLE ingredient (`id` int(10) NOT NULL AUTO_INCREMENT, `name` varchar(25) NOT NULL, `unit_of_measurement` varchar(25) NOT NULL, `quantity_numerator` int(2) NOT NULL, `quantity_denominator` int(2) NOT NULL, `recipe_id` int(10) NOT NULL, PRIMARY KEY (`id`), FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE);

create table instruction
(
    id          int auto_increment primary key,
    recipeRank      int          not null,
    description varchar(500) not null,
    image_id    int          null,
    recipe_id   int          not null,
    constraint recipeRank_recipeId_unique
        unique (recipeRank, recipe_id),
    constraint instruction_ibfk_1
        foreign key (image_id) references image (id)
            on update cascade on delete cascade,
    constraint instruction_ibfk_2
        foreign key (recipe_id) references recipe (id)
            on update cascade on delete cascade);

INSERT INTO users (id, username, password, email, first_name, last_name, user_role) VALUES (1, 'jdone934', 'AWESOME PASSWORD', 'jdone934@hotmail.com', 'Jacob', 'Doney', 'admin'), (2, 'lktucker', 'SUPER AWESOME PASSWORD', 'lktucker@gmail.com', 'Laura', 'Tucker', 'user'), (3, 'zfabry', 'KINDA LAME PASSWORD', 'zfabry@gmail.com', 'Zach', 'Fabry', 'user'), (4, 'acollegnon', 'BEST PASSWORD', 'acollegnon@gmail.com', 'Ashley', 'Fabry', 'user');
INSERT INTO image (id, filepath, description) VALUES (1, 'catLightning.png', 'cat shooting lightning from his paws'), (2, 'waterfall.png', 'waterfall from Grand Portage');
INSERT INTO recipe (id, name, created_by_user_id) VALUES (1, "Doney's BBQ Pork", 1), (2, "Laura's Panag Curry", 2);
INSERT INTO instruction (id, recipeRank, description, image_id, recipe_id) VALUES (1, 1, 'First, place the roast in the roaster and fill iwth water until the water covers about half the pork. Cook on LOW for about 10 hours.', null, 1);
INSERT INTO instruction (id, recipeRank, description, image_id, recipe_id) VALUES (2, 2, 'Then, remove the raost form the heat and shred it, discarding as much fat as you can. You can then place the meat back into the roaster.', null, 1);