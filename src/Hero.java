import java.awt.*;

public class Hero {
    //variable declaration section
    public String name;
    public int xpos;
    public int ypos;
    public int dx;      //speed of hero in x direction
    public int dy;      //speed of hero in y direction
    public int width;
    public int height;
    public boolean isAlive;
    public boolean isBig;
    public Rectangle rec;


    public Hero(int pXpos, int pYpos, int pDx, int pDy){
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx;
        dy = pDy;
        width = 100;
        height = 120;
        isAlive = true;
        isBig = false;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){
        ypos = ypos+dy;
        xpos = xpos+dx;
        rec = new Rectangle(xpos,ypos,width,height);
    }

    public void bouncingMove(){
        if(xpos>1000-width){
            dx = -dx;
            System.out.println("TEST");
        }
        if (xpos<0){
            dx = -dx;
        }
        if(ypos>700-height){
            dy = -dy;
        }
        if(ypos<0){
            dy = -dy;
        }
        ypos = ypos+dy;
        xpos = xpos+dx;
        rec = new Rectangle(xpos,ypos,width-20,height-10);
    }

    public void wrappingMove(){

        if(xpos>1000){
            xpos = 0;
        }
        if (xpos<0){
            xpos = 1000;
        }
        if(ypos>700){
            ypos = 0;
        }
        if (ypos<0){
            ypos = 700;
        }
        //the two lines of code below actually update the position
        //this is what makes the object move
        ypos = ypos+dy;
        xpos = xpos+dx;
        rec = new Rectangle(xpos,ypos,width,height);

    }

    public void printInfo(){
        System.out.println("(x,y): ("+xpos+", "+ypos+")");
        System.out.println("X speed: " + dx);
        System.out.println("Y speed: " + dy);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Is Alive: " + isAlive);
    }

}
