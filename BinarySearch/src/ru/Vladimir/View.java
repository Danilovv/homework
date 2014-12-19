package ru.Vladimir;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vladimir_Danilov on 19-Dec-14.
 */
public class View {

    public class ArrayList<T> extends java.util.ArrayList<T> {
        public T getLast() {
           return get(size() - 1) ;
        }
    }

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int DISTANCE_X = 5;
    private final int DISTANCE_Y = 5;
    private final Dimension START_FRAME_SIZE = new Dimension(330, 180);

    private JPanel panel;
    private JTextField input_length_whatToSearch;
    private JTextField input_length_whereSearch;
    private JTextField[] input_lengths;
    private JLabel message_whatToSearch;
    private JLabel message_whereSearch;
    private JLabel warning_label;
    private JButton button_find;
    private JFrame frame;

    private ArrayList<JTextField> _toSearch;
    private ArrayList<JTextField> _whereSearch;
    private ArrayList<JTextField>[] _searchArrays;

    View() {
        /*input_lengths.add(input_length_whatToSearch);
        input_lengths.add(input_length_whereSearch);*/

        initJObjects();
        setProperties();
        setJPositions();

        panel.add(message_whatToSearch);
        panel.add(message_whereSearch);
        panel.add(input_length_whatToSearch);
        panel.add(input_length_whereSearch);
        panel.add(warning_label);
        panel.add(button_find);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void initJObjects() {
        frame = new JFrame();
        panel = new JPanel();

        _toSearch = new ArrayList<JTextField>();
        _whereSearch = new ArrayList<JTextField>();
        _searchArrays = new ArrayList[]{_toSearch, _whereSearch};

        button_find = new JButton();
        message_whatToSearch = new JLabel();
        message_whereSearch = new JLabel();

        input_length_whatToSearch = new JTextField();
        input_length_whereSearch = new JTextField();
        input_lengths = new JTextField[] { input_length_whatToSearch, input_length_whereSearch };

        warning_label = new JLabel();
    }

    private void setJPositions() {
        frame.setLocation((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);

        message_whatToSearch.setBounds(DISTANCE_X, DISTANCE_Y, 250, 25);
        message_whereSearch.setBounds(DISTANCE_X, message_whatToSearch.getY() + message_whatToSearch.getHeight() * 2 + DISTANCE_Y * 2,
                message_whatToSearch.getWidth(), message_whatToSearch.getHeight());

        input_length_whatToSearch.setBounds(message_whatToSearch.getWidth(), message_whatToSearch.getY(), 25, 25);
        input_length_whereSearch.setBounds(message_whereSearch.getWidth(), message_whereSearch.getY(), 25, 25);

        warning_label.setBounds(DISTANCE_X, DISTANCE_Y * 4 + 25 * 4, 250, 25);

        button_find.setBounds(DISTANCE_X, DISTANCE_X * 5 + 25 * 5, 150, 25);
    }

    private void setProperties() {
        frame.setTitle("Binary Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button_find.setText("Start binary search");
        message_whatToSearch.setText("Input size of array for nums to search:");
        message_whereSearch.setText("Input size of array where search:");
        //input_length_whatToSearch.setText("2");
        //input_length_whereSearch.setText("2");

        panel.setPreferredSize(START_FRAME_SIZE);
        panel.setLayout(null);
    }

    public int getMaxElements() {
        return  ((int) screenSize.getWidth() - 20) / (25 + 5);
    }

    public void changeSearchArraysLength(int[] newLengths) {
        for(int i=0;i<_searchArrays.length;i++) {
            do {
                if (newLengths[i] > _searchArrays[i].size()) {
                    JTextField newJText = new JTextField();
                    _searchArrays[i].add(newJText);
                    panel.add(_searchArrays[i].get(_searchArrays[i].size() - 1));
                }
                if (newLengths[i] < _searchArrays[i].size()) {
                    panel.remove(_searchArrays[i].get(_searchArrays[i].size() - 1));
                    _searchArrays[i].remove(_searchArrays[i].size() - 1);
                }
            } while (_searchArrays[i].size() != newLengths[i]);
        }
    }

    public void refreshSearchArrays(ArrayList<Integer>[] newNumArrays) {
        String newTextForSearchArrays;
        for(int j=0; j<newNumArrays.length;j++) {
            ArrayList<Integer> newNums = newNumArrays[j];
            for(int i=0;i<newNums.size();i++) {
                newTextForSearchArrays = "" + newNums.get(i);
                _searchArrays[j].get(i).setText(newTextForSearchArrays);
            }
        }
    }

    public void refreshFrame() {
        setPositionsForTextFieldsBelongToArrays();
        changeFrameSize();
        frame.pack();
    }

    private void changeFrameSize() {
        int bordersX = 25 + DISTANCE_X * 2;
        int posXOfLastAtWhatSearch = _searchArrays[0].getLast().getX();
        int posXOfLastAtWhereSearch = _searchArrays[1].getLast().getX();

        int newWidth = (posXOfLastAtWhatSearch > posXOfLastAtWhereSearch)
                ? posXOfLastAtWhatSearch : posXOfLastAtWhereSearch;
        newWidth += bordersX;
        if (newWidth < START_FRAME_SIZE.getWidth()) newWidth = (int) START_FRAME_SIZE.getWidth();
        panel.setPreferredSize(new Dimension(newWidth, panel.getHeight()));
    }

    private void setWarning(boolean state) {
        warning_label.setText( (state) ? "Just integer!" : "");
    }

    public boolean sizeInputsIsInteger() {
        boolean isInteger;
        try {
            for (JTextField size : input_lengths) {
                Integer.parseInt(size.getText());
                System.out.println(size.getText());
            }
            isInteger = true;
        }
        catch (Exception ex) {
            isInteger = false;
        }
        setWarning(!isInteger);
        return isInteger;
    }

    public boolean sizeInputsIsNull() {
        for(JTextField length : input_lengths) {
            System.out.println(length.getText() + "  --- " + length);
            if(length == null || length.getText() == " ") return true;
        }
        return false;
    }

    public int[] getLengths() {
        int[] lengths = new int[input_lengths.length];
        int i=0;
        for (JTextField size : input_lengths) {
            lengths[i] = Integer.parseInt(size.getText());
            i++;
        }
        return lengths;
    }

    public void setTextForFieldsWithLenghts(int[] newLengths) {
        int i = 0;
        for(JTextField size : input_lengths) {
            size.setText(new String("" + newLengths[i++]));
        }
    }

    /*public void setTextForFieldsWithLenghts(int[] newLengths) {
        for(int i = 0;i < input_lengths.size(); i++) {
            input_lengths.get(i).setText("" + newLengths[i]);
        }
    }*/

    private void setPositionsForTextFieldsBelongToArrays() {
        for (int i = 0; i < _searchArrays.length; i++) {
            int y;
            int x;
            if (i == 0) y = message_whatToSearch.getY();
            else y = message_whereSearch.getY();
            y += message_whatToSearch.getHeight() + DISTANCE_Y;
            x = 5;
            for (JTextField tF : _searchArrays[i]) {
                tF.setBounds(x, y, 25, 25);
                x += 25 + DISTANCE_X;
            }
        }
    }

    public JTextField get_input_size_whatToSearch() {
        return input_length_whatToSearch;
    }

    public JTextField get_input_size_whereSearch() {
        return input_length_whereSearch;
    }

    public JTextField[] get_input_sizes () {
        return new JTextField[] { get_input_size_whatToSearch(), get_input_size_whereSearch() };
    }

    public JButton get_button_find() {
        return button_find;
    }

}
