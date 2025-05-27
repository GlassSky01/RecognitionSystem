package ciallo.glasssky.view.mainFrame.inner.Students.contents;

import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import java.awt.*;

public class CheckStd  extends JPanel {
    public CheckStd(int w, int h) {
        setProperties();
        setContents(w , h);
    }
    private void setProperties(){
        this.setLayout(new BorderLayout());

    }
    private void setContents(int w ,int h){
        Font fontTitle = UIUnit.getFont(h , 10);
        JLabel title = new JLabel("认证标准" , SwingConstants.CENTER);
        title.setFont(fontTitle);
        JTextArea area = new JTextArea("这是认证标准");
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        Font font = UIUnit.getFont(h , 25);
        area.setFont(font);
        JScrollPane pane = new JScrollPane(area);
        this.add(title , BorderLayout.NORTH);
        this.add(pane , BorderLayout.CENTER);
    }
}
