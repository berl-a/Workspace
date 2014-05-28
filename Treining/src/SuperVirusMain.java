


import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.IOException;

import javax.swing.JFrame;

public class SuperVirusMain {

	
	public static void main(String[] args) throws IOException {
		
		final JFrame frame=new JFrame("");
		frame.setBounds(0,0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setUndecorated(true);
		frame.add(new SuperVirus());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setAutoRequestFocus(true);
		
		frame.addWindowStateListener(new WindowStateListener(){
            public void windowStateChanged(WindowEvent e){
                frame.setState(JFrame.NORMAL);
            }
        });
		frame.addComponentListener(new ComponentListener(){

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				frame.setLocation(0,0);
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
