# Katie-PetStore-P1

## Introduction

This is a Java and Angular based pet store application. The application will be primarily built using Java, Angular, and Spring while utilizing a PostgreSQL database to store animal and user information.

## User Stories

*	**As a user**, I want to register an account so that I can have a personalized shopping experience.
*	**As a user**, I want to login to my personalized account so I can see animals I’m interested in.
*	**As a user**, I want to browse through animals when logged in.
*	**As a user**, I want to search for animals by species, breed, gender, and price.
*	**As a user**, I want to add pets to my favorites while browsing to save a pet to my profile so I can find them quicker next time.
*	**As a user**, I want to view my favorited pets on my profile page.
*	**As a user**, I want to add animals to my shopping cart so that I can purchase them later.
*	**As a user**, I want to modify the quantity or remove animals from my cart so that I can make changes before finalizing the purchase.
*	**As a user**, I want to check out and pay for my order securely so that my personal and financial information is safe.
*	**As a user**, I want to view my order history so I can keep track of my purchases.

## MVP (Minimum Viable Product)

*	User registration and login
*	Browsing and searching for pets
*	Favoriting feature and viewing favorites
*	Adding products to a shopping cart
*	Modifying the shopping cart
*	Secure payment process
*	Order history

## Stretch Goal

*	Product rating and reviewing.
*	Adding an admin role to add, remove, or modify the store.

## Tech Stack

*	**Java**: The main programming language used for building the application.
*	**Spring Boot**: Has the beans.
*	**Angular**: Front end
*	**PostgreSQL**: Used as the database to store user, pet, and order data.
*	**Maven**: Used for managing project dependencies.
*	**Junit**: A testing framework for Java applications, used to ensure our code works as expected.
*	**Log4j**: A logging utility for debugging purposes.
*	**JDBC**: An API for connecting and executing queries on the database.
*	**BCrypt**: A Java library for hashing and checking passwords for security.
*	**Mockito, Junit, and PowerMock**: Used for unit and integration testing.
*	**Git and GitHub**: Used for version control. The hub that controls all the Gits.

## Requirements

*	**Clean Codebase**: All code should be clean and well-documented. The repository should not include any unnecessary files or folders such as the target/, .DS_Store, application.properties, and app.logger. All files and directories should be appropriately named and organized.
*	**Database Design**: The database should be designed following the principles of the 3rd Normal Form to ensure data integrity and efficiency. An Entity Relationship Diagram (ERD) should be included in the documentation.
*	**Secure**: All sensitive user data such as passwords must be securely hashed before storing it in the database. The application should not display any sensitive information in error messages.
*	**Error Handling**: The application should handle potential errors gracefully and provide clear and helpful error messages to the users.
*	**Testing**: The application should have high-test coverage. Unit tests and integration tests should be implemented using, Junit¸ Mockito, and PowerMock.
*	**Version Control**: The application should be developed using a version control system, preferably Git, with regular commits denoting the progress.
*	**Documentation**: The repository should include a README file with clear instructions on how to run the application. Code should be well-commented to allow for easy understanding and maintenance.
*	**Scalable**: The design of the application should be scalable, allowing for easy addition of new features or modifications in the future.
