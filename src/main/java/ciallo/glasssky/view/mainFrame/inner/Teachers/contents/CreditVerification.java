package ciallo.glasssky.view.mainFrame.inner.Teachers.contents;

import ciallo.glasssky.controller.GetFilteredRequestsController;
import ciallo.glasssky.controller.GetFilteredRequestsController;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static ciallo.glasssky.controller.GetFilteredRequestsController.*;

public class CreditVerification  extends JPanel {
    public CreditVerification(int w, int h) {
        setProperties();
        setContents(w , h);
    }
    private void setProperties(){
        this.setLayout(new BorderLayout());
    }

    JTable table ;
    JTextField nameFilter = new JTextField();
    JComboBox<String> gradeFilter = new JComboBox<>(new String[]{"全部" , "1" , "2" , "3" , "4"});
    JComboBox<String> academyFilter = new JComboBox<>();
    JTextField usernameFilter = new JTextField();
    JTextField dateFilter = new JTextField();

    private void setContents(int w,  int h){
        Font fontTitle = UIUnit.getFont(h , 10);
        JLabel title = new JLabel("学分审核" , SwingConstants.CENTER);
        title.setFont(fontTitle);

        JPanel panel = new JPanel(new GridBagLayout());
        int padx = w / 50;
        int pady = h / 50;
        int dx = padx / 3;
        int dy = pady / 3;



        DefaultTableModel model = new DefaultTableModel(new Object[][]{} ,
                new String[]{"申请id" , "姓名" , "年级" , "学院" , "学号" ,"申请日期" , "申请学分" , ""});

        table = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column){
                if(column == 5)
                    return true;
                return false;
            }
        };

        JButton confirmFilter = new JButton("筛选");

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);

        Font font = UIUnit.getFont(h , 40);
        table.setRowHeight((int) (font.getSize() *1.5));


        JLabel tmpLabel1 = new JLabel();
        JLabel tmpLabel2 = new JLabel();
        UIUnit.setFont(font , nameFilter , gradeFilter , academyFilter , usernameFilter , dateFilter ,
                tableHeader , table , confirmFilter);
        UIUnit.clearSize(nameFilter , gradeFilter , academyFilter , usernameFilter , dateFilter , tmpLabel1 , tmpLabel2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets.set(pady , padx , 0 , 0);
        Lays.add(panel , tmpLabel1 , gbc ,
                0 , 0 , 1 , 1 , 1 , 0);

        gbc.insets.set(pady , 0 , 0 , 0);
        Lays.add(panel , nameFilter , gbc ,
                1 , 0 , 1 , 1 , 1 , 0);
        Lays.add(panel , gradeFilter , gbc  ,
                2 , 0 , 1 , 1 , 1 , 0);
        Lays.add(panel , academyFilter , gbc  ,
                3 , 0 , 1 , 1 , 1 , 0);
        Lays.add(panel , usernameFilter , gbc  ,
                4 , 0 , 1 , 1 , 1 , 0);
        Lays.add(panel , dateFilter , gbc  ,
                5 , 0 , 1 , 1 , 1 , 0);

        gbc.insets.set(pady , 0 , 0 , padx);
        Lays.add(panel,tmpLabel2, gbc ,
                6,  0 , 1 , 1 , 2 , 0);
        gbc.insets.set(dy , padx , 0 , padx);
        Lays.add(panel , new JScrollPane(table) , gbc  ,
                0 , 1 , 8 , 5 , 1 , 1);

        gbc.fill = GridBagConstraints.NONE;
        gbc.insets.set(dy , 0 , pady ,  0);
        Lays.add(panel ,  confirmFilter , gbc  ,
                0 , 6 , 8 , 1);


        confirmFilter.addActionListener(e->{
            filter();
        });



        this.add(title , BorderLayout.NORTH);
        this.add(panel , BorderLayout.CENTER);

    }

    public void init(){
        nameFilter.setText("");

        academyFilter.removeAllItems();
        academyFilter.addItem("全部");

        usernameFilter.setText("");
        dateFilter.setText("");




        filter();

    }

    private void filter(){
        Result result = GetFilteredRequestsController.get(nameFilter.getText() , (String) gradeFilter.getSelectedItem(), (String) academyFilter.getSelectedItem(),
                usernameFilter.getText() , dateFilter.getText());
        System.out.println(result.code);
        if(result.code == 0)
            return;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Object[] objects : (ArrayList<Object[]>) result.content)
            model.addRow(objects);

    }


}
