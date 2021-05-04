import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;

public class MrNashCracker extends JFrame implements ActionListener {
    
    ImageIcon crack1 = new ImageIcon("images\\MRNASH-MAIN2.png");
    JLabel l2 = new JLabel(crack1);
    JLabel account = new JLabel("Twitter: @MrNef0");
    JLabel account2 = new JLabel("Linkedin: https://www.linkedin.com/in/naif-hussain/");
    JLabel version = new JLabel("Version 1.2");
    JButton b1 = new JButton("Hash Creator");
    JButton b2 = new JButton("Hash Cracker");

    public MrNashCracker() {
        setLayout(null);
        account.setForeground(Color.GRAY);
        account2.setForeground(Color.GRAY);
        version.setForeground(Color.GRAY);
        add(b1).setBounds(90, 350, 150, 60);
        add(b2).setBounds(270, 350, 150, 60);
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);
        add(version).setBounds(423, 452, 500, 60);
        add(account).setBounds(10, 432, 500, 60);
        add(account2).setBounds(10, 452, 500, 60);
        add(l2).setBounds(0, 0, 500, 508);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setLocation(690, 320);
        setResizable(false);
    }

    public static void main(String[] args) throws Exception {
        
        MrNashCracker f = new MrNashCracker();
        f.setTitle("MrNash Cracker");
        f.setForeground(Color.RED);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(505, 530);
        f.setVisible(true);
        ImageIcon icon2 = new ImageIcon("images\\MRNASH-LOGOB.png");
        f.setIconImage(icon2.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent x) {
        if (x.getSource() == b1) {
            HashCreator.main(null);
        } else if (x.getSource() == b2) {
            HashCracker.main(null);
        }

    }

}