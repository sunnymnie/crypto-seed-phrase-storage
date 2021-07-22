# Crypto wallet Seed-phrase storage

**Note**: see below for the `Crypto information` section for a general description of the crypto terminology used for this project and README. 

## Purpose (What it does):
This application will store crypto seed-prase info and other sensitive information. Users can set their own security questions for each item saved, and will have to answer their security questions to retrieve back their info. The application gives many different methods for information retrieval such to minimize the effects of key-loggers, hidden cameras, screen-recorders, or audio-recorders tampering with this process. 

## Who will use it?
I will. But generally, people who are looking for a reliable way to store their wallet seed-phrases can use it. This application comes with an added protection layer in that it never makes any external calls so information is never stored on an external database. 

## Why is this project of interest to me?
I own cryptocurrencies and I have crypto-wallets that have seed-phrases that need to be saved properly. 

## User Stories
- As a user, I want to be able to add a seed-phrases to my collection
- As a user, I want to be able to set one or more security question and answer for each seed-phrase in my collection
- As a user, I want to be able to set general security questions that are enabled for all seed-phrases in my collection
- As a user, I want to be able to remove non-general security questions for a seed-phrase
- As a user, I want to be able to edit my security questions and answers
- As a user, I want to be able to set how many security questions I have to get right to unlock my seed-phrase. 
- As a user, I want to be able to skip security questions when I get stuck
- As a user, I want to be able to delete seed-phrases from my collection once I unlock them
- As a user, I want to be able to have the option to display my entire seed-phrase once I answer enough security questions correctly
- As a user, I want to be able to have the option to display my seed phrase word-by-word once I answer enough security questions correctly

## Crypto information
- **cryptocurrency**: A currency that uses blockchain technology. Examples include Bitcoin, Ethereum, Doge Coin, etc. 
- **seed phrase**: a long password for a crypto wallet. Usually is 12 or 24 words long. An example: `cat tree box tissue umbrella ...`
- **crypto wallet**: a 'bank account' that 'stores' the crypto currency
- **crypto address**: the identification used by the crypto wallet to allow users to send cryptocurrencies to that wallet. 
