package ciallo.glasssky.view.mainFrame.inner.Administrators.contents;

import ciallo.glasssky.controller.GetTeachersController;
import ciallo.glasssky.controller.InsertTeacherController;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class TeacherInfo extends JPanel {
    public TeacherInfo(int w, int h) {
        setProperties();
        setContents(w, h);
    }

    private void setProperties() {
        this.setLayout(new BorderLayout());
    }

    JTable table ;
    private void setContents(int w, int h) {
        Font fontTitle = UIUnit.getFont(h, 10);
        JLabel title = new JLabel("学分申请", SwingConstants.CENTER);
        title.setFont(fontTitle);

        int padx = w / 30;
        int pady = h / 30;
        int dx = padx / 5;
        int dy = pady / 5;
        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        DefaultTableModel model = new DefaultTableModel(new Object[][]{} ,
                new String[]{ "账号" , "姓名" , "电话" , "邮箱" , "密码"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        JTableHeader tableHeader = table.getTableHeader();
        JTextField name = new JTextField();
        JTextField phoneNumber = new JTextField();
        JTextField email = new JTextField();
        JButton remove = new JButton("删除教师");
        JButton add = new JButton("添加教师");

        Font font = UIUnit.getFont(h, 30);
        UIUnit.setFont(font , table , tableHeader , name , phoneNumber , email , remove , add);
        table.setRowHeight((int) (font.getSize() * 1.5));
        UIUnit.clearHeight(pane , name , phoneNumber , email , remove , add);
        UIUnit.clearWidth(pane , name , phoneNumber , email , remove , add);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.set(pady , padx , dy , padx);
        Lays.add(center , pane , gbc ,
                0 , 0 , 5 , 1 , 1 , 7);

        gbc.insets.set(dy , padx , dy , dx);
        Lays.add(center , Lays.getEmptyLabel() , gbc ,
                0 , 1 , 1 , 1 , 1 , 1);
        gbc.insets.set(dy , dx , dy , dx);
        Lays.add(center , name , gbc ,
                1 , 1 , 1 , 1 , 1 , 1);
        Lays.add(center , phoneNumber , gbc ,
                2 , 1 , 1 , 1 , 1 , 1);
        Lays.add(center , email , gbc ,
                3 , 1 , 1 , 1 , 1 , 1);
        gbc.insets.set(dy , dx , dy , padx);
        Lays.add(center , Lays.getEmptyLabel() , gbc ,
                4 , 1 , 1 , 1 , 1 , 1);

        gbc.insets.set(dy , padx , pady , dx);
        Lays.add(center , Lays.getEmptyLabel() , gbc ,
                0 , 2 , 1 , 1 , 1 , 1);
        gbc.insets.set(dy , dx , pady , dx);
        Lays.add(center , remove , gbc ,
                1 , 2 , 1 , 1 , 1 , 1);
        Lays.add(center , Lays.getEmptyLabel() , gbc ,
                2 , 2 , 1 , 1 , 1 , 1);
        Lays.add(center , add , gbc ,
                3 , 2 , 1 , 1 , 1 , 1);
        gbc.insets.set(dy , dx , pady , padx);
        Lays.add(center , Lays.getEmptyLabel() , gbc ,
                4 , 2 , 1 , 1 , 1 , 1);

        addRemove(remove);
        addAdd(add , name , phoneNumber , email);

        this.add(title, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    public void init(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Result result = GetTeachersController.get();
        if(result.code == 0)
        {
            model.addRow(new Object[]{"查询失败"});
            return;
        }
        ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;
        for(Object[] objects : arr)
            model.addRow(objects);

    }
    private void addRemove(JButton remove){
        remove.addActionListener(e->{
            int[] rows = table.getSelectedRows();
            for(int i = rows.length - 1 ; i >= 0 ; i --)
            {
                int row = rows[i];

            }
        });
    }
    private void addAdd(JButton add, JTextField name, JTextField phoneNumber, JTextField email){
        add.addActionListener(e->{
            Result result = InsertTeacherController.insert(name.getText() , phoneNumber.getText() , email.getText());
            if(result.code == 0 )
                JOptionPane.showConfirmDialog(this , result.info , "warning" , JOptionPane.DEFAULT_OPTION);
            init();
        });
    }
}
