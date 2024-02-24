package tec.poo.hello;

import javax.swing.*;

public class HelloWorldSwing {
    public static void main(String... args){

        JFrame frame = new JFrame("Hello Java");
        JLabel label = new JLabel("Hello java!!", JLabel.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);
        frame.setSize(300,150);
        frame.setLocation(400,400);
        frame.setVisible(true);
    }
}
