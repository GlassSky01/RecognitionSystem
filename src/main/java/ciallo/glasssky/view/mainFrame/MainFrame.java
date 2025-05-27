package ciallo.glasssky.view.mainFrame;

import ciallo.glasssky.utils.LocalUser;
import ciallo.glasssky.utils.UIUnit;
import ciallo.glasssky.view.login.AppLogin;
import ciallo.glasssky.view.mainFrame.inner.Administrators.AdminNavigation;
import ciallo.glasssky.view.mainFrame.inner.Administrators.contents.Statistics;
import ciallo.glasssky.view.mainFrame.inner.Administrators.contents.StudentInfo;
import ciallo.glasssky.view.mainFrame.inner.Administrators.contents.TeacherInfo;
import ciallo.glasssky.view.mainFrame.inner.Common.DefaultPage;
import ciallo.glasssky.view.mainFrame.inner.Common.Navigation;
import ciallo.glasssky.view.mainFrame.inner.Students.StudentNavigation;
import ciallo.glasssky.view.mainFrame.inner.Students.contents.ApplyForCredits;
import ciallo.glasssky.view.mainFrame.inner.Students.contents.CheckStd;
import ciallo.glasssky.view.mainFrame.inner.Students.contents.StuPersonalInfo;
import ciallo.glasssky.view.mainFrame.inner.Teachers.TeacherNavigation;
import ciallo.glasssky.view.mainFrame.inner.Teachers.contents.CheckDetails;
import ciallo.glasssky.view.mainFrame.inner.Teachers.contents.CreditVerification;
import ciallo.glasssky.view.mainFrame.inner.Teachers.contents.TeaPersonalInfo;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private int w, h;
    private JPanel contentCard = new JPanel(new CardLayout());
    private JPanel navigationCard = new JPanel(new CardLayout());
    private AppLogin appLogin;
    public AppLogin getAppLogin(){
        return appLogin;
    }
    public MainFrame(AppLogin appLogin) {
        this.appLogin = appLogin;
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
                        new String[]{"查看认证标准" , "申请学分" , "个人信息修改"} ,
                        new Component[]{new CheckStd(w , h) , new ApplyForCredits(w , h) , new StuPersonalInfo(w , h)}),
                new TeacherNavigation(h , contentCard ,
                        new String[]{"审核学分" , "查看学分明细" , "个人信息修改"} ,
                        new Component[]{new CreditVerification(w , h) , new CheckDetails(w , h) , new TeaPersonalInfo(w , h)}),
                new AdminNavigation(h , contentCard ,
                        new String[]{"教师信息管理" , "学生信息管理" , "统计"} ,
                        new Component[]{new TeacherInfo(w , h) , new StudentInfo(w , h) , new Statistics(w , h)})
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
        ((CardLayout)contentCard.getLayout()).show(contentCard , "default");
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
