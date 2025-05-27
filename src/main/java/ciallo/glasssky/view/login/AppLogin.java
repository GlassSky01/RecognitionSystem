package ciallo.glasssky.view.login;

import ciallo.glasssky.controller.AppLoginController;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.model.User;
import ciallo.glasssky.utils.Gbc;
import ciallo.glasssky.utils.LocalUser;
import ciallo.glasssky.utils.UIUnit;
import ciallo.glasssky.view.mainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AppLogin extends JFrame {
    private AppLoginController appLoginController = new AppLoginController();
    private MainFrame mainFrame = new MainFrame();
    public AppLogin(DbLogin dbLogin, Font font1, Font font2){
        this.setProperties(dbLogin );
        this.setContents(font1 , font2);

    }
    public void setProperties(DbLogin dbLogin)
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("登录学分认证管理系统");
        this.setBounds(dbLogin.getBounds());
    }
    private void setContents(Font font1, Font font2) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("登录学分认证系统" , SwingConstants.CENTER);
        JLabel userLabel = new JLabel("用户名:", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("密码:", SwingConstants.CENTER);
        JTextField user = new JTextField();
        JPasswordField password = new JPasswordField();
        password.setEchoChar('*');
        JCheckBox showPassword = new JCheckBox("<html>显示<br/>密码</html>");
        JButton login = new JButton("登录");

        UIUnit.setFont(font1 , title , login );
        UIUnit.setFont(font2 ,  userLabel, passwordLabel, user, password );
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.anchor = GridBagConstraints.NORTH;

        int h = this.getHeight();
        int w = this.getWidth();
        gbc.insets = new Insets(h / 20, 0, h / 20, 0);
        Gbc.add(this, title, gbc,
                0, 0, 3, 1);

        gbc.insets = new Insets(0, w / 50, 0, w / 50);
        Gbc.add(this, userLabel, gbc,
                0, 1, 1, 1);
        Gbc.add(this, passwordLabel, gbc,
                0, 2, 1, 1);
        Gbc.add(this, user, gbc,
                1, 1, 1, 1, 1, 0);
        Gbc.add(this, password, gbc,
                1, 2, 1, 1, 1, 0);
        Gbc.add(this , showPassword  , gbc,
                2 , 2 , 1 , 1);

        gbc.insets = new Insets(h / 20, 0, h / 20, 0);
        Gbc.add(this, login, gbc,
                0, 3, 3, 1, 0, 1);

        showPassword.addActionListener(e->{
            if(showPassword.isSelected())
                password.setEchoChar((char)0);
            else
                password.setEchoChar('*');
        });



        login.addActionListener(e->{
            login(user , password);
        });
    }

    private void login(JTextField user , JTextField password){
        Result result = appLoginController.login(new User( user.getText() , password.getText()));
        if(result.code == 1){
            this.setVisible(false);
            mainFrame.setVisible(true);
            LocalUser.setInfo(user.getText() , password.getText());
            user.setText("");
            password.setText("");
            mainFrame.show((String) result.content);
        }
        else
            JOptionPane.showConfirmDialog(this, result.info , "warning" , JOptionPane.DEFAULT_OPTION);
    }



}
