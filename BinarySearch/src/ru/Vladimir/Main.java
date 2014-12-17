package ru.Vladimir;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by vladimir_danilov on 17-Dec-14.
 */
public class Main {

    private static final Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();

    private static JPanel panel;
    private static JTextField   input_size1;
    private static JTextField   input_size2;
    private static JLabel       message_input_size1;
    private static JLabel       message_input_size2;
    private static JLabel       warning_label;
    public  static JButton      button_find;
    private static JFrame       frame;

    private static ArrayList<JTextField> _toSearch;
    private static ArrayList<JTextField> _whereSearch;
    private static ArrayList[] arrayLists;
    private static  final int MAX_COUNT = ((int)sSize.getWidth()-20) / (25 + 5) ;
    private static final int MIN_COUNT = 2;

    private static final int DISTANCE_X = 5;
    private static final int DISTANCE_Y = 5;
    private static final Dimension START_FRAME_SIZE = new Dimension(330,180);

    private static int[] newTextForInputSize = new int[2];

    public static void main(String[] args) {
        _toSearch = new ArrayList<JTextField>();
        _whereSearch = new ArrayList<JTextField>();
        arrayLists = new ArrayList[]{_toSearch, _whereSearch};
        frame = new JFrame("Binary Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((int) sSize.getWidth() / 2, (int) sSize.getHeight() / 2);

        panel = new JPanel();
        panel.setPreferredSize(START_FRAME_SIZE);
        panel.setLayout(null);

        button_find = new JButton("Start binary search");

        message_input_size1 = new JLabel("Input size of array for nums to search:");
        message_input_size2 = new JLabel("Input size of array where search:");

        input_size1 = new JTextField("2");
        input_size2 = new JTextField("2");

/*        input_size1.setName("1");
        input_size2.setName("2");*/

        warning_label = new JLabel();

        message_input_size1.setBounds(DISTANCE_X, DISTANCE_Y, 250, 25);
        message_input_size2.setBounds(DISTANCE_X, message_input_size1.getY() + message_input_size1.getHeight() * 2 + DISTANCE_Y * 2,
                message_input_size1.getWidth(), message_input_size1.getHeight());
        input_size1.setBounds(message_input_size1.getWidth(), message_input_size1.getY(), 25, 25);
        input_size2.setBounds(message_input_size2.getWidth(), message_input_size2.getY(), 25, 25);
        warning_label.setBounds(DISTANCE_X, DISTANCE_Y * 4 + 25 * 4, 250, 25);
        button_find.setBounds(DISTANCE_X, DISTANCE_X * 5 + 25 * 5, 150, 25);

        DocumentListener onInputChange = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (checkInputSizeFields()) {
                    newTextForInputSize =
                            changeLength(Integer.parseInt(input_size1.getText()),
                                    Integer.parseInt(input_size2.getText()));
                }
            }
        };


        FocusListener onInputFocusLost = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                setPositionsForTextFields();
                changeFrameSize();
                panel.repaint();
                frame.pack();
                input_size1.setText("" + newTextForInputSize[0]);
                input_size2.setText("" + newTextForInputSize[1]);
            }
        };



        /*ChangeListener onInputChange = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(checkInputSizeFields())
                    changeLength(Integer.parseInt(input_size1.getText()),
                            Integer.parseInt(input_size2.getText()));
            }
        };*/

        button_find.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(checkInputNumsFields()) {
                    int[][] nums_input = getNums();
                    System.out.println("INPUT");
                    for(int i=0;i<2;i++) {
                        for(int j = 0; j < nums_input[i].length; j++) {
                            System.out.println(nums_input[i][j] + " ");
                        }
                    }
                    int[][] nums_output = BinarySearch.start(nums_input);
                    System.out.println("OUTPUT");
                    for(int i=0;i<2;i++) {
                        for(int j = 0; j < nums_output[i].length; j++) {
                            System.out.println(nums_output[i][j] + " ");
                        }
                    }
                    System.out.println("EXIT OF MOUSE LISTENER");
                    showResult(nums_input[0],nums_output[0],nums_output[1]);
                }
            }
        });

        input_size1.getDocument().addDocumentListener(onInputChange);
        input_size2.getDocument().addDocumentListener(onInputChange);
        input_size1.addFocusListener(onInputFocusLost);
        input_size2.addFocusListener(onInputFocusLost);

        panel.add(message_input_size1);
        panel.add(message_input_size2);
        panel.add(input_size1);
        panel.add(input_size2);
        panel.add(warning_label);
        panel.add(button_find);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        checkInputSizeFields();
        changeLength(Integer.parseInt(input_size1.getText()),
                Integer.parseInt(input_size2.getText()));
        setPositionsForTextFields();
    }

    public static int[][] getNums () {
        int[][] nums = new int[2][];
        nums[0] = new int[_toSearch.size()];
        nums[1] = new int[_whereSearch.size()];
        int i=0;
        int j=0;
        for(ArrayList<JTextField> arr: arrayLists) {
            for (JTextField tF : arr) {
                nums[i][j] = Integer.parseInt(tF.getText());
                System.out.println(nums[i][j]);
                j++;
            }
            i++;
            j=0;
        }
        return nums;
    }

    private static boolean checkInputSizeFields() {
        try {
            Integer.parseInt(input_size1.getText());
            Integer.parseInt(input_size2.getText());
            warning_label.setText("");
        }
        catch (Exception ex) {
            warning_label.setText("Just integer!");
            return false;
        }
        return true;
    }

    public static boolean checkInputNumsFields() {
        for(ArrayList<JTextField> arr:arrayLists)
            for(JTextField tF: arr) {
                try { Integer.parseInt(tF.getText()); }
                catch (Exception ex) {
                    warning_label.setText("Just integer!");
                    return false;
                }
            }
        return true;
    }

    private static int[] changeLength(int lenToSearch, int lenWhereSearch){
        int[] lenghts = {lenToSearch, lenWhereSearch};

        for(int i=0; i<arrayLists.length;i++){
            if(lenghts[i] < MIN_COUNT) lenghts[i]=MIN_COUNT;
            if(lenghts[i] > MAX_COUNT) lenghts[i] = MAX_COUNT;
            setLength(arrayLists[i],lenghts[i]);
        }

        return lenghts;
    }

    private static void setLength(ArrayList<JTextField> arr, int _length) {
        do {
            if (_length>arr.size()) {
                JTextField newJText = new JTextField();
                arr.add(newJText);
                panel.add(arr.get(arr.size()-1));
            }
            if (_length<arr.size()) {
                panel.remove(arr.get(arr.size()-1));
                arr.remove(arr.size()-1);
            }
        } while(arr.size()!=_length);
    }

    private static void setPositionsForTextFields() {
        for(int i=0;i<arrayLists.length;i++) {
            int y;
            int x;
            if(i==0) y = message_input_size1.getY();
            else y = message_input_size2.getY();
            y += message_input_size1.getHeight() + DISTANCE_Y;
            x = 5;
            for(JTextField tF:(ArrayList<JTextField>)arrayLists[i]) {
                tF.setBounds(x,y,25,25);
                x+=25+DISTANCE_X;
            }
        }
    }

    private static void changeFrameSize() {
        int positionOfLastTextField1_X = (_toSearch.get( ((ArrayList<JTextField>)arrayLists[0]).size() - 1 )).getX();
        int positionOfLastTextField2_X = (_whereSearch.get( ((ArrayList<JTextField>)arrayLists[1]).size() - 1 )).getX();
        int bordersX = 25+DISTANCE_X*2;
        int newWidth;

        if(positionOfLastTextField1_X>positionOfLastTextField2_X)
            newWidth = positionOfLastTextField1_X;
        else newWidth = positionOfLastTextField2_X;

        newWidth +=bordersX;
        if(newWidth<START_FRAME_SIZE.getWidth()) newWidth = (int)START_FRAME_SIZE.getWidth();
        panel.setPreferredSize(new Dimension(newWidth, panel.getHeight()));
    }

private static void showResult(int[] _toSearch, int[] _foundIndex, int[] _whereSearch) {
    System.out.println(_toSearch);
    System.out.println(_foundIndex);
    System.out.println(_whereSearch);
    JDialog dialogResult = new JDialog();
    dialogResult.setLocation((int) sSize.getWidth() / 2, (int) sSize.getHeight() / 2);
    int dialog_height = 0;

    JPanel dialogPanel = new JPanel();
    dialogPanel.setPreferredSize(new Dimension(200, 50));

    JLabel[] reports = new JLabel[_foundIndex.length];

    for (int i = 0; i < reports.length; i++) {
        reports[i] = new JLabel(_toSearch[i] + " found at " + _foundIndex[i] + " position..");
        System.out.println(_toSearch[i] + " found at " + _foundIndex[i] + " position..");
        dialogPanel.add(reports[i]);
        
    }

    JLabel outputArray;
    String output = "Output array:";
    for(int one: _whereSearch)
        output += " " + one;
    outputArray = new JLabel(output);
    dialogPanel.add(outputArray);

    dialogResult.add(dialogPanel);
    dialogResult.pack();
    dialogResult.setVisible(true);

    System.out.println("CHECK");
}


}
