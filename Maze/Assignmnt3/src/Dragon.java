import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author muhammad
 */
public class Dragon extends Sprite {

    private double x_pos =1;
    private double y_pos ;
    private boolean WhetherX = true;
    public String tenDir = "x";
    public double tendVal = 0;

    
    public Dragon(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
    }
    
    //    For moving in X direction
    public void moveX(){
        if((x_pos < 0 && x>0 ) || (x_pos >0 && x+ width <=780)){
            x += x_pos;
        }
        else
        {
            ChangeDirection();
        }
    }
    //    For moving in Y direction
    public void moveY(){
        if((y_pos < 0 && y>0 ) || (y_pos >0 && y+ height <=740)){
            y += y_pos;
        }
        else
        {
            ChangeDirection();
        }
    }
//    Implementation of changing the direction of Dragon randomly
    
    public void ChangeDirection(Level level){
        Random rand = new Random();
        int n = rand.nextInt(4);
        if(n==0){
            boolean flag = true;
            Rectangle rec = new Rectangle(x,y-1,width,height);
            for(Wall wall: level.walls){
                Rectangle otherRect = new Rectangle(wall.x,wall.y,wall.width,wall.height);
                if(rec.intersects(otherRect)){
                    flag = false;
                }
                    
            }
            if (flag){
                y_pos = -1;
                tendVal = y_pos;
                tenDir = "y";
                WhetherX = false;
            }
            else{
                ChangeDirection(level);
            }
        }
        else if (n==2){
            boolean flag = true;
            Rectangle rec = new Rectangle(x-1,y,width,height);
            for(Wall wall: level.walls){
                Rectangle otherRect = new Rectangle(wall.x,wall.y,wall.width,wall.height);
                if(rec.intersects(otherRect)){
                    flag = false;
                }
                    
            }
            if (flag){
                x_pos = -1;
                tendVal = x_pos;
                tenDir = "x";
                WhetherX = true;
            }
            else{
                ChangeDirection(level);
            }
        }
        else{
            boolean flag = true;
            Rectangle rec = new Rectangle(x+1,y,width,height);
            for(Wall wall: level.walls){
                Rectangle otherRect = new Rectangle(wall.x,wall.y,wall.width,wall.height);
                if(rec.intersects(otherRect)){
                    flag = false;
                }
                    
            }
            if (flag){
                x_pos = 1;
                tendVal = x_pos;
                tenDir = "x";
                WhetherX = true;
            }
            else{
                ChangeDirection(level);
            }
        }
    }
    public void ChangeDirection(){
        Random rand = new Random();
        int n = rand.nextInt(4);
        if(n == 0){
            y_pos = -1;
            tendVal = y_pos;
            tenDir = "y";
            WhetherX = false;
        }
        else if(n==1){
            y_pos = 1;
            tendVal = y_pos;
            tenDir = "y";
            WhetherX = false;
        }
        else if(n==2){
            x_pos = -1;
            tendVal = x_pos;
            tenDir = "x";
            WhetherX = true;
        }
        else
        {
            x_pos = 1;
            tendVal = x_pos;
            tenDir = "x";
            WhetherX = true;
        }
    }
       public void move()
    {
        if(WhetherX == true)
        {
            moveX();
        }else
        {
            moveY();
        }
    }

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
