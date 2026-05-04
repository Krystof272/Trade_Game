package Windows;

import GameMechanics.Settings;
import Others.CustomButton;

import javax.swing.*;
import java.awt.*;

public class SettingDialog extends JDialog {
    public SettingDialog(Frame owner, Settings settings) {
        super(owner, true);
        setSize(200, 160);
        setLocationRelativeTo(owner);
        setResizable(false);
        setTitle("Settings");

        JPanel comboBoxPanel = new JPanel(new GridLayout(3, 1));

        JComboBox<String> backgroundImageList = new JComboBox<>(settings.getBackgroundList());
        JComboBox<String> currencyList = new JComboBox<>(settings.getCurrencyList());

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(settings.getDelay(), 1, null, 1);
        JSpinner amountSpinner = new JSpinner(spinnerModel);

        comboBoxPanel.add(backgroundImageList);
        comboBoxPanel.add(currencyList);
        comboBoxPanel.add(amountSpinner);

        add(comboBoxPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        CustomButton.changeGreen(save);
        CustomButton.changeRed(cancel);
        save.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        cancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        buttonPanel.add(save);
        buttonPanel.add(cancel);

        add(buttonPanel,  BorderLayout.SOUTH);

        save.addActionListener(e -> {
            settings.setImageBackgroundName((String) backgroundImageList.getSelectedItem());
            settings.setCurrency((String) currencyList.getSelectedItem());
            settings.setDelay((int) amountSpinner.getValue());
            this.dispose();
        });

        cancel.addActionListener(e -> {
            this.dispose();
        });

        this.setVisible(true);
    }
}
