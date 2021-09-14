import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui {

    public static void sendButtonPressed(JTextField tf,JTextArea ta){
        ta.append(tf.getText());
        ta.update(ta.getGraphics());
    }

    public static void modeSelected(JMenu m, String queryType) {
        System.out.println(queryType);
        switch(queryType){
            case "XQuery":
                System.out.println("XQuery");
                m.getItem(0).setText("XQuery 1");
                m.getItem(1).setText("XQuery 2");
                m.getItem(2).setText("XQuery 3");
                m.getItem(3).setText("XQuery 4");
                break;
            case "SQL":
                System.out.println("SQL");
                m.getItem(0).setText("Coauthor Search");
                m.getItem(1).setText("Paper Search");
                m.getItem(2).setText("Journal Search");
                m.getItem(3).setText("Conference Search");
                break;

        }
        m.updateUI();
    }

    public static void querySelected(String menuString){
        System.out.println(menuString);
    }

    public static void main(String args[]) {
        //Creating the Frame
        JFrame frame = new JFrame("Lab1 Queries");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);


        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("Queries");
        JMenu m2 = new JMenu("Mode");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Coauthor Search");
        JMenuItem m12 = new JMenuItem("Paper Search");
        JMenuItem m13 = new JMenuItem("Journal Search");
        JMenuItem m14 = new JMenuItem("Conference Search");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);

        JMenuItem m21 = new JMenuItem("SQL");
        JMenuItem m22 = new JMenuItem("XQuery");
        m2.add(m21);
        m2.add(m22);

        m22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                modeSelected(m1,m22.getText());
            }
        });

        m21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                modeSelected(m1,m21.getText());
            }
        });

        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                querySelected(m11.getText());
            }
        });

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");

        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        send.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               sendButtonPressed(tf,ta);
            }
        });

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }



}
