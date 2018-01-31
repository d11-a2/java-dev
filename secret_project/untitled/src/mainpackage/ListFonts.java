package mainpackage;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Created by nikita.shubarev@masterdata.ru on 26.01.2018.
 */
public class ListFonts {
  public static void main(String[] args) {
    String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    for (String s : fonts) {
      System.out.println(s);
    }

    EventQueue.invokeLater(() -> {
      JFrame frame = new FontFrame();
      frame.setTitle("FontTest");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    });
  }
}

class FontFrame extends JFrame {
  public FontFrame() {
    add(new FontComponent());
    pack();
  }
}

class FontComponent extends JComponent {
  private static final int DEFAULT_WIDTH  = 300;
  private static final int DEFAULT_HEIGHT = 200;

  public void paintComponent(Graphics graphics)
  {
    Graphics2D graphics2D = (Graphics2D) graphics;

    String message = "Hello, world";

    Font font = new Font("LucidaSans", Font.ITALIC, 34);
    graphics2D.setFont(font);

    FontRenderContext context = graphics2D.getFontRenderContext();
    Rectangle2D bounds = font.getStringBounds(message, context);

    double x = (getWidth() - bounds.getWidth()) / 2;
    double y = (getHeight() - bounds.getHeight()) / 2;

    double ascent = -bounds.getY();
    double baseY = y + ascent;

    graphics2D.drawString(message, (int) x, (int) baseY);

    graphics2D.setPaint(Color.LIGHT_GRAY);

    graphics2D.draw(new Line2D.Double(x, baseY, x + bounds.getWidth(), baseY));

    Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());
    graphics2D.draw(rectangle2D);

  }

  public Dimension getPreferredSize()
  {
    return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }
}