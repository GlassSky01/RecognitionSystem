package ciallo.glasssky.view.mainFrame.inner.Common;

import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import java.awt.*;

public class PersonalInfo extends JPanel {
    JPanel publicInfo = new JPanel();
    JPanel privateInfo = new JPanel();
    public PersonalInfo(int w,  int h){
        this.setLayout(new BorderLayout());
        setProperties();
        setContents(w, h );
    }
    public void setProperties(){

    }
    public void setContents(int w , int h){
        Font font = UIUnit.getFont(h , 10);
        JLabel title = new JLabel("个人信息设置", SwingConstants.CENTER);
        title.setFont(font);
        setPublic(w , h);
        setPrivate(w  ,h);
        this.add(title , BorderLayout.NORTH);
        this.add(publicInfo , BorderLayout.CENTER);
        this.add(privateInfo , BorderLayout.SOUTH);

    }

    private void setPublic(int w, int h) {
        Font font = UIUnit.getFont(h , 15);
        JLabel usernameL = new JLabel("账号");
        JLabel usernameR = new JLabel();
        UIUnit.setFont(font , usernameL , usernameR);
        Lays.add(publicInfo , usernameL , usernameR);

    }

    private void setPrivate(int w, int h) {
        Font font = UIUnit.getFont(h , 15);
        JLabel passwordL = new JLabel("密码");

    }
}
