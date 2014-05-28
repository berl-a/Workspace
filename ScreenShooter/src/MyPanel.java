import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;


public class MyPanel extends JPanel implements ActionListener {

	private static final int LINE_SIZE = 2;
	private static final Color COLOR_OF_LINE = Color.RED;
	private static final int FONT_SIZE = 20;
	private static final int REPAINT_DELAY = 5;
	private Rectangle rectangle;
	
	private JTextField delayChooser = new JTextField(5);
	private JButton stopButton = new JButton("Stop");
	private JButton addButton = new JButton("Add picture");
	
	private Shower shower;
	
	//public Timer insideRepaintTimer;
	
	public MyPanel(Rectangle rect){
		//rectangle = new Rectangle((int)rect.getX() + LINE_SIZE / 2, (int)rect.getY() + LINE_SIZE / 2, (int)(rect.getWidth() - LINE_SIZE * 34.2), (int)(rect.getHeight() - LINE_SIZE * 34.2));
		
		setLayout(new BorderLayout());
		
		delayChooser.setActionCommand("Delay chooser");
		delayChooser.addActionListener(this);
		delayChooser.setBackground(new Color(200, 10, 10, 150));
		delayChooser.setBorder(null);
		delayChooser.setFont(new Font("font", Font.ITALIC, FONT_SIZE));
		delayChooser.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		delayChooser.setAlignmentY(JComponent.CENTER_ALIGNMENT);
		delayChooser.setForeground(Color.WHITE);
		add(delayChooser, BorderLayout.NORTH);
		
		stopButton.addActionListener(this);
		stopButton.setBackground(new Color(200, 10, 10, 150));
		stopButton.setForeground(Color.WHITE);
		stopButton.setVisible(false);
		
		addButton.addActionListener(this);
		addButton.setBackground(new Color(10, 10, 200, 150));
		addButton.setVisible(false);
		addButton.setForeground(Color.WHITE);
		add(addButton, BorderLayout.SOUTH);
		
		
		setOpaque(false);
		
		new Timer(REPAINT_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		}).start();
	}
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		
		Graphics2D g = (Graphics2D) badG;
		
		rectangle = getBounds();
		
		g.setColor(COLOR_OF_LINE);
		g.setStroke(new BasicStroke(LINE_SIZE));
		
		g.draw(rectangle);
		
		if(shower != null)
			g.drawImage(shower.nowImage, null, 0, 0);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "Delay chooser"){
			
			if(getNumber(delayChooser.getText())){
				
				Frame.shooter.setUsingTimer(true);
				Frame.shooter.setTimerDelay(Integer.parseInt(delayChooser.getText()));
				Frame.shooter.startShooting();
				delayChooser.setVisible(false);
				add(stopButton, BorderLayout.NORTH);
				stopButton.setVisible(true);
				
			}else{
				
				Frame.shooter.setUsingTimer(false);
				
				delayChooser.setVisible(false);
				add(stopButton, BorderLayout.NORTH);
				stopButton.setVisible(true);
				addButton.setVisible(true);
				
			}
			
		}else if(e.getActionCommand() == "Stop"){
			
			Frame.shooter.stopShooting();
			
			stopButton.setText("Loading images you made");
			stopButton.repaint();
			
			while(!Frame.shooter.isDone()){
			}
			
			stopButton.setText("Export to .gif");
			stopButton.setBackground(new Color(10, 10, 200, 150));
			stopButton.repaint();
			
			shower = new Shower(Frame.shooter.getTimer().getDelay(), Frame.shooter.getShots());
			
		}else if(e.getActionCommand() == "Add picture")
			Frame.shooter.shoot();
	}

	private static boolean getNumber(String text) {
		
		if(text == "")
			return false;
		try{
			Integer.parseInt(text);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
