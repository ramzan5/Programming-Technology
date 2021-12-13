import java.awt.Image;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author muhammad
 */
public class BlackBoard {
    private final int BB_Width = 40;
    private final int BB_Height = 40;
    private final int width = 800;
    private final int height = 800;
    public ArrayList<Darkness> dark;
    
    public BlackBoard(){
        dark = new ArrayList<>();
        Image img = new ImageIcon("Images/Darkness.png").getImage();
        for(int i=0;i<width/BB_Width;i++){
            for(int j=0;j<height/BB_Height;j++){
                dark.add(new Darkness(i*BB_Width,j*BB_Height,BB_Width,BB_Height,img));
            }
        }
    }
//    Function for drawing the graphics
    public void draw(Graphics g){
        for(Darkness darkness:dark ){
            if(!darkness.exposed){
                darkness.draw(g);
            }
        }
    }
/**
     * Determine whether blacks intersects with the 7*7 rectangle centered on the player. 
     * If it intersects, we modify the attributes to ensure that it is not drawn by the draw() method
      
     */    
    public void modifyGame(Player player){
        for(Darkness darks: dark){
            Rectangle rect = new Rectangle(player.x - 3 * player.width, player.y - 3 * player.height, 7 * player.width,
                    7 * player.height);
            Rectangle otherRect = new Rectangle(darks.x,darks.y, darks.width, darks.height);
            if (rect.intersects(otherRect)) {
                darks.exposed = true;
            }else
            {
                darks.exposed = false;
            }
        }
    }
    
    
}
