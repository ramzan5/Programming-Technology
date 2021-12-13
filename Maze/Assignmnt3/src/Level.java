
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.ImageIcon;

/**
 *
 * @author muhammad
 */
public class Level {
    private final int Wall_Width = 40;
    private final int Wall_Height = 40;
    public ArrayList<Wall> walls;
    
    
    //throw java IO Exception
    public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }
    
    public void loadLevel(String levelPath)throws FileNotFoundException,IOException{
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        walls = new ArrayList<>();
        int y =0;
        String line ;
        while((line = br.readLine()) != null){
            int x = 0;
            for(char blockType:line.toCharArray()){
                if(Character.isDigit(blockType)){
                    Image img = new ImageIcon("Images/wall.png").getImage();
                    walls.add(new Wall(x*Wall_Width,y*Wall_Height,Wall_Width,Wall_Height,img));                
                    
                }
                x++;
            }
            y++;
        }
        
    }
    
    public void draw(Graphics g) {
        for (Wall wall : walls) {
            wall.draw(g);
        }
    }
    
    public boolean collides(Dragon dragon){
        Wall collided = null;
        for(Wall wall: walls){
            if(dragon.collides(wall)){
                collided = wall;
                break;
            }
        }
        if (collided!= null) {
            
            return true;
        } else {
            return false;
        } 
        
    }
    
    public boolean collides(Player player){
        Wall collided = null;
        for(Wall wall: walls){
            if(player.collides(wall)){
                collided = wall;
                break;
            }
        }
        if (collided!= null) {
            
            return true;
        } else {
            return false;
        } 
        
    }
    
    
}
