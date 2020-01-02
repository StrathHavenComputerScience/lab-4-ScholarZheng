public class Lab4
{
    //// HELPER METHODS ARE AT THE BOTTOM. THANK YOU :)
    public static void turnRight()
    {
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }
    public static void turnAround()
    {
        Robot.turnLeft();
        Robot.turnLeft();
    }
    public static void backUp()
    {
        turnAround();
        Robot.move();
        turnAround();
    }
    public static void completeBars()
    {
        /* Pre-Code Ideas:
        * Like the lighting candles, you want to keep a count of how high up the robot goes until it touches
        * dark. Once it touches dark, let it come down until the count is zero. Move onto the next line and
        * repeat until the end.
        * 
        * OR
        * 
        * Make the robot have a "toggle" mode where it keeps placing down dark, "1", until it touches dark
        * the it does nothing, "0", loops around, and then once it touches dark, "1", make things keep being dark
        * until it touches dark again, which toggles it to "0". Make the robot automatically turn 
        * and move on to the next line when it can not move foward. Loop this a couple of times.
        */
       
        //NEW CODE : Infinitely scaleable maps.
        int amountOfColumns = 0; // Tracks the total amount of columns in the map.
        int currentColumn = 0; // Tracks the current column that the robot is in.
        
        // This makes the robot check how long the map is horizontally.
        while (Robot.frontIsClear()) {
            currentColumn++;
            Robot.move();
        }
        // The Robot should be on the opposite end of the map.
        
        amountOfColumns = currentColumn;
        Lab4.turnAround();
        
        //This makes the Robot run all the way to the beginning.
        while (Robot.frontIsClear()) {
            Robot.move();
        }
        currentColumn = 0;
        Lab4.turnAround();
        // The Robot should be in its original position (starting position).
        // And have finished calculating the total amount of columns in the map.
        while (currentColumn <= amountOfColumns) {
            if (Robot.onDark() == true) {
                Lab4.moveWithCondition();
            } else {
                Robot.turnLeft();
                while (Robot.onDark() == false) {
                    Robot.makeDark();
                    Robot.move();
                }
                // The Robot should be at the dark space in the column.
                Lab4.turnAround();
                while (Robot.frontIsClear()) {
                    Robot.move();
                } 
                Robot.turnLeft();
                Lab4.moveWithCondition(); 
            }
            currentColumn++;
        }
    }
    public static void testCompleteBars1()
    {
        Robot.load("bars1.txt");
        Robot.setDelay(0.025);
        completeBars();
    }
    public static void testCompleteBars2()
    {
        Robot.load("bars2.txt");
        Robot.setDelay(0.025);
        completeBars();
    }
    public static void combinePiles()
    {
        /* OLD Pre-code Ideas
        * First, make the robot move foward and count the amount of black spaces, this variable
        * will count the amount of black spaces it needs to move and, eventually, remove on its way down.
        * Once it goes over the first white block, it turns around, going all the way to the bottom.
        * Then it goes to the next pile, goes until it is on light, and then start changing each
        * successive white into dark until the count reaches zero.
        * NEW CODE: Works with infinately tall piles :)
        */

        // Keeps track of how many dark blocks are needed to be placed down.
        int amountInStorage = 0;
        boolean runTime = true;
        
        // This line takes the entire pile on the right side and stores it.
        while (runTime == true){
           if (Robot.onDark() == true) {
              Lab4.changeToLight();
              amountInStorage++;
           } 
           if (Robot.frontIsClear() == true) {
              Robot.move();
           } else { 
              runTime = false;
           }    
        }
        // At the end, it should have every dark space 
        // collected and be at the top of the left pile.
        Lab4.turnAround();
        // The Robot should end at the top of the left pile.
        // Moves the robot to the original position.
        while (Robot.frontIsClear() == true) {
            Robot.move();
        }
        // The robot should be at the bottom of the left pile. 
        
        Robot.turnLeft();
        Lab4.moveWithCondition();
        Robot.turnLeft();
        // Combines the piles.
        runTime = true;
        while (runTime == true) {
            if (Robot.onDark() != true && amountInStorage > 0) { 
                Lab4.changeToDark();
                amountInStorage -= 1;
        
            }
            if (Robot.frontIsClear() == true) {
                Lab4.moveWithCondition();
            } else {
                runTime = false;
            }
            //The robot should be at the top of the combined pile.
            }
    }
    public static void testCombinePiles1()
    {
        Robot.load("piles1.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }
    public static void testCombinePiles2()
    {
        Robot.load("piles2.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }
    public static void connectDots()
    {
        // NEW CODE : "The Printer" Method
        // Works for any sized map. Bigger the better.
    
        // This section gets the robot to the bottom corner.
        while (Robot.frontIsClear() == true) {Robot.move();}
        Robot.turnLeft();
        while (Robot.frontIsClear() == true) {Robot.move();}
        Robot.turnLeft();
        
        // This section checks the dimension of the map.
        int numberOfRows = 0; int numberOfColumns = 0;
        //Checks the amount of columns.
        while (Robot.frontIsClear() == true) {Robot.move(); numberOfColumns++;}
        Lab4.turnAround();
        while (Robot.frontIsClear() == true) {Robot.move();}
        Lab4.turnRight();
        //Checks the amount of rows.
        while (Robot.frontIsClear() == true) {Robot.move(); numberOfRows++;}
        Lab4.turnAround();
        while (Robot.frontIsClear() == true) {Robot.move();}
        Robot.turnLeft();
        
        boolean facingUpOrRight = true;
        int darkSpaceDetector = 0;
        int gapDeterminer = 0;
        //This section connects the dots horizontally.
        while (numberOfRows > -1) {
            while (Robot.frontIsClear() == true) {
                Robot.move();
                if (Robot.onDark() == true) {
                    darkSpaceDetector++;
                } else if (darkSpaceDetector == 1 && Robot.onDark() == false) { 
                    gapDeterminer++;
                }
                if (gapDeterminer > 1) {
                    gapDeterminer = 0;
                    darkSpaceDetector = 0;
                }
                if (darkSpaceDetector == 2 && gapDeterminer == 1) {
                    Lab4.turnAround();
                    Robot.move();
                    Lab4.changeToDark();
                    Lab4.turnAround();
                    Robot.move();
                    gapDeterminer = 0;
                    darkSpaceDetector = 1;   
                }
            }
            if (facingUpOrRight == true) {
                Robot.turnLeft();
                Lab4.moveWithCondition();
                Robot.turnLeft();
                facingUpOrRight = false;
            } else {
                Lab4.turnRight();
                Lab4.moveWithCondition();
                Lab4.turnRight();
                facingUpOrRight = true;
            }
            numberOfRows--;
        }
        Robot.turnLeft();
        facingUpOrRight = false;
        //The Robot will always end on the top right corner.
        
        //This section connects the dots vertically.
        while (numberOfColumns > -1) {
            while (Robot.frontIsClear() == true) {
                            Robot.move();
                if (Robot.onDark() == true) {
                    darkSpaceDetector++;
                } else if (darkSpaceDetector == 1 && Robot.onDark() == false) { 
                    gapDeterminer++;
                }
                if (gapDeterminer > 1) {
                    gapDeterminer = 0;
                    darkSpaceDetector = 0;
                }
                if (darkSpaceDetector == 2 && gapDeterminer == 1) {
                    Lab4.turnAround();
                    Robot.move();
                    Lab4.changeToDark();
                    Lab4.turnAround();
                    Robot.move();
                    gapDeterminer = 0;
                    darkSpaceDetector = 1;   
                }
            }
            if (facingUpOrRight == true) {
                Robot.turnLeft();
                Lab4.moveWithCondition();
                Robot.turnLeft();
                facingUpOrRight = false;
            } else {
                Lab4.turnRight();
                Lab4.moveWithCondition();
                Lab4.turnRight();
                facingUpOrRight = true;
            }
            numberOfColumns--;
        }

        /* OLD CODE : Pre-Code Idea : "Cross" Method
        * Test for up, down, left, and right for a gap of light and then for dark. Draw a line if
        * the second block is black (based on the first dot it touches). Then move to next block.
        * Loop this infinately until it no longer touches black on up, down, left, or right.
        * /
        /*
        boolean connectable = true; 
        // While this is true, the robot will keep checking around for connectable spaces.
        
        int sideChecks = 0;
        // This variable tells the amount of side checks that have been done.
        // 0 = Left, 1 = Up, 2 = Right, 3 = Down a
        while (connectable == true ) {
            // The variable "side" denotes that the for loop "sides" the robot.
            for (int side = 0; side < 3; side++) {
                if (Robot.frontIsClear() == true) {
                    Robot.move();
                    
                    // This checks if the robot is retracing its path. 
                    if (Robot.onDark() == true) { 
                        Lab4.turnAround();
                        Robot.move();
                        connectable = false;
                        } else {
                            if (Robot.frontIsClear() == true 
                            && Robot.onDark() == false) {
                                Robot.move();
                                if (Robot.onDark() == true) {
                                    Lab4.turnAround();
                                    Robot.move();
                                    Lab4.changeToDark();
                                    Lab4.turnAround();
                                    Robot.move();
                                    side = 3;
                                } else {
                                    sideChecks++;
                                    Lab4.turnAround();
                                    Robot.move();  
                                    Robot.move();  
                                }
                            }
                            Robot.turnLeft();
                    }
                }
               }
        
            // If there are no dots around the robot that it can connect to
            // it stops the program. Else it keeps checking and connecting dots.
            if (sideChecks == 4) {
                connectable = false;   
                } else {
                    sideChecks = 0;
            }
        } */
    } 
    public static void testConnectDots1()
    {
        Robot.load("connect1.txt");
        Robot.setDelay(0.025);
        connectDots();
    }
    public static void testConnectDots2()
    {
        Robot.load("connect2.txt");
        Robot.setDelay(0.025);
        connectDots();
    }
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////HELPER METHODS//////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    public static void changeToLight() 
    {
        if (Robot.onDark() == true) {
            Robot.makeLight();
        }
    } 
    public static void changeToDark() 
    {
        if (Robot.onDark() != true) {
            Robot.makeDark();
        } 
    } 
    public static void moveWithCondition()
    { 
        if (Robot.frontIsClear() == true) {
            Robot.move();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
}
