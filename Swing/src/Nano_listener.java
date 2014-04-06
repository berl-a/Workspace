import java.awt.event.*;


public class Nano_listener implements ActionListener {
long l_temp;
public void actionPerformed(ActionEvent ae){
	
	if(ae.getActionCommand().equals("Start")){
	    l_temp=System.nanoTime();
	    Nanotime.jlbl.setText("Counting...");
	    }
	else{
		long l_result=System.nanoTime()-l_temp; 
	    Nanotime.jlbl.setText(String.valueOf(l_result));
	    l_temp=0;
	    l_result=0;
	    }
    }
}
	
	
