package gui2;

import logika2.Igra2;
import splosno.KdoIgra;
import splosno.Poteza;
import vodja.Vodja;
import vodja2.Vodja2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static vodja2.PlayerType.C;

public class Panel extends JPanel implements MouseListener, ComponentListener {
    protected Igra2 igra;
    protected Color colorBackground;
    protected Color colorBoard;
    protected Color colorGrid;
    protected Color colorPlayerBlack;
    protected Color colorPlayerBlackOutline;
    protected Color colorPlayerWhite;
    protected Color colorPlayerWhiteOutline;
    protected Color colorCapturedBlock;
    protected Stroke gridWidth;
    protected Stroke playerOutlineWidth;


    public Panel(int width, int height) {
        super();
        setGame(null);
        setPreferredSize(new Dimension(width, height));
        addMouseListener(this);
        setFocusable(true);

        //
        gridWidth = new BasicStroke(2);
        playerOutlineWidth = new BasicStroke(2);


        // initial color constants:
        colorBackground = gui2.ColorConstants.LIGHT_BACKGROUND;
        colorBoard = gui2.ColorConstants.LIGHT_BOARD;
        colorGrid = gui2.ColorConstants.LIGHT_GRID;
        colorPlayerBlack = gui2.ColorConstants.LIGHT_PLAYER_BLACK;
        colorPlayerBlackOutline = gui2.ColorConstants.LIGHT_PLAYER_BLACK_OUTLINE;
        colorPlayerWhite = gui2.ColorConstants.LIGHT_PLAYER_WHITE;
        colorPlayerWhiteOutline = gui2.ColorConstants.LIGHT_PLAYER_WHITE_OUTLINE;
        colorCapturedBlock = gui2.ColorConstants.LIGHT_CAPTURED_BLOCK;
    }

    public void setGame(Igra2 igra) {
        this.igra = igra;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (igra == null) return;
        int size = igra.size;
        int cellSize = Math.min(getWidth(), getHeight()) / (size + 1);
        Graphics2D g2 = (Graphics2D) g;

        //bcg
        g.setColor(colorBackground);
        g.fillRect(0,0, getWidth(), getHeight());

        //board
        int boardWidth = (size - 1) * cellSize;
        int boardHeight = (size - 1) * cellSize;
        int boardX = (getWidth() - boardWidth) / 2;
        int boardY = (getHeight() - boardHeight) / 2;
        g.setColor(colorBoard);
        g.fillRect(boardX, boardY, boardWidth, boardHeight);

        //grid
        g.setColor(colorGrid);
        for (int i = 0; i < size; ++i) {
            g2.setStroke(gridWidth);
            g.drawLine(boardX + i * cellSize, boardY, boardX + i * cellSize, boardY + (size -1) * cellSize); //navpicne
            g.drawLine(boardX, boardY + i * cellSize, boardX + (size-1) * cellSize, boardY + i * cellSize ); // vodoravnice
        }
        //stones
        for (int j = 0; j < igra.size; ++j) {
            for (int i = 0; i < igra.size; ++i){
                char c = igra.grid[j][i];
                switch (c) {
                    case 'B' -> {
                        Color stoneColor = colorPlayerBlack;
                        Color stoneOutline = colorPlayerBlackOutline;
                        drawStone(g, boardX, boardY, j, i, stoneColor, stoneOutline);
                    }
                    case 'W' -> {
                        Color stoneColor = colorPlayerWhite;
                        Color stoneOutline = colorPlayerWhiteOutline;
                        drawStone(g, boardX, boardY, j, i, stoneColor, stoneOutline);
                    }
                    case 'b' -> {
                        Color stoneColor = colorPlayerBlack;
                        Color stoneOutline = colorCapturedBlock;
                        drawStone(g, boardX, boardY, j, i, stoneColor, stoneOutline);}
                    case 'w' -> {
                        Color stoneColor = colorPlayerWhite;
                        Color stoneOutline = colorCapturedBlock;
                        drawStone(g, boardX, boardY, j, i, stoneColor, stoneOutline);}
                    default -> {continue;}
                }
            }
        }
    }

    private void drawStone(Graphics g, int boardX, int boardY, int row, int col, Color colorStone, Color colorStoneOutline) {
        int size = igra.size;
        int cellSize = Math.min(getWidth(), getHeight()) / (size + 1);
        int radius = 4 * cellSize / 5;
        g.setColor(colorStone);
        g.fillOval(boardX + col * cellSize - radius / 2, boardY + row * cellSize - radius / 2, radius, radius);
        g.setColor(colorStoneOutline);
        g.drawOval(boardX + col * cellSize - radius / 2, boardY + row * cellSize - radius / 2, radius, radius);
    }
    /**
     * Invoked when the component's size changes.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentResized(ComponentEvent e) {

    }

    /**
     * Invoked when the component's position changes.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentMoved(ComponentEvent e) {

    }

    /**
     * Invoked when the component has been made visible.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentShown(ComponentEvent e) {

    }

    /**
     * Invoked when the component has been made invisible.
     *
     * @param e the event to be processed
     */
    @Override
    public void componentHidden(ComponentEvent e) {

    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (igra == null || igra.gameOver) return;
        int size = igra.size;
        int cellSize = Math.min(getWidth(), getHeight()) / (size + 1);
        if(Vodja2.vrstaIgralca.get(igra.naVrsti) == C) {
            int x = e.getX();
            int y = e.getY();
            int boardWidth = (size - 1) * cellSize;
            int boardHeight = (size - 1) * cellSize;
            int boardX = (getWidth() - boardWidth) / 2;
            int boardY = (getHeight() - boardHeight) / 2;
            int col = (x - boardX + cellSize / 2) / cellSize;
            int row = (y - boardY + cellSize / 2) / cellSize;
            Poteza poteza = new Poteza(col, row);
            if (Vodja2.playHumanTurn(poteza)) {
                repaint();
            }
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
