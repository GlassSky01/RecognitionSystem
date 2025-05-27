package ciallo.glasssky.utils;

import java.awt.*;

public class Lays {

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
}
