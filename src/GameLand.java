//Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
//*******************************************************************************
// Class Definition Section

public class GameLand implements Runnable {

    //Variable Declaration Section
    //Declare the variables used in the program
    //You can set their initial values here if you want


    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    //Declare the objects used in the program below
    /** STEP 1: Declare your object and give it a name **/
    public Hero astro;
    public Hero dog;
    public Hero milk;
    public Hero eggs;
    public Hero bread;
    public Hero isle;
    public Hero cereal;
    /** STEP 2: Declare an image for your object**/
    public Image astroPic;
    public Image dogPic;
    public Image milkPic;
    public Image eggsPic;
    public Image breadPic;
    public Image islePic;
    public Image cerealPic;

    public boolean milkIntersectsBread;
    public boolean milkIntersectsCereal;
    public boolean cerealIntersectsEggs;

    // Main method definition: PSVM
    // This is the code that runs first and automatically
    public static void main(String[] args) {
       GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
        new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
    }

    // Constructor Method
    // This has no return type and has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() {
        setUpGraphics(); //this calls the setUpGraphics() method

        //create (construct) the objects needed for the game below
        //for each object that has a picture, load in images as well
        /** STEP 3: Construct a specific Hero**/
        //astro = new Hero(400,500, 20, 20);
        //dog = new Hero(300,300,20,20);
        milk = new Hero(600,300,10,2);
        eggs = new Hero(400,400,8,3);
        bread = new Hero(200,400,-8,4);
        isle = new Hero(-100,0,0,0);
        cereal = new Hero(200,100, -2, 4);


        /** STEP 4: load in the image for your object **/
        //astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png");
        //dogPic = Toolkit.getDefaultToolkit().getImage("dog.jpg");
        milkPic = Toolkit.getDefaultToolkit().getImage("milk.png");
        eggsPic = Toolkit.getDefaultToolkit().getImage("eggs.png");
        breadPic = Toolkit.getDefaultToolkit().getImage("bread.png");
        islePic = Toolkit.getDefaultToolkit().getImage("isle.jpg");
        cerealPic = Toolkit.getDefaultToolkit().getImage("cereal.png");


        //astro.printInfo();
        //dog.printInfo();
        milk.printInfo();
        eggs.printInfo();
        bread.printInfo();
        isle.printInfo();
        cereal.printInfo();
    }// GameLand()

//*******************************************************************************
//User Method Section
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever using a while loop
        while (true) {
            moveThings();  //move all the game objects
            collisions(); //checks for rec intersections
            render();  // paint the graphics
            pause(20); // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of your objects below:
        /** STEP 5: draw the image of your object to the screen**/
        //g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width,astro.height,null);
        //g.drawImage(dogPic, dog.xpos, dog.ypos, astro.width, astro.height, null);
        g.drawImage(islePic, isle.xpos, isle.ypos, 1200, 800, null);

        g.drawImage(milkPic, milk.xpos, milk.ypos, milk.width, milk.height,null);
        if (eggs.isAlive == true){
            g.drawImage(eggsPic, eggs.xpos, eggs.ypos, eggs.width, eggs.height, null);
        }
        if(bread.isAlive == true){
            g.drawImage(breadPic, bread.xpos, bread.ypos, bread.width, bread.height, null);
        }
        g.drawImage(cerealPic, cereal.xpos, cereal.ypos, cereal.width, cereal.height, null);

        //dispose the images each time(this allows for the illusion of movement).
        g.dispose();

        bufferStrategy.show();
    }

    public void moveThings() {
        //call the move() method code from your object class
        //astro.bouncingMove();
        //dog.bouncingMove();
        milk.bouncingMove();
        eggs.bouncingMove();
        bread.bouncingMove();
        cereal.bouncingMove();
    }

    public void collisions(){
        if(milk.rec.intersects(bread.rec)&&milkIntersectsBread==false){
            milkIntersectsBread = true;
            System.out.println("ouch");
            bread.dx = -1*bread.dx;
            bread.dy = -1*bread.dy;
            milk.dx = -1*milk.dx;
            milk.dy = -1*milk.dy;

            //this makes the bread dissappear
//            bread.isAlive = false;
//            bread.dy = 0;
//            bread.dx = 0;
//            bread.xpos = 2000;

            //this bounces the characters
            //milk.dx = -1*milk.dx;
            //bread.dx = -1*bread.dx;
        }

        if(bread.height>=200){
            bread.isBig = true;
        }

        if(milk.rec.intersects(bread.rec)&& bread.isBig==true){
            bread.height = bread.height - 10;
            bread.width = bread.width -10;
            System.out.println(bread.height);
        }
        if(milk.rec.intersects(bread.rec)&& !bread.isBig){
            bread.height = bread.height + 10;
            bread.width = bread.width + 10;
            System.out.println(bread.height);
        }



        if(milk.rec.intersects(bread.rec)==false){
            milkIntersectsBread = false;
        }
        if(bread.rec.intersects(eggs.rec)&&eggs.isAlive == true){
            eggs.isAlive = false;
        }
        if(bread.rec.intersects(eggs.rec)&&eggs.isAlive == false){
            eggs.isAlive = true;
        }

        if(cereal.rec.intersects(eggs.rec)==false){
            cerealIntersectsEggs = false;
        }
        
        if(cereal.rec.intersects(eggs.rec)){
            eggs.isAlive = false;
        }

//        if(eggs.rec.intersects(cereal.rec)&&cerealIntersectsEggs==false){
//            cerealIntersectsEggs = true;
//            cereal.dx = cereal.dx*2;
//            cereal.dy = cereal.dy*2;
//            System.out.println(cereal.dx);
//            System.out.println(cereal.dy);
//
//        }

        if(milk.rec.intersects(cereal.rec) ==false){
            milkIntersectsCereal = false;
        }

        if(milk.rec.intersects(cereal.rec)&&milkIntersectsCereal == false){
            milkIntersectsCereal = true;
            System.out.println("ouch");
            cereal.dx = -1*cereal.dx;
            cereal.dy = -1*cereal.dy;
            milk.dx = -1*milk.dx;
            milk.dy = -1*milk.dy;
        }

    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}