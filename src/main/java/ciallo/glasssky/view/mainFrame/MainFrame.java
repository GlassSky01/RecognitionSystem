package ciallo.glasssky.view.mainFrame;

import ciallo.glasssky.utils.LocalUser;
import ciallo.glasssky.utils.UIUnit;
import ciallo.glasssky.view.mainFrame.inner.Administrators.AdminNavigation;
import ciallo.glasssky.view.mainFrame.inner.Administrators.contents.Statistics;
import ciallo.glasssky.view.mainFrame.inner.Administrators.contents.StudentInfo;
import ciallo.glasssky.view.mainFrame.inner.Administrators.contents.TeacherInfo;
import ciallo.glasssky.view.mainFrame.inner.DefaultPage;
import ciallo.glasssky.view.mainFrame.inner.Navigation;
import ciallo.glasssky.view.mainFrame.inner.Students.StudentNavigation;
import ciallo.glasssky.view.mainFrame.inner.Teachers.TeacherNavigation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private int w, h;
    private JPanel contentCard = new JPanel(new CardLayout());
    private JPanel navigationCard = new JPanel(new CardLayout());

    public MainFrame() {
        setProperties();
        setContents();
    }

    private void setProperties() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w = (int) (UIUnit.getW() * 3 / 4);
        h = (int) (UIUnit.getH() * 3 / 4);
        int x = (int) ((UIUnit.getW() - w) / 2);
        int y = (int) ((UIUnit.getH() - h) / 2);
        this.setBounds(x, y, w, h);
        this.setLayout(new BorderLayout());

    }

    private void setContents() {
        Navigation[] navigation = new Navigation[]{
                new StudentNavigation(h , contentCard,
                        new String[]{} ,
                        new Component[]{}),
                new TeacherNavigation(h , contentCard ,
                        new String[]{} ,
                        new Component[]{}),
                new AdminNavigation(h , contentCard ,
                        new String[]{"教师信息管理" , "学生信息管理" , "统计"} ,
                        new Component[]{new TeacherInfo() , new StudentInfo() , new Statistics()})
        };


        contentCard.add(new DefaultPage(h) , "default");
        for(Navigation n : navigation)
        {
            navigationCard.add(n , n.getClass().toString());
            for(Component comp : n.getAllPages())
            {
                contentCard.add(comp , comp.getClass().toString());
            }
        }

        this.add(contentCard , BorderLayout.CENTER);
        this.add(navigationCard , BorderLayout.EAST);
    }

    public void show(String user) {

        DefaultPage defaultPage = (DefaultPage) contentCard.getComponent(0);
        ((CardLayout)contentCard.getLayout()).show(contentCard , defaultPage.getClass().toString());
        CardLayout cardLayout = (CardLayout) navigationCard.getLayout();

        if ("Student".equals(user)) {
            cardLayout.show(navigationCard , StudentNavigation.class.toString());

            //学生欢迎语
            defaultPage.setText("<html>欢迎登录,<br/>"+ LocalUser.username + "同学");
        } else if ("Teacher".equals(user)) {
            cardLayout.show(navigationCard , TeacherNavigation.class.toString());

            //教师欢迎语
            defaultPage.setText("<html>欢迎登录,<br/>教师" + LocalUser.username);
        } else if ("Administrator".equals(user)) {
            cardLayout.show(navigationCard , AdminNavigation.class.toString());


            //管理员欢迎语
            defaultPage.setText("<html>欢迎登录,<br/>管理员" + LocalUser.username);
        } else
            System.out.println("角色出错");
    }
}
