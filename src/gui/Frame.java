package gui;

import logika.Igra;
import vodja.PlayerType;
import vodja.Vodja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Frame extends JFrame implements ActionListener {
    protected Panel panel;
    protected Igra game;
    protected JPanel buttonPanel;
    protected JButton pvpButton, pvcButton, cvpButton, cvcButton;
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

        // top level menu
        JMenu menuGame = addMenu(menubar, "Game");
        JMenu menuSettings = addMenu(menubar, "Settings");

        // game menu
        JMenu gamePlay = addSubMenu(menuGame, "Play");
        gamePlayerVsPlayer = addMenuItem(gamePlay, "Player vs player");
        gamePlayerVsComputer = addMenuItem(gamePlay, "Player vs computer");
        gameComputerVsPlayer = addMenuItem(gamePlay, "Computer vs player");
        gameComputerVsComputer = addMenuItem(gamePlay, "Computer vs Computer");

        // settings menu
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
        menuPlayerWhiteColor = addMenuItem(settingsColorPicker, "Second player color");
        menuPlayerWhiteOutlineColor = addMenuItem(settingsColorPicker, "Second player outline color");

        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(800, 800));
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);

        pvpButton = new JButton("Player vs player");
        pvpButton.setPreferredSize(new Dimension(200,100));
        pvpButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(pvpButton, gbc);

        pvcButton = new JButton("Player vs computer");
        pvcButton.setPreferredSize(new Dimension(200,100));
        pvcButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(pvcButton, gbc);

        cvpButton = new JButton("Computer vs player");
        cvpButton.setPreferredSize(new Dimension(200,100));
        cvpButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(cvpButton, gbc);

        cvcButton = new JButton("Computer vs computer");
        cvcButton.setPreferredSize(new Dimension(200,100));
        cvcButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(cvcButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);

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
        if (object == gamePlayerVsPlayer || object == pvpButton) {
            remove(buttonPanel);
            add(panel);
            Vodja.vrstaIgralca = new HashMap<>();
            Vodja.vrstaIgralca.put(Igra.BLACK_STATE, PlayerType.C);
            Vodja.vrstaIgralca.put(Igra.WHITE_STATE, PlayerType.C);
            Vodja.playNewGame();
            panel.igra = Vodja.game;
            SwingUtilities.updateComponentTreeUI(panel);
        }
        else if (object == gamePlayerVsComputer || object == pvcButton) {
            remove(buttonPanel);
            add(panel);
            Vodja.vrstaIgralca = new HashMap<>();
            Vodja.vrstaIgralca.put(Igra.BLACK_STATE, PlayerType.C);
            Vodja.vrstaIgralca.put(Igra.WHITE_STATE, PlayerType.R);
            Vodja.playNewGame();
            panel.igra = Vodja.game;
            SwingUtilities.updateComponentTreeUI(panel);
        }
        else if (object == gameComputerVsPlayer || object == cvpButton) {
            remove(buttonPanel);
            add(panel);
            Vodja.vrstaIgralca = new HashMap<>();
            Vodja.vrstaIgralca.put(Igra.BLACK_STATE, PlayerType.R);
            Vodja.vrstaIgralca.put(Igra.WHITE_STATE, PlayerType.C);
            Vodja.playNewGame();
            panel.igra = Vodja.game;
            SwingUtilities.updateComponentTreeUI(panel);
        }
        else if (object == gameComputerVsComputer || object == cvcButton) {
            remove(buttonPanel);
            add(panel);
            Vodja.vrstaIgralca = new HashMap<>();
            Vodja.vrstaIgralca.put(Igra.BLACK_STATE, PlayerType.R);
            Vodja.vrstaIgralca.put(Igra.WHITE_STATE, PlayerType.R);
            Vodja.playNewGame();
            panel.igra = Vodja.game;
            SwingUtilities.updateComponentTreeUI(panel);
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
        }
        else if (object == menuBackgroundColor) {
            Color color = JColorChooser.showDialog(this, "Choose background color", panel.colorBackground);
            if (color != null) {
                panel.colorBackground = color;
            }
        }
        else if (object == menuBoardColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the board", panel.colorBoard);
            if (color != null) {
                panel.colorBoard = color;
            }
        }
        else if (object == menuGridColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the grid", panel.colorGrid);
            if (color != null) {
                panel.colorGrid = color;
            }
        }
        else if (object == menuCapturedBlockColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the captured block", panel.colorCapturedBlock);
            if (color != null) {
                panel.colorCapturedBlock = color;
            }
        }
        else if (object == menuPlayerBlackColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the first player", panel.colorPlayerBlack);
            if (color != null) {
                panel.colorPlayerBlack = color;
            }
        }
        else if (object == menuPlayerBlackOutlineColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the first player's outline", panel.colorPlayerBlackOutline);
            if (color != null) {
                panel.colorPlayerBlackOutline = color;
            }
        }
        else if (object == menuPlayerWhiteColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the second player", panel.colorPlayerWhite);
            if (color != null) {
                panel.colorPlayerWhite = color;
            }
        }
        else if (object == menuPlayerWhiteOutlineColor) {
            Color color = JColorChooser.showDialog(this, "Choose color of the second player's outline", panel.colorPlayerWhiteOutline);
            if (color != null) {
                panel.colorPlayerWhiteOutline = color;
            }
        }
        repaint();
    }
}
