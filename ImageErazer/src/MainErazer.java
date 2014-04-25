import javax.swing.SwingUtilities;


public class MainErazer {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new Frame();
			}
		});
	}

}
