import javax.swing.*;
import java.io.*;
import java.security.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HashCreator extends JFrame implements ActionListener {

    DefaultTableModel defaultTableModel;
    JScrollPane scrollPane;

    JLabel titleLabel = new JLabel("New Hashes");
    JTable hpTable = new JTable();
    JFileChooser savefile = new JFileChooser();
    JFrame saveframe;

    JTextField string = new JTextField();

    JRadioButton rMD5 = new JRadioButton("MD5");
    JRadioButton rSHA1 = new JRadioButton("SHA-1");
    ButtonGroup G = new ButtonGroup();
    public boolean boof = false;
    JComboBox typeBox = new JComboBox();

    JButton insertBtn = new JButton("INSERT");
    JButton deleteBtn = new JButton("DELETE");
    JButton saveBtn = new JButton("SAVE");
    String header[] = {"Hashed String"};
    String data[][];

    public HashCreator() {

        JPanel headerPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 50, 20));
        JPanel footerPanel = new JPanel(new GridLayout(2, 1, 0, 0));

        headerPanel.add(titleLabel);
        G.add(rMD5);
        G.add(rSHA1);
        inputPanel.add(string);
        inputPanel.add(rMD5);
        inputPanel.add(rSHA1);
        buttonPanel.add(insertBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(saveBtn);

        footerPanel.add(inputPanel);
        footerPanel.add(buttonPanel);

        defaultTableModel = new DefaultTableModel(data, header);
        hpTable = new JTable(defaultTableModel);
        hpTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(hpTable);
        tablePanel.add(scrollPane);
        setLayout(new BorderLayout(0, 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        insertBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        rMD5.addActionListener(this);
        rSHA1.addActionListener(this);
    }

    public static void main(String[] args) {
        HashCreator obj = new HashCreator();  
        Class class1 = obj.getClass();  
        URL url = class1.getResource("images\\MRNASH-LOGOG.png"); 
        
        HashCreator f = new HashCreator();
        f.setTitle("Hash Creator");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocation(600, 400);
        f.setSize(500, 500);
        f.setVisible(true);
        ImageIcon icon2 = new ImageIcon("images\\MRNASH-LOGOG.png");
        f.setIconImage(icon2.getImage());
        new HashCreator();
    }

    private void addRows(String string) {
        defaultTableModel = (DefaultTableModel) hpTable.getModel();
        String[] rowData = {string};
        defaultTableModel.addRow(rowData);
    }

    @Override
    public void actionPerformed(ActionEvent x) {
        if (x.getSource() == insertBtn) {
            if (rMD5.isSelected()) {
                try {
                    addRows(md5hash(string.getText()));
                    string.setText(null);
                    JOptionPane.showMessageDialog(null, "Row entered sucessfully");
                } catch (Exception e) {

                }
                
            } else if (rSHA1.isSelected()) {
                try {
                    addRows(sha1hash(string.getText()));
                    string.setText(null);
                    JOptionPane.showMessageDialog(null, "Row entered sucessfully");
                } catch (Exception e) {

                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Make sure you entered credentials correctly", "Error", 2);
            }
            
            if ((rSHA1.isSelected() || rMD5.isSelected()) == boof) {
                JOptionPane.showMessageDialog(null, "You must select at least 1 hash algorithm", "Hash Algorithm Error", 2);
            }
        }
        
        try {
            if (x.getSource() == deleteBtn) {
                defaultTableModel.removeRow(hpTable.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
        } catch (Exception e) {

        }

        if (x.getSource() == saveBtn) {

            Scanner input;
            try {
                String values = null;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:\\Users\\PC\\Desktop"));
                fileChooser.showSaveDialog(null);
                FileWriter fw = new FileWriter(fileChooser.getSelectedFile() + ".txt");
                BufferedWriter bw = new BufferedWriter(fw);
                for (int row = 0; row < hpTable.getRowCount(); row++) {
                    for (int column = 0; column < hpTable.getColumnCount(); column++) {
                        values = (values + "" + hpTable.getValueAt(row, column));

                    }
                    values = (values + "\n");
                }
                String updatedvalues = values.replaceFirst("(?:null)", "");
                updatedvalues = updatedvalues.substring(0, updatedvalues.length() - 1);
                bw.write(updatedvalues);
                bw.close();
                fw.close();
            } catch (Exception e) {

            }
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