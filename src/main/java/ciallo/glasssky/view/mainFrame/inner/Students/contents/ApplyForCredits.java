package ciallo.glasssky.view.mainFrame.inner.Students.contents;

import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import java.awt.*;

public class ApplyForCredits  extends JPanel {

    private Font font ;
    private int dx;
    private int dy;
    private int padx;
    private int pady;
    public ApplyForCredits(int w, int h) {
        setProperties();
        setContents(w , h);
    }
    private void setProperties(){
        this.setLayout(new BorderLayout());
    }
    private void setContents(int w , int h){
        Font fontTitle = UIUnit.getFont(h, 10);
        JLabel title = new JLabel("个人信息设置", SwingConstants.CENTER);
        title.setFont(fontTitle);

        this.font = UIUnit.getFont(h, 30);
        dx = w / 50;
        dy = h / 50;
        padx = dx * 3;
        pady = dy * 3;
        JPanel center = new JPanel();
        setCenter(center);

        this.add(title, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    private void setCenter(JPanel center){

    }
}
