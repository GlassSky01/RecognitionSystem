package ciallo.glasssky.view.login;

import ciallo.glasssky.controller.AppLoginController;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.model.User;
import ciallo.glasssky.utils.Gbc;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import java.awt.*;

public class AppLogin extends JFrame {
    private DbLogin dbLogin ;
    private AppLoginController appLoginController = new AppLoginController();
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

        JLabel title = new JLabel("登录系统" , SwingConstants.CENTER);
        JComboBox<String> type = new JComboBox<>(new String[]{"------角色------" , "学生" , "教师" , "管理员"});
        JLabel userLabel = new JLabel("用户名:", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("密码:", SwingConstants.CENTER);
        JTextField user = new JTextField();
        JTextField password = new JTextField();
        JButton login = new JButton("登录");

        type.setRenderer(UIUnit.getCenter());
        UIUnit.setFont(font1 , title , login );
        UIUnit.setFont(font2 ,  userLabel, passwordLabel, user, password , type);
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.anchor = GridBagConstraints.NORTH;

        int h = this.getHeight();
        int w = this.getWidth();

        gbc.insets = new Insets(h / 20 , 0, h / 20 , 0);
        Gbc.add(this , title , gbc , 0 , 0 , 2 , 1);

        gbc.insets = new Insets(0 , w / 50 , 0, w / 50);
        Gbc.add(this , type , gbc , 0 , 1 , 2 , 1);
        Gbc.add(this , userLabel , gbc , 0 , 2 , 1 , 1);
        Gbc.add(this , passwordLabel , gbc , 0 , 3, 1 , 1 );
        Gbc.add(this , user , gbc , 1 , 2 , 1 , 1 , 1 , 0);
        Gbc.add(this , password , gbc , 1 , 3 , 1 , 1 ,  1 , 0);

        gbc.insets = new Insets(h / 20 , 0, h / 20 , 0);
        Gbc.add(this , login , gbc , 0 , 4 , 2 , 1 ,  0 , 1);

        login.addActionListener(e->{
            Result result = appLoginController.login(new User(type.getSelectedItem(), user.getText() , password.getText()));
            if(result.code == 1)
                System.out.println("登录成功");
            else
                JOptionPane.showConfirmDialog(this, result.info , "warning" , JOptionPane.DEFAULT_OPTION);
        });
    }



}
