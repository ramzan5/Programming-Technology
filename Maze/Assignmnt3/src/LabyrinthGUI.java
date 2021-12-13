import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author muhammad
 */
public class LabyrinthGUI {
    private JFrame frame;
    private GameEngine gameSetup;
    public LabyrinthGUI(){
        frame = new JFrame("Labyrinth/Maze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameSetup = new GameEngine();
        frame.getContentPane().add(gameSetup);
        frame.setPreferredSize(new Dimension(800, 800));
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem newMenu = new JMenuItem("New");
        gameMenu.add(newMenu);
        newMenu.addActionListener(new ActionListener() {
            /**
     * Determines the operating after clicking the button.
     */
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameSetup.levelNum = 1;
                gameSetup.score = 0;
                gameSetup.restart();
            }
        });
        JMenuItem Ranking = new JMenuItem("Ranking");
        gameMenu.add(Ranking);
        Ranking.addActionListener(new ActionListener() {
            /**
     * Determines the operating after clicking the button.
     */
            @Override
            public void actionPerformed(ActionEvent ae) {
            try{
            database db = new database();
            ArrayList<Data> Points = db.getHighScores();
            String content = "                Ranking   \n";
            for(int i=0 ;i < Points.size();i++)
            {
                if(i<10){
                content += String.valueOf(i+1)+". Name: " + Points.get(i).name + "  Point:  "+ Points.get(i).Scores +"\n";
                }
            }
             JOptionPane.showMessageDialog(gameSetup, content,
                            "Ranking", JOptionPane.PLAIN_MESSAGE);
            
            }
            catch(SQLException ex)
            {
                Logger.getLogger(database.class.getName());
            }
            }
            }); 
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
}
