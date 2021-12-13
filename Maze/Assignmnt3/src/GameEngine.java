import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.util.Random;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 *
 * @author muhammad
 */
public class GameEngine extends JPanel{
    private final int FPS = 240;
    private final int PlayeMovement = -1;
    private boolean pausedGame = false;
    private Image background;
    public int levelNum = 1;
    public int score = 0;
    private Level level;
    private Player player;
    private Dragon dragon;
    private BlackBoard bb;
    private Timer newFrameTimer;
    
    
//    Constructor of the game
    public GameEngine(){
        
        super();
        background = new ImageIcon("Images/BG.jpg").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"pressed left");
        this.getActionMap().put("pressed left", new AbstractAction(){
            @Override
            
            public void actionPerformed(ActionEvent ae) {
                player.setX_pos(PlayeMovement);
            }
        });
                this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
                  /**
     * Check what to do when pressed right
     */
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setX_pos(-PlayeMovement);
  
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
                  /**
     * Check what to do when pressed down

     */
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setY_pos(-PlayeMovement);
               
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
                  /**
     * to Check what to do when pressed up
     */
            @Override
            public void actionPerformed(ActionEvent ae) {
                player.setY_pos(PlayeMovement);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
                  /**
     * Decide what to do when pressed Esc
     */
            @Override
            public void actionPerformed(ActionEvent ae) {
                pausedGame = !pausedGame;
            }
        });
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener(this));
        newFrameTimer.start();

    }
    public void restart(){
        try{
            level = new Level("data/level" + levelNum + ".txt");
        } catch(IOException ex)
        {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Image playerImage = new ImageIcon("Images/player2.png").getImage();
        player = new Player(0, 600, 40, 40, playerImage);

        GenerateDragon(level);
        bb = new BlackBoard();
        bb.modifyGame(player);

    }
    private Dragon GenerateDragon(Level leve){
        Random r = new Random();
        boolean exist = false;
        while(!exist){
            int x = r.nextInt(741);
            int y = r.nextInt(321) + 40;
            boolean flag = true;
            for (Wall wall : level.walls){
                Rectangle rect = new Rectangle(x, y, 40, 40);
                Rectangle otherRect = new Rectangle(wall.x, wall.y, wall.width, wall.height);
                if (rect.intersects(otherRect)) {
                    flag = false;
                }
            }
            if(flag){
                Image dragonImage = new ImageIcon("Images/dragon.png").getImage();
                dragon = new Dragon(x,y,40,40,dragonImage);
                exist = true;
                return dragon;
            }
        }
         return null;
    }
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 800, 800, null);
        level.draw(grphcs);
        player.draw(grphcs);
        dragon.draw(grphcs);
        bb.modifyGame(player);
        bb.draw(grphcs);

    }
     class NewFrameListener implements ActionListener {
          public JPanel panel;
          NewFrameListener(JPanel panel) {
            this.panel = panel;
        }
          /**
     * Decide what to do in each frame
     * @param ae
     */
        @Override
        public void actionPerformed(ActionEvent ae){
            if(!pausedGame){
                dragon.move();
                if (dragon.collides(player)){
                    String name = JOptionPane.showInputDialog("You are caught, you got " + (score) + " points\n      Please give your name:","Player    ");
                    if(name != null){
                        try{
                           database db = new database();
                            db.insertScore(name,score);
                            }
                        catch (SQLException ex) 
                        {
                                Logger.getLogger(database.class.getName());
                         }
                            score = 0;
                            levelNum = 1;
                            restart();
                    }
                }
                if(player.win()){
                    score = score + 1;
                    JOptionPane.showMessageDialog(panel, "You won this level, you got " + score + " points",
                            "Congrats", JOptionPane.PLAIN_MESSAGE);
                    if(levelNum<=9)
                            {
                                levelNum =  levelNum + 1;
                            }else
                            {
                                levelNum = 1;
                            }
                            restart();
                }
                if (level.collides(dragon)) {
                    dragon.ChangeDirection(level);
                }
                if (level.collides(player)) {
                    player.setX_pos(0);
                    player.setY_pos(0);
                }
                 player.move();

            }
            repaint();
        }

     }
}
     

