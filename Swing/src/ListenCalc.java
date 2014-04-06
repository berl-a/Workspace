import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;


public class ListenCalc implements ActionListener  {
	

double numberResult;
	
	public void actionPerformed(ActionEvent ae){
		resultGiver(ae.getActionCommand());
	    }
	
	public double resultGiver(String textResult){
		StringTokenizer ST=new StringTokenizer(textResult, "+-*/e",true);
				
		   String s1=ST.nextToken();
		   String s2=ST.nextToken();
		   String s3=ST.nextToken();
		   double op1=Double.parseDouble(s1);
		   double op2=Double.parseDouble(s3);
		   if(s2.equals("+")){numberResult=op1+op2;}
		   else if(s2.equals("-")){numberResult=op1-op2;}
		   else if(s2.equals("*")){numberResult=op1*op2;}
		   else if(s2.equals("/")){numberResult=op1/op2;}
		   else{}
		return 0;
    }
}
	


