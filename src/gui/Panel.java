package gui;

import logika.Igra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements MouseListener {

    protected Igra igra;
    protected Stroke gridWidth;
    protected Stroke playerOutlineWidth;
    protected int radius;

    // light mode colors
    protected Color colorLightBackground;
    protected Color colorLightBoard;
    protected Color colorLightGrid;
    protected Color colorLightPlayerBlack;
    protected Color colorLightPlayerBlackOutline;
    protected Color colorLightPlayerWhite;
    protected Color colorLightPlayerWhiteOutline;
    protected Color colorLightCapturedBlock;

    // dark mode colors
    protected Color colorDarkBackground;
    protected Color colorDarkBoard;
    protected Color colorDarkGrid;
    protected Color colorDarkPlayerBlack;
    protected Color colorDarkPlayerBlackOutline;
    protected Color colorDarkPlayerWhite;
    protected Color colorDarkPlayerWhiteOutline;
    protected Color colorDarkCapturedBlock;

    public Panel(int length, int height) {
        super();
        gameState(new Igra());
        setPreferredSize(new Dimension(length, height));
        addMouseListener(this);
        setFocusable(true);

        // startup values:
        gridWidth = new BasicStroke(10);
        playerOutlineWidth = new BasicStroke(5);
        radius = 20;

        colorLightBackground = Color.LIGHT_GRAY;
        colorLightBoard = new Color(242,176,109,255);
        colorLightGrid = Color.BLACK;
        colorLightPlayerBlack = Color.BLACK;
        colorLightPlayerBlackOutline = Color.BLACK;
        colorLightPlayerWhite = Color.WHITE;
        colorLightPlayerWhiteOutline = Color.BLACK;
        colorLightCapturedBlock = Color.YELLOW;

        colorDarkBackground = Color.DARK_GRAY;
        colorDarkBoard = new Color(32,33,36,255);
        colorDarkGrid = new Color(145,149,130,255);
        colorDarkPlayerBlack = new Color(129,180,120,255);
        colorDarkPlayerBlackOutline = Color.BLACK;
        colorDarkPlayerWhite = new Color(198,70,52,255);
        colorDarkPlayerWhiteOutline = Color.BLACK;
        colorDarkCapturedBlock = new Color(240,180,66,255);

    }

    // pač null se požene sam takrt k pržgeš to sam pomen da igre še ni aktivne in takoj k daš da je igra aktivna
    //

    public void gameState(Igra igra) {
        if (igra.state != 0){
            int player = igra.state;
            int size = igra.size;
        } else {
            return;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (igra == null) return;
        g.setColor(colorLightBoard);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle board = new Rectangle(10, 10, 100, 100);
        g2.draw(board);
        g2.setColor(colorLightBoard);
        g2.fill(board);
        g.setColor(colorLightGrid);
        g2.setStroke(gridWidth);
        for (int i = 0; i < igra.size; i++) {
            g.drawLine(10 + (100 * i / igra.size), 10, 10 + (100 * i / igra.size), 110);
        }
        for (int i = 0; i < igra.size; i++) {
            for (int j = 0; j < igra.size; j++) {
                if (igra.grid[i][j] == -1) {
                    drawPlayer(g, i, j, colorLightPlayerBlack, colorLightPlayerBlackOutline);
                }
                else if (igra.grid[i][j] == 1) {
                    drawPlayer(g, i, j, colorLightPlayerWhite, colorLightPlayerWhiteOutline);
                }
            }
        }
    }

    private void drawPlayer(Graphics g, int i, int j, Color colorLightPlayerWhite, Color colorLightPlayerWhiteOutline) {
        g.setColor(colorLightPlayerWhite);
        g.fillOval(10 + (100 * j / igra.size), 10 + (100 * i / igra.size), 2 * radius, 2 * radius);
        g.setColor(colorLightPlayerWhiteOutline);
        g.drawOval(10 + (100 * j / igra.size), 10 + (100 * i / igra.size), 2 * radius, 2 * radius);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (igra == null) return;
        int x = e.getX();
        int y = e.getY();

        repaint();
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
