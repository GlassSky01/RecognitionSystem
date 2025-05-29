package ciallo.glasssky.view.mainFrame.inner.Students.contents;

import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.UIUnit;
import ciallo.glasssky.view.mainFrame.inner.Common.PersonalInfo;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Collections;

public class StuPersonalInfo extends PersonalInfo {
    public StuPersonalInfo(int w, int h) {
        super(w , h);
        setContents();
    }

    JTextField tutor;
    private void setContents(){
        JLabel label00 = new JLabel("年级: " , SwingConstants.CENTER);
        JLabel label01 = new JLabel("学院: " , SwingConstants.CENTER);
        JLabel label1 = new JLabel("专业: " , SwingConstants.CENTER);
        JLabel label2 = new JLabel("班级: " , SwingConstants.CENTER);
        JLabel label3 = new JLabel("导师: " , SwingConstants.CENTER);



        JTextField grade = new JTextField();
        JTextField academy = new JTextField();
        JTextField professional = new JTextField();
        JTextField clazz = new JTextField();
        tutor = new JTextField();

        Collections.addAll(fields , grade ,academy ,  professional , clazz , tutor);
        Collections.addAll(fieldsName , "grade", "academy" , "professional" , "class" , "tutorId");
        Collections.addAll(fieldsType ,Integer.class , String.class ,  String.class , Integer.class, Integer.class);


        grade.setEditable(false);
        academy.setEditable(false);
        professional.setEditable(false);
        clazz.setEditable(false);
        tutor.setEditable(false);

        UIUnit.setFont(font , label1 , label2 , label3 , professional , clazz , tutor , label00 , label01  , grade , academy);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(dy, padx, 0, dx);
        Lays.add(publicInfo, label00, gbc,
                0, 4, 1, 1);
        Lays.add(publicInfo, label01, gbc,
                0, 5, 1, 1);
        Lays.add(publicInfo, label1, gbc,
                0, 6, 1, 1);
        Lays.add(publicInfo, label2, gbc,
                0, 7, 1, 1);
        Lays.add(publicInfo, label3, gbc,
                0, 8, 1, 1);

        gbc.insets = new Insets(dy, 0, 0, padx);
        Lays.add(publicInfo, grade, gbc,
                1, 4, 1, 1, 1, 0);
        Lays.add(publicInfo, academy, gbc,
                1, 5, 1, 1, 1, 0);
        Lays.add(publicInfo, professional, gbc,
                1, 6, 1, 1, 1, 0);
        Lays.add(publicInfo, clazz, gbc,
                1, 7, 1, 1, 1, 0);
        Lays.add(publicInfo, tutor, gbc,
                1, 8, 1, 1, 1, 0);
    }
    public void init(){
        super.init();
        try {
            tutor.setText(DbOperators.executeQuery("select name from UsersInformation where id = ? ;",
                    new Class<?>[]{String.class} , Integer.parseInt(tutor.getText())).get(0)[0].toString());
        } catch (Exception e) {
            System.out.println("查询导师失败");
        }
    }
}
