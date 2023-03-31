import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements MouseListener {

    protected Stroke gridWidth;
    protected Stroke playerOutlineWidth;
    protected double radius;

    // light mode colors
    protected Color colorLightBackground;
    protected Color colorLightBoard;
    protected Color colorLightGrid;
    protected Color colorLightPlayer1;
    protected Color colorLightPlayer1Outline;
    protected Color colorLightPlayer2;
    protected Color colorLightPlayer2Outline;
    protected Color colorLightCapturedBlock;

    // dark mode colors
    protected Color colorDarkBackground;
    protected Color colorDarkBoard;
    protected Color colorDarkGrid;
    protected Color colorDarkPlayer1;
    protected Color colorDarkPlayer1Outline;
    protected Color colorDarkPlayer2;
    protected Color colorDarkPlayer2Outline;
    protected Color colorDarkCapturedBlock;

    public Panel(int length, int height) {
        super();
        setPreferredSize(new Dimension(length, height));
        addMouseListener(this);
        setFocusable(true);

        // startup values:
        gridWidth = new BasicStroke(10);
        playerOutlineWidth = new BasicStroke(5);
        radius = 20;

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
}
