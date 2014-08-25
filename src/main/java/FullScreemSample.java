import java.awt.Canvas;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;


public class FullScreemSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphicsDevice gs = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		JFrame frame = new JFrame(gs.getDefaultConfiguration());
		Window win = new Window(frame);
		Canvas c = new Canvas();
		c.setBackground(Color.RED);
		win.add(c);
		//win.show();
		win.setVisible(true);
		
		// Enter full-screen mode
		gs.setFullScreenWindow(win);
		win.validate();
	}

}
