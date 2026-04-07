Feature: Login
    As a registred user i want to login to the website
    so that i can purches items

    Background:
        Given the user is on the login page
    
    Scenario Outline: Login attempt with valid and invalid credentials
        Given the user typed his email "<email>" and password "<password>"
        When the user click the login button
        Then the login result should be "<result>"

        Examples:
            | email                 | password  | result                                                                          |
            | xiridoh256@exahut.com | L1L0$1234 | "You are logged in!" popup appears                                              |
            | invalid@email.exp     | Wr0nG-P55 | "You have entered an invalid email or password. Please try again" popup appears |