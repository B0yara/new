package com.company;

import javax.swing.*;
import java.awt.*;


public abstract class Display {
    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;
    private static DataProcess data = new DataProcess();
    public static void create (){
        if (created)
                return;
        window =new JFrame("Graph");



        content = new Canvas() {
            public void paint(Graphics g) {
                super.paint(g);
               render(g);

            }
        };

        content.setBounds(5,100,635,740);
        window.setResizable(false);
        window.add(content);

        window.getContentPane().setSize(625,710);
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setLayout(null);


    }


    public static void render(){
        content.repaint();
    }
    private static void render(Graphics g){
        content.setBackground(Color.white);
        g.setColor(Color.RED);
        g.drawPolyline(data.getCordX(),data.getCordY(),data.getN());
        g.setColor(Color.black);
        g.drawLine(0,300,600,300);
        g.drawLine(300,0,300,600);

        Float step = (data.getMax() - data.getMin()) / 20;
        float s = data.getMin();

        for(int i=0;i<=600;i+=30)

        {
            g.drawLine(i,295,i,305);
            g.drawLine(295,i,305,i);
            if (i!=300){
                g.drawString(Float.toString((float) (Math.round(s*100.00)/100.00)),i-5,320);


                g.drawString(Float.toString((float) (Math.round(s*(-100.00))/100.00)),310,i+5);}
            s+=step;
        }
    }


}

