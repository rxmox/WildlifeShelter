# WildLifeSHelter


Application : Wildlife Rescue  
Provides : Tasks based on the animals & GUI to allow user to create a schedule txt file  

## Table of Contents
- [Members](#Members)
- [Installation](#installation)
- [Usage](#Usage)
- [Documentation](#Documentation)

## Members

* Omar Ahmed
* Marcus Gee
* Matteo Valente
* Findlay Dunn-Wolbaum


## Installation

### Setup
- Open a desired folder ```cd 'directory'```
- Run ```git clone [git link]```
- Refer to [usage](#usage)

### Dependencies

- Postgre-SQL [Download Link](https://www.postgresql.org/download/)
- JUnit [Download Link](https://repo1.maven.org/maven2/junit/junit/4.13.2/)
- Hamcrest [Download Link](https://repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/)

## Usage

### Running Program (Using Windows CMD)

- Clone & cd into `src`
- Add the dependencies listed in [dependencies](#Dependencies) to the `lib` folder
- Run the compilation
```
javac -cp .;..\lib\postgresql-42.7.2.jar edu/ucalgary/oop/Main.java
```
- Run the main file
```
java -cp .;..\lib\postgresql-42.7.2.jar edu.ucalgary.oop.Main
```

### Runnning Tests (Using Windows CMD)

- Clone & cd into `src`
- Add the dependencies listed in [dependencies](#Dependencies) to the `lib` folder
- Run the compliation
```
javac -cp .;..\lib\junit-4.13.2.jar;..\lib\hamcrest-core-1.3.jar edu/ucalgary/oop/Tests/ExampleTest.java
```
- Run the test file
```
java -cp .;..\lib\junit-4.13.2.jar;..\lib\hamcrest-core-1.3.jar org.junit.runner.JUnitCore edu.ucalgary.oop.Tests.ExampleTest
```

Replace 'ExampleTest' with the test file you would like to run.

## Documentation

### Classes:

#### Animal, Beaver, Coyote, Fox, Porcupine, Raccoon:
   - Represents an animal in the Wildlife Shelter. This class encapsulates details such as the animal's unique identifier, nickname, species, and provides a structured way to manage animal information and their basic care needs, such as feeding schedules.
   -  Each animal species extends the {@link Animal} class with specific behaviors and properties unique to that species.

#### Tasks, Treatments:
   - Represents a task associated with animal treatment. This class is used to define and manage tasks related to the treatments of animals, such as medication administration, feeding schedules, or any other treatment-related activities. It ensures tasks are clearly defined with a unique identifier, a description, a maximum allowable time window, and a duration.
   - Manages the assignment of treatments to animals. Each treatment is uniquely identified and associated with a specific animal and task, along with a designated start hour for when the treatment should begin. This class is crucial for scheduling and tracking the treatment processes for animals, ensuring timely and organized care.

#### ImportData, Item, Schedule:
   - Handles the database interactions necessary to import and manage data pertaining to animals, tasks, and treatments. It provides functionality to establish a database connection and retrieve data required for the daily operations of a wildlife rescue center, such as Example Wildlife Rescue (EWR).
   - Represents a schedule item for tracking and managing tasks associated with animal care in a wildlife rescue center. Each item combines information about an animal, a specific task, and optionally a treatment, along with timing information such as when the task should start, its maximum allowable duration, and how long it is expected to take.
   - The Schedule class manages the scheduling of tasks for animals in the shelter. It interfaces with a database to import animal, task, and treatment data. It schedules tasks based on animal needs, task flexibility, and available volunteers. The schedule can be displayed via GUI and written to a text file.
   
#### Main, GUI:
   - The Main class initializes the GUI and Schedule objects and manages user interaction for accessing the schedule. The program prompts the user for credentials through the GUI to establish a database connection. It handles exceptions, such as invalid credentials, by displaying error messages and allowing users to retry or cancel. Once valid credentials are provided, it creates a Schedule object and generates the daily schedule.
   - The GUI class provides a graphical user interface for obtaining user credentials (username and password) to connect to the database. It displays input dialogs for the user to input their credentials and stores them internally. It also includes a method for displaying an error message in case of exceptions or invalid input.
