package com.lbrce.devops;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {
    TextField display;
    Button[] numberButtons = new Button[10];
    Button addButton, subButton, mulButton, divButton, eqButton, clrButton;
    String operator = "";
    double num1 = 0, num2 = 0, result = 0;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setLayout(null);
        setVisible(true);

        display = new TextField();
        display.setBounds(50, 50, 300, 50);
        display.setEditable(false);
        add(display);

        int x = 50, y = 120;
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setBounds(x, y, 50, 50);
            numberButtons[i].addActionListener(this);
            add(numberButtons[i]);
            x += 70;
            if ((i + 1) % 3 == 0) {
                x = 50;
                y += 70;
            }
        }

        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        eqButton = new Button("=");
        clrButton = new Button("C");

        addButton.setBounds(260, 120, 50, 50);
        subButton.setBounds(260, 190, 50, 50);
        mulButton.setBounds(260, 260, 50, 50);
        divButton.setBounds(260, 330, 50, 50);
        eqButton.setBounds(50, 400, 120, 50);
        clrButton.setBounds(180, 400, 120, 50);

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        eqButton.addActionListener(this);
        clrButton.addActionListener(this);

        add(addButton);
        add(subButton);
        add(mulButton);
        add(divButton);
        add(eqButton);
        add(clrButton);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals("+") || command.equals("-") || 
                   command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(display.getText());
            operator = command;
            display.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 == 0) {
                        display.setText("Error");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            display.setText(String.valueOf(result));
        } else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
