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
    // a number that updates whenever a player makes a move. this helps determine
    // the colour of a captured block:
    protected int index;

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
        gridWidth = new BasicStroke(2);
        playerOutlineWidth = new BasicStroke(2);
        radius = 40;
        int index = 0;

        colorLightBackground = Color.LIGHT_GRAY;
        colorLightBoard = new Color(242,176,109,255);
        colorLightGrid = Color.BLACK;
        colorLightPlayerBlack = Color.BLACK;
        colorLightPlayerBlackOutline = Color.BLACK;
        colorLightPlayerWhite = Color.WHITE;
        colorLightPlayerWhiteOutline = Color.BLACK;
        colorLightCapturedBlock = Color.RED;

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
        Graphics2D g2 = (Graphics2D) g;

        // drawing the board:
        int boardWidth = (size - 1) * CELL_SIZE;
        int boardHeight = (size - 1) * CELL_SIZE;
        int boardX = (getWidth() - boardWidth) / 2 - CELL_SIZE / 2;
        int boardY = (getHeight() - boardHeight) / 2 - CELL_SIZE / 2;
        g.setColor(colorLightBoard);
        g.fillRect(boardX, boardY, boardWidth, boardHeight);

        // drawing the grid:
        g.setColor(colorLightGrid);
        for (int i = 0; i < size; i++) {
            g2.setStroke(gridWidth);
            g.drawLine(boardX + i * CELL_SIZE, boardY, boardX + i * CELL_SIZE, boardY + (size - 1) * CELL_SIZE);
            g.drawLine(boardX, boardY + i * CELL_SIZE, boardX + (size - 1) * CELL_SIZE, boardY + i * CELL_SIZE);
        }

        //drawing the stones:
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int stone = grid[row][col];
                g2.setStroke(playerOutlineWidth);
                if (stone == Igra.BLACK_STATE) {
                    drawStone(g, boardX, boardY, row, col, colorLightPlayerBlack, colorLightPlayerBlackOutline);
                }
                else if (stone == Igra.WHITE_STATE) {
                    drawStone(g, boardX, boardY, row, col, colorLightPlayerWhite, colorLightPlayerWhiteOutline);
                }
                else if (stone == Igra.CAPTURED_STATE) {
                    if (index % 2 == 0) {
                        drawStone(g, boardX, boardY, row, col, colorLightPlayerBlack, colorLightCapturedBlock);
                    }
                    else drawStone(g, boardX, boardY, row, col, colorLightPlayerWhite, colorLightCapturedBlock);
                }
            }
        }
    }

    private void drawStone(Graphics g, int boardX, int boardY, int row, int col, Color colorStone, Color colorStoneOutline) {
        g.setColor(colorStone);
        g.fillOval(boardX + col * CELL_SIZE - radius / 2, boardY + row * CELL_SIZE - radius / 2, radius, radius);
        g.setColor(colorStoneOutline);
        g.drawOval(boardX + col * CELL_SIZE - radius / 2, boardY + row * CELL_SIZE - radius / 2, radius, radius);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (igra == null) return;
        if (igra.gameOver()) return;
        int x = e.getX();
        int y = e.getY();
        int size = igra.size;
        int boardWidth = (size - 1) * CELL_SIZE;
        int boardHeight = (size - 1) * CELL_SIZE;
        int boardX = (getWidth() - boardWidth) / 2 - CELL_SIZE / 2;
        int boardY = (getHeight() - boardHeight) / 2 - CELL_SIZE / 2;
        int col = (x - boardX + CELL_SIZE / 2) / CELL_SIZE;
        int row = (y - boardY + CELL_SIZE / 2) / CELL_SIZE;

        Poteza poteza = new Poteza(col, row);
        boolean success = igra.odigraj(poteza);
        if (success) {
            igra.odigraj(poteza);
            index++;
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
