package br.unb.cic.iris;

import static br.unb.cic.iris.i18n.Message.message;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import br.unb.cic.iris.gui.MainPanel;

public class GuiMainProgram {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GuiMainProgram gui = new GuiMainProgram();
				gui.show();
			}
		});
	}

	void show() {
		// 1. Create the frame.
		JFrame frame = new JFrame(message("title"));

		// 2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Create components and put them in the frame.
		frame.getContentPane().add(new MainPanel(), BorderLayout.CENTER);

		// 4. Size the frame.
		frame.pack();

		// 5. Show it.
		frame.setVisible(true);
	}

}
