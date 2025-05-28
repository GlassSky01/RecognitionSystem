package ciallo.glasssky.view.mainFrame.inner.Students.contents;

import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.LocalUser;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;

public class ApplyForCredits  extends JPanel {

    private Font font ;
    private int dx;
    private int dy;
    private int padx;
    private int pady;
    public ApplyForCredits(int w, int h) {
        setProperties();
        setContents(w , h);
    }
    private void setProperties(){
        this.setLayout(new BorderLayout());
    }
    private void setContents(int w , int h){
        Font fontTitle = UIUnit.getFont(h, 10);
        JLabel title = new JLabel("学分申请", SwingConstants.CENTER);
        title.setFont(fontTitle);

        this.font = UIUnit.getFont(h, 30);
        dx = w / 50;
        dy = h / 50;
        padx = dx * 3;
        pady = dy * 3;
        JPanel center = new JPanel();
        setCenter(center);

        this.add(title, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    private JLabel date ;
    private JLabel tutor;
    private void setCenter(JPanel center){
        DefaultTableModel model = new DefaultTableModel(new Object[][]{} , new String[]{"认证类型" , "认定项目" , "认定内容" , "申请学分"})
        {
          @Override
          public boolean isCellEditable(int row , int column){
              return false;
          }
        };

//        for(int i = 0 ; i < 20 ; i ++)
//            model.addRow(new Object[]{"内容" , "内容" , "内容" , "内容" });
        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        tutor = new JLabel();
        JButton upload = new JButton("上传");
        date = new JLabel("申请日期: ");
        JLabel total = new JLabel("总分: ");
        JButton commit = new JButton("提交");
        JButton add = new JButton("+");
        JButton remove = new JButton("-");

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        table.setRowHeight((int) (font.getSize() * 1.2));

        UIUnit.setFont(font , table , tableHeader , tutor , upload , date , total , commit , add , remove);


        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets.set(pady , padx , 0 , dx);
        Lays.add(center , pane , gbc ,
                0 , 0 , 4 , 4 , 1 , 0);
        gbc.insets.set(2 * pady , 0 , 0 , dx);
        Lays.add(center , add , gbc ,
                4 , 0 , 1 , 1 );
        gbc.insets.set(6 * pady , 0 , 0 , dx);
        Lays.add(center , remove, gbc,
                4 , 3 , 1 , 1);
        gbc.insets.set(dy , padx , 0 , 0);
        Lays.add(center , tutor, gbc,
                0 , 4 , 1 , 1  , 0.2  ,0);
        gbc.insets.set(dy , dx , 0 , 0);
        Lays.add(center , upload , gbc ,
                1 , 4 , 1 , 1 , 0.5  , 0);
        Lays.add(center ,date   , gbc ,
                2 , 4 , 1 , 1 , 1  ,0);
        Lays.add(center , total , gbc ,
                3 , 4 , 1 , 1 , 1 ,  0);

        gbc.fill= GridBagConstraints.NONE;
        gbc.insets.set(dx , 0 , 0 , 0);
        Lays.add(center , commit , gbc ,
                0 , 5 , 4 , 1 , 0 , 1);

        add.addActionListener(e->{
            JDialog jd = new JDialog(Lays.getFrame(this),  "添加内容" , true);
            int w = this.getWidth() ;
            int h = this.getHeight() / 4;
            int x = (int) ((UIUnit.getW() - w) / 2);
            int y = (int) ((UIUnit.getH() - h) / 2);
            jd.setBounds(x , y ,  w , h);
            jd.setLayout(new GridBagLayout());
            int dx = w / 50 , dy = h / 20;
            int padx = dx * 3 , pady = dy * 3;
            Font font1 = UIUnit.getFont(h , 10);
            JLabel label1 = new JLabel("认证类型" , SwingConstants.CENTER);
            JLabel label2 = new JLabel("认定项目" , SwingConstants.CENTER);
            JLabel label3 = new JLabel("认定内容" , SwingConstants.CENTER);
            JLabel label4 = new JLabel("申请学分" , SwingConstants.CENTER);

            JTextField type = new JTextField();
            JTextField project = new JTextField();
            JTextField content = new JTextField();
            JTextField score = new JTextField();

            JButton clear = new JButton("清空");
            JButton confirm = new JButton("确认");

            UIUnit.setFont(font1 , label1 , label2 , label3 , label4 , type , project , content , score , confirm);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets.set(pady , padx , 0 , 0);
            Lays.add(jd , label1 , gbc ,
                    0 , 0 , 1 , 1 , 1 , 0);
            gbc.insets.set(pady , dx , 0 , 0);
            Lays.add(jd , label2 , gbc ,
                    1 , 0 , 1 , 1 , 1 , 0);
            Lays.add(jd , label3 , gbc ,
                    2 , 0 , 1 , 1 , 1 , 0);
            gbc.insets.set(pady , dx , 0 , padx);
            Lays.add(jd , label4 , gbc ,
                    3 , 0 , 1 , 1 , 1 , 0);
            gbc.insets.set(dy , padx , 0 , 0);
            Lays.add(jd , type , gbc ,
                    0 , 1 , 1 , 1 , 1 , 0);
            gbc.insets.set(dy , dx , 0 , 0);
            Lays.add(jd , project , gbc ,
                    1 , 1 , 1 , 1 , 1 , 0);
            Lays.add(jd , content , gbc ,
                    2 , 1 , 1 , 1 , 1 , 0);
            gbc.insets.set(dy , dx , 0 , padx);
            Lays.add(jd , score , gbc ,
                    3 , 1 , 1 , 1 , 1 , 0);
            Lays.add(jd , clear , gbc,
                    4 , 1 , 1 ,1 );
            gbc.fill = GridBagConstraints.NONE;
            gbc.insets.set(dy , 0 , 0 , 0);
            Lays.add(jd , confirm , gbc,
                    0 , 2 , 4 , 1 , 0 , 1);



            clear.addActionListener(ex->{
                type.setText("");
                project.setText("");
                content.setText("");
                score.setText("");
            });
            confirm.addActionListener(ex->{
                String text1 = type.getText();
                String text2 = project.getText();
                String text3 = content.getText();
                String text4 = score.getText();
                if(text1.isEmpty() || text2.isEmpty() || text3.isEmpty() || text4.isEmpty()){
                    JOptionPane.showConfirmDialog(jd , "请输入信息" , "warning" , JOptionPane.DEFAULT_OPTION);
                    return;
                }

                double sc ;
                try{
                    sc = Double.parseDouble(text4);
                }catch (Exception eeee)
                {
                    JOptionPane.showConfirmDialog(jd , "学分必须是数字" , "warning" , JOptionPane.DEFAULT_OPTION);
                    return;
                }

                model.addRow(new Object[]{text1 , text2 , text3 , sc});
            });

            jd.setVisible(true);

        });

        remove.addActionListener(e->{
            int flag = JOptionPane.showConfirmDialog(this , "是否删除所有选中行?" , "request" , JOptionPane.YES_NO_OPTION);
            if(flag == 1)
                return;
            int[] rows = table.getSelectedRows();
            for(int i = rows.length - 1 ; i >= 0 ; i --)
            {
                int row = rows[i];
                model.removeRow(row);
            }

        });

        upload.addActionListener(e->{


        });

        commit.addActionListener(e->{

        });


    }

    public void init(){
        date.setText("日期: "+LocalDate.now().toString());
        String t = null;
        try {
            t = (String) DbOperators.executeQuery("select tutor from usersinformation where id = ?;" ,
                    new String[]{String.class.toString()} ,LocalUser.id).get(0)[0];

        } catch (Exception e) {
            System.out.println("获取导师出错");
        }
        if(t == null)
            t = "暂无导师";
        else
            t = "导师: " + t;
        tutor.setText(t);
    }

}
