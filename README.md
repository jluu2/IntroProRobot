# IntroProRobot

The methods of Robot class: 
up(), down(), extend(), contract(), raise(), lower(), pick(), drop(),speedUp(), slowDown()

•	The height (h) of the main arm can be changed by 1 unit using methods up() and down(). The height (h) of the this arm must lie in the range 2 to 14, and is initially set to 2. 

•	The second arm can be moved horizontally by 1 unit using extend() or contract(). The width (w) must lie in the range 1 to 10, and is initially set to 1.  

•	The depth (d) of the third arm can be changed by 1 unit using lower() or raise(). The depth cannot be less than 0 but must be less than the height of the main arm ( d < h ). In the initial position depth d is set to 0 (third arm is not visible).  

•	Robot class has two other methods slowDown(int factor) and speedUp(int factor) to adjust the time between moves by the specified factor.

•	An item can be picked from the top of the stack of blocks at source using pick(). 

•	It can be dropped at the top of the stack of blocks at one of the three targets using drop(), depending on the size of the block. The height of blocks can only be 1, 2 or 3. Blocks of height 1, 2 and 3 must be placed in columns 1, 2 and 3 respectively.

Other Constraints
•	Algorithms for moving the blocks must avoid collisions with other blocks and bars.  
