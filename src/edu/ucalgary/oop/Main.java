package edu.ucalgary.oop;
import edu.ucalgary.oop.Schedule.*;
import edu.ucalgary.oop.GUI.*;

/**
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 2.9.0
 * @since 2.8.0
 */

public class Main {

    public static void main(String[] args) {
        boolean flag = true;//use the flag as a indicator on when to end the program
        GUI gui = new GUI();
        Schedule schedule = null;
        while(flag){
            try {
                schedule = new Schedule(gui.getUsername(),gui.getPassword());
                flag = false;}
            catch(Exception e){
                //if an exception occurs then we use the GUI error display message then reprompt the user for input
                //if the user presses the cancel button then the username or password is null and we exit the program
                gui.errorMessage();
                gui = new GUI();
                flag = true;
                if(gui.getPassword() == null || gui.getUsername() == null){
                    flag = false;
                }
            } finally{
                if(schedule != null){
                    schedule.createSchedule();
                }
            }

            
            
        }

   
    }
    
}

