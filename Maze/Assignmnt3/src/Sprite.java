import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author muhammad
 */
public class Sprite {
//    Coordinate of the top left corner of the sprites
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image img;
//Constructer of the sprite
    public Sprite(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
    
    public void draw(Graphics g){
        g.drawImage(img, x, y,width,height,null);
    }
    
    public boolean collides(Sprite other){
        Rectangle rect = new Rectangle(x,y,width,height);
        Rectangle otherRec = new Rectangle(other.x,other.y,other.width,other.height);
        return rect.intersects(otherRec);
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }








    public void setHeight(int height) {
        this.height = height;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    
    
    
}
