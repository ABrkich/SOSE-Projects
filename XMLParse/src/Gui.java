import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gui {


    public static void sendButtonPressed(ArrayList<JTextField> tfs,JTextArea ta, String queryOption) throws SQLException {


        switch(queryOption){
            case "Coauthor Search":
                ta.setText(DatabaseQueries.getCoauthors(tfs.get(0).getText()));
                ta.update(ta.getGraphics());
                break;
            case "Paper Search":
                ta.setText(DatabaseQueries.getPaperMetadata(tfs.get(0).getText()));
                break;
            case "Journal Search":
                ta.setText(DatabaseQueries.getJournalMetadata(tfs.get(0).getText(),tfs.get(1).getText(),tfs.get(2).getText()));
                break;
            case "Conference Search":
                ta.setText(DatabaseQueries.getConferenceMetadata(tfs.get(0).getText(),tfs.get(1).getText()));
        }

    }

    public static void resetButtonPressed(ArrayList<JTextField> tfs,JTextArea ta){

        for (JTextField tf: tfs){
            tf.setText("");
            tf.updateUI();
        }
        ta.setText("");
        ta.update(ta.getGraphics());

    }

    public static void setQueryOptions(String queryOption,JPanel panel, JTextArea ta){

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
                        } catch (SQLException ex) {
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
                        } catch (SQLException ex) {
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
                        } catch (SQLException ex) {
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
                        } catch (SQLException ex) {
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
        }

        panel.revalidate();
        panel.repaint();


    }

    public static void modeSelected(JMenu m, String queryType) {
        System.out.println(queryType);
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

        m11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setQueryOptions(m11.getText(),panel,ta);
            }
        });
        m12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setQueryOptions(m12.getText(),panel,ta);
            }
        });
        m13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setQueryOptions(m13.getText(),panel,ta);
            }
        });
        m14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setQueryOptions(m14.getText(),panel,ta);
            }
        });


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, scroll);
        frame.setVisible(true);
    }



}