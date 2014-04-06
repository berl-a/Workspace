package Shifrator;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Shifrator implements KeyListener, FocusListener{
	JFrame frame;
	JTextField fieldIn,fieldOut;
	boolean InIsFocused;
	
	Shifrator(){
		frame=new JFrame("Encrypter. Made by BubbleGum");
		frame.setLayout(new FlowLayout());
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-100, Toolkit.getDefaultToolkit().getScreenSize().height/2-46, 200, 92);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.getContentPane().setBackground(new Color(255,248,220));
		fieldIn=new JTextField(15);
		fieldIn.addKeyListener(this);
		fieldIn.addFocusListener(this);
		fieldIn.setToolTipText("Натисніть enter для шифрування, shift для копіювання, ctrl для очищення");
		fieldIn.setBackground(new Color(0,255,127));
		fieldOut=new JTextField(15);
		fieldOut.setBackground(new Color(255,99,71));
		fieldOut.addKeyListener(this);
		fieldOut.addFocusListener(this);
		fieldOut.setToolTipText("Натисніть enter для розшифрування, shift для копіювання, ctrl для очищення");
		frame.add(fieldIn);
		frame.add(fieldOut);
		InIsFocused=true;
		
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Shifrator();
			}
		});

	}
	
	public String code(String s) {
		char[] c=s.toCharArray(),newc=new char[s.length()];
		for(int i=0;i<c.length;i++){
			int n=(int)c[i]+15;
			newc[i]=(char)n;
		}
		
		return String.copyValueOf(newc)+c[0];
		
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if((e.getKeyCode()==17)&&(InIsFocused==true)){
			fieldIn.setText("");
		}
		if((e.getKeyCode()==17)&&(InIsFocused==false)){
			fieldOut.setText("");
		}
		
		if((e.getKeyCode()==16)&&(InIsFocused==true)){
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(fieldIn.getText()), null);
			}
		else if((e.getKeyCode()==16)&&(InIsFocused==false)){
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(fieldOut.getText()), null);
		}
		if((e.getKeyCode()==10)&&(InIsFocused==true)&&(fieldIn.getText()!="")){
			fieldOut.setText(code(fieldIn.getText()));
			fieldOut.requestFocus();
		}
		if((e.getKeyCode()==10)&&(InIsFocused==false)&&(fieldIn.getText()!="")){
			fieldIn.setText(decode(fieldOut.getText()));
			fieldIn.requestFocus();
		}
		if(e.getKeyCode()==27){
			
		}
		
	}


	
	public String decode(String s) {
		char[] newc=new char[s.toCharArray().length-1];
		char[] c=s.toCharArray();
		for(int vi=0;vi<s.length()-1;vi++){
			int n=(int)c[vi]-15;
			newc[vi]=(char)n;
		}

		return String.valueOf(newc);
		
	}


	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}


	@Override
	public void focusGained(FocusEvent e) {
		if(e.getComponent()==fieldIn){
			InIsFocused=true;
		}
		else{InIsFocused=false;} 
		
	}


	@Override
	public void focusLost(FocusEvent e) {
		if(e.getComponent()==fieldIn){
			InIsFocused=false;
		}
		else{InIsFocused=true;}
		
	}
	
}
