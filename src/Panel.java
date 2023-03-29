import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements MouseListener, MouseMotionListener{

    public Panel(int length, int height) {
        super();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(length, height));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
