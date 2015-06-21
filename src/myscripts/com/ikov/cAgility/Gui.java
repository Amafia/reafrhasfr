package myscripts.com.ikov.cAgility;

import myscripts.com.ikov.cAgility.data.Variables;
import org.rev317.min.api.methods.Npcs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by Capslock
 * On 25/05/15
 * With IntelliJ
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Gui extends JFrame {

    /**
     * Create the frame.
     */
    public Gui() {
        setResizable(false);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Gnome Course Net", "Barbarian Course Swing", "Wildy Course Swing"}));
        comboBox.setBounds(57, 73, 329, 148);
        contentPane.add(comboBox);

        JLabel lblCagility = new JLabel("cAgility");
        lblCagility.setFont(new Font("Zapfino", Font.BOLD, 24));
        lblCagility.setBounds(117, 21, 192, 82);
        contentPane.add(lblCagility);

        JButton btnStartScript = new JButton("Start Script");
        btnStartScript.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (comboBox.getSelectedIndex()) {
                    case 0:
                        Variables.setNetGnome(true);
                        break;
                    case 1:
                        Variables.setSwingBarb(true);
                        break;
                    case 2:
                        Variables.setSwingWild(true);
                        break;
                    default:
                        System.out.println("Something went wrong while configuring the GUI, please contact the script writer.");
                        break;
                }
                setVisible(false);
                System.out.println(Arrays.toString(Npcs.getNpcs()));
            }
        });
        btnStartScript.setBounds(155, 233, 113, 29);
        contentPane.add(btnStartScript);
    }

    private JComboBox comboBox = new JComboBox();
}
