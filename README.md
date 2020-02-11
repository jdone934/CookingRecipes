# Jacob Doney Individual Project

### Problem Statement

I recently moved to an apartment where I have a far better ability to cook for myself.
I also recently purchased a tablet which I use to follow recipes while I cook.
While there are plenty of sources around for finding recipes, there's a lack of good 
sites that have an easy to use interface while cooking. My aim is to create a website that
makes it easy to follow along with a recipe while you are cooking, while giving the user access
to different tools they may need while cooking. 

One "Nice to have" feature that served as a large motivation for this project is being able to control the website using Alexa.
One massive setback for using laptops/tablets for reading recipes while cooking is getting
the device dirty while cooking. Being able to control the website would be a huge benefit to
avoiding this drawback.

### Project Technologies/Techniques 

* Security/Authentication
  * Tomcat's JDBC Realm Authentication
  * Admin role: create/read/update/delete (crud) of all data
  * User role: create recipes, store favorite recipes, edit data they have entered previously
  * All: anyone can view trail information (no login)
* Database
  * MySQL
  * Store users and roles
  * Store all data for the recipes
* ORM Framework
  * Hibernate 5
* Dependency Management
  * Maven
* CSS 
  * Bootstrap or Materialize
* Data Validation
  * Bootstrap Validator for front end
  * Explore Hibernate's validation
* Logging
  * Configurable logging using Log4J2. In production, only errors will normally be logged, but logging at a debug level can be turned on to facilitate trouble-shooting. 
* Hosting
  * AWS
* Independent Research Topic/s
  * Alexa Skills
  * Alexa Voice Service
  * Alexa Skill Kit SDK (ASK SDK) for Java
  * Hibernate Validation
  * Hibernate Search
* Project Lombok to eliminate boilerplate code like getters/setters/equals
* Unit Testing
  * JUnit tests to achieve 80%+ code coverage 
* IDE: IntelliJ IDEA

### Design

* [User Stories](DesignDocuments/userStories.md)
* [Screen Design](DesignDocuments/Screens.md)
* [Application Flow](DesignDocuments/applicationFlow.md)
* [Database Design](DesignDocuments/databaseDiagram.png)

### [Project Plan](ProjectPlan.md)

### [Weekly Refelection](WeeklyReflection.md)