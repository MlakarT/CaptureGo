package gui;

import logika.Igra;
import splosno.Poteza;
import vodja.Vodja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static vodja.PlayerType.C;
import static vodja.Vodja.vrstaIgralca;

public class Panel extends JPanel implements MouseListener, ComponentListener {
    private int cellSize;
    protected Igra igra;
    protected Stroke gridWidth;
    protected Stroke playerOutlineWidth;
    protected int radius;
    protected Color colorBackground;
    protected Color colorBoard;
    protected Color colorGrid;
    protected Color colorPlayerBlack;
    protected Color colorPlayerBlackOutline;
    protected Color colorPlayerWhite;
    protected Color colorPlayerWhiteOutline;
    protected Color colorCapturedBlock;

    public Panel(int panelWidth, int panelHeight) {
        super();
        setGameState(new Igra());
        addMouseListener(this);
        addComponentListener(this);
        setFocusable(true);

        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setMaximumSize(new Dimension(panelWidth, panelHeight));
        setLayout(new BorderLayout());

        // initial values:
        gridWidth = new BasicStroke(2);
        playerOutlineWidth = new BasicStroke(2);


        // initial color constants:
        colorBackground = ColorConstants.LIGHT_BACKGROUND;
        colorBoard = ColorConstants.LIGHT_BOARD;
        colorGrid = ColorConstants.LIGHT_GRID;
        colorPlayerBlack = ColorConstants.LIGHT_PLAYER_BLACK;
        colorPlayerBlackOutline = ColorConstants.LIGHT_PLAYER_BLACK_OUTLINE;
        colorPlayerWhite = ColorConstants.LIGHT_PLAYER_WHITE;
        colorPlayerWhiteOutline = ColorConstants.LIGHT_PLAYER_WHITE_OUTLINE;
        colorCapturedBlock = ColorConstants.LIGHT_CAPTURED_BLOCK;

    }

    public void setGameState(Igra igra) {
        if (igra != null) {
            int player = igra.state;
            int size = igra.size;
            if (this.igra != null && this.igra.state == player && this.igra.size == size) {
                return;
            }
            this.igra = igra;
            cellSize = Math.min(getWidth(), getHeight()) / (igra.size + 1);
            radius = 4 * cellSize / 5;
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

        // drawing the background:
        g.setColor(colorBackground);
        g.fillRect(0, 0, getWidth(), getHeight());

        // drawing the board:
        int boardWidth = (size - 1) * cellSize;
        int boardHeight = (size - 1) * cellSize;
        int boardX = (getWidth() - boardWidth) / 2;
        int boardY = (getHeight() - boardHeight) / 2;
        g.setColor(colorBoard);
        g.fillRect(boardX, boardY, boardWidth, boardHeight);

        // drawing the grid:
        g.setColor(colorGrid);
        for (int i = 0; i < size; i++) {
            g2.setStroke(gridWidth);
            g.drawLine(boardX + i * cellSize, boardY, boardX + i * cellSize, boardY + (size - 1) * cellSize);
            g.drawLine(boardX, boardY + i * cellSize, boardX + (size - 1) * cellSize, boardY + i * cellSize);
        }

        // drawing the stones:
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int stone = grid[row][col];
                g2.setStroke(playerOutlineWidth);
                if (stone == Igra.BLACK_STATE) {
                    drawStone(g, boardX, boardY, row, col, colorPlayerBlack, colorPlayerBlackOutline);
                }
                else if (stone == Igra.WHITE_STATE) {
                    drawStone(g, boardX, boardY, row, col, colorPlayerWhite, colorPlayerWhiteOutline);
                }
                else if (stone == Igra.WHITE_WIN) {
                    drawStone(g, boardX, boardY, row, col, colorPlayerBlack, colorCapturedBlock);
                }
                else if (stone == Igra.BLACK_WIN) {
                    drawStone(g, boardX, boardY, row, col, colorPlayerWhite, colorCapturedBlock);
                }
            }
        }

        // drawing top text (whose turn it is/who has won):
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, cellSize / 3));
        String text;
        if (igra.state == Igra.WHITE_WIN || igra.state == Igra.BLACK_WIN) {
            text = "Game over.";
        }
        else if (igra.state == Igra.BLACK_STATE) {
            text = "It's BLACK's turn.";
        }
        else {
            text = "It's WHITE's turn.";
        }
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textX = (getWidth() - textWidth) / 2;
        int textY = boardY / 2;
        g.drawString(text, textX, textY);

    }

    // method that gets rid of duplicate code for drawing stones in paintComponent()
    private void drawStone(Graphics g, int boardX, int boardY, int row, int col, Color colorStone, Color colorStoneOutline) {
        g.setColor(colorStone);
        g.fillOval(boardX + col * cellSize - radius / 2, boardY + row * cellSize - radius / 2, radius, radius);
        g.setColor(colorStoneOutline);
        g.drawOval(boardX + col * cellSize - radius / 2, boardY + row * cellSize - radius / 2, radius, radius);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // check if the game is running and is not over
        if (igra == null) return;
        if (igra.gameOver() != null) return;
        // if it's the human player's turn, listen for the mouse click
        if (vrstaIgralca.get(igra.state) == C) {
            int x = e.getX();
            int y = e.getY();
            int size = igra.size;
            int boardWidth = (size - 1) * cellSize;
            int boardHeight = (size - 1) * cellSize;
            int boardX = (getWidth() - boardWidth) / 2;
            int boardY = (getHeight() - boardHeight) / 2;
            int col = (x - boardX + cellSize / 2) / cellSize;
            int row = (y - boardY + cellSize / 2) / cellSize;
            Poteza poteza = new Poteza(col, row);
            if (Vodja.playHumanTurn(poteza)) repaint();
        }

    }

    // making the board resizeable based on panel size:
    @Override
    public void componentResized(ComponentEvent e) {
        Component c = e.getComponent();
        if (igra != null) {
            cellSize = Math.min(c.getWidth(), c.getHeight()) / (igra.size + 1);
            radius = 4 * cellSize / 5;
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

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
