package ciallo.glasssky.utils;

import javax.swing.*;
import java.awt.*;

public class Lays {

    public static JFrame getFrame(Component a) {
        while (!(a instanceof JFrame)) {
            a = a.getParent();
        }
        return (JFrame) a;
    }

    public static void add(Container a, Component... b) {
        for (Component i : b)
            a.add(i);
    }

    public static void add(Container a, Component b, GridBagConstraints gbc, int x, int y, int w, int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        a.add(b, gbc);
        gbc.weighty = 0;
        gbc.weightx = 0;
    }

    public static void add(Container a, Component b, GridBagConstraints gbc, int x, int y, int w, int h, double wx, double wy) {
        gbc.weightx = wx;
        gbc.weighty = wy;
        add(a, b, gbc, x, y, w, h);
    }

    public static JLabel getEmptyLabel() {
        JLabel label = new JLabel();
        UIUnit.clearWidth(label);
        UIUnit.clearHeight(label);
        return label;
    }

    public static void add(Container a, Component b, MyInsets i, int x, int y, int w, int h,
                           double wx, double wy, String dir) {

        dir = dir.toLowerCase();
        UIUnit.clearHeight(b);
        UIUnit.clearWidth(b);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = wx;
        gbc.weighty = wy;
        gbc.insets.set(i.dy, i.dx, i.dy, i.dx);
        if (dir.contains("l"))
            gbc.insets.left = i.padx;
        if (dir.contains("r"))
            gbc.insets.right = i.padx;
        if (dir.contains("u"))
            gbc.insets.top = i.pady;
        if (dir.contains("d"))
            gbc.insets.bottom = i.pady;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        a.add(b, gbc);
    }
    public static void add(Container a , Component b , MyInsets i  ,int x , int y , int w , int h,
                           double wx , double wy){
        add(a , b , i , x , y , w , h , wx , wy , "");
    }


}
