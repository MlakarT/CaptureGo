import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    protected Panel panel;

    public Frame() {
        super();
        setTitle("Play Capture Go");
        //todo make dynamically scaling window
        Panel panel = new Panel(800,800);
        add(panel);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu menuGame = addMenu(menubar, "Settings");




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private JMenu addMenu(JMenuBar menubar, String title) {
        JMenu menu = new JMenu(title);
        menubar.add(menu);
        return menu;
    }
    private JMenuItem addMenuItem(JMenu menu, String title){
        JMenuItem menuitem = new JMenuItem(title);
        menu.add(menuitem);
        menuitem.addActionListener(this);
        return menuitem;
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
