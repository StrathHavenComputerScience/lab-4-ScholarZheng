
/**
 * Write a description of class RobotTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RobotTest
{
    public static void main(String[] args)
    {
        Robot.load("bars1.txt");
        Robot.setDelay(0.025);
        Lab4.completeBars();
        Draw.pauseUntilMouse();
        Robot.load("bars2.txt");
        Robot.setDelay(0.025);
        Lab4.completeBars();
        Draw.pauseUntilMouse();
        /*Uncomment before turning in
        Robot.load("bars3.txt");
        Robot.setDelay(0.025);
        Lab4.completeBars();
        Draw.pauseUntilMouse();
        Robot.load("bars4.txt");
        Robot.setDelay(0.025);
        Lab4.completeBars();
        Draw.pauseUntilMouse();*/

        Robot.load("piles1.txt");
        Robot.setDelay(0.025);
        Lab4.combinePiles();
        Draw.pauseUntilMouse();
        Robot.load("piles2.txt");
        Robot.setDelay(0.025);
        Lab4.combinePiles();
        Draw.pauseUntilMouse();
        /*Uncomment before turning in
        Robot.load("piles3.txt");
        Robot.setDelay(0.025);
        Lab4.combinePiles();
        Draw.pauseUntilMouse();
        Robot.load("piles4.txt");
        Robot.setDelay(0.025);
        Lab4.combinePiles();
        Draw.pauseUntilMouse();*/

        Robot.load("connect1.txt");
        Robot.setDelay(0.025);
        Lab4.connectDots();
        Draw.pauseUntilMouse();

        Robot.load("connect2.txt");
        Robot.setDelay(0.025);
        Lab4.connectDots();

        /*Uncomment before turning in
        Draw.pauseUntilMouse();

        Robot.load("connect3.txt");
        Robot.setDelay(0.025);
        Lab4.connectDots();
        Draw.pauseUntilMouse();

        Robot.load("connect4.txt");
        Robot.setDelay(0.025);
        Lab4.connectDots();*/

    }
}
