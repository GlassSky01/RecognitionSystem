package ciallo.glasssky.view.mainFrame.inner;

import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import java.awt.*;

public class DefaultPage extends JPanel {
    JLabel welcome = new JLabel("" , SwingConstants.CENTER);
    public DefaultPage(int h){
        this.setLayout(new GridLayout(1 , 1));
        welcome.setFont(UIUnit.getFont(h , 10));
        this.add(welcome);
    }

    public void setText(String s){
        welcome.setText(s);
    }
}
