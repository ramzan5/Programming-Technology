
package pkg2nd_assignment;


import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class MainWindow extends BaseWindow {
    
    private List<Window> gameWindows = new ArrayList<>();
    
    public MainWindow() {
        
        JButton smallbtn = new JButton();
        smallbtn.setText("8 x 5");
        
        smallbtn.addActionListener(getActionListener(8,5));
        
        JButton mediumbtn = new JButton();
        mediumbtn.setText("10 x 6");

        mediumbtn.addActionListener(getActionListener(10,6));

        JButton largebtn = new JButton();
        largebtn.setText("12 x 7");
        
        largebtn.addActionListener(getActionListener(12,7));
        
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(smallbtn);
        getContentPane().add(mediumbtn);
        getContentPane().add(largebtn);
    }
    
    private ActionListener getActionListener(final int rows, final int columns) {
        return new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(rows, columns, MainWindow.this);
                window.setVisible(true);
                gameWindows.add(window);
            }
            
        };
    }
    
    public List<Window> getWindowList() {
        return gameWindows;
    }
    
    @Override
    protected void doUponExit() {
        System.exit(0);
    }

}
