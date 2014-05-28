import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.Timer;


public class Shower implements ActionListener{

	private List<BufferedImage> images;
	public BufferedImage nowImage;
	private int nowIndex = 0;
	private Timer imageChangeTimer;
	
	public Shower(int delay, List<BufferedImage> images){
		imageChangeTimer = new Timer(delay, this);
		imageChangeTimer.start();
		
		this.images = images;
		nowImage = images.get(nowIndex);
		
		//Frame.panel.insideRepaintTimer.start();
	}

	public void actionPerformed(ActionEvent e) {
		
		nowImage = images.get(nowIndex);
		nowIndex++;
		if(nowIndex >= images.size())
			nowIndex = 0;
	}
	
}
