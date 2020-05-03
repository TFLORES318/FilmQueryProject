## Film Query - Week 7 Skill Distillery


### Overview (what the project is, how to do it, how to run it)
- This project allows the user to search through a film database of 1000 films and find information on a film based on whether they search with a film's id or a keyword.
- To begin, the user will be presented with a menu, where they can enter in a film's id or enter in a keyword.
- By entering in the film id, the film's information (title, description, release year, language, rating and actors in film) are displayed.
- If the user enters in the keyword, all of the film's information as mentioned above are displayed. But every film that has this keyword inputted by the user will be displayed.

All methods are located in the DatabaseAccessorObject class. This class has an interface called DatabaseAccessor which contains all methods declared. The FilmQueryApp class is the only class with a main that runs the program. There is an Actor and Film class completed with setters, getters, to String, hashcode and equals as well as constructors.

To start, a scanner is passed to the method where menu selection is displayed within a do-while loop.

If the user chooses option 1, a method is called to find the film by the id entered in. This is located in the Database Accessor Object class. At this point, the connection to the database is made and the request is initiated. The SQL query is made and executed. A response is then returned and handled. The response received, after being handled, is the data returned and then it is printed out back in the film query app. In this case, if the user enters a valid film id, a connection is made to the sdvid database. Once made, the information is received from the film table as well as the language table. SQL keywords - SELECT, FROM, JOIN and WHERE are included in the query and this is formed as a prepared statement and sent to the database. Since this program is taking in user input, the parameter ? does not have a value yet. Once this query is executed, binding occurs which will bind all values to the parameters. In this case, the film object is being created that will be returned based on the id the user entered in.

If the user chooses option 2, a method is called to find the films (based on title or description) that contain the keyword that the user entered. For example, if the user entered "cat," all films will be returned that have "cat" in their title or description, whether as a stand alone word or if it's contained within a word. A list is made at the execution of this method and all steps completed to establish connection to database and get result are used. In this case, a while loop with the iterator next() is used to find all films with this keyword. This is then added to the list made in the beginning of the method and all film objects with the keyword are displayed by running the list through a for each loop back in the main app.


### Technologies Used
- Eclipse
- Java
- Terminal Commands
- GitHub/Git
- Atom
- MAMP
- Apache Maven
- SQL
- MySQL
- ERD


### Lessons Learned
- This project was a good introduction in understanding how Java will interact with the database.
- Practice in implementing referential integrity.
- Java Database Connectivity API introduction
- Connecting to database, executing queries and prepared statements, obtaining result and handling results.
- The set-up has become more clear as well as how a Java program connects to the database.
