# My Personal Project
***Find your commuter friends on campus!***

This application will allow commuter students on campus to find their community through the creation of a profile,
containing information related to their academic studies and their commute to campus, and be able to view and sort
other profiles based on various conditions (eg. year level, faculty, commute route, etc.). In addition, the application
will allow users to keep track of people (profiles) they're interested in connecting with through the use
of a list. This application is intended to be used by commuter students to find friends and community on campus!

This project interests me both as a commuter student to campus, but also as a Collegia Advisor with the UBC Collegia
program, which supports first-year students in their transition to university by creating a
home-away-from-home space that allows them to find friends and community on campus. In my role as a Collegia Advisor,
I've noticed that some students are not able to come to the space
due to conflicting schedules and commitments, resulting in a lack of social connection
with other commuter students. This application is designed to hopefully alleviate that issue and have a central
space for commuter students to find other commuter students!

  User Stories
- 
- As a user, I want to be able to create a profile that shares my name, my faculty and my commute route to campus, and a short message.
- As a user, I want to be able to sort all the profiles by faculty or commute route.
- As a user, I want to be able to add a profile to a list of profiles of people who have something in common with me.
- As a user, I want to be able to remove a profile from my list of profiles.
- As a user, I want to be able to save my profiles to file.
- As a user, I want to be able to load my profiles from file.

## Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by selecting a profile from the database and clicking the button "Add to Your List".
- You can generate the second required event related to adding Xs to a Y by clicking "Sort by Route" and following the instructions.
- You can locate my visual component by clicking either button related to saving/loading the application.
- You can save the state of my application by clicking the button labelled "Save the Application".
- You can load the state of my application by clicking the button labelled "Load the Application".

## Phase 4: Task 2
Wed Nov 30 21:57:15 PST 2022\
Marie was added to your list!

Wed Nov 30 21:57:15 PST 2022\
Tyler was added to your list!

Wed Nov 30 21:57:18 PST 2022\
Tyler was added to your list!

Wed Nov 30 21:57:19 PST 2022\
Nathan was added to your list!

Wed Nov 30 21:57:20 PST 2022\
Tyler was removed from your list.

Wed Nov 30 21:57:22 PST 2022\
Profiles sorted by route.

Note: more actions appear in the log once printed to the console due how the GUI is instantiated. In essence, in order to add some profiles to the database when the program begins, the method that adds the profiles also logs the events and thus adds these initial events to the log despite them technically not being called by the user.

## Phase 4: Task 3
If I had more time, I would improve my project by refactoring the following:
- Refactoring the GUI class to consist of a central GUI class that instantiates a number of different smaller GUI classes related to specific functionality of the GUI. This results in decreased coupling and increased cohesion.


***Sources***
- Teller App : https://github.students.cs.ubc.ca/CPSC210/TellerApp
- JSON Demo : https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
- C3 Lecture Lab - Traffic Light : https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter
- Alarm System : https://github.students.cs.ubc.ca/CPSC210/AlarmSystem