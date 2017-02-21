package com.propig.game.game2048.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by msi on 2017/2/20.
 */
public class JAboutDialog extends JDialog {
    public JAboutDialog(JFrame parent) {
        setTitle("About");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel name = new JLabel("Notes");
        name.setAlignmentX(0.5f);
        add(name);

        add(Box.createRigidArea(new Dimension(0, 100)));
        setSize(300, 200);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
    }
}
