package ciallo.glasssky.view.login;

import javax.swing.*;
import java.awt.*;

public class AppLogin extends JFrame {
    private DbLogin dbLogin ;
    public AppLogin(DbLogin dbLogin){
        this.dbLogin = dbLogin;
        this.setBounds(dbLogin.getBounds());
        this.setBackground(Color.BLACK);
    }
}
