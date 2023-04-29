package gui;

import logika.Igra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame implements ActionListener {
    protected Panel panel;
    protected Igra game;
    private final JMenuItem gamePlayerVsPlayer, gamePlayerVsComputer, gameComputerVsPlayer, gameComputerVsComputer;
    private final JMenuItem settingsLightTheme, settingsDarkTheme;
    private final JMenuItem menuBackgroundColor, menuBoardColor, menuGridColor, menuPlayerBlackColor,
    menuPlayerBlackOutlineColor, menuPlayerWhiteColor, menuPlayerWhiteOutlineColor, menuCapturedBlockColor;

    public Frame() {
        super();
        setTitle("Play Capture Go");
        panel = new Panel(800,800);
        add(panel);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        // top level buttons
        JMenu menuGame = addMenu(menubar, "Game");
        JMenu menuSettings = addMenu(menubar, "Settings");

        // game buttons
        JMenu gamePlay = addSubMenu(menuGame, "Play");
        gamePlayerVsPlayer = addMenuItem(gamePlay, "Player vs player");
        gamePlayerVsComputer = addMenuItem(gamePlay, "Player vs computer");
        gameComputerVsPlayer = addMenuItem(gamePlay, "Computer vs player");
        gameComputerVsComputer = addMenuItem(gamePlay, "Computer vs Computer");

        // settings buttons
        JMenu settingsTheme = addSubMenu(menuSettings, "Theme");
        settingsLightTheme = addMenuItem(settingsTheme, "Light Theme");
        settingsTheme.addSeparator();
        settingsDarkTheme = addMenuItem(settingsTheme, "Dark Theme");

        JMenu settingsColorPicker = addSubMenu(menuSettings, "Select colors...");
        menuBackgroundColor = addMenuItem(settingsColorPicker, "Background color");
        menuBoardColor = addMenuItem(settingsColorPicker, "Board color");
        menuGridColor = addMenuItem(settingsColorPicker, "Grid color");
        menuCapturedBlockColor = addMenuItem(settingsColorPicker, "Captured block color");
        settingsColorPicker.addSeparator();
        menuPlayerBlackColor = addMenuItem(settingsColorPicker, "First player color");
        menuPlayerBlackOutlineColor = addMenuItem(settingsColorPicker, "First player outline color");
        menuPlayerWhiteColor = addMenuItem(settingsColorPicker, "Second player outline color");
        menuPlayerWhiteOutlineColor = addMenuItem(settingsColorPicker, "Second player outline color");

        //todo game related info
        // menubar.add(Game.info(game));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JMenu addMenu(JMenuBar menuBar, String title) {
        JMenu menu = new JMenu(title);
        menuBar.add(menu);
        return menu;
    }
    private JMenuItem addMenuItem(JMenu menu, String title) {
        JMenuItem menuItem = new JMenuItem(title);
        menu.add(menuItem);
        menuItem.addActionListener(this);
        return menuItem;
    }

    private JMenu addSubMenu (JMenu menu, String title) {
        JMenu subMenu = new JMenu(title);
        menu.add(subMenu);
        return subMenu;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == gamePlayerVsPlayer) {
            //todo pvc
        }
        else if (object == gamePlayerVsComputer) {
            //todo pvc
        }
        else if (object == gameComputerVsPlayer) {
            //todo cvp
        }
        else if (object == gameComputerVsComputer) {
            //todo cvc
        }
        else if (object == settingsLightTheme) {
            panel.colorBackground = ColorConstants.LIGHT_BACKGROUND;
            panel.colorBoard = ColorConstants.LIGHT_BOARD;
            panel.colorGrid = ColorConstants.LIGHT_GRID;
            panel.colorPlayerBlack = ColorConstants.LIGHT_PLAYER_BLACK;
            panel.colorPlayerBlackOutline = ColorConstants.LIGHT_PLAYER_BLACK_OUTLINE;
            panel.colorPlayerWhite = ColorConstants.LIGHT_PLAYER_WHITE;
            panel.colorPlayerWhiteOutline = ColorConstants.LIGHT_PLAYER_WHITE_OUTLINE;
            panel.colorCapturedBlock = ColorConstants.LIGHT_CAPTURED_BLOCK;
            repaint();
        }
        else if (object == settingsDarkTheme) {
            panel.colorBackground = ColorConstants.DARK_BACKGROUND;
            panel.colorBoard = ColorConstants.DARK_BOARD;
            panel.colorGrid = ColorConstants.DARK_GRID;
            panel.colorPlayerBlack = ColorConstants.DARK_PLAYER_BLACK;
            panel.colorPlayerBlackOutline = ColorConstants.DARK_PLAYER_BLACK_OUTLINE;
            panel.colorPlayerWhite = ColorConstants.DARK_PLAYER_WHITE;
            panel.colorPlayerWhiteOutline = ColorConstants.DARK_PLAYER_WHITE_OUTLINE;
            panel.colorCapturedBlock = ColorConstants.DARK_CAPTURED_BLOCK;
            repaint();
        }
        else if (object == menuBackgroundColor) {
            Color color = JColorChooser.showDialog(this, "Choose background color", panel.colorBackground);
            if (color != null) {
                panel.colorBackground = color;
                repaint();
            }
        }
        else if (object == menuBoardColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the board", panel.colorBoard);
            if (color != null) {
                panel.colorBoard = color;
                repaint();
            }
        }
        else if (object == menuGridColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the grid", panel.colorGrid);
            if (color != null) {
                panel.colorGrid = color;
                repaint();
            }
        }
        else if (object == menuCapturedBlockColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the captured block", panel.colorCapturedBlock);
            if (color != null) {
                panel.colorCapturedBlock = color;
                repaint();
            }
        }
        else if (object == menuPlayerBlackColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the first player", panel.colorPlayerBlack);
            if (color != null) {
                panel.colorPlayerBlack = color;
                repaint();
            }
        }
        else if (object == menuPlayerBlackOutlineColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the first player's outline", panel.colorPlayerBlackOutline);
            if (color != null) {
                panel.colorPlayerBlackOutline = color;
                repaint();
            }
        }
        else if (object == menuPlayerWhiteColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the second player", panel.colorPlayerWhite);
            if (color != null) {
                panel.colorPlayerWhite = color;
                repaint();
            }
        }
        else if (object == menuPlayerWhiteOutlineColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the second player's outline", panel.colorPlayerWhiteOutline);
            if (color != null) {
                panel.colorPlayerWhiteOutline = color;
                repaint();
            }
        }
    }
}
