import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ButtonDemo implements ActionListener{

	public JLabel jlab;
	ButtonDemo(){
		JFrame jfrm=new JFrame("Frame!");
		jfrm.setLayout(new GridLayout(2,2));
		jfrm.setSize(535, 320);
	    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JButton Button1=new JButton("+");
	    JButton Button2=new JButton("Button2");
	    Button1.addActionListener(this);
	    Button2.addActionListener(this);
	    JButton Button3=new JButton("Button3");
	    Button3.addActionListener(this);
	    JButton Button4=new JButton("Button4");
	    Button4.addActionListener(this);
	    Button1.setBounds(1, 1, 50, 50);
	    jlab=new JLabel("LABEL!!!");
	    jfrm.add(jlab);
	    jfrm.add(Button1);
	    jfrm.add(Button2);
	    jfrm.add(Button3);
	    jfrm.add(Button4);
	    jfrm.setVisible(true);
	    }
	    public void actionPerformed(ActionEvent ae){
	    	if(ae.getActionCommand().equals("+")){
	    		jlab.setText("Pres 1");
	    	    }
	    	else{
	    		jlab.setText("Pres 2");
	    	    }
	        }

	    public static void main(String[] args){
	        SwingUtilities.invokeLater(new Runnable(){
	        	public void run(){
	        		new ButtonDemo();
	        	    }
	            });
	    }
}	    
	        
	        
	    
	    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
