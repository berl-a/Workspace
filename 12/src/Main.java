import java.applet.Applet;
import java.awt.*;

public class Main extends Applet {
	private static final long serialVersionUID = 4902057285471885308L;

	public void init() {
		grad = Grad();
	}

	private int a = 0;

	public void paint(Graphics g) {
		setSize(new Dimension(700, 600));
		int i;
		a++;
		if (a == 2) {
			g.setColor(Color.black);
			int x = 350, y = 300;
			try {
				for (i = 0; i < n; ++i) {
					g.setColor(Color.blue);
					int coord[] = Coord(i * grad);
					g.drawLine(350 - (int) (coord[0] * 1.5 * mas[i] / 100),
							300 - (int) (coord[1] * 1.5 * mas[i] / 100), x, y);
					g.setColor(Color.red);
					g.drawLine(350 - (int) (coord[0] * 1.5 * mas[i] / 100),
							300 - (int) (coord[1] * 1.5 * mas[i] / 100), 350,
							300);
					x = 350 - (int) (coord[0] * 1.5 * mas[i] / 100);
					y = 300 - (int) (coord[1] * 1.5 * mas[i] / 100);
					Thread.sleep(25);
				}
			} catch (InterruptedException ex) {
			}
			int coord[] = Coord(0);
			g.setColor(Color.blue);
			g.drawLine(350 - (int) (coord[0] * 1.5 * mas[0] / 100),
					300 - (int) (coord[1] * 1.5 * mas[0] / 100), x, y);
		}
		if (a > 2) {
			g.setColor(Color.black);
			int x = 350, y = 300;
			for (i = 0; i < n; ++i) {
				g.setColor(Color.blue);
				int coord[] = Coord(i * grad);
				g.drawLine(350 - (int) (coord[0] * 1.5 * mas[i] / 100),
						300 - (int) (coord[1] * 1.5 * mas[i] / 100), x, y);
				g.setColor(Color.red);
				g.drawLine(350 - (int) (coord[0] * 1.5 * mas[i] / 100),
						300 - (int) (coord[1] * 1.5 * mas[i] / 100), 350, 300);
				x = 350 - (int) (coord[0] * 1.5 * mas[i] / 100);
				y = 300 - (int) (coord[1] * 1.5 * mas[i] / 100);
			}
			int coord[] = Coord(0);
			g.setColor(Color.blue);
			g.drawLine(350 - (int) (coord[0] * 1.5 * mas[0] / 100),
					300 - (int) (coord[1] * 1.5 * mas[0] / 100), x, y);
		}
	}

	private int mas[];
	private final int n = 120;
	private double grad;
	private final int radius = 200;

	private void Gen() {
		int i;
		mas = new int[n];
		for (i = 0; i < n; ++i)
			mas[i] = (int) (Math.random() * 100);
	}

	private double Grad() {
		Gen();
		return 360 / n;
	}

	private int[] Coord(double cgr) {
		int coord[] = new int[2];
		coord[0] = (int) ((radius) * Math.cos(Math.toRadians(cgr)));
		coord[1] = (int) ((radius) * Math.sin(Math.toRadians(cgr)));
		return coord;
	}
}