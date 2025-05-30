package ciallo.glasssky.utils;

import javax.swing.*;
import java.awt.*;

public class Lays {

    public static JFrame getFrame(Component a){
        while(!(a instanceof JFrame))
        {
            a = a.getParent();
        }
        return (JFrame) a;
    }

    public static void add(Container a , Component... b){
        for(Component i : b)
            a.add(i);
    }
    public static void add(Container a , Component b , GridBagConstraints gbc , int x , int y , int w , int h){
        gbc.gridx = x;
        gbc.gridy = y ;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        a.add(b , gbc);
        gbc.weighty = 0;
        gbc.weightx = 0;
    }
    public static void add(Container a , Component b , GridBagConstraints gbc , int x , int y , int w , int h , double wx , double wy){
        gbc.weightx = wx;
        gbc.weighty = wy;
        add(a , b , gbc , x , y , w , h);
    }

    public static JLabel getRowLabel(){
        JLabel label = new JLabel();
        UIUnit.clearWidth(label);
        return label;
    }
    public static JLabel getColumnLabel(){
        JLabel label = new JLabel();
        UIUnit.clearHeight(label);
        return label;
    }

}
