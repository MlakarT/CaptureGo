import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    public Frame() {
        super();
        setTitle("Play Capture Go");
        //todo make dynamically scaling window
        panel = new Panel(800,800);
        add(panel);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
