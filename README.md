# Matrix calculator

========================================================
## Proposal: *Phase 0*

\
**Questions:**
<p>1. What will the application do?<br>
The application will let a user input a calculator in augmented form <br>
and choose row operations to apply such as swap, scalar multiplication <br>
and addition.
<br>
2. Who will use it?<br>
Students working with algebra and matrices
<br>
3. Why is this project of interest to you?<br>
This project would have a connection to other courses I am taking and I <br>
enjoy being able to explore applications of programming to other fields.
</p>

\
**User Stories:**
> As a user, I want to be able to input an arbitrary length and width of calculator
> 
> As a user, I want to be able to view the state of calculator after each operation
> 
> As a user, I want to be able to add and remove row vectors
> 
> As a user, I want to be able to perform row operations such as swap, addition, subtraction and constant multiplication
> 
> As a user, I want to be able to save the state of my calculator
> 
> As a user, I want to choose between loading last calculator or creating new calculator

# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by adding or removing row objects from the matrix under the row Editor and matrix Editor menu. Choose which row in the text box by typing integers and hit the submit button.
- You can generate the second required action related to adding Xs to a Y by reordering(swap) and performing operations on row objects through selecting rows inside the operations menu and hitting the submit button.
- You can locate my visual component by the top right panel display for icon representations of last row operation (add, subtract, multiply, swap)
- You can save the state of my application by clicking the save button
- You can reload the state of my application by clicking the load button

# Phase 4: Task 2
- Thu Apr 06 14:46:02 PDT 2023
- Add row 1 and row 3
- Thu Apr 06 14:46:03 PDT 2023
- Subtract row 1 and row 2
- Thu Apr 06 14:46:06 PDT 2023
- Multiply row 2 with constant 2.0
- Thu Apr 06 14:46:10 PDT 2023
- Swap row 3 and row 1
- Thu Apr 06 14:46:15 PDT 2023
- Removed row at position 1
- Thu Apr 06 14:46:21 PDT 2023
- Insert row at position 3

# Phase 4: Task 3
Some refactoring ideas I have for this project would be to make better use of exception throwing in classes such as Column, Row and Matrix. 
Since these classes were made in phase 1 before learning about exceptions, they were made to run a check method on inputs for integers within certain ranges (0, arbitrary int) using if else statements rather than exceptions.
Refactoring with exception throwing would reduce the amount of redundant methods being used for checks in the initial console UI. 

Other additional refactoring I would want to implement would be to split up the main Calculator class responsible for the console UI.
After beginning phase 3 with GUI, I realized the calculator class was handling too many distinct method/functions at the same time that could
be better split into classes to that handle the menu, operations, history printing and save/load functions independently. This would better
adhere to the single responsibility principle and also improve readability of the code.
