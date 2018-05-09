package edu.mccc.cos210.ds.fp.javaforth.viewIde;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import edu.mccc.cos210.ds.SinglyLinkedList;
import edu.mccc.cos210.ds.fp.javaforth.machineModel.GraphicsWordBase;;

@SuppressWarnings("serial")
public class GraphicsFrame extends JFrame {
	private Color backgroundColor;
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public GraphicsFrame() {
		super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.backgroundColor = new Color(230, 230, 230);
		// Calculate star coordinates.
		final double[][] starCoordinates = new double[5][2];
		for (int i = 0; i < 5; i++) {
			starCoordinates[i][0] = 250 * Math.sin(Math.toRadians(72 * i));
			starCoordinates[i][1] = 250 * Math.cos(Math.toRadians(72 * i));
		}
		final GraphicsFrame frame = this;
		super.add(new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(800, 600);
			}
			@Override
			public Color getBackground() {
				return frame.getBackgroundColor();
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Setup canvas.
				Graphics2D g2d = (Graphics2D) g.create();
				// for each word, call GraphicsWordBase.draw(g2d);
				for (GraphicsWordBase word : words) {
					word.draw(g2d);
				}
				g2d.setStroke(new BasicStroke());
				g2d.dispose();
			}
		}, BorderLayout.CENTER);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.getContentPane().setPreferredSize(new Dimension(800, 800));
	}
	private SinglyLinkedList<GraphicsWordBase> words = new SinglyLinkedList<>();
	public void clear() {
		EventQueue.invokeLater(() -> {
			this.words = new SinglyLinkedList<>();
			this.repaint();
		});
	}
	public void addWord(GraphicsWordBase word) {
		EventQueue.invokeLater(() -> {
			super.setVisible(true);
			this.words.addLast(word);
			this.repaint();
		});
	}
}
