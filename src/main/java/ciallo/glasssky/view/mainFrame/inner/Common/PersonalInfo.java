package ciallo.glasssky.view.mainFrame.inner.Common;

import ciallo.glasssky.controller.GetPersonalInfoController;
import ciallo.glasssky.dao.SaveInfoDao;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.LocalUser;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PersonalInfo extends JPanel {
    protected JPanel publicInfo = new JPanel(new GridBagLayout());
    protected JPanel privateInfo = new JPanel(new GridBagLayout());
    protected Font font;
    protected int padx;
    protected int pady;
    protected int dx;
    protected int dy;
    protected ArrayList<JTextField> fields = new ArrayList<>();
    protected ArrayList<String> fieldsName = new ArrayList<>();
    protected ArrayList<String> fieldsType = new ArrayList<>();

    public PersonalInfo(int w, int h) {
        setProperties();
        setContents(w, h);
    }

    public void setProperties() {
        this.setLayout(new BorderLayout());
    }

    public void setContents(int w, int h) {
        Font fontTitle = UIUnit.getFont(h, 10);
        JLabel title = new JLabel("个人信息设置", SwingConstants.CENTER);
        title.setFont(fontTitle);

        this.font = UIUnit.getFont(h, 25);
        dx = w / 50;
        dy = h / 50;
        padx = dx * 3;
        pady = dy * 3;

        setPublic();
        setPrivate();
        this.add(title, BorderLayout.NORTH);
        this.add(publicInfo, BorderLayout.CENTER);
        this.add(privateInfo, BorderLayout.SOUTH);

    }

    private void setPublic() {


        JLabel label1 = new JLabel("账号: ", SwingConstants.CENTER);
        JTextField username = new JTextField("测试");
        JLabel label2 = new JLabel("姓名: ", SwingConstants.CENTER);
        JTextField name = new JTextField("测试");
        JLabel label3 = new JLabel("电话号: ", SwingConstants.CENTER);
        JTextField phoneNumber = new JTextField();
        JLabel label4 = new JLabel("邮箱: ", SwingConstants.CENTER);
        JTextField email = new JTextField();
        JButton saveInfo = new JButton("保存设置");

        Collections.addAll(fields  , username , name , phoneNumber , email);
        Collections.addAll(fieldsName , "username" , "name" , "phoneNumber" , "email");
        Collections.addAll(fieldsType , String.class.toString() , String.class.toString(),
                String.class.toString() , String.class.toString());

        saveInfo.addActionListener(e -> {
            save(phoneNumber.getText(), email.getText());
        });


        username.setEditable(false);
        name.setEditable(false);
        UIUnit.setFont(font,
                label1, username, label2, name,
                label3, phoneNumber, label4, email,
                saveInfo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        //左
        gbc.insets = new Insets(pady, padx, 0, dx);
        Lays.add(publicInfo, label1, gbc,
                0, 0, 1, 1);

        gbc.insets = new Insets(dy, padx, 0, dx);

        Lays.add(publicInfo, label2, gbc,
                0, 1, 1, 1);
        Lays.add(publicInfo, label3, gbc,
                0, 2, 1, 1);
        Lays.add(publicInfo, label4, gbc,
                0, 3, 1, 1);

        //右
        gbc.insets = new Insets(pady, 0, 0, padx);
        Lays.add(publicInfo, username, gbc,
                1, 0, 1, 1, 1, 0);


        gbc.insets = new Insets(dy, 0, 0, padx);
        Lays.add(publicInfo, name, gbc,
                1, 1, 1, 1, 1, 0);
        Lays.add(publicInfo, phoneNumber, gbc,
                1, 2, 1, 1, 1, 0);
        Lays.add(publicInfo, email, gbc,
                1, 3, 1, 1, 1, 0);

        //下
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(dy, 0, 0, 0);
        Lays.add(publicInfo, saveInfo, gbc,
                0, 10, 2, 1, 0, 1);
    }

    private void setPrivate() {
        JLabel label1 = new JLabel("旧密码: ");
        JLabel label2 = new JLabel("新密码: ");
        JTextField oldPassword = new JTextField();
        JTextField newPassword = new JTextField();
        JButton resetPassword = new JButton("修改密码");

        UIUnit.setFont(font, label1, oldPassword, label2, newPassword,
                resetPassword);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.insets = new Insets(pady, padx, 0, dx);
        Lays.add(privateInfo, label1, gbc,
                0, 0, 1, 1);


        gbc.insets = new Insets(dy, padx, 0, dx);
        Lays.add(privateInfo, label2, gbc,
                0, 1, 1, 1);

        gbc.insets = new Insets(pady, 0, 0, padx);
        Lays.add(privateInfo, oldPassword, gbc,
                1, 0, 1, 1, 1, 0);


        gbc.insets = new Insets(dy, 0, 0, padx);
        Lays.add(privateInfo, newPassword, gbc,
                1, 1, 1, 1, 1, 0);

        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(dy, 0, pady, 0);
        Lays.add(privateInfo, resetPassword, gbc,
                0, 2, 2, 1);
    }

    private SaveInfoDao saveInfoDao = new SaveInfoDao();

    private void save(String phoneNum, String email) {
        if (saveInfoDao.save(phoneNum, email).code == 0)
            JOptionPane.showConfirmDialog(this, "保存失败", "warning", JOptionPane.DEFAULT_OPTION);
        else
            JOptionPane.showConfirmDialog(this, "保存成功", "accept", JOptionPane.DEFAULT_OPTION);

    }

    public void init(){
        fields.get(0).setText(LocalUser.username);
        Result result = GetPersonalInfoController.get( fieldsName , fieldsType);
        Object[] texts = (Object[]) result.content;
        for(int i = 0 ; i < texts.length ; i ++)
        {
            fields.get(i + 1).setText(texts[i].toString());
        }
    }
}
