import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author muhammad
 */
public class Player extends Sprite{
    private double x_pos =0;
    private double y_pos = 0;
    private boolean WhetherX = true;
    public String tenDir = "x";
    public double tendVal = 0;

    public Player(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
    }
//    For moving in X direction
    public void moveX(){
        if((x_pos < 0 && x>0 ) || (x_pos >0 && x+ width <=800)){
            x += x_pos;
        }
    }
//    For moving in Y direction
    public void moveY(){
        if((y_pos < 0 && y>0 ) || (y_pos >0 && y+ height <=800)){
            y += y_pos;
        }
    }

    public void setX_pos(double x_pos) {
        this.x_pos = x_pos;
        tendVal = x_pos;
        tenDir = "x";
        WhetherX = true;
        
    }

    public void setY_pos(double y_pos) {
        this.y_pos = y_pos;
        tendVal = y_pos;
        tenDir = "y";
        WhetherX = false;
    }

    public double getX_pos() {
        return x_pos;
    }

    public double getY_pos() {
        return y_pos;
    }
    public void move()
    {
        if(WhetherX== true)
        {
            moveX();
        }else
        {
            moveY();
        }
    }
    
//    determine weather the player reached the upper right corner of the map
    public boolean win()
    {
        Rectangle rect = new Rectangle(x , y, width, height);
        Rectangle otherRect = new Rectangle(770, 0, 40, 40); 
        return rect.intersects(otherRect);
    }
//Implementation of checking weather both rectangles collide or not
    @Override
    public boolean collides(Sprite other) {
        if(tenDir.equals("x"))
        {
            Rectangle rect = new Rectangle(x + (int)x_pos , y, width, height);
            Rectangle otherRec = new Rectangle(other.x,other.y,other.width,other.height);
            return rect.intersects(otherRec);
        }
        else
        {
            Rectangle rect = new Rectangle(x , y+ (int)y_pos, width, height);
            Rectangle otherRec = new Rectangle(other.x,other.y,other.width,other.height);
            return rect.intersects(otherRec);
        }
    }
    
    
}
