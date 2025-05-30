package ciallo.glasssky.view.mainFrame.inner.Administrators.contents;

import ciallo.glasssky.dao.GetNoTutorStudentDao;
import ciallo.glasssky.dao.GetStudentInfoDao;
import ciallo.glasssky.dao.GetTutorDao;
import ciallo.glasssky.dao.UpdateTutorDao;
import ciallo.glasssky.model.MyContainer;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.InsertStudentService;
import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.MyInsets;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StudentInfo  extends JPanel {
    public StudentInfo(int w, int h) {
        setProperties();
        setContents(w, h);
    }
    private void setProperties() {
        this.setLayout(new BorderLayout());
    }

    JTable table ;
    String[] academyBox = new String[]{"计算机学院" , "软件学院"};
    String[] professionalBox = new String[]{"计算机科学与技术" , "软件工程" , "智能科学与技术" };
    Integer[] gradeBox = new Integer[]{1 , 2 ,3 ,4};
    Integer[] classBox = new Integer[]{1, 2 ,3 ,4 ,5 ,6 ,7 , 8};
    private void setContents(int w, int h) {
        Font fontTitle = UIUnit.getFont(h, 10);
        JLabel title = new JLabel("学分申请", SwingConstants.CENTER);
        title.setFont(fontTitle);

        JPanel center = new JPanel(new GridBagLayout());
        DefaultTableModel model = new DefaultTableModel(new Object[][]{} ,
                new String[]{ "账号" , "姓名" , "学院" , "专业" , "年级" , "班级" , "导师"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        JTableHeader tableHeader = table.getTableHeader();
        JTextField name = new JTextField();
        JComboBox<String> academy = new JComboBox<>(academyBox);
        JComboBox<String> professional = new JComboBox<>(professionalBox);
        JComboBox<Integer> grade = new JComboBox<>(gradeBox);
        JComboBox<Integer> clazz = new JComboBox<>(classBox);

        JButton remove = new JButton("删除学生");
        JButton add = new JButton("添加学生");
        JButton assign = new JButton("分配导师");

        Font font = UIUnit.getFont(h, 50);
        UIUnit.setFont(font , table , tableHeader , name ,
                academy , professional , remove , add , assign ,
                grade , clazz);
        table.setRowHeight((int) (font.getSize() * 1.5));
        tableHeader.setReorderingAllowed(false);

        MyInsets i = new MyInsets(w / 50 , h / 50 , 0 , h / 100);
        MyContainer centerC = new MyContainer(center , i);

        //第一行
        centerC.add(pane ,
                0 , 0 , 7 , 7 , 1 , 13 , "LUR");

        //第二行
        centerC.add(Lays.getEmptyLabel(),
                0 , 7 , 1 , 1 , 1 , 1 , "L");
        centerC.add(name ,
                1 , 7 , 1 , 1 , 1 , 1 );
        centerC.add(academy ,
                2 , 7 , 1 , 1 , 1 , 1);
        centerC.add(professional ,
                3 , 7 , 1 , 1 , 1 , 1);
        centerC.add(grade ,
                4 , 7 , 1 , 1 , 1 , 1);
        centerC.add(clazz ,
                5 , 7 , 1 , 1 , 1 , 1);
        centerC.add(Lays.getEmptyLabel() ,
                6 , 7 , 1 , 1 , 1 , 1 , "R");

        i.setDx(w / 100);
        //第三行
        centerC.add( Lays.getEmptyLabel(),
                0 , 8 , 2 , 1 , 1 , 1 , "LD");
        centerC.add(remove ,
                2 , 8 , 1 , 1 , 1 , 1 , "D");
        centerC.add(add ,
                3 , 8 , 1 , 1 , 1 , 1, "D");
        centerC.add(assign ,
                4 , 8 , 1 , 1 , 1 , 1, "D");
        centerC.add(Lays.getEmptyLabel() ,
                5 , 8 , 2 , 1 , 1 , 1 , "DR");

        setRemove(remove);
        setAdd(add , name, academy , professional,  grade , clazz);
        setAssign(assign);

        this.add(title , BorderLayout.NORTH);
        this.add(center , BorderLayout.CENTER);

    }

    private void setAssign(JButton assign){
        assign.addActionListener(e->{
            Frame frame = Lays.getFrame(this);
            JDialog jd = new JDialog(frame, "分配导师" , true);
            jd.setLayout(new GridBagLayout());

            int w = frame.getWidth() * 3 / 4;
            int h = frame.getHeight() * 3 / 4;
            int x = (int) ((UIUnit.getW() - w) / 2);
            int y = (int) ((UIUnit.getH() - h) / 2);
            jd.setBounds(x , y , w , h);


            DefaultTableModel model1 = new DefaultTableModel(new Object[][]{} ,
                    new String[]{"账号" , "姓名" , "导师" , "导师账号"}){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            DefaultTableModel model2 = new DefaultTableModel(new Object[][]{} ,
                    new String[]{"姓名" , "账号" }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JTable table1 = new JTable(model1);
            JTable table2 = new JTable(model2);
            table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JTableHeader tableHeader1 = table1.getTableHeader();
            JTableHeader tableHeader2 = table2.getTableHeader();
            JScrollPane pane1 = new JScrollPane(table1);
            JScrollPane pane2 = new JScrollPane(table2);
            JButton confirm = new JButton("确认分配");

            Font font = UIUnit.getFont(h , 20);
            UIUnit.setFont(font , table1 , table2 ,
                    tableHeader1 , tableHeader2 , confirm );
            table1.setRowHeight((int) (font.getSize() *1.5));
            table2.setRowHeight((int) (font.getSize() *1.5));
            tableHeader1.setReorderingAllowed(false);
            tableHeader2.setReorderingAllowed(false);

            MyInsets i = new MyInsets(w / 30 , h / 30 , w / 100 , h / 100);
            MyContainer jdC = new MyContainer(jd , i);

            jdC.add(pane1 ,
                    0 , 0 , 3 , 1 , 3 , 7 , "LU");
            jdC.add(pane2 ,
                    3 , 0 , 2 , 1 , 2 , 7 , "UR");
            jdC.add(Lays.getEmptyLabel() ,
                    0 , 1 , 2 , 1 , 2 , 1 , "LD");
            jdC.add(confirm ,
                    2 , 1 , 1 , 1 , 1 , 1 , "D");
            jdC.add(Lays.getEmptyLabel() ,
                    3 , 1 , 2 , 1 , 2 , 1 , "DR");

            table2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table2.rowAtPoint(e.getPoint());
                    String tutorName = (String) table2.getValueAt(row, 0);
                    String tutorUsername = (String) table2.getValueAt(row,  1);
                    int[] rows = table1.getSelectedRows();
                    for(int r : rows) {
                        table1.setValueAt(tutorName , r , 2);
                        table1.setValueAt(tutorUsername , r , 3);
                    }
                }
            });

            setConfirm(confirm , model1 , model2);

            confirm.doClick();
            jd.setVisible(true);
        });
    }

    private void assigning(DefaultTableModel model1) {
        ArrayList<String[]> arr = new ArrayList<>();
        for(int i = 0 ; i < model1.getRowCount() ; i ++)
        {
            if(model1.getValueAt(i , 3) == null)
                continue;
            arr.add(new String[]{(String) model1.getValueAt(i , 0),
                    (String) model1.getValueAt(i , 3)});
        }
        UpdateTutorDao.update(arr);
    }

    private void setConfirm(JButton confirm, DefaultTableModel model1, DefaultTableModel model2){
        confirm.addActionListener(e->{

            assigning(model1);
            init();

            model1.setRowCount(0);
            model2.setRowCount(0);
            Result result1 = GetNoTutorStudentDao.get();
            Result result2 = GetTutorDao.get();

            ArrayList<Object[]> content = (ArrayList<Object[]>) result1.content;
            for(Object[] objects : content)
                model1.addRow(objects);
            content = (ArrayList<Object[]>) result2.content;
            for(Object[] objects : content)
                model2.addRow(objects);

        });
    }



    public void init(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Result result = GetStudentInfoDao.get();
        if (result.code == 0)
        {
            model.addRow(new Object[]{result.info});
            return;
        }
        ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;
        for(Object[] objects : arr)
            model.addRow(objects);

    }
    private void setRemove(JButton remove){
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
    private void setAdd(JButton add, JTextField name, JComboBox<String> academy, JComboBox<String> professional, JComboBox<Integer> grade, JComboBox<Integer> clazz){
        add.addActionListener(e->{
            Result result = InsertStudentService.insert(name.getText() , (String) academy.getSelectedItem(),
                    (String) professional.getSelectedItem(), (Integer) grade.getSelectedItem(), (Integer) clazz.getSelectedItem());
            if(result.code == 0 )
                JOptionPane.showConfirmDialog(this , result.info , "warning" , JOptionPane.DEFAULT_OPTION);
            init();
        });
    }

}
