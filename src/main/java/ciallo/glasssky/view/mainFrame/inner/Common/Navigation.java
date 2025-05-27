package ciallo.glasssky.view.mainFrame.inner.Common;

import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import java.awt.*;

public class Navigation extends JPanel {


    protected Component[] allPages = new Component[0];

    public Navigation(int h, JPanel contentCard , String[] buttonTexts ,Component[] allPages){
        setProperties();
        setContents(h , contentCard , buttonTexts , allPages);
    }

    public void setProperties(){
        this.setLayout(new BorderLayout());
    }
    public void setContents(int h, JPanel contentCard , String[] buttonTexts ,Component[] allPages){
        Font font = UIUnit.getFont(h , 20);
        this.allPages = allPages;
        JButton[] buttons = new JButton[buttonTexts.length];
        JPanel panel = new JPanel(new GridLayout(allPages.length , 1));
        this.add(panel , BorderLayout.NORTH);

        for(int i = 0 ; i < allPages.length ; i ++)
        {
            buttons[i] = new JButton(buttonTexts[i]);
            buttons[i].setFont(font);
            panel.add(buttons[i]);
            CardLayout cardLayout = (CardLayout) contentCard.getLayout();
            int finalI = i;
            buttons[i].addActionListener(e->{
                try{
                    allPages[finalI].getClass().getMethod("init").invoke(allPages[finalI]);
                }catch (Exception ex){
                    System.out.println("没有这个方法");
                }
                cardLayout.show(contentCard , allPages[finalI].getClass().toString());
            });
        }
    }



    public Component[] getAllPages(){
        return allPages;
    }

}
