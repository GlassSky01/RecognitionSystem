package ciallo.glasssky.view.login;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.DbLoginService;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class DbLogin extends JFrame {
    private AppLogin appLogin;
    private int w, h;


    public DbLogin() {
        setProperties();
        setContents();
        if (login(null, null).code == 0)
            this.setVisible(true);

    }

    public void setProperties() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("登录MYSQL");
        h = (int) (UIUnit.getH() / 2);
        w = h;
        int x = (int) ((UIUnit.getW() - w) / 2);
        int y = (int) ((UIUnit.getH() - h) / 2);
        this.setBounds(x, y, w, h);
    }

    public void setContents() {
        Font font1 = UIUnit.getFont(h, 10);
        Font font2 = UIUnit.getFont(h, 15);
        appLogin = new AppLogin(this, font1, font2);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("登录MYSQL", SwingConstants.CENTER);
        JLabel userLabel = new JLabel("username:", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("password:", SwingConstants.CENTER);
        JTextField user = new JTextField();
        JPasswordField password = new JPasswordField();
        password.setEchoChar('*');
        JCheckBox showPassword = new JCheckBox("<html>显示<br/>密码</html>");
        JButton login = new JButton("登录");

        UIUnit.setFont(font1, title, login);
        UIUnit.setFont(font2, userLabel, passwordLabel, user, password);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(h / 5, 0, h / 20, 0);
        Lays.add(this, title, gbc,
                0, 0, 3, 1);

        gbc.insets = new Insets(0, w / 20, 0, 0);
        Lays.add(this, userLabel, gbc,
                0, 1, 1, 1);
        Lays.add(this, passwordLabel, gbc,
                0, 2, 1, 1);

        gbc.insets = new Insets(0, 0, 0, 0);

        Lays.add(this, user, gbc,
                1, 1, 1, 1, 1, 0);
        Lays.add(this, password, gbc,
                1, 2, 1, 1, 1, 0);
        gbc.insets = new Insets(0, 0, 0, w / 50);

        Lays.add(this , showPassword  , gbc,
                2 , 2 , 1 , 1);

        gbc.fill = GridBagConstraints.NONE ;
        gbc.insets = new Insets(h / 20, 0, 0, 0);
        Lays.add(this, login, gbc,
                0, 3, 3, 1, 0, 1);

        showPassword.addActionListener(e->{
            if(showPassword.isSelected())
                password.setEchoChar((char)0);
            else
                password.setEchoChar('*');
        });

        login.addActionListener(e -> {
            Result result = login(user.getText(), password.getText());
            if (result.code == 0)
                JOptionPane.showConfirmDialog(this, result.info, "warning", JOptionPane.PLAIN_MESSAGE);

        });
    }


    private Result login(String user, String password) {
        File file = new File("./config/dbConfig.properties");
        Result result = Result.failure("未知错误");
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
            if (user == null)
                user = prop.getProperty("db.user");
            if (password == null)
                password = prop.getProperty("db.password");

            result = DbLoginService.login(user, password);
            if (result.code == 1) {
                this.setVisible(false);
                appLogin.setVisible(true);
                OutputStream os = new FileOutputStream(file);
                prop.setProperty("db.user", user);
                prop.setProperty("db.password", password);
                prop.store(os, "database config");
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
