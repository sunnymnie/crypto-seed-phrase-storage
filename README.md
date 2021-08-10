# Crypto wallet Seed-phrase storage

**Note**: see below for the `Crypto information` section for a general description of the crypto terminology used for this project and README. 

## Purpose (What it does):
This application will store crypto seed-prase info and other sensitive information. Users can set their own security questions for each item saved, and will have to answer their security questions to retrieve back their info. The application gives many different methods for information retrieval such to minimize the effects of key-loggers, hidden cameras, screen-recorders, or audio-recorders tampering with this process. 

## Who will use it?
I will. But generally, people who are looking for a reliable way to store their wallet seed-phrases can use it. This application comes with an added protection layer in that it never makes any external calls so information is never stored on an external database. 

## Why is this project of interest to me?
I own cryptocurrencies and I have crypto-wallets that have seed-phrases that need to be saved properly. 

## User Stories (Part 2)
- As a user, I want the program to automatically save my seed-phrases to file whenever I quit the program
- As a user, I want the program to automatically load my seed-phrases list from file whenever I run the program

Optional user-stories (criteria says only two, so refer to above):
- As a user, I want the program to automatically save my security-questions to file whenever I quit the program
- As a user, I want the program to automatically load my security-questions list from file whenever I run the program

## User Stories (Part 1)
- As a user, I want to be able to add a seed-phrases to my collection
- As a user, I want to be able to set security questions that are enabled for all seed-phrases in my collection
- As a user, I want to be able to delete security questions
- As a user, I want to be able to edit my security questions and answers
- As a user, I want to be able to set how many security questions I have to get right to unlock my seed-phrase. 
- As a user, I want to be able to delete seed-phrases from my collection once I unlock them
- As a user, I want to be able to have the option to display my entire seed-phrase once I answer enough security questions correctly
- As a user, I want to be able to have the option to display my seed phrase word-by-word once I answer enough security questions correctly

## Phase 4: Task 2
> Test and design a class in your model package that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.

- Class: `model>verification`
- Method: `public SecurityQuestion get(int index) throws NegativeIndexException`
- Tests: `testGetPossible()`, `testGetNegative()`, `testGetNegativeTwo()`

