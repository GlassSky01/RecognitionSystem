package ciallo.glasssky.view.mainFrame.inner.Teachers.contents;

import ciallo.glasssky.controller.GetAllStudentRequestController;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.UUID;

public class CheckDetails  extends JPanel {
    public CheckDetails(int w, int h) {
        setProperties();
        setContents(w , h);
    }
    private void setProperties(){
        this.setLayout(new BorderLayout());
    }

    JTable table ;
    private void setContents(int w , int h){

        JLabel title = new JLabel("学分明细" , SwingConstants.CENTER);
        Font fontTitle = UIUnit.getFont(h , 10);
        title.setFont(fontTitle);


        DefaultTableModel model = new DefaultTableModel(new Object[][]{} , new String[]{ "id" ,  "姓名" , "年级" , "学院" , "学号", "总认定学分" , "总申请学分" , "申请总数" , "待审核数" , ""}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };



        table = new JTable(model);
        JScrollPane tablePane = new JScrollPane(table);
        JPanel panel = new JPanel(new GridBagLayout());

        Font font = UIUnit.getFont(h , 40);
        JTableHeader tableHeader = table.getTableHeader();
        UIUnit.setFont(font , table , tableHeader);
        table.setRowHeight((int) (font.getSize() * 1.5));

        tableHeader.setReorderingAllowed(false);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(UIUnit.getTableCenter());
        TableColumn column = table.getColumnModel().getColumn(0);
        column.setMaxWidth(0);
        column.setMinWidth(0);
        column.setPreferredWidth(0);


        int pady = h / 25 ;
        int padx = w / 25 ;


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets.set(pady , padx , pady , padx);
        gbc.fill = GridBagConstraints.BOTH;

        Lays.add(panel , tablePane , gbc ,
                0 , 0 , 40 , 40 , 1 , 1);

        this.add(title , BorderLayout.NORTH);
        this.add(panel , BorderLayout.CENTER);


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(table.columnAtPoint(e.getPoint()) == table.getColumnCount() - 1)
                    showStudents((Integer) table.getValueAt(table.rowAtPoint(e.getPoint()) , 0) , (String) table.getValueAt(table.rowAtPoint(e.getPoint()) , 1));
            }
        });


    }

    private void showStudents(int id , String name){
        JFrame frame = new JFrame("学生详情" );
        frame.setLayout(new GridBagLayout());

        int w = this.getWidth();
        int h= this.getHeight();
        int x = (int) ((UIUnit.getW() - w) / 2);
        int y= (int) ((UIUnit.getH() - h) / 2);

        int padx = w / 30;
        int pady = h / 30;
        int dx= padx / 3;
        int dy = pady / 3;


        frame.setBounds(x,  y , w , h );

        JLabel title = new JLabel(name , SwingConstants.CENTER);
        Font fontTitle = UIUnit.getFont(h , 10);
        title.setFont(fontTitle);
        DefaultTableModel model = new DefaultTableModel(new Object[][]{} , new String[]{
                "申请id"  , "申请日期" , "申请学分" , "认定学分", "成绩等级" , "审核时间" , "申请状态" , "详情"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable studentTable = new JTable(model);
        JTableHeader tableHeader = studentTable.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        JScrollPane pane = new JScrollPane(studentTable);

        Font font = UIUnit.getFont(h , 50);
        UIUnit.setFont(font , studentTable , tableHeader );
        table.setRowHeight((int) (font.getSize()*1.5));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets.set(pady , 0 , 0 , 0);
        Lays.add(frame , title, gbc ,
                0 , 0 , 1 , 1  , 1 , 0 );

        gbc.insets.set(dy , padx , pady , padx);
        Lays.add(frame , pane , gbc ,
                0 , 1 , 1 , 1 , 1 , 1);



        frame.setVisible(true);
    }

    public void init(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Result result = GetAllStudentRequestController.get();
        if(result.code == 0){
            model.addRow(new Object[]{result.info});
            return;
        }
        ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;
        for(int i = 0 ; i < arr.size() ; i ++)
        {
            model.addRow(arr.get(i));
            model.setValueAt( "查看详情" , i , model.getColumnCount() - 1);
        }

    }


}
