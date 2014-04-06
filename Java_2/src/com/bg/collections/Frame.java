package com.bg.collections;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Frame extends JFrame implements FocusListener, MouseWheelListener{

	private static final int LENGTH_OF_KEY_FIELD = 10;
	private static final int LENGTH_OF_NUMBER_FIELD = 15;
	
	JTextField keyField;
	JTextField numberField;
	Map<String, Double> map;

	Frame(){
		
		JFrame frame = new JFrame("PhoneBook");
		frame.setLayout(new FlowLayout());
		keyField = new JTextField(LENGTH_OF_KEY_FIELD);
		frame.add(keyField);
		numberField = new JTextField(LENGTH_OF_NUMBER_FIELD);
		frame.add(numberField);
		frame.addMouseWheelListener(this);
		numberField.addFocusListener(this);
		frame.pack();
		frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - frame.getWidth()/2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - frame.getHeight()/2));
		frame.setVisible(true);
		
		map = new TreeMap<String, Double>();
		Set<String> s = new TreeSet<String>();
		
		s = map.keySet();
		Iterator i = s.iterator();
		
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if((keyField.getText() != "")&&(numberField.getText() != "")){
			map.put(keyField.getText(), Double.parseDouble(numberField.getText()));  
		}
		keyField.setText("");
		numberField.setText("");
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getScrollAmount() > 0){
			showCollection();
		}
		
	}

	private void showCollection() {
		
		for(Iterator<Entry<String, Double>> i = map.entrySet().iterator(); i.hasNext(); ){
			Entry<String, Double> e = i.next();
			System.out.println("²ì'ÿ: " + e.getKey() + ". Îö³íêà: " + e.getValue());
			
		}
		
	}
	

}
