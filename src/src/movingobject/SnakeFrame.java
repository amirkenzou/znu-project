package src.movingobject;

import javax.swing.*;

public class  SnakeFrame extends JFrame {
    SnakeFrame(int sizeS){
       SankePanel sankePanel=new SankePanel(sizeS);
        System.out.println("10: "+sizeS);
        this.add(sankePanel);
        this.setTitle("Moving");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);


    }


}