HOMEWORK 4 - TAKE-AWAY 
TEAM 40 : Ioannis Anastasi ID: 1131712 - Christoforos Kontzias ID: 1134670  

Basic idea: there are 2 separate programs (so 2 different main methods). OrderGenerator creates the random orders and places them in the text file. 
OrderDelivery calculates the order completion time based on set parameters and 3 different time scheduling algorithms and it outputs the results in a different text file. A report can then be created.


The classes I ,(Christoforos Kontzias: 1134670) implemented are:
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

The classes that I (Ioannis Anastasi ID: 1131712) implemented are:
- OrderGenerator: (Main class: generates orders and writes them to a text file:"Orders.txt")
- Order: (Object(superclass): represents an order in a restaurant)
- Generator: (Object(subclass of Order): generates random orders for a restaurant)

  - Order Class
The Order class is responsible for representing an order in a restaurant. It encapsulates the order details such as time of order, time of delivery request, and quantities of different food items.
It does not have multiple reasons to change. Its responsibility is well-defined and focused solely on managing order data.
The Order class is open for extension (e.g., through inheritance for specialized order types) but closed for modification. It provides methods to set and get order details, allowing for extension without modifying its internal implementation.
  - Generator Class:
The Generator class is responsible for generating random orders with random quantities of food items and random order times.
It encapsulates the logic for generating random orders and does not have additional responsibilities such as managing order storage or file I/O.
Similar to the Order class, the Generator class is open for extension (e.g., for adding new methods or behaviors) but closed for modification. Its methods provide the necessary hooks for extension without altering the existing functionality.
Since Generator extends Order, it's crucial that it behaves as a subtype of Order. In this case, Generator specializes Order by adding methods for generating random orders.
