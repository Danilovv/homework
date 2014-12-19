package ru.Vladimir;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;

/**
 * Created by Vladimir_Danilov on 19-Dec-14.
 */
public class Main {

    private static DocumentListener onChange;
    private static FocusListener onFocusLost;
    private static MouseAdapter onClick;

    private static View view;
    private static Data data;

    public static void main(String[] args) {
        int[] startSizeOfArrays = new int[] { 2, 2 };
        view = new View();
        data = new Data(view.getMaxElements());
        initListeners();
        sendListeners();
        // First time set default length for arrays and refreshing frame
        data.setLengths(startSizeOfArrays);
        view.setTextForFieldsWithLenghts(startSizeOfArrays);
        view.changeSearchArraysLength(data.getLengths());
        view.refreshFrame();
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
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (view.sizeInputsIsInteger()) {
                            if (!view.sizeInputsIsNull()) {
                                if (data.isLengthsFitLimits(view.getLengths())) {
                                    data.setLengths(view.getLengths());
                                } else {
                                    data.normalizeLengths(view.getLengths());
                                    view.setTextForFieldsWithLenghts(data.getLengths());
                                }
                            } /*else {
                                view.setTextForFieldsWithLenghts(data.getLengths());
                            }*/
                        }
                    }
                });

            }
        };

        onFocusLost = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (view.sizeInputsIsNull()) {
                    view.setTextForFieldsWithLenghts(data.getLengths());
                }
                view.changeSearchArraysLength(data.getLengths());
                view.refreshSearchArrays((View.ArrayList<Integer>[]) data.get_searchArrays());
                view.refreshFrame();
            }
        };
    }

    private static void sendListeners() {
        for(JTextField textField : view.get_input_sizes()) {
            textField.getDocument().addDocumentListener(onChange);
            textField.addFocusListener(onFocusLost);
        }


    }

    private void setLength() {

    }
}
