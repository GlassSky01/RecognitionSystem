package ciallo.glasssky.view.mainFrame.inner.Administrators.contents;

import ciallo.glasssky.model.MyContainer;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.GetTeachersService;
import ciallo.glasssky.service.InsertTeacherService;
import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.MyInsets;
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
        JLabel title = new JLabel("教师信息管理", SwingConstants.CENTER);
        title.setFont(fontTitle);

        JPanel center = new JPanel(new GridBagLayout());

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
        tableHeader.setReorderingAllowed(false);

        MyInsets i = new MyInsets(w / 30 , h / 30 , 0 , h / 100);
        MyContainer centerC = new MyContainer(center , i);
        //第一行
        centerC.add(pane ,
                 0 , 0 , 5 , 1 , 1 , 7 ,"LRU" );

        //第二行
        centerC.add(Lays.getEmptyLabel() ,
                0 , 1 , 1 , 1 , 1 , 1, "L" );

        centerC.add(name ,
                1 , 1 , 1 , 1 , 1 , 1 );

        centerC.add(phoneNumber ,
                2 , 1 , 1 , 1 , 1 , 1 );

        centerC.add(email ,
                3 , 1 , 1 , 1 , 1 , 1);

        centerC.add(Lays.getEmptyLabel() ,
                4 , 1 , 1 , 1 , 1 , 1, "R" );

        i.setDx(w / 100);
        //第三行
        centerC.add(Lays.getEmptyLabel() ,
                0 , 2 , 1 , 1 , 1 , 1 , "LD");

        centerC.add(add ,
                1 , 2 , 1 , 1 , 1 , 1 , "D");

        centerC.add(Lays.getEmptyLabel() ,
                2 , 2 , 1 , 1 , 1 , 1, "D");

        centerC.add( remove,
                3 , 2 , 1 , 1 , 1 , 1, "D");

        centerC.add(Lays.getEmptyLabel() ,
                4 , 2 , 1 , 1 , 1 , 1, "RD");

        addRemove(remove);
        addAdd(add , name , phoneNumber , email);

        this.add(title, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    public void init(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Result result = GetTeachersService.get();
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
            if(JOptionPane.showConfirmDialog(this , "是否删除所有选中教师?" , "确认" , JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                return;

            int[] rows = table.getSelectedRows();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for(int i = rows.length - 1 ; i >= 0 ; i --)
            {
                int row = rows[i];
                String username = table.getValueAt(row , 0).toString();
                try {
                    DbOperators.execute("delete from users where username = ?;" , username);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                model.removeRow(row);
            }

            JOptionPane.showConfirmDialog(this,  "已删除" , "success" , JOptionPane.DEFAULT_OPTION);
        });
    }
    private void addAdd(JButton add, JTextField name, JTextField phoneNumber, JTextField email){
        add.addActionListener(e->{
            Result result = InsertTeacherService.insert(name.getText() , phoneNumber.getText() , email.getText());
            if(result.code == 0 )
                JOptionPane.showConfirmDialog(this , result.info , "warning" , JOptionPane.DEFAULT_OPTION);
            init();
        });
    }
}
