import javax.swing.*;
import javax.xml.xquery.XQException;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gui {




    public static void sendButtonPressed(ArrayList<JTextField> tfs,JTextArea ta, String queryOption) throws SQLException, XQException {
        /*
            Function to have the send button call the appropriate function to display the text in the GUI
          */

        switch(queryOption){
            case "Coauthor Search":
                ta.setText(DatabaseQueries.getCoauthors(tfs.get(0).getText()));
                ta.update(ta.getGraphics());
                break;

            case "Paper Search":
                ta.setText(DatabaseQueries.getPaperMetadata(tfs.get(0).getText()));
                ta.update(ta.getGraphics());
                break;

            case "Journal Search":
                ta.setText(DatabaseQueries.getJournalMetadata(tfs.get(0).getText(),tfs.get(1).getText(),tfs.get(2).getText()));
                ta.update(ta.getGraphics());

            case "Conference Search":
                ta.setText(DatabaseQueries.getConferenceMetadata(tfs.get(0).getText(),tfs.get(1).getText()));
                ta.update(ta.getGraphics());
                break;

            case "XQuery 1":
                ta.setText(XQueryTester.xQuery1());
                ta.update(ta.getGraphics());
                break;

            case "XQuery 2":
                ta.setText(XQueryTester.xQuery2(tfs.get(0).getText(),tfs.get(1).getText()));
                ta.update(ta.getGraphics());
                break;

            case "XQuery 3":
                ta.setText(XQueryTester.xQuery3());
                ta.update(ta.getGraphics());
                break;

            case "XQuery 4":
                ta.setText(XQueryTester.xQuery4(tfs.get(0).getText()));
                ta.update(ta.getGraphics());
                break;
        }

    }

    public static void resetButtonPressed(ArrayList<JTextField> tfs,JTextArea ta){
        /*
            Function to clear the fields and text area when the Reset button is pressed in the GUI
         */
        for (JTextField tf: tfs){
            tf.setText("");
            tf.updateUI();
        }
        ta.setText("");
        ta.update(ta.getGraphics());

    }

    public static void setQueryOptions(String queryOption,JPanel panel, JTextArea ta) throws SQLException, XQException {

        /*
            Create the desired layout for the GUI given on which query is submitted from the menu choice.
            The Function removes the previous layout then applies the desired layout with the correct number of fields
            labels and buttons pointed to the correct functions.
         */

        ArrayList<JTextField> tfs = new ArrayList<JTextField>();

        panel.removeAll();
        switch(queryOption){

            case "Coauthor Search":
                JLabel authlabel = new JLabel("Author Name");
                tfs.add(new JTextField(50));
                JButton authsend = new JButton("Send");
                JButton authreset = new JButton("Reset");
                panel.add(authlabel);
                panel.add(tfs.get(0));
                panel.add(authsend);
                panel.add(authreset);

                authsend.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        try {
                            sendButtonPressed(tfs,ta,queryOption);
                        } catch (SQLException | XQException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                authreset.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        resetButtonPressed(tfs,ta);
                    }
                });

                break;

            case "Paper Search":
                JLabel paperlabel = new JLabel("Title");
                tfs.add(new JTextField(50));
                JButton papersend = new JButton("Send");
                JButton paperreset = new JButton("Reset");
                panel.add(paperlabel);
                panel.add(tfs.get(0));
                panel.add(papersend);
                panel.add(paperreset);

                papersend.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        try {
                            sendButtonPressed(tfs,ta,queryOption);
                        } catch (SQLException | XQException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                paperreset.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        resetButtonPressed(tfs,ta);
                    }
                });

                break;
            case "Journal Search":
                JLabel journallabel = new JLabel("Journal Title:");
                JLabel volumelabel = new JLabel("Volume:");
                JLabel issuelabel = new JLabel("Issue:");
                tfs.add(new JTextField(100));
                tfs.add(new JTextField(3));
                tfs.add(new JTextField(2));
                JButton journalsend = new JButton("Send");
                JButton journalreset = new JButton("Reset");
                panel.add(journallabel);
                panel.add(tfs.get(0));
                panel.add(volumelabel);
                panel.add(tfs.get(1));
                panel.add(issuelabel);
                panel.add(tfs.get(2));
                panel.add(journalsend);
                panel.add(journalreset);

                journalsend.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        try {
                            sendButtonPressed(tfs,ta,queryOption);
                        } catch (SQLException | XQException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                journalreset.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        resetButtonPressed(tfs,ta);
                    }
                });

                break;

            case "Conference Search":
                JLabel conflabel = new JLabel("Conference:");
                JLabel yearlabel = new JLabel("Year:");
                tfs.add(new JTextField(50));
                tfs.add(new JTextField(4));
                JButton confsend = new JButton("Send");
                JButton confreset = new JButton("Reset");
                panel.add(conflabel);
                panel.add(tfs.get(0));
                panel.add(yearlabel);
                panel.add(tfs.get(1));
                panel.add(confsend);
                panel.add(confreset);

                confsend.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        try {
                            sendButtonPressed(tfs,ta,queryOption);
                        } catch (SQLException | XQException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                confreset.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        resetButtonPressed(tfs,ta);
                    }
                });

                break;

            /*
                For the Xquery 1 and XQuery 3 there are no parameters to pass through so the function is called as soon
                as the menu item is selected and it is displayed in the text area on the GUI
             */

            case "XQuery 1":
                sendButtonPressed(tfs,ta,queryOption);
                break;

            case "XQuery 2":
                JLabel xq2Authlabel = new JLabel("Author:");
                JLabel xq2Yearlabel = new JLabel("Year:");
                tfs.add(new JTextField(50));
                tfs.add(new JTextField(4));
                JButton xq2send = new JButton("Send");
                JButton xq2reset = new JButton("Reset");
                panel.add(xq2Authlabel);
                panel.add(tfs.get(0));
                panel.add(xq2Yearlabel);
                panel.add(tfs.get(1));
                panel.add(xq2send);
                panel.add(xq2reset);

                xq2send.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        try {
                            sendButtonPressed(tfs,ta,queryOption);
                        } catch (SQLException | XQException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                xq2reset.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        resetButtonPressed(tfs,ta);
                    }
                });
                break;

            case "XQuery 3":
                sendButtonPressed(tfs,ta,queryOption);
                break;

            case "XQuery 4":
                JLabel xq1label = new JLabel("Title");
                tfs.add(new JTextField(50));
                JButton xq1send = new JButton("Send");
                JButton xq1reset = new JButton("Reset");
                panel.add(xq1label);
                panel.add(tfs.get(0));
                panel.add(xq1send);
                panel.add(xq1reset);

                xq1send.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        try {
                            sendButtonPressed(tfs,ta,queryOption);
                        } catch (SQLException | XQException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                xq1reset.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        resetButtonPressed(tfs,ta);
                    }
                });
                break;
        }

        panel.revalidate();
        panel.repaint();


    }

    public static void modeSelected(JMenu m, String queryType) {
        /*
            Changes the mode that the GUI is in switching the Queries menu to have either XQuery Options or SQL based
            options. The text from here is sent to another function to get the desired layout for submission.
         */
        switch(queryType){
            case "XQuery":
                m.getItem(0).setText("XQuery 1");
                m.getItem(1).setText("XQuery 2");
                m.getItem(2).setText("XQuery 3");
                m.getItem(3).setText("XQuery 4");
                break;
            case "SQL":
                m.getItem(0).setText("Coauthor Search");
                m.getItem(1).setText("Paper Search");
                m.getItem(2).setText("Journal Search");
                m.getItem(3).setText("Conference Search");
                break;

        }
        m.updateUI();
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




        //Creating the panel at bottom and adding components
        JPanel panel =  new JPanel();
        JTextArea ta = new JTextArea();

        JScrollPane scroll = new JScrollPane (ta);

        frame.add(scroll);
        frame.setVisible (true);


        ta.setEditable(false);

        //Adding actions to each of the 4 menu buttons.

        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setQueryOptions(m11.getText(),panel,ta);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (XQException ex) {
                    ex.printStackTrace();
                }
            }
        });
        m12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setQueryOptions(m12.getText(),panel,ta);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (XQException ex) {
                    ex.printStackTrace();
                }
            }
        });
        m13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setQueryOptions(m13.getText(),panel,ta);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (XQException ex) {
                    ex.printStackTrace();
                }
            }
        });
        m14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    setQueryOptions(m14.getText(),panel,ta);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (XQException ex) {
                    ex.printStackTrace();
                }
            }
        });


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, scroll);
        frame.setVisible(true);
    }



}