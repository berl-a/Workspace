import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class Focused_password implements FocusListener, ActionListener,CaretListener{
	
	JTextField field;
	JButton button;
	
	Focused_password(){
		JFrame frame=new JFrame("Focused password");
		field=new JTextField();
		field.addCaretListener(this);
		field.setCaretColor(Color.GREEN);
		button=new JButton("������ ������");
		field.setBackground(Color.YELLOW);
		field.addFocusListener(this);
		frame.add(field,BorderLayout.CENTER);
		frame.add(button,BorderLayout.EAST);
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-150, Toolkit.getDefaultToolkit().getScreenSize().height/2-100, 300, 200);
		frame.setVisible(true);
		button.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
	}

	
	
	public void focusLost(FocusEvent e) {

		if((field.getText().toCharArray().length<8)||(field.getText().toCharArray().length>10)){
			field.setBackground(Color.RED);
			button.setEnabled(false);
		}		
		else if(field.getText().toCharArray().length>=8){
			field.setBackground(Color.GREEN);
			button.setEnabled(true);
		}
		else{
			field.setText("���!");
			}
		
	}


	public void focusGained(FocusEvent arg0) {
		button.setEnabled(true);
		
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Focused_password();
			}
		});
	}




	public void actionPerformed(ActionEvent arg0) {
		button.setText("������ '"+field.getText()+"' ��������!");
		//�������� �� ���� ����� 
		//���������:	s=field.getText();
		
	}




	public void caretUpdate(CaretEvent e) {
		if(e.getDot()<8){
			button.setText(8-e.getDot()+" ������� ����������");
		}
		else if(e.getDot()==8){
			button.setText("��� ������!");
		}
		
		else if(e.getDot()==9){
			button.setText("������ ������ �� ���� ������");
			
		}else if(e.getDot()==10){
			button.setText("�� ����������� ������� ������");
		}
		else{button.setText("���������� ����������� �������!");
		}
		
	}
	
}