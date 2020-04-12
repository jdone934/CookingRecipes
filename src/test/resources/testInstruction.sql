create table testInstruction
(
    id          int auto_increment primary key,
    `rank`      int          not null
--     description varchar(500) not null,
--     image_id    int          null,
--     recipe_id   int          not null,
--     constraint rank_recipeId_unique
--         unique (`rank`, recipe_id),
--     constraint instruction_ibfk_1
--         foreign key (image_id) references image (id)
--             on update cascade on delete cascade,
--     constraint instruction_ibfk_2
--         foreign key (recipe_id) references recipe (id)
--             on update cascade on delete cascade);
);