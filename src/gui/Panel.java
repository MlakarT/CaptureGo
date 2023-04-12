package gui;

import logika.Igra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements MouseListener {
    protected Igra game;
    protected int state;
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
        game = new Igra();
        state = Igra.NULL_STATE;
        gridWidth = new BasicStroke(10);
        playerOutlineWidth = new BasicStroke(5);
        radius = 20;

        colorLightBackground = Color.LIGHT_GRAY;
        colorLightBoard = new Color(242,176,109,255);
        colorLightGrid = Color.BLACK;
        colorLightPlayer1 = Color.WHITE;
        colorLightPlayer1Outline = Color.BLACK;
        colorLightPlayer2 = Color.BLACK;
        colorLightPlayer2Outline = Color.BLACK;
        colorLightCapturedBlock = Color.YELLOW;

        colorDarkBackground = Color.DARK_GRAY;
        colorDarkBoard = new Color(32,33,36,255);
        colorDarkGrid = new Color(145,149,130,255);
        colorDarkPlayer1 = new Color(198,70,52,255);
        colorDarkPlayer1Outline = Color.BLACK;
        colorDarkPlayer2 = new Color(129,180,120,255);
        colorDarkPlayer2Outline = Color.BLACK;
        colorDarkCapturedBlock = new Color(240,180,66,255);

    }
    public void setGameState(Igra game) {
        if (game.state == Igra.NULL_STATE){
            //todo null state
        } else {
            //todo not null state
        };
        repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //todo paint
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
