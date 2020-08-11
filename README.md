# CSX42: Assignment 5
## Name: Jasmeet Kaur Dua

-----------------------------------------------------------------------
-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentskills/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

ant -buildfile textdecorators/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

 ant -buildfile textdecorators/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

  ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" -Dmisspelled="misspelled.txt" -Dkeywords="keyword.txt" -Doutput="output.txt" -Ddebug="1" //"Enter the number"

  ###Please Note:
1. Enter 1 for MOSTFREQUENTDECORATOR
2. Enter 2 for KEYWORDDECORATOR
3. Enter 3 for SPELLCHECKDECORATOR
4. Enter 4 for SENTENCEDECORATOR

Kindly use keyword.txt for the keywords and misspelled.txt for the misspelled words.

-----------------------------------------------------------------------
## Description:
### Note : Using 4 slag day to complete.

1. ArrayList : insert,remove and contains method has time complexity O(n). 
2. HashMap : insert , lookup and get time complexity is O(1).

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [4/Aug/2020]


# csx42-summer-2020-assign5-Jasmeet10
