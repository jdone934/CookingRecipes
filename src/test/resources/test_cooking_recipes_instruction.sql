INSERT INTO test_cooking_recipes.instruction (id, `rank`, description, image_id, recipe_id) VALUES (1, 1, 'First, place the roast in the roaster and fill iwth water until the water covers about half the pork. Cook on LOW for about 10 hours.', null, 1);
INSERT INTO test_cooking_recipes.instruction (id, `rank`, description, image_id, recipe_id) VALUES (2, 2, 'Then, remove the raost form the heat and shred it, discarding as much fat as you can. You can then place the meat back into the roaster.', null, 1);

INSERT INTO test_cooking_recipes.testInstruction (id, `rank`, recipe_id) values (1, 1, 1);
INSERT INTO test_cooking_recipes.testInstruction (id, rank, recipe_id) values (2, 2, 1);