import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class Parser implements ActionListener{

	int for_name;
	String[][] array=new String[4][1000];
	int array_layer=0;
	JTextField f_shop;
	JTextField f_thing;
	JTextField f_price1;
	JTextField f_price2;
	
	
	Parser(){
		//Перевірка
		while(true){
			try{
				for_name=Integer.parseInt(JOptionPane.showInputDialog("Скільки літер вам вистачить для назв?"));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Введіть число!");
				continue;
			}
			break;
		}

		//Графічна частина
		JFrame frame=new JFrame("Parser");
		frame.setLayout(new FlowLayout());
		frame.setFocusCycleRoot(true);
		frame.addMouseWheelListener(new MouseWheelListener(){

			public void mouseWheelMoved(MouseWheelEvent e) {
				parser();
			}			
		});
		
		JLabel l_shop=new JLabel("Магазин");
		JLabel l_thing=new JLabel("Товар");
		JLabel l_price1=new JLabel("Ціна покупки");
		JLabel l_price2=new JLabel("Ціна продажу");
						
		f_shop=new JTextField(for_name);
		f_thing=new JTextField(for_name);
		f_price1=new JTextField(5);
		f_price2=new JTextField(5);
				
		f_shop.addCaretListener(new CaretListener(){

			public void caretUpdate(CaretEvent e) {
				if(f_shop.getText().length()==for_name){
					f_shop.transferFocus();
				}				
			}			
		});
		
		f_thing.addCaretListener(new CaretListener(){

			public void caretUpdate(CaretEvent e) {
				if(f_thing.getText().length()==for_name){
					f_thing.transferFocus();
				}				
			}			
		});
		
						
		f_price1.addActionListener(this);
		f_price2.addActionListener(this);
		
		frame.add(l_shop);
		frame.add(f_shop);
		frame.add(l_thing);
		frame.add(f_thing);
		frame.add(l_price1);
		frame.add(f_price1);
		frame.add(l_price2);
		frame.add(f_price2);
		frame.pack();
		frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-(frame.getWidth()))/2, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-(frame.getHeight()))/2);
		frame.setVisible(true);
				
		
	}
	
	//Пишемо в масив
		
	public void actionPerformed(ActionEvent ae) {
				
		if(ae.getSource()==f_price1) f_price1.transferFocus();
		
		else{
			
			//Перевірка цін
			boolean vse_chisto=true;
			char[] c=(f_price1.getText()+f_price2.getText()).toCharArray();
			int dovgina_cin=f_price1.getText().length()+f_price2.getText().length();
			for(int i=0;i<dovgina_cin;i++){
				try{int schos=Integer.parseInt(String.valueOf(c[i]));
				}catch(Exception e){
					vse_chisto=false;
				}
			}
			
			if(!(vse_chisto)) JOptionPane.showMessageDialog(null, "Перевірте ціни");
				
			
			if(vse_chisto){
				//Запис у масив
				array[0][array_layer]=f_shop.getText();
				array[1][array_layer]=f_thing.getText();
				array[2][array_layer]=f_price1.getText();
				array[3][array_layer]=f_price2.getText();
				f_shop.setText("");
				f_thing.setText("");
				f_price1.setText("");
				f_price2.setText("");
				f_price2.transferFocus();	
				array_layer++;
			}
		}
	}
	
	//Найголовніше
	
	public void parser() {
		
		String[][] new_array=new String[4][1000];
		int zag_d=array_layer;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Parser();
			}
		});

	}

	
}
