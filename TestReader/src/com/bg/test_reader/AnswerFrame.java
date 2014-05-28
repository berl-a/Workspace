package com.bg.test_reader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class AnswerFrame extends JFrame {

	static JFileChooser chooser;
	
	static JFrame frame;
	
	static JTextField searchField = new JTextField(10);
	
	//static JButton chooseFileButton = new JButton("Оберу інший");
	static JButton chooseFileButton = new JButton(" ");
	
	static JTextArea textArea = new JTextArea(5, 30);
	static Reader reader;
	
	public static void main(String[] args) {
		
		chooser = new JFileChooser();
		chooser.setApproveButtonText("Я обираю тебе!");
		chooser.setMultiSelectionEnabled(false);
		chooser.setCurrentDirectory(new File("").getAbsoluteFile());
				
		for(int i = 0; i < 5; i++){
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				reader = new Reader(chooser.getSelectedFile());
				reader.read();
				break;
			}else
				JOptionPane.showMessageDialog(null, "Оберіть щось!");
		}
		
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		
		frame.setTitle(reader.getName() + " solver");
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowLostFocus(WindowEvent arg0) {}
			
			public void windowGainedFocus(WindowEvent arg0) {
				searchField.setText("");
				textArea.setText("");
			}
		});
		frame.setUndecorated(true);
		frame.setBackground(Color.YELLOW);
		
		searchField.setBorder(null);					
		searchField.setBackground(new Color(150, 215, 255));
		searchField.setForeground(Color.GRAY.darker());
		searchField.setFont(new Font("f", Font.BOLD, 20));
		searchField.setHorizontalAlignment(JTextField.CENTER);
		searchField.setCaretColor(Color.PINK.darker());
		
		searchField.addCaretListener(new CaretListener(){
			public void caretUpdate(CaretEvent e) {
				
				if(searchField.getText() != ""){
					LinkedList<String> resultList = reader.search(searchField.getText());
					
					if(resultList != null){
						String resultString = "";
						for(String token : resultList)
							resultString += token + System.lineSeparator();
						textArea.setText(resultString);
					}else
						//textArea.setText("Безрезультатно");
						textArea.setText("");
				}else
					textArea.setText("");
				
				searchField.repaint();
				textArea.repaint();
			}
		});
		frame.add(searchField, BorderLayout.NORTH);    
		
		chooseFileButton.setBorder(null);                                    
		chooseFileButton.setBackground(new Color(250, 75, 75));
		chooseFileButton.setForeground(Color.WHITE);                        
		chooseFileButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 5; i++){
					if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
						reader = new Reader(chooser.getSelectedFile());
						reader.read();
						frame.setTitle(reader.getName() + " solver");
						break;
					}else
						JOptionPane.showMessageDialog(null, "Обиріть щось!");
				}
			}
		});
		frame.add(chooseFileButton, BorderLayout.SOUTH);
		
		Border border = BorderFactory.createDashedBorder(null, 3, 2);
		
		textArea.setBorder(border);
		textArea.setEditable(false);
		textArea.setBackground(new Color(175, 250, 140));
		textArea.setForeground(Color.GRAY.darker());
		textArea.setFont(new Font("f", Font.BOLD, 14));
		textArea.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		frame.add(textArea, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth() - 50), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight() - 50));
		frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 15, 15)); 
		
		//frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

}
