
# PA Laborator 11

## Students

  - Cojocariu Alexandru, II A2

## Description

This project implements a Spring application for storing Gomoku games and players.

The implementation was inspired/based on Centric's presentation by Florin Olariu [https://www.youtube.com/watch?v=xGrzlvFuq3Y](https://www.youtube.com/watch?v=xGrzlvFuq3Y)

It uses a Model - Repository - Service - Controller design pattern. I also attached the package for exception handling. The server application from the previous lab calles the create method from controller when a winner is determinated.

Also, for the security part I followed the tutorial series starting from here https://www.youtube.com/watch?v=oVpFJr-Z35A . The code does not belong to me except that i adapted it to use my API. 

## Features

  - (Compulsory) Implemented a Spring Boot application which has REST services
  - (Compulsory) Game and Player each have a set of a Model, Repository, Service and Controller classes which implement the desired functions
  - (Optional) I attached a png to show how i used keytool command in order to use HTTPS
  - (Optional) The previous project is integrated, when a winner is determined, the game is added in the repository in GameLogic class
  - (Optional) The package exceptionManager makes use of RestControllerAdvice in order to treat runtime exception
  - (Optional) The reading and insertion of games is covered in the game service
  - (Bonus) I tried to use Swagger for documentation, but I ran into problems so I tried to manually edit a .yaml file which is attached
  - (Bonus) JWT authentication implemented
  
## Not resolved

  - (Bonus) AI for the game
  
