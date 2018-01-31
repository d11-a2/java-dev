package mainpackage.mouse;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * Created by nikita.shubarev@masterdata.ru on 31.01.2018.
 */
public class MouseComponent extends JComponent {
  private static final int DEFAULT_WIDTH  = 300;
  private static final int DEFAULT_HEIGHT = 200;

  private static final int SIDELENGHT = 10;
  private ArrayList<Rectangle2D> squares;
  private Rectangle2D            current;

  public MouseComponent() {
    squares = new ArrayList<>();
    current = null;
    addMouseListener(new MouseHandler());
    addMouseMotionListener(new MouseMotionHandler());
  }

  public Dimension getPreferredSize()
  {
    return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D graphics2D = (Graphics2D) g;

    for (Rectangle2D r : squares) {
      graphics2D.draw(r);
    }
  }

  public Rectangle2D find(Point2D p) {
    for (Rectangle2D r : squares) {
      if (r.contains(p)) { return r; }
    }
    return null;
  }

  public void add(Point2D p) {
    double x = p.getX();
    double y = p.getY();

    current = new Rectangle2D.Double(x - SIDELENGHT / 2, y - SIDELENGHT / 2, SIDELENGHT, SIDELENGHT);

    squares.add(current);
    repaint();
  }

  public void remove(Rectangle2D s) {
    if (s == null) { return; }
    if (s == current) { current = null; }
    squares.remove(s);
    repaint();
  }

  private class MouseHandler extends MouseAdapter {
    public void mousePressed(MouseEvent event) {
      current = find(event.getPoint());
      if (current == null) { add(event.getPoint()); }
    }

    public void mouseClicked(MouseEvent event) {
      current = find(event.getPoint());
      if (current != null && event.getClickCount() >= 2) { remove(current); }
    }
  }

  private class MouseMotionHandler implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {
      if (current != null) {
        int x = e.getX();
        int y = e.getY();

        current.setFrame(x - SIDELENGHT / 2, y - SIDELENGHT / 1, SIDELENGHT, SIDELENGHT);
        repaint();
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      if (find(e.getPoint()) == null) { setCursor(Cursor.getDefaultCursor()); }
      else { setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)); }
    }
  }
}
