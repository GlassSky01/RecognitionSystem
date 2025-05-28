package ciallo.glasssky.view.mainFrame.inner.Students.contents;

import ciallo.glasssky.controller.StudentDetailsQueryController;
import ciallo.glasssky.controller.StudentRequestQueryController;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.LocalUser;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ApplyForCredits extends JPanel {

    private Font font;
    private int dx;
    private int dy;
    private int padx;
    private int pady;
    private int tutorId;
    File[] uploadFile = new File[1];

    public ApplyForCredits(int w, int h) {
        setProperties();
        setContents(w, h);
    }

    private void setProperties() {
        this.setLayout(new BorderLayout());
    }

    private void setContents(int w, int h) {
        Font fontTitle = UIUnit.getFont(h, 10);
        JLabel title = new JLabel("学分申请", SwingConstants.CENTER);
        title.setFont(fontTitle);

        this.font = UIUnit.getFont(h, 30);
        dx = w / 50;
        dy = h / 50;
        padx = dx * 2;
        pady = dy * 2;
        JPanel center = new JPanel();
        setCenter(center);

        this.add(title, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    private JTextField date;
    private JTextField tutor;

    private void setCenter(JPanel center) {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"认证类型", "认定项目", "认定内容", "申请学分"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

//        for(int i = 0 ; i < 20 ; i ++)
//            model.addRow(new Object[]{"内容" , "内容" , "内容" , "内容" });

        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);

        JLabel label1 = new JLabel("导师:");
        tutor = new JTextField();
        tutor.setEditable(false);
        JButton upload = new JButton("上传认证材料");
        JLabel label2 = new JLabel("申请日期:");
        date = new JTextField();
        date.setEditable(false);
        JLabel label3 = new JLabel("总分:");
        JTextField total = new JTextField("0");
        total.setEditable(false);
        JButton commit = new JButton("提交");
        JButton add = new JButton("+");
        JButton remove = new JButton("-");
        JButton query = new JButton("查询");

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        table.setRowHeight((int) (font.getSize() * 1.2));

        UIUnit.setFont(font, table, tableHeader, tutor, upload, date, total, commit, add, remove,
                label1, label2, label3 , query);


        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        //表格
        gbc.insets.set(pady, padx, 0, dx);
        Lays.add(center, pane, gbc,
                0, 0, 7, 4, 1, 0);

        //增删查
        gbc.insets.set(2 * pady, 0, 0, dx);
        Lays.add(center, add, gbc,
                7, 0, 1, 1);
        gbc.insets.set(dy, 0, 0, dx);
        Lays.add(center, remove, gbc,
                7, 1, 1, 1);
        Lays.add(center, query, gbc,
                7, 2, 1, 1);
        //信息
        gbc.insets.set(dy, padx, 0, 0);
        Lays.add(center, label1, gbc,
                0, 4, 1, 1);

        gbc.insets.set(dy, dx, 0, 0);
        Lays.add(center, tutor, gbc,
                1, 4, 1, 1);

        gbc.insets.set(dy * 3 / 4, dx, 0, 0);
        Lays.add(center, upload, gbc,
                2, 4, 1, 1);
        gbc.insets.set(dy, dx, 0, 0);

        Lays.add(center, label2, gbc,
                3, 4, 1, 1);

        Lays.add(center, date, gbc,
                4, 4, 1, 1);

        Lays.add(center, label3, gbc,
                5, 4, 1, 1);

        Lays.add(center, total, gbc,
                6, 4, 1, 1);

        gbc.fill = GridBagConstraints.NONE;

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets.set(dy, 0, 0, 0);
        Lays.add(center, commit, gbc,
                0, 5, 7, 1, 0, 1);

        add.addActionListener(e -> {
            JDialog jd = new JDialog(Lays.getFrame(this), "添加内容", true);
            int w = this.getWidth();
            int h = this.getHeight() / 4;
            int x = (int) ((UIUnit.getW() - w) / 2);
            int y = (int) ((UIUnit.getH() - h) / 2);
            jd.setBounds(x, y, w, h);
            jd.setLayout(new GridBagLayout());
            int dx = w / 50, dy = h / 20;
            int padx = dx * 3, pady = dy * 3;
            Font font1 = UIUnit.getFont(h, 10);
            JLabel label4 = new JLabel("认证类型", SwingConstants.CENTER);
            JLabel label5 = new JLabel("认定项目", SwingConstants.CENTER);
            JLabel label6 = new JLabel("认定内容", SwingConstants.CENTER);
            JLabel label7 = new JLabel("申请学分", SwingConstants.CENTER);

            JTextField type = new JTextField();
            JTextField project = new JTextField();
            JTextField content = new JTextField();
            JTextField score = new JTextField();

            JButton clear = new JButton("清空");
            JButton confirm = new JButton("确认");

            UIUnit.setFont(font1, label4, label5, label6, label7, type, project, content, score, confirm);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets.set(pady, padx, 0, 0);
            Lays.add(jd, label4, gbc,
                    0, 0, 1, 1, 1, 0);
            gbc.insets.set(pady, dx, 0, 0);
            Lays.add(jd, label5, gbc,
                    1, 0, 1, 1, 1, 0);
            Lays.add(jd, label6, gbc,
                    2, 0, 1, 1, 1, 0);
            gbc.insets.set(pady, dx, 0, padx);
            Lays.add(jd, label7, gbc,
                    3, 0, 1, 1, 1, 0);
            gbc.insets.set(dy, padx, 0, 0);
            Lays.add(jd, type, gbc,
                    0, 1, 1, 1, 1, 0);
            gbc.insets.set(dy, dx, 0, 0);
            Lays.add(jd, project, gbc,
                    1, 1, 1, 1, 1, 0);
            Lays.add(jd, content, gbc,
                    2, 1, 1, 1, 1, 0);
            gbc.insets.set(dy, dx, 0, padx);
            Lays.add(jd, score, gbc,
                    3, 1, 1, 1, 1, 0);
            Lays.add(jd, clear, gbc,
                    4, 1, 1, 1);
            gbc.fill = GridBagConstraints.NONE;
            gbc.insets.set(dy, 0, 0, 0);
            Lays.add(jd, confirm, gbc,
                    0, 2, 4, 1, 0, 1);


            clear.addActionListener(ex -> {
                type.setText("");
                project.setText("");
                content.setText("");
                score.setText("");
            });
            confirm.addActionListener(ex -> {
                String text1 = type.getText();
                String text2 = project.getText();
                String text3 = content.getText();
                String text4 = score.getText();
                if (text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty()) {
                    JOptionPane.showConfirmDialog(jd, "请输入信息", "warning", JOptionPane.DEFAULT_OPTION);
                    return;
                }

                double sc;
                try {
                    sc = Double.parseDouble(text4);
                } catch (Exception eeee) {
                    JOptionPane.showConfirmDialog(jd, "学分必须是数字", "warning", JOptionPane.DEFAULT_OPTION);
                    return;
                }
                if (sc <= 0 || sc > 10) {
                    JOptionPane.showConfirmDialog(jd, "学分必须是不超过10的正数", "warning", JOptionPane.DEFAULT_OPTION);
                    return;
                }

                model.addRow(new Object[]{text1, text2, text3, sc});
                totalUpdate(table, total);
            });

            jd.setVisible(true);

        });

        remove.addActionListener(e -> {
            int flag = JOptionPane.showConfirmDialog(this, "是否删除所有选中申请?", "request", JOptionPane.YES_NO_OPTION);
            if (flag == 1)
                return;
            int[] rows = table.getSelectedRows();
            for (int i = rows.length - 1; i >= 0; i--) {
                int row = rows[i];
                model.removeRow(row);
            }

        });

        query.addActionListener(e->{
            showRequest();
        });

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./"));
        upload.addActionListener(e -> {
            int value = fileChooser.showOpenDialog(this);
            if (value == JFileChooser.APPROVE_OPTION) {
                uploadFile[0] = fileChooser.getSelectedFile();
                upload.setText("   已上传   ");
            }
        });

        commit.addActionListener(e -> {
            if (tutor.getText().equals("暂无导师")) {
                JOptionPane.showConfirmDialog(this, "还未分配导师", "warning", JOptionPane.DEFAULT_OPTION);
                return;
            }
            if (table.getRowCount() == 0) {
                JOptionPane.showConfirmDialog(this, "请提交申请项目", "warning", JOptionPane.DEFAULT_OPTION);
                return;
            }
            if (uploadFile[0] == null) {
                JOptionPane.showConfirmDialog(this, "请上传认证材料", "warning", JOptionPane.DEFAULT_OPTION);
                return;
            }
            int value = JOptionPane.showConfirmDialog(this, "是否确认申请?", "申请确认", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.NO_OPTION) {
                return;
            }
            int requestId;
            try {
                requestId = DbOperators.executeWithKey("insert into CreditRequestMain( tutorId, file, dates, total, id) value(? , ? , ? , ? , ?);",
                        tutorId , new FileInputStream(uploadFile[0]) , date.getText() , Double.parseDouble(total.getText()) , LocalUser.id);
            } catch (FileNotFoundException ex) {
                JOptionPane.showConfirmDialog(this , "上传失败" , "failure" , JOptionPane.DEFAULT_OPTION);
                return ;
            }

            for(int i = 0 ; i< table.getRowCount() ; i ++)
            {
                try {
                    DbOperators.execute("insert into CreditRequestDetails(types, project, content, score, requestId) value(? , ? , ? , ? , ?);",
                            model.getValueAt(i , 0) ,
                            model.getValueAt(i , 1) ,
                            model.getValueAt(i , 2) ,
                            model.getValueAt(i , 3) ,
                            requestId);
                } catch (Exception ex) {
                    System.out.println("第" + (i + 1) + "条数据插入失败");
                }
            }

            System.out.println(requestId);
            total.setText("0");
            model.setRowCount(0);
            uploadFile[0] = null;
            upload.setText("上传认证材料");
            JOptionPane.showConfirmDialog(this , "申请成功!" , "success" , JOptionPane.DEFAULT_OPTION);

        });


    }

    public void init() {
        date.setText(LocalDate.now().toString());
        tutorId = -1;
        try {
            tutorId = (Integer)DbOperators.executeQuery("select tutorId from UsersInformation where id = ?;",
                    new Class[]{Integer.class} , LocalUser.id).get(0)[0];

        } catch (Exception e) {
            System.out.println("获取导师出错");
        }
        String t = "暂无导师";
        try {
            t = (String)DbOperators.executeQuery("select name from UsersInformation where id = ?;" ,
                    new Class[]{String.class} , tutorId).get(0)[0];
        } catch (Exception e) {
            System.out.println("获取导师出错");
        }
        tutor.setText(t);
    }

    private void totalUpdate(JTable table, JTextField total) {
        double tt = 0;
        TableModel model = table.getModel();
        for (int i = 0; i < table.getRowCount(); i++) {
            tt += (Double) model.getValueAt(i, 3);
        }
//        System.out.println(Double.toString(tt));
        total.setText(Double.toString(tt));
    }
    private void showRequest(){
        JFrame frame = Lays.getFrame(this);
        JDialog jd = new JDialog(frame , "查询历史申请" , true);
        int w = frame.getWidth() * 3 / 4;
        int h = frame.getHeight() * 3 / 4;
        int x = (int) ((UIUnit.getW() - w ) / 2);
        int y = (int) ((UIUnit.getH() - h) / 2);
        jd.setBounds(x , y , w , h);
        jd.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel(new Object[][]{} , new String[]{"申请id" , "申请日期" , "总分值" ,
                "获得分值" , "成绩" , "审核日期" , "审核状态"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable mainTable = new JTable(model);

        Font font = UIUnit.getFont(h , 30);
        JTableHeader tableHeader = mainTable.getTableHeader();
        tableHeader.setReorderingAllowed(false);

        UIUnit.setFont(font , tableHeader , mainTable);
        mainTable.setRowHeight((int) (font.getSize() * 1.2));

        JScrollPane pane = new JScrollPane(mainTable);
        jd.add(pane , BorderLayout.CENTER);
        Result result = StudentRequestQueryController.query();
        if(result.code == 0){
            System.out.println("查询失败");
            jd.dispose();
            return ;
        }
        ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;
        for(Object[] objects : arr)
            model.addRow(objects);

        JPanel panel = new JPanel(new FlowLayout( FlowLayout.CENTER , w / 5 , 0));
        JButton remove = new JButton("撤销申请");
        JButton check = new JButton("查看详情");

        Font font2 = UIUnit.getFont(font.getSize() , 0.5);

        Lays.add(panel , remove , check);
        UIUnit.setFont(font2 , remove , check);
        jd.add(panel , BorderLayout.SOUTH);

        remove.addActionListener(e->{
            if(JOptionPane.showConfirmDialog(jd , "是否删除所有选中申请?" , "删除确认" , JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                return;

            System.out.println("已删除");
            int[] rows = mainTable.getSelectedRows();
            for(int i = rows.length - 1 ; i >= 0 ; i --) {
                int row =rows[i];
                try {
                    DbOperators.execute("delete from CreditRequestMain where requestId = ?;",
                            model.getValueAt(row, 0));
                    model.removeRow(row);
                } catch (Exception ex) {
                    System.out.println("第" + (row+ 1) + "行删除失败");
                }
            }
        });

        check.addActionListener(e->{
            JDialog detail = new JDialog(jd , "详情" , false);
            int w1 = w * 3 / 4;
            int h1  = h * 9 / 10;
            int x0 = (int) ((UIUnit.getW() - w1) / 2);
            int y0 = (int) ((UIUnit.getH() - h1) / 2);
            detail.setBounds(x0 , y0 , w1 , h1);
            int row = mainTable.getSelectedRow();
            int requestId = (int) model.getValueAt(  row , 0);
            Result result1 = StudentDetailsQueryController.query(requestId);

            if(result1.code == 0 )
            {
                System.out.println("查询details失败");
            }
            else{
                ArrayList<Object[]> arr1 = (ArrayList<Object[]>) result1.content;
                Object[][] data = new Object[arr1.size()][];
                for(int i = 0 ; i< data.length ; i ++)
                {
                    if(model.getValueAt(row , 6).equals("未审核"))
                    {
                        arr1.get(i)[5] = "";
                    }
                    data[i] = arr1.get(i);
                }
                JTable detailsTable = new JTable(data , new String[]{});
            }

            detail.setVisible(true);
        });

        jd.setVisible(true);
    }

}
