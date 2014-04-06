import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class Loader {
	
	static boolean was_closed=false;
	static int caretM=0;
	static long time0=0;
	static long time1=0;
	static String vvid="";
	
	static Icon nowIcon;
	static Icon noClose=new ImageIcon("res/No close.png");
	static Icon Caf=new ImageIcon("res/Caf.png");
	static Icon uPidtverdgennia=new ImageIcon("res/Experiment 3.png");
	static Icon ifIsnt=new ImageIcon("res/If isn't.png");

	static JFrame frame=new JFrame("Eazy loader");
	static JLabel label=new JLabel(Caf);
	static JTextField field=new JTextField(10);
	
	static int nowX=Toolkit.getDefaultToolkit().getScreenSize().width/2-frame.getWidth()/2;
	static int nowY=Toolkit.getDefaultToolkit().getScreenSize().height/2-frame.getHeight()/2;	
	static int nowWidth=frame.getWidth();
	static int nowHeight=frame.getHeight();
	
	static Timer t;
	
		
	public static void main(String[] args) {
		frame.setLayout(new FlowLayout());
		frame.add(label);
		frame.add(field);
		frame.pack();
		frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(frame.getWidth()/2), Toolkit.getDefaultToolkit().getScreenSize().height/2-(frame.getHeight()/2));		
		frame.setVisible(true);
		frame.setFocusable(true);

		frame.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {
				vvid=vvid+String.valueOf(e.getKeyChar());
				System.out.println(vvid);
				if(vvid.endsWith("rberla")) field.setText("Dev. by BubbleGum");
				
			}

			public void keyReleased(KeyEvent arg0) {
				
				
			}

			public void keyTyped(KeyEvent arg0) {
				
				
			}
			
		});
		
		nowIcon=Caf;
		nowX=Toolkit.getDefaultToolkit().getScreenSize().width/2-frame.getWidth()/2;
		nowY=Toolkit.getDefaultToolkit().getScreenSize().height/2-frame.getHeight()/2;
		nowWidth=frame.getWidth();
		nowHeight=frame.getHeight();
				
		t=new Timer(900, new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				frame.setBounds(nowX, nowY, nowWidth, nowHeight);
				frame.setResizable(true);
				label.setIcon(nowIcon);
				frame.pack();
			}
			
		});
		t.setRepeats(false);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		field.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Icon tmpIcon=new ImageIcon(field.getText());
				label.setIcon(tmpIcon);
				while(true){
					if(label.getIcon().getIconHeight()==-1){
						String s1=(String)JOptionPane.showInputDialog(null, null, "Введіть існуючий файл!", 0, ifIsnt, null, null);
						if(s1==null) System.exit(1);
						label.setIcon(tmpIcon);
					}
					else{break;}
					
				}
				
				frame.pack();
				frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(frame.getWidth()/2), Toolkit.getDefaultToolkit().getScreenSize().height/2-(frame.getHeight()/2));
				nowIcon=tmpIcon;
				nowX=Toolkit.getDefaultToolkit().getScreenSize().width/2-frame.getWidth()/2;
				nowY=Toolkit.getDefaultToolkit().getScreenSize().height/2-frame.getHeight()/2;
				nowWidth=frame.getWidth();
				nowHeight=frame.getHeight();							
			}
			
		});
		field.addCaretListener(new CaretListener(){

			public void caretUpdate(CaretEvent arg0) {
				if(caretM==0){
					caretM=1;
					field.setCaretColor(Color.BLUE);					
				}
				else{
					caretM=0;
					field.setCaretColor(Color.GREEN);
				}				
			}			
		});
		field.setToolTipText("Введіть ім'я і розширення файлу");
		
		frame.addWindowListener(new WindowListener(){
			
			public void windowActivated(WindowEvent arg0) {
				if(was_closed==true){
					label.setIcon(noClose);
					frame.pack();
					frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(frame.getWidth()/2), Toolkit.getDefaultToolkit().getScreenSize().height/2-(frame.getHeight()/2));
					t.start();
				}				
			}

			public void windowClosed(WindowEvent arg0) {

			}

			
			public void windowClosing(WindowEvent arg0) {
				time1=System.currentTimeMillis()-time0;
				int timeR=(int)time1/1000;
				String fin="";
				if(timeR==1) fin="у";
				else if(timeR==2) fin="и";
				else if(timeR==3) fin="и";
				else if(timeR==4) fin="и";
				else if((timeR>4)&&(timeR<21)) fin="";
				else if((timeR%10==1)&&(timeR>20)) fin="у";
				else if((timeR%10==2)&&(timeR>20)) fin="и";
				else if((timeR%10==3)&&(timeR>20)) fin="и";
				else if((timeR%10==4)&&(timeR>20)) fin="и";
				else fin="";	
							
				if(JOptionPane.showConfirmDialog(null, null, ("Ви подивилися тільки "+timeR+" секунд"+fin+". Досить?"), 0, 0, uPidtverdgennia)==(JOptionPane.OK_OPTION)) System.exit(0); 
				
			}

			
			public void windowDeactivated(WindowEvent arg0) {
				was_closed=true;
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				if(was_closed==true){
					label.setIcon(noClose);
					frame.pack();
					frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(frame.getWidth()/2), Toolkit.getDefaultToolkit().getScreenSize().height/2-(frame.getHeight()/2));
				    t.start();	
				}				
			}

			public void windowIconified(WindowEvent arg0) {
				was_closed=true;
				
			}

			public void windowOpened(WindowEvent arg0) {
				time0=System.currentTimeMillis();				
			}
			
		});
		

	}

}





































