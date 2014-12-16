package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Vladimir_Danilov on 16-Dec-14.
 */


public class Main {

    private static JTextField input_factorial;
    private static JTextField input_fibonacci;
    private static JLabel warning_label;

    private static final Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Factorial&FibonacciNums");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((int)sSize.getWidth()/2,(int)sSize.getHeight()/2);

        JPanel panel =  new JPanel();

        panel.setPreferredSize(new Dimension(330,125));
        panel.setLayout(null);

        JButton calc_button = new JButton("Click to calc");

        warning_label = new JLabel();

        input_factorial = new JTextField("Input factorial, you wanna calc, here");
        input_fibonacci = new JTextField("Input number of fibonacci number, you wanna see, here");

        input_factorial.setBounds(5, 5,  320, 25);
        input_fibonacci.setBounds(5, 40, 320, 25);
        calc_button.setBounds    (5, 70, 150, 25);
        warning_label.setBounds  (5, 100,320, 25);

        MouseListener onTextClick = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ((JTextField) e.getSource()).setText("");
                }
                catch (Exception ex) {      // For Indians
                    System.err.println("don't assign this exception to non-TextField");
                }
            }
        };

        /*
        CaretListener onTextChanged = new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {

            }
        };
        */

        input_factorial.addMouseListener(onTextClick);
        input_fibonacci.addMouseListener(onTextClick);

        calc_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        //MaskFormatter formatterTextField = new MaskFormatter("###");

        panel.add(calc_button);
        panel.add(warning_label);
        panel.add(input_factorial);
        panel.add(input_fibonacci);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static boolean checkFields() {
        try {
            Integer.parseInt(input_factorial.getText());
            Integer.parseInt(input_fibonacci.getText());
            warning_label.setText("");
        }
        catch (Exception ex) {
            warning_label.setText("Just integer!");
            return false;
        }

        return true;
    }

    public static void calculate() {
        if(checkFields()) {
            JDialog dialog = new JDialog();
            dialog.setLocation((int)sSize.getWidth()/2,(int)sSize.getHeight()/2);

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(200,50));

            panel.add(new JLabel("Factorial of " + input_factorial.getText() + " = " + Factorial.calc(Long.parseLong(input_factorial.getText()))));
            panel.add(new JLabel(input_fibonacci.getText() + "th Fibonacci number is " + Fibonacci.calc(Long.parseLong(input_fibonacci.getText()))));

            dialog.add(panel);
            dialog.pack();
            dialog.setVisible(true);
        }


    }
}

