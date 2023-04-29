package gui;

import logika.*;
import splosno.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Panel extends JPanel implements MouseListener {

    private static final int CELL_SIZE = 50;
    private Igra igra;
    private int xClicked, yClicked;
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

    public Panel(int panelWidth, int panelHeight) {
        super();
        setGameState(new Igra());
        // make the board centered and fit to screen:
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setMaximumSize(new Dimension(panelWidth, panelHeight));
        setLayout(new BorderLayout());
        addMouseListener(this);
        setFocusable(true);

        // startup values:
        gridWidth = new BasicStroke(5);
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

    public void setGameState(Igra igra) {
        if (igra.state != 0) {
            int player = igra.state;
            int size = igra.size;
            if (this.igra != null && this.igra.state == player && this.igra.size == size) {
                return;
            }
            this.igra = igra;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (igra == null) return;
        int size = igra.size;
        int[][] grid = igra.grid;

        // drawing the board:
        int boardWidth = size * CELL_SIZE;
        int boardHeight = size * CELL_SIZE;
        int boardX = (getWidth() - boardWidth) / 2;
        int boardY = (getHeight() - boardHeight) / 2;
        g.setColor(colorLightBoard);
        g.fillRect(boardX, boardY, boardWidth, boardHeight);

        // drawing the grid:
        g.setColor(colorLightGrid);
        for (int i = 0; i <= size; i++) {
            g.drawLine(boardX + i * CELL_SIZE, boardY, boardX + i * CELL_SIZE, boardY + size * CELL_SIZE);
            g.drawLine(boardX, boardY + i * CELL_SIZE, boardX + size * CELL_SIZE, boardY + i * CELL_SIZE);
        }

        //drawing the stones:
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int stone = grid[row][col];
                if (stone == Igra.BLACK_STATE) {
                    g.setColor(colorLightPlayerBlack);
                    g.fillOval(boardX + col * CELL_SIZE, boardY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g.setColor(colorLightPlayerBlackOutline);
                    g.drawOval(boardX + col * CELL_SIZE, boardY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (stone == Igra.WHITE_STATE) {
                    g.setColor(colorLightPlayerWhite);
                    g.fillOval(boardX + col * CELL_SIZE, boardY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g.setColor(colorLightPlayerWhiteOutline);
                    g.drawOval(boardX + col * CELL_SIZE, boardY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (igra == null) return;
        int size = igra.size;
        int boardWidth = size * CELL_SIZE;
        int boardHeight = size * CELL_SIZE;
        int boardX = (getWidth() - boardWidth) / 2;
        int boardY = (getHeight() - boardHeight) / 2;
        int x = (e.getX() - boardX) / CELL_SIZE;
        int y = (e.getY() - boardY) / CELL_SIZE;
        Poteza poteza = new Poteza(x,y);
        Igra novaigra = igra;
        boolean success = novaigra.odigraj(poteza);
        if (success) {
            igra.odigraj(poteza);
        }
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
