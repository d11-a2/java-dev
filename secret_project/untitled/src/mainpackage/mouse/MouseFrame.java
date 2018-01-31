package mainpackage.mouse;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Created by nikita.shubarev@masterdata.ru on 31.01.2018.
 */
public class MouseFrame extends JFrame {
  public MouseFrame() {
    add(new MouseComponent());
    pack();
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      MouseFrame frame = new MouseFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    });
  }
}
