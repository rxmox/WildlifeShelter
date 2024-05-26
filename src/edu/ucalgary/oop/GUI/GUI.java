/**
 * @author Matteo Valente
 * @author Marcus Gee
 * @author Findlay Dunn-Wolbaum
 * @author Omar Ahmed
 * @version 2.10.0
 * @since 2.9.0
 */

package edu.ucalgary.oop.GUI;
import javax.swing.*;
import java.awt.*;


public class GUI  {
    //the data base password and username
    private String username;
    private String password;

    //the instructions for each window and the error message
    private String instruction;
    private final String ERRORMESSAGE = "An Error Occurred, Please Try Again";

    //labels used for the username and password boxes
    private JLabel passLabel;
    private JLabel userLabel;

    //ask for username and password
    public GUI(){
        setGUIUsername();
        setGUIPassword();    
    }

    //display a GUI asking for the user to input their username and store it in the class
    public void setGUIUsername(){
    
        instruction = "Enter Username to connect to the Database";

        userLabel = new JLabel("UserName:");

        JPanel body = new JPanel();
        body.setLayout(new FlowLayout());


        body.add(userLabel);
    
        String word = JOptionPane.showInputDialog(null,body,instruction,JOptionPane.DEFAULT_OPTION);

        this.username = word;
    }

    //ask the user to input their password and then store it in the class
    public void setGUIPassword(){
    
        instruction = "Enter Password to connect to the Database";

        passLabel = new JLabel("Password:");
   

        JPanel body = new JPanel();
        body.setLayout(new FlowLayout());


        body.add(passLabel);
    
        String word = JOptionPane.showInputDialog(null,body,instruction,JOptionPane.DEFAULT_OPTION);
      
        this.password = word;
    }

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    //displays an error message
    public void errorMessage(){
        JOptionPane.showMessageDialog(null, ERRORMESSAGE);
    }

}
