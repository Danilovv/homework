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
class Main2 {

    private static final Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int MAX_COUNT = ((int) sSize.getWidth() - 20) / (25 + 5);
    private static final int MIN_COUNT = 2;
    private static final int DISTANCE_X = 5;
    private static final int DISTANCE_Y = 5;
    private static final Dimension START_FRAME_SIZE = new Dimension(330, 180);

    private static JPanel panel;
    private static JTextField input_size_whatToSearch;
    private static JTextField input_size_whereSearch;
    private static JLabel message_whatToSearch;
    private static JLabel message_whereSearch;
    private static JLabel warning_label;
    private static JButton button_find;
    private static JFrame frame;
    private static ArrayList<JTextField> _toSearch;
    private static ArrayList<JTextField> _whereSearch;
    private static ArrayList<JTextField>[] arrayList;
    private static int[] input_size_newText = new int[2];

    private static DocumentListener onChange;
    private static FocusListener onFocusLost;
    private static MouseAdapter onClick;

    // TODO: what if add Container with all swing objects?

    public static void main(String[] args) {
        _toSearch = new ArrayList<JTextField>();
        _whereSearch = new ArrayList<JTextField>();
        arrayList = new ArrayList[]{_toSearch, _whereSearch};

        initJObjects();
        initListeners();
        setProperties();
        setJPositions();
        setJListeners();

        panel.add(message_whatToSearch);
        panel.add(message_whereSearch);
        panel.add(input_size_whatToSearch);
        panel.add(input_size_whereSearch);
        panel.add(warning_label);
        panel.add(button_find);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        checkInputSizeFields();
        changeLength(Integer.parseInt(input_size_whatToSearch.getText()),
                Integer.parseInt(input_size_whereSearch.getText()));
        setPositionsForTextFields();
    }

    private static void initJObjects() {
        frame = new JFrame();
        panel = new JPanel();

        button_find = new JButton();
        message_whatToSearch = new JLabel();
        message_whereSearch = new JLabel();

        input_size_whatToSearch = new JTextField();
        input_size_whereSearch = new JTextField();

        warning_label = new JLabel();
    }

    private static void setJListeners() {
        button_find.addMouseListener(onClick);

        input_size_whatToSearch.getDocument().addDocumentListener(onChange);
        input_size_whereSearch.getDocument().addDocumentListener(onChange);
        input_size_whatToSearch.addFocusListener(onFocusLost);
        input_size_whereSearch.addFocusListener(onFocusLost);
    }

    private static void setJPositions() {
        frame.setLocation((int) sSize.getWidth() / 2, (int) sSize.getHeight() / 2);

        message_whatToSearch.setBounds(DISTANCE_X, DISTANCE_Y, 250, 25);
        message_whereSearch.setBounds(DISTANCE_X, message_whatToSearch.getY() + message_whatToSearch.getHeight() * 2 + DISTANCE_Y * 2,
                message_whatToSearch.getWidth(), message_whatToSearch.getHeight());

        input_size_whatToSearch.setBounds(message_whatToSearch.getWidth(), message_whatToSearch.getY(), 25, 25);
        input_size_whereSearch.setBounds(message_whereSearch.getWidth(), message_whereSearch.getY(), 25, 25);

        warning_label.setBounds(DISTANCE_X, DISTANCE_Y * 4 + 25 * 4, 250, 25);

        button_find.setBounds(DISTANCE_X, DISTANCE_X * 5 + 25 * 5, 150, 25);
    }

    private static void setProperties() {
        frame.setTitle("Binary Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button_find.setText("Start binary search");
        message_whatToSearch.setText("Input size of array for nums to search:");
        message_whereSearch.setText("Input size of array where search:");
        input_size_whatToSearch.setText("2");
        input_size_whereSearch.setText("2");

        panel.setPreferredSize(START_FRAME_SIZE);
        panel.setLayout(null);
    }

    private static void initListeners() {
        onChange = new DocumentListener() {
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
                    input_size_newText =
                            changeLength(Integer.parseInt(input_size_whatToSearch.getText()),
                                    Integer.parseInt(input_size_whereSearch.getText()));
                }
            }
        };

        onFocusLost = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                refreshFrame();
            }
        };

        onClick = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (checkInputNumsFields()) {
                    int[][] nums_input = getNums();
                    int[][] nums_output = BinarySearch.start(nums_input);
                    showResult(nums_input[0], nums_output[0], nums_output[1]);
                }
            }
        };
    }

    private static int[][] getNums() {
        int[][] nums = new int[2][];
        nums[0] = new int[_toSearch.size()];
        nums[1] = new int[_whereSearch.size()];
        int i = 0;
        int j = 0;
        for (ArrayList<JTextField> arr : arrayList) {
            for (JTextField tF : arr) {
                nums[i][j] = Integer.parseInt(tF.getText());
                j++;
            }
            i++;
            j = 0;
        }
        return nums;
    }

    private static boolean checkInputSizeFields() {
        try {
            Integer.parseInt(input_size_whatToSearch.getText());
            Integer.parseInt(input_size_whereSearch.getText());
            warning_label.setText("");
        } catch (Exception ex) {
            warning_label.setText("Just integer!");
            return false;
        }
        return true;
    }

    private static boolean checkInputNumsFields() {
        for (ArrayList<JTextField> arr : arrayList)
            for (JTextField tF : arr) {
                try {
                    Integer.parseInt(tF.getText());
                } catch (Exception ex) {
                    warning_label.setText("Just integer!");
                    return false;
                }
            }
        return true;
    }



    private static int[] changeLength(int lenToSearch, int lenWhereSearch) {
        int[] lengths = {lenToSearch, lenWhereSearch};

        for (int i = 0; i < arrayList.length; i++) {
            if (lengths[i] < MIN_COUNT) lengths[i] = MIN_COUNT;
            if (lengths[i] > MAX_COUNT) lengths[i] = MAX_COUNT;
            setLength(arrayList[i], lengths[i]);
        }

        return lengths;
    }

    private static int[] getLengthsOfArrays()  {
        return new int[] { arrayList[0].size()-1, arrayList[1].size()-1 };
    }

    private static void setLength(ArrayList<JTextField> arr, int _length) {
        do {
            if (_length > arr.size()) {
                JTextField newJText = new JTextField();
                arr.add(newJText);
                panel.add(arr.get(arr.size() - 1));
            }
            if (_length < arr.size()) {
                panel.remove(arr.get(arr.size() - 1));
                arr.remove(arr.size() - 1);
            }
        } while (arr.size() != _length);
    }

    private static void setPositionsForTextFields() {
        for (int i = 0; i < arrayList.length; i++) {
            int y;
            int x;
            if (i == 0) y = message_whatToSearch.getY();
            else y = message_whereSearch.getY();
            y += message_whatToSearch.getHeight() + DISTANCE_Y;
            x = 5;
            for (JTextField tF : (ArrayList<JTextField>) arrayList[i]) {
                tF.setBounds(x, y, 25, 25);
                x += 25 + DISTANCE_X;
            }
        }
    }

    private static void changeFrameSize() {
        int positionOfLastTextField1_X = (_toSearch.get(((ArrayList<JTextField>) arrayList[0]).size() - 1)).getX();
        int positionOfLastTextField2_X = (_whereSearch.get(((ArrayList<JTextField>) arrayList[1]).size() - 1)).getX();
        int bordersX = 25 + DISTANCE_X * 2;
        int newWidth;

        if (positionOfLastTextField1_X > positionOfLastTextField2_X)
            newWidth = positionOfLastTextField1_X;
        else newWidth = positionOfLastTextField2_X;

        newWidth += bordersX;
        if (newWidth < START_FRAME_SIZE.getWidth()) newWidth = (int) START_FRAME_SIZE.getWidth();
        panel.setPreferredSize(new Dimension(newWidth, panel.getHeight()));
    }

    private static void showResult(int[] _toSearch, int[] _foundIndex, int[] _whereSearch) {
        JDialog dialogResult = new JDialog();
        dialogResult.setLocation((int) sSize.getWidth() / 2, (int) sSize.getHeight() / 2);
        int dialog_height = 0;

        JPanel dialogPanel = new JPanel();

        JLabel[] reports = new JLabel[_foundIndex.length];

        for (int i = 0; i < reports.length; i++) {
            reports[i] = new JLabel(_toSearch[i] + " found at " + _foundIndex[i] + " position..");
            reports[i].setBounds(0, dialog_height, 200, 15);
            dialogPanel.add(reports[i]);
            dialog_height += 20;
        }

        JLabel outputArray;
        String output = "Output array:";
        for (int one : _whereSearch)
            output += " " + one;
        outputArray = new JLabel(output);
        outputArray.setBounds(0, dialog_height, 400, 15);
        dialogPanel.add(outputArray);
        dialog_height += outputArray.getHeight();

        dialogPanel.setPreferredSize(new Dimension(400, dialog_height));
        dialogPanel.setLayout(null);

        dialogResult.add(dialogPanel);
        dialogResult.pack();
        dialogResult.setVisible(true);
    }

    private static void refreshFrame() {
        setPositionsForTextFields();
        changeFrameSize();
        panel.repaint();
        frame.pack();
        input_size_whatToSearch.setText("" + input_size_newText[0]);
        input_size_whereSearch.setText("" + input_size_newText[1]);
    }
}
