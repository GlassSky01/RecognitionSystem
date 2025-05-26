package ciallo.glasssky.view.login;

import ciallo.glasssky.controller.DbLoginController;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.Dbs;
import ciallo.glasssky.utils.Gbc;
import ciallo.glasssky.utils.SizeUnit;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class DbLogin extends JFrame {
    private AppLogin appLogin;
    private int w, h;

    DbLoginController dbLoginController = new DbLoginController();
    public DbLogin() {
        setProperties();
        setContent();
        this.setVisible(true);
    }

    public void setProperties() {
        this.setTitle("登录mysql");
        h = (int) (SizeUnit.getH() / 2);
        w = h;
        int x = (int) ((SizeUnit.getW() - w) / 2);
        int y = (int) ((SizeUnit.getH() - h) / 2);
        this.setBounds(x, y, w, h);
        appLogin = new AppLogin(this);
        login(null , null);
    }

    public void setContent() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font font1 = SizeUnit.getFont(h , 10);
        Font font2 = SizeUnit.getFont(h  , 15);
        JLabel title = new JLabel("登录数据库" , SwingConstants.CENTER);
        JLabel userLabel = new JLabel("user:", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("password:", SwingConstants.CENTER);
        JTextField user = new JTextField();
        JTextField password = new JTextField();
        JButton login = new JButton("登录");

        SizeUnit.setFont(font1 , title , login);
        SizeUnit.setFont(font2 ,  userLabel, passwordLabel, user, password );
        gbc.fill = GridBagConstraints.HORIZONTAL ;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(h / 20 , 0, h / 20 , 0);
        Gbc.add(this , title , gbc , 0 , 0 , 2 , 1);

        gbc.insets = new Insets(0 , w / 50 , 0, w / 50);
        Gbc.add(this , userLabel , gbc , 0 , 1 , 1 , 1);
        Gbc.add(this , passwordLabel , gbc , 0 , 2 , 1 , 1 );
        Gbc.add(this , user , gbc , 1 , 1 , 1 , 1 , 1 , 0);
        Gbc.add(this , password , gbc , 1 , 2 , 1 , 1 ,  1 , 0);

        gbc.insets = new Insets(h / 20 , 0, h / 20 , 0);
        Gbc.add(this , login , gbc , 0 , 3 , 2 , 1 ,  0 , 1);
        login.addActionListener(e->{
            if(!login(user.getText() , password.getText()))
                JOptionPane.showConfirmDialog(this , "用户名或密码错误" , "warning" , JOptionPane.PLAIN_MESSAGE);

        });
    }


    private boolean login(String user , String password) {
        File file = new File("./config/dbConfig.properties");
        if (!file.exists()) {
            try {
                new File("./config").mkdir();
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            InputStream is = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(is);
            if(user == null)
                user = prop.getProperty("db.user");
            if(password == null)
                password = prop.getProperty("db.password");
            if(dbLoginController.login(user ,password).code == 1)
            {
                this.setVisible(false);
                appLogin.setVisible(true);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
