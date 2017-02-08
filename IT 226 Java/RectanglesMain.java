package edu.ilstu;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;

public class RectanglesMain extends JFrame {
	String pathname = "data.dat";
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	JPanel panel = new JPanel();
	JButton clear = new JButton("Clear drawing");
    JButton save = new JButton("Save image");
    JCheckBox drawInt = new JCheckBox("Draw intersections");
    JCheckBox drawUn = new JCheckBox("Draw union");
    JCheckBox drawCA = new JCheckBox("Draw common area");
	
    public static void main(String[] args) {
		new RectanglesMain();
	}

	public RectanglesMain() {
		this.setSize(800, 700);
		this.setTitle("Rectangles");
	    this.add(panel,BorderLayout.SOUTH);
	    panel.add(drawInt);
	    panel.add(drawUn);
	    panel.add(drawCA);
	    panel.add(clear);
	    panel.add(save);
	    ButtonHandler handler1 = new ButtonHandler();
	    save.addActionListener(handler1);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new PaintSurface(), BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			saveFile();
	    }
	}
	
	private class PaintSurface extends JComponent {
		Point startDrag, endDrag;
		public PaintSurface() {
			this.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	startDrag = new Point(e.getX(), e.getY());
		    	endDrag = startDrag;
		        repaint();
		    }

		    public void mouseReleased(MouseEvent e) {
		    	Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
		    	shapes.add(r);
		    	startDrag = null;
		    	endDrag = null;
		    	repaint();
		    }
			});

		    this.addMouseMotionListener(new MouseMotionAdapter() {
		    public void mouseDragged(MouseEvent e) {
		    	endDrag = new Point(e.getX(), e.getY());
		    	repaint();
		    }
		    });
		}
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    int colorIndex = 0;
		    g2.setStroke(new BasicStroke(2));
		    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
		    for (Shape s : shapes) {
		    	g2.setPaint(Color.BLACK);
		        g2.draw(s);
		        g2.setPaint(Color.GREEN);
		        g2.fill(s);
		    }

		    if (startDrag != null && endDrag != null) {
		    	g2.setPaint(Color.LIGHT_GRAY);
		        Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
		        g2.draw(r);
		    }
		}
		private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
			return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		}
	}
	public void saveFile(){
		BufferedImage imagebuf = null;
		Graphics2D graphics2D = imagebuf.createGraphics();
		panel.paint(graphics2D);
		try {
			ImageIO.write(imagebuf,"jpeg", new File("save1.jpeg"));
		} catch (Exception e) {
			System.out.println("error");
		}
	}	
}

