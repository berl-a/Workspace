import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
    public static void main(String[] args) {
        JFrame frame=new JFrame("Test");
        frame.setBounds(0, 0,400,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(){
            Graphics2D g2;

            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g2=(Graphics2D)g;
                g2.setColor(Color.BLACK);
                g2.drawLine(20, 20, 360, 20);
            }
        };
        frame.setContentPane(contentPane);
    }
}