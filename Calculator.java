
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author Quiet_Storm
 * thanks to Schutzzz @
 * http://www.dreamincode.net/forums/topic/321933-creating-a-calculator-using-jframe/
 */
import java.awt.*; //essential for visual components
import javax.swing.*; //also essential for components and to set visual feel
import java.awt.event.*; //essential for handling events such as the ActionListener

public class Calculator extends JFrame implements ActionListener{

    /**
     * @param args the command line arguments
     */
    
    //BEGIN CLASS DECLARATIONS    
    JPanel[] row = new JPanel[5];

    //my Calculator will implement 19 buttons
    JButton[] button = new JButton[19];

    //string values for each button; arranged in order of display
    String[] buttonString = {"7", "8", "9", "+",
                             "4", "5", "6", "-",
                             "1", "2", "3", "*",                              
                             ".", "/", "C", "√",
                             "+/-", "=", "0"};

    //I intend to modify these dimensions once I understand implementation
    int[] dimW = {300, 45, 100, 90}; //an array containing widths of buttons
    int[] dimH = {35, 40}; //35 for display and 40 pix for buttons

    //where are the Dimension objects defined?
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
    Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
    Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);

    //Now declare an array of boolean variables for arithmetic functions
    boolean[] function = new boolean[4];

    //declare an array of doubles for performing calculations (operands)
    double[] temporary = {0, 0}; //intialized to 0

    //JTextArea used for IO display... parameter specifies width? 
    JTextArea display = new JTextArea(1, 20);
    Font font = new Font("Times new Roman", Font.BOLD, 14);
    //END DECLARATIONS
    
    //CONSTRUCTOR    
    Calculator(){
        super("Calculator");
        setDesign();
        setSize(380, 250);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //change background to blue
        /**
         * Why doesn't this work?
         * 
        for (JPanel item : row)
            item.setBackground(Color.BLUE);
         *
        **/
        
        //GridLayout manager? Specifies that calculator has 5 rows
        //and 5 components in each row
        GridLayout grid = new GridLayout(5, 5);
        setLayout(grid);

        //Note: design leverages the rule to omit curly braces for single
        //line loops.
        //Now, initialize arithmetic function variables as false
        for (int i = 0; i < 4; i++)
            function[i] = false;

        //FlowLayout manager - organizes components from left to right...
        //and top to bottom.  Need to research.

        //(FlowLayout.CENTER,1,1)?  the 1's are integers for horiz/vert gap
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER); //handles row1
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1); //row2

        for (int i = 0; i < 5; i++)
            row[i] = new JPanel();

        row[0].setLayout(f1);
        for(int i = 1; i < 5; i++)
            row[i].setLayout(f2);

        for(int i = 0; i < 19; i++){
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setFont(font);
            button[i].addActionListener(this); 
        }

        display.setFont(font);
        display.setEditable(false);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);

        //loop to set dimensions of right column and regular buttons
        for(int i = 0; i < 14; i++)
            button[i].setPreferredSize(regularDimension);
        for(int i = 14; i < 18; i++)
            button[i].setPreferredSize(rColumnDimension);

        button[18].setPreferredSize(zeroButDimension);
        
        //syntax is panel.add(component);
        row[0].add(display); //adds display to row 1
        add(row[0]); //adds row 1 to GUI
        
        for(int i = 0; i < 4; i++)
            row[1].add(button[i]);
        row[1].add(button[14]);
        add(row[1]);
        
        for(int i = 4; i < 8; i++)
            row[2].add(button[i]);
        row[2].add(button[15]);
        add(row[2]);
        
        for(int i = 8; i < 12; i++)
            row[3].add(button[i]);
        row[3].add(button[16]);
        add(row[3]);
        
        row[4].add(button[18]);
        for(int i = 12; i < 14; i++)
            row[4].add(button[i]);
        row[4].add(button[17]);
        add(row[4]);
        
        setVisible(true);
    }
   
    public final void setDesign(){
        try{
            UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                //"com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); //who made this?
                //UIManager.getSystemLookAndFeelClassName()); //matches to system
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == button[0])
            display.append("7");
        if(ae.getSource() == button[1])
            display.append("8");
        if(ae.getSource() == button[2])
            display.append("9");
        if(ae.getSource() == button[3]){
            //addition function
            temporary[0] = Double.parseDouble(display.getText());
            function[0] = true;
            display.setText(""); //reset display for next number
        }
        if(ae.getSource() == button[4])
            display.append("4");
        if(ae.getSource() == button[5])
            display.append("5");
        if(ae.getSource() == button[6])
            display.append("6");
        if(ae.getSource() == button[7]){
            //subtraction function
            temporary[0] = Double.parseDouble(display.getText());
            function[1] = true;
            display.setText("");
        }
        if(ae.getSource() == button[8])
            display.append("1");
        if(ae.getSource() == button[9])
            display.append("2");
        if(ae.getSource() == button[10])
            display.append("3");
        if(ae.getSource() == button[11]){
            //multiplication function
            temporary[0] = Double.parseDouble(display.getText());
            function[2] = true;
            display.setText("");
        }
        if(ae.getSource() == button[12])
            display.append(".");
        if(ae.getSource() == button[13]){
            //division function
            temporary[0] = Double.parseDouble(display.getText());
            function[3] = true;
            display.setText("");
        }
        if(ae.getSource() == button[14])
            clear();
        if(ae.getSource() == button[15])
            getSqrt();
        if(ae.getSource() == button[16])
            getPosNeg();
        if(ae.getSource() == button[17])
            getResult();
        if(ae.getSource() == button[18])
            display.append("0");
    }
    
    /*
     * I'm not sure I fully understand the purpose of the null pointer exception
     * catch.  Also, why don't we specify in the method's signature that it can 
     * throw a nullPointer exception?
    */
    public void clear(){
        try{
            display.setText(""); //blanks the display
            for(int i = 0; i < 4; i++)
                function[i] = false; //resets all function (operator) keys
            for(int i = 0; i < 2; i++)
                temporary[i] = 0; //Sets temporary doubles back to zero
        } catch(NullPointerException e ) {
        }
    }
    
    public void getSqrt(){
        try{
            //create a variable for value and take the square root of it
            double value = Math.sqrt(Double.parseDouble(display.getText()));
            //converts the "value" variable to a String and displays in JTextArea
            display.setText(Double.toString(value));
        } catch (NumberFormatException e) {
        }
    }
    
    public void getPosNeg() {
        try{
            double value = Double.parseDouble(display.getText());
            if (value != 0){
                value = value * -1; //multiply by neg. 1 to get opposite value
            }else{
            }
        }catch(NumberFormatException e){
        }
    }

    public void getResult() {
        double result = 0; //varible for result
        //captures the number input by user prior to hitting "=" and store to
        //array
        temporary[1] = Double.parseDouble(display.getText());
        String temp0 = Double.toString(temporary[0]); //necessary to convert neg
        String temp1 = Double.toString(temporary[1]); //ditto
        
        try{
            if(temp0.contains("-"))
            {
                //make a string array of size 2, "-":0th element, numeral: 1st
                String[] temp00 = temp0.split("-", 2);
                //converts numeral portion of string to Double & multiplies by -1
                //temporary array is updated with the true value
                temporary[0] = (Double.parseDouble(temp00[1]) * -1);
            }
            if(temp1.contains("-"))
            {
                //see above
                String[] temp11 = temp1.split("-", 2);
                temporary[1] = (Double.parseDouble(temp11[1]) * -1);
            }
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        try{
            if (function[2] == true) //why do we start with multiplication?
                result = temporary[0] * temporary[1];
            else if(function[3] == true) //function[3] = division function
                result = temporary[0] / temporary[1];
            else if(function[0] == true) //function[0] =  addition function
                result = temporary[0] + temporary[1];
            else if(function[1] == true) //function[1] = subtraction function
                result = temporary[0] - temporary[1];
            //put result in display
            display.setText(Double.toString(result));
            for (int i = 0; i < 4; i++)
                function[i] = false; //set all functions to false
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    //MAIN METHOD
    public static void main(String[] args) {
        Calculator c = new Calculator();
    }
    

}