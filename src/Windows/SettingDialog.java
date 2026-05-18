package Windows;

import GameMechanics.Settings;
import Others.CustomButton;

import javax.swing.*;
import java.awt.*;

/**
 * A dialog window that allows the user to configure game settings,
 * such as the background image, active currency, and simulation speed.
 */
public class SettingDialog extends JDialog {

    /**
     * Constructs and displays the SettingDialog.
     *
     * @param owner    The parent frame that owns this dialog.
     * @param settings The global settings object to read from and update.
     */
    public SettingDialog(Frame owner, Settings settings) {
        super(owner, true);
        setSize(300, 160);
        setLocationRelativeTo(owner);
        setResizable(false);
        setTitle("Settings");

        JPanel settingPanel = new JPanel(new GridLayout(3, 2));

        JComboBox<String> backgroundImageList = new JComboBox<>(settings.getBackgroundList());
        JComboBox<String> currencyList = new JComboBox<>(settings.getCurrencyList());

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(settings.getDelay(), 1, null, 1);
        JSpinner amountSpinner = new JSpinner(spinnerModel);

        JLabel backgroundLabel = new JLabel("Background Image:");
        JLabel currencyLabel = new JLabel("Currency:");
        JLabel delayLabel = new JLabel("Day speed (ms):");
        delayLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        currencyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        backgroundLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        settingPanel.add(backgroundLabel);
        settingPanel.add(backgroundImageList);
        settingPanel.add(currencyLabel);
        settingPanel.add(currencyList);
        settingPanel.add(delayLabel);
        settingPanel.add(amountSpinner);

        add(settingPanel);

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
