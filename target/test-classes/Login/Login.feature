#Author: Suraj Ardak

Feature: Login Functionality

  @ValidLogin
  Scenario: Successful login with all valid data
    Given User is on the login page 
    When User enters valid email and password and click login button
    Then User is navigated to home page to continue shopping
  
  @InvalidLogin
  Scenario: Failure in login after entering an invalid data
    Given User is on the login page
    When User enters an invalid Email as"<email>" or an invalid password as "<pass>" and click login
    Then User should be on login page

  @RememberMe
  Scenario: Session is get stored or not
    Given User is on the login page 
    When User enters the valid email and password and user check remember me option
    Then login should be stored even browser is closed and opened again
    
  @InvalidLoginOutline
  Scenario Outline: Failure in login after entering an invalid data
    Given User is on the login page
    When User enters an invalid "<email>" or an invalid "<pass>" and click login
    Then User should be on login page
  Examples:
  |email			|pass		|
  |admin10@gmail.com|admin		|
  |admin@gmail.com	|admin123	|
  |admin@gmail.com	|			|
  |					|admin123	|
  |admin10			|			|