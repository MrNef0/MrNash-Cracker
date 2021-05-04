import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Paths;
import java.security.*;
import java.util.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HashCracker extends JFrame implements ActionListener {

    public ArrayList<String> rockyouarray = new ArrayList<String>();
    public ArrayList<String> md5rockyouarray = new ArrayList<String>();
    public ArrayList<String> sha1rockyouarray = new ArrayList<String>();
    public List<String> HashPassArray = new ArrayList<String>();
    public List<String> NameArray = new ArrayList<String>();
    public File file2;
    SwingWorker worker;
    JButton b1 = new JButton("Select Hashes List");
    JButton b2 = new JButton("Select Wordlist");
    JFileChooser choosefile = new JFileChooser();
    JFrame chooseframe;
    JTextArea t2 = new JTextArea(5, 40);
    JScrollPane pane = new JScrollPane(t2);
    JLabel conLabel = new JLabel("Console:");
    JLabel reopLabel = new JLabel("Reopen \"Hash Cracker\" to use it again");
    //---
    JLabel titleLabel = new JLabel("Cracked Hashes");
    String header[] = {"Hash", "Cracked Hash"};
    String data[][];
    DefaultTableModel defaultTableModel;
    JTable hpTable = new JTable(defaultTableModel);
    JScrollPane scrollPane;
    JPanel tablePanel = new JPanel();
    JFileChooser choosefile2 = new JFileChooser();

    //---
    public HashCracker() {
        add(titleLabel);
        defaultTableModel = new DefaultTableModel(data, header);
        hpTable = new JTable(defaultTableModel);
        hpTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(hpTable);
        tablePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        tablePanel.add(scrollPane);
        add(tablePanel);
        setLayout(new FlowLayout());
        t2.setBackground(Color.BLACK);
        t2.setForeground(Color.GREEN);
        t2.setEditable(false);
        add(pane);
        t2.setText("Console:");
        add(b1);
        add(b2);
        reopLabel.setVisible(false);
        add(reopLabel);
        b1.setEnabled(false);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public static void main(String[] args) {
        HashCracker f = new HashCracker();
        f.setTitle("Hash Cracker");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setLocation(1000, 400);
        f.setSize(505, 650);
        f.setVisible(true);
        ImageIcon icon2 = new ImageIcon("images/MRNASH-LOGOR.png");
        f.setIconImage(icon2.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent x) {
        Scanner input;

        t2.append("\nLoading...");
        if (x.getSource() == b1) {
            b1.setEnabled(false);
            reopLabel.setVisible(true);
            choosefile.showOpenDialog(null);
            File file = choosefile.getSelectedFile();
            try {
                input = new Scanner(Paths.get(file.getAbsolutePath()));
                while (input.hasNextLine()) {
                    String s = input.next();
                    HashPassArray.add(s);
                    input.nextLine();
                }

            } catch (Exception e) {
                t2.setText("Console:\n");
            }

            t2.setText("Console:\n");
            for (int i = 0; i < HashPassArray.toArray().length; i++) {

                if (HashPassArray.get(i).matches("[A-Za-z0-9]{32}")) {
                    for (int y = 0; y < md5rockyouarray.toArray().length; y++) {
                        if (HashPassArray.get(i).equals(md5rockyouarray.get(y))) {
                            t2.append("1 MD5 password cracked :\n");
                            t2.append("Cracked!, Hash is " + HashPassArray.get(i) + " and the cracked hash is " + rockyouarray.get(y) + "\n");
                            defaultTableModel.insertRow(0, new Object[]{HashPassArray.get(i), rockyouarray.get(y)});
                            break;
                        }
                    }
                } else if (HashPassArray.get(i).matches("[A-Za-z0-9]{40}")) {
                    for (int y = 0; y < sha1rockyouarray.toArray().length; y++) {
                        if (HashPassArray.get(i).equals(sha1rockyouarray.get(y))) {
                            t2.append("1 SHA-1 password cracked :\n");
                            t2.append("Cracked!, Hash is " + HashPassArray.get(i) + " and the cracked hash is " + rockyouarray.get(y) + "\n");
                            defaultTableModel.insertRow(0, new Object[]{HashPassArray.get(i), rockyouarray.get(y)});
                            break;
                        }
                    }
                }
            }
            if (hpTable.getRowCount() == 0) {
                t2.append("Not Crackable");
            }
        }
        if (x.getSource() == b2) {
            b1.setEnabled(true);
            b2.setEnabled(false);
            choosefile2.showOpenDialog(null);
            file2 = choosefile2.getSelectedFile();
            try {

                rockyoulist(file2);
                md5rockyoulist(file2);
                sha1rockyoulist(file2);
                t2.append("\nLoading...");
                t2.append("\nWordlist loaded successfully");
            } catch (Exception e) {
            }
        }
    }

    public void rockyoulist(File x) {
        try {
            Scanner input = new Scanner(Paths.get(x.getAbsolutePath()));
            int counter1 = 0;
            while (input.hasNextLine() && counter1 < 1000000) {
                rockyouarray.add(input.nextLine());
                counter1++;
            }

        } catch (Exception e) {
        }

    }

    public void md5rockyoulist(File x) {
        try {
            Scanner input = new Scanner(Paths.get(x.getAbsolutePath()));
            int counter2 = 0;
            while (input.hasNextLine() && counter2 < 1000000) {
                md5rockyouarray.add(md5hash(input.nextLine()));
                counter2++;
            }

        } catch (Exception e) {

        }

    }

    public void sha1rockyoulist(File x) {
        try {
            Scanner input = new Scanner(Paths.get(x.getAbsolutePath()));
            int counter3 = 0;
            while (input.hasNextLine() && counter3 < 1000000) {
                sha1rockyouarray.add(sha1hash(input.nextLine()));
                counter3++;
            }

        } catch (Exception e) {

        }

    }

    public static String md5hash(String s) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        md5.update(s.getBytes());
        byte[] md5Bytes = md5.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) {
            String hex = Integer.toHexString(0xff & md5Bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static String sha1hash(String x) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(x.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
