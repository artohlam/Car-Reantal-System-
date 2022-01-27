package GUI;

import BackendCode.CarOwner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class CarOwner_Add {

    JButton Add_Button, Cancel_Button;
    JLabel ADR_Label, Name_Label, Contact_Label, Email_Label, UserName_Label, Password_Label, ADRValidity_Label, contactValidity_Label, NameValidity_Label, EmailValidity_Label, UserNameValidity_Label, PasswordValidity_Label;
    JTextField ADR_TextField, Name_TextField, Contact_TextField, Email_TextField, UserName_TextField, Password_TextField;
    JFrame frame = new JFrame();

    public CarOwner_Add() {
        frame.setTitle("Add CarOwner");
        frame.setLayout(new AbsoluteLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                frame.dispose();
            }
        });

        frame.setSize(new Dimension(450, 290));
        frame.setResizable(false);
        frame.setLocationRelativeTo(Parent_JFrame.getMainFrame());

        Add_Button = new JButton("Add");
        Cancel_Button = new JButton("Cancel");

        ADR_Label = new JLabel("Enter ADHAAR No.(no dashes)");
        Name_Label = new JLabel("Enter Name");
        Contact_Label = new JLabel("Enter Contact");
        Email_Label = new JLabel("Enter Email");
        UserName_Label = new JLabel("Enter Username");
        Password_Label = new JLabel("Enter Password");
        ADRValidity_Label = new JLabel();
        NameValidity_Label = new JLabel();
        EmailValidity_Label = new JLabel();
        UserNameValidity_Label = new JLabel();
        PasswordValidity_Label = new JLabel();
        contactValidity_Label = new JLabel();
        ADR_TextField = new JTextField();
        Name_TextField = new JTextField();
        Contact_TextField = new JTextField();
        Email_TextField = new JTextField();
        UserName_TextField = new JTextField();
        Password_TextField = new JTextField();

        ADR_TextField.setPreferredSize(new Dimension(240, 22));
        Name_TextField.setPreferredSize(new Dimension(240, 22));
        Contact_TextField.setPreferredSize(new Dimension(240, 22));
        Email_TextField.setPreferredSize(new Dimension(240, 22));
        UserName_TextField.setPreferredSize(new Dimension(240, 22));
        Password_TextField.setPreferredSize(new Dimension(240, 22));

        ADR_Label.setPreferredSize(new Dimension(175, 22));
        Name_Label.setPreferredSize(new Dimension(175, 22));
        Contact_Label.setPreferredSize(new Dimension(175, 22));
        Email_Label.setPreferredSize(new Dimension(175, 22));
        UserName_Label.setPreferredSize(new Dimension(175, 22));
        Password_Label.setPreferredSize(new Dimension(175, 22));
        ADRValidity_Label.setPreferredSize(new Dimension(240, 9));
        contactValidity_Label.setPreferredSize(new Dimension(240, 9));
        NameValidity_Label.setPreferredSize(new Dimension(240, 9));
        EmailValidity_Label.setPreferredSize(new Dimension(240, 9));
        UserNameValidity_Label.setPreferredSize(new Dimension(240, 9));
        PasswordValidity_Label.setPreferredSize(new Dimension(240, 9));

        ADRValidity_Label.setForeground(Color.red);
        contactValidity_Label.setForeground(Color.red);
        NameValidity_Label.setForeground(Color.red);
        EmailValidity_Label.setForeground(Color.red);
        UserNameValidity_Label.setForeground(Color.red);
        PasswordValidity_Label.setForeground(Color.red);

        frame.add(ADR_Label, new AbsoluteConstraints(10, 5));
        frame.add(ADR_TextField, new AbsoluteConstraints(195, 5));
        frame.add(ADRValidity_Label, new AbsoluteConstraints(195, 30));

        frame.add(Name_Label, new AbsoluteConstraints(10, 42));
        frame.add(Name_TextField, new AbsoluteConstraints(195, 42));
        frame.add(NameValidity_Label, new AbsoluteConstraints(195, 66));

        frame.add(Contact_Label, new AbsoluteConstraints(10, 77));
        frame.add(Contact_TextField, new AbsoluteConstraints(195, 77));
        frame.add(contactValidity_Label, new AbsoluteConstraints(195, 102));

        frame.add(Add_Button, new AbsoluteConstraints(100, 225, 100, 22));
        frame.add(Cancel_Button, new AbsoluteConstraints(250, 225, 100, 22));

        Add_Button.addActionListener(new CarOwner_Add_ActionListener());

        Cancel_Button.addActionListener(new CarOwner_Add_ActionListener());
    }

    private class CarOwner_Add_ActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Add": {
                    String cnic = ADR_TextField.getText().trim();
                    String name = Name_TextField.getText().trim();
                    String contact = Contact_TextField.getText().trim();

                    if (CarOwner.isADRValid(cnic)) {
                        CarOwner carOwner = CarOwner.SearchByADR(cnic);
                        if (carOwner == null) {
                            if (CarOwner.isNameValid(name)) {
                                if (CarOwner.isContactNoValid(contact)) {
                                    new CarOwner(0, 0, cnic, name, contact).Add(); // ID is Auto
                                    Parent_JFrame.getMainFrame().getContentPane().removeAll();
                                    CarOwner_Details cd = new CarOwner_Details();
                                    Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                                    Parent_JFrame.getMainFrame().getContentPane().revalidate();
                                    Parent_JFrame.getMainFrame().setEnabled(true);
                                    JOptionPane.showMessageDialog(null, "Car Owner added successfully !");
                                    frame.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid contact no. !");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid Name !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "This ADHAAR No. is already registered !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid ADHAAR No.");
                    }
                    break;
                }
                case "Cancel": {
                    Parent_JFrame.getMainFrame().setEnabled(true);
                    frame.dispose();
                    break;
                }
            }
        }

    }
}
