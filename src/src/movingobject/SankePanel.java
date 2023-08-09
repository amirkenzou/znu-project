package src.movingobject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
public class SankePanel extends JPanel implements ActionListener{
    static final int S_Width=720;
    static final int S_Height=720;
    private   int sizeS=8;
    public   int tileSize = S_Width/sizeS; //8 Tiles for checkers board
    public   int Game_unit_size=S_Width/sizeS;
    Timer timer;
    Random random;
    int foodEAten;
    int foodX;
    int foodY;
    int bodylength = 1;
    boolean game_flag = false;
    char dir = 'R';
    static final int DELAY = 400;
    public final int G_Size=(S_Width*S_Height)/(Game_unit_size*Game_unit_size);
    final int x_snake[]=new int[G_Size];
    final int y_snake[]=new int[G_Size];
    SankePanel(int s){
        this.setPreferredSize(new Dimension(S_Width,S_Height));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKey());
        this.sizeS=s;
        random = new Random();
        Game_start();
    }
    public void Game_start() {
        game_flag=true;
        timer=new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        tileSize=720/sizeS;
        System.out.println("42: "+sizeS);
        System.out.println("43: "+tileSize);
        for(int row = 0; row < 16; row++){
            for(int col = 0; col < 16; col++){
                if((row%2 == 0 && col%2 == 0) || (row%2 != 0 && col%2 != 0)){ // This assigns the checkerboard pattern
                    graphic.setColor(Color.lightGray);
                    graphic.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                }
                else{
                    graphic.setColor(Color.darkGray);
                    graphic.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                }

            }
        }

        draw(graphic);
    }
    public void draw(Graphics graphic) {
        if(game_flag){
            graphic.setColor(Color.red);
            graphic.fillOval(foodX, foodY,Game_unit_size,Game_unit_size);
            System.out.println(bodylength);
            Game_unit_size=S_Width/sizeS;
            for(int i=0;i<bodylength;i++){
                if(i==0){
                    graphic.setColor(Color.YELLOW);
                    graphic.fillRect(x_snake[i],y_snake[i],Game_unit_size,Game_unit_size);
                }
                else{
                    graphic.setColor(new Color(196, 197, 13));
                    graphic.fillRect(x_snake[i],y_snake[i],Game_unit_size,Game_unit_size);
                }
            }
            graphic.setColor(Color.blue);
            graphic.setFont(new Font("Ink Free",Font.BOLD,40));
           // graphic.drawString("Score:"+foodEAten,(S_Width-font_me.stringWidth("Score:"+foodEAten))/2,graphic.getFont().getSize());

        }
        else{

        }
    }
    public void move() throws InterruptedException {
        for(int i=bodylength;i>0;i--){
            x_snake[i]=x_snake[i-1];
            y_snake[i]=y_snake[i-1];

        }
        timer.stop();

        switch (dir) {
            case 'U':
                y_snake[0]=y_snake[0]-Game_unit_size;
                break;
            case 'L':
                x_snake[0] = x_snake[0] - Game_unit_size;
                break;
            case 'D':
                y_snake[0] = y_snake[0] + Game_unit_size;
                break;
            case 'R':
                x_snake[0] = x_snake[0] + Game_unit_size;
                break;
        }
    }

    public void food_EatenOrNot() {// for checking the food has been eaten by snake or not
        //if((x_snake[0]==foodX)&&(y_snake[0]==foodY)){
            bodylength++;

    }


    public class MyKey extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            timer.start();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(dir!='R'){
                        dir='L';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (dir != 'D') {
                        dir = 'U';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (dir != 'L') {
                        dir = 'R';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (dir != 'U') {
                        dir = 'D';
                    }
                    break;
                case KeyEvent.VK_R:
                    if(!game_flag){
                        foodEAten=0;
                        bodylength=2;
                        dir='R';
                        Arrays.fill(x_snake,0);
                        Arrays.fill(y_snake,0);
                        Game_start();
                    }
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (game_flag) {
            try {
                move();
            } catch (InterruptedException e) {

            }
            food_EatenOrNot();

        }
        repaint();
    }
    public  void setSizeS(int sizeS) {
        System.out.println("size s: "+sizeS);
        this.sizeS = sizeS;
    }


}