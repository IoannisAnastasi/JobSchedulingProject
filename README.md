HOMEWORK 4 - TAKE-AWAY
Ioannis Anastasi ID: 1131712 - Christoforos Kontzias ID: 1134670

Basic idea: there are 2 separate programs (so 2 different main methods). OrderGenerator creates the random orders and places them in the text file. 
OrderDelivery calculates the order completion time based on set parameters and 3 different time scheduling algorithms and it outputs the results in a different text file. A report can then be created.


The classes I ,(Christoforos Kontzias: 1134670) will implement are:
OrderDelivery (handles the delivery process)

Scheduler (defines scheduling basic behaviour)

FCFSScheduler (handles FCFS scheduling  algorithm for use in this program)

SJFScheduler  (handles SJF scheduling  algorithm for use in this program)

WeightedScheduler ( wighted scheduling using the formula)

Grill (handles how the grill will work)

Fryer (handles how the fryer will work)

OrderDeliveryTest ( I will use Junit tests here to test weather it works as expected)

The Scheduler class will be an interface . The class for each algorithm will be implementing the Scheduling interface.
I believe with this implemantation none of the SOLID principles will be violated as each class is doing its own thing (Single Responsibility principle) , I can add more scheduling algorithms without having to modify my code (Open/closed principle), the different scheduling implemantations can all work on OrderDelivery without changing any code there( Liskov Substitution principle) , no classes are forced to implement methods they dont need(Interface segregation principle). 
Also, OrderDelivery depends on an interface and not on concrete subclasses so I believe the Dependency Inversion principle is also met.
