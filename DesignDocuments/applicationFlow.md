# Application Flow

### User Sign Up

1. User chooses sign up on the menu.
1. User fills out form to create a new account.
1. Request goes to servlet.
1. Servlet creates a user object and then creates user in the database.
1. Response to user confirming addition.

### User Sign Up Using Google or Facebook Account

1. User chooses to sign up on the home page.
1. User selects create account with Google/Facebook account.
1. Request is sent via API to create the account.
1. Response is received from API with successful account created.
1. Request goes to servlet to create the User.
1. Servlet creates a user object and then creates user in the database.
1. Response to user confirming addition

### User Sign In

1. User chooses sign in on the menu.
1. User enters username and password and submits.
1. If user is authenticated, the server will allow the user to access their account information and allow them to add new recipes.
1. If authentication fails, show error message/page.

### Search for new Recipe

1. User types a search phrase and category to search for on home page and submits.
1. Request is sent to servlet to search database for similar results.
1. Database responds with list of recipes that match the query.
1. Response from servlet provides recipe id, title, brief description, and display image for each recipe returned by the database. 
1. Display results to user.

### User Favorites a Recipe

1. User requests to favorite a recipe.
1. Request goes to servlet.
1. Recipe ID is saved in User's favorite list.
1. Response goes back to user, confirming the recipe was saved

### View Recipe

1. User selects Recipe from results list or from saved Recipes.
1. Request goes to servlet to collect information about the recipe.
1. Servlet queries database for complete recipe information.
1. Servlet creates a recipe object that contains information returned by database.
1. Response is sent by servlet and recipe is displayed to user.

### Add new Recipe

1. User selects to add a new recipe from their account page.
1. User fills out form with recipe information.
1. User submits form with recipe information.
1. Request goes to the servlet and a recipe object is created.
1. Recipe is submitted for storage in the database.
1. Response is sent to the user display success message.
