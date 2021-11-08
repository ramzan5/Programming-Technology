
package pkg2nd_assignment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class BaseWindow extends JFrame {

    public BaseWindow() {
        setTitle("Connect Four");
        setSize(720, 540);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }

        });
        URL url = Window.class.getResource("icon.png");
        //adding the image icon of the game
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

    }
    ///Implementation of showExitConfirmation
    private void showExitConfirmation() {
        int i = JOptionPane.showConfirmDialog(this, "DO YOU Really Want to Quit the Game?",
                "REALLY?", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }
    //Function for disposing the screen
    protected void doUponExit() {
        this.dispose();
    }
    
}

