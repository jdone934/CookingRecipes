# Design/Code Review 1

## Project: Preschool Forward

### Developer: Suparin Fhlug

### Reviewer: Jacob Doney

|Item |Considerations| Comments/Suggestions|
|--------|---------|---|
|||Reviewer comments and suggestions go here. Each item should have *at least* one "kudos" and two suggestions for improvement|
|**Problem Statement**|1. Accurately describes project purpose<br> 2. Is professional and free of typos, slang, etc.<br> 3. Fully explains the problem and the solution<br> 4. Is understandable by the average person|Kudos<br>1. Your project plan provides a good overview of what your website will do.<br><br>Suggestions <br>1. The problem statement seems too brief to me. It would be helpful to have more information why this project is being done and what you plan to do.<br>2. There's a partial sentence at the end of your problem statement stating "Getting Started". This seems to be leftover from a template and could be removed|
|**Design Documentation**|1. Navigation/flow through the application is logical and easy to use. <br>2. The order in which values are displayed are logical and easy to understand/use <br>3. The order in which the form fields entered are logical and easy to understand/use<br>4. All data discussed/documented (problem statement, flow, db design, etc.) is represented on the screens |Kudos<br>1. User Stories covers everything you described you wanted your website to do when you presented it to Matt and myself.<br><br>Suggestions <br>1. Project plan in the projectPlan.md is very broad. Going into some granular detail about when you plan to accomplish certain tasks may help to keep you on track.<br>2.  It was difficult to find a few of these documents. Creating a directory or a linking them in your readme.md would be useful.|
|**Data model/Database**|1. Everything on the screens and problem statement/flow is represented in the model <br> 2. There is at least one 1-to-many relationship.<br> 3. The model represents good database design <br>|Kudos<br>1. The relationships between these tables seem complicated at first glance and you do a good job of assigning the correct relationship.<br><br>Suggestions <br>1. I would create a new table for parents. There's a lot of information in the student table that only pertains to the parent.<br>2.  Admin and Teacher seem like they could be user_roles rather than their own tables.|
|**Code**|1. Proper Maven project structure is used<br> 2. a .gitignore file for IntelliJ Java projects has been implemented <br> 3. There is not any redundant or copy/paste code in the JSPs or classes<br> 4. Classes are appropriately-sized (no monster classes)<br> Property files are used appropriately: no hard-coded values <br> 5. Logging statements are used rather than System.out.println and printStackTrace.<br> 6. There are appropriate unit tests/code coverages.|Kudos<br>1. Your code is well documented and easy to read!<br><br>Suggestions <br>1. Implementing the Generic DAO you created further would help to reduce some redundency in your persistence code.<br>2. I would recommend including a logger in your testing classes to make debugging easier.|  
  












