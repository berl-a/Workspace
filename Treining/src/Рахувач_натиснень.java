import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class Рахувач_натиснень implements KeyListener, ActionListener {

	Timer ts;
	JLabel label,label1;
	int i=0,nos=10;
	public Рахувач_натиснень(){
		Timer t=new Timer(10000,this);
		ts=new Timer(500,this);
		ts.setRepeats(false);
		ts.setActionCommand("Enought");
		t.setRepeats(true);
		t.start();
		JFrame frame=new JFrame("rve");
		label=new JLabel(String.valueOf(i),JLabel.RIGHT);
		label1=new JLabel("",JLabel.LEFT);
		frame.add(label,BorderLayout.CENTER);
		frame.add(label1,BorderLayout.NORTH);
		label1.setVisible(false);
		frame.setSize(300, 200);
		frame.setVisible(true);
		frame.addKeyListener(this);
	}
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Рахувач_натиснень();
			}
		});

	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		i++;
		label.setText(String.valueOf(i));
		
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(e.getActionCommand()=="Enought")){
		nos+=10;
		label1.setText(String.valueOf((i/(nos)))+" symbols per second");
		label1.setVisible(true);
		ts.start();
		}
		else{label1.setVisible(false);}
		
	}

}
