package ciallo.glasssky.view.mainFrame.inner.Administrators.contents;

import ciallo.glasssky.model.MyContainer;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.GetStaticsService;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.MyInsets;
import ciallo.glasssky.utils.UIUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
public class Statistics extends JPanel {
    public Statistics(int w, int h) {
        setProperties();
        setContents(w, h);
    }

    private void setProperties() {
        this.setLayout(new BorderLayout());
    }

    JTable table;

    private void setContents(int w, int h) {
        Font fontTitle = UIUnit.getFont(h, 10);
        JLabel title = new JLabel("统计", SwingConstants.CENTER);
        title.setFont(fontTitle);

        JPanel center = new JPanel(new GridBagLayout());

        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new String[]{"申请id", "学号", "姓名", "日期", "申请学分",
                        "获得学分", "成绩", "审核时间", "审核状态"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        JTableHeader tableHeader = table.getTableHeader();
        JTextField username = new JTextField();
        JTextField name = new JTextField();
        String[] box = new String[]{"升序", "降序"};
        JComboBox<String> requestDate = new JComboBox<>(box);
        JComboBox<String> requestScore = new JComboBox<>(box);
        JComboBox<String> getScore = new JComboBox<>(box);
        JComboBox<String> grade = new JComboBox<>(new String[]{"全部", "优秀", "良好", "中等", "及格", "不及格"});
        JComboBox<String> auditDate = new JComboBox<>(box);
        JComboBox<String> state = new JComboBox<>(new String[]{"全部", "已审核", "未审核"});
        JButton export = new JButton("导出");

        Font font = UIUnit.getFont(h, 40);
        UIUnit.setFont(font, table, tableHeader, username, name,
                requestDate, requestScore, getScore, grade,
                auditDate, state, export);
        table.setRowHeight((int) (font.getSize() * 1.5));
        tableHeader.setReorderingAllowed(false);

        MyInsets i = new MyInsets(w / 30, h / 30, 0, h / 100);
        MyContainer centerC = new MyContainer(center, i);


        //第一行
        centerC.add(Lays.getEmptyLabel(),
                0, 0, 1, 1, 1, 1, "LU");
        centerC.add(username,
                1, 0, 1, 1, 1, 1, "U");
        centerC.add(name,
                2, 0, 1, 1, 1, 1, "U");
        centerC.add(requestDate,
                3, 0, 1, 1, 1, 1, "U");
        centerC.add(requestScore,
                4, 0, 1, 1, 1, 1, "U");
        centerC.add(getScore,
                5, 0, 1, 1, 1, 1, "U");
        centerC.add(grade,
                6, 0, 1, 1, 1, 1, "U");
        centerC.add(auditDate,
                7, 0, 1, 1, 1, 1, "U");
        centerC.add(state,
                8, 0, 1, 1, 1, 1, "UR");


        //第二行
        centerC.add(pane,
                0, 1, 9, 9, 9, 15, "LR");

        //第三行
        centerC.add(Lays.getEmptyLabel(),
                0, 10, 4, 1, 4, 1, "LD");

        centerC.add(export,
                4, 10, 1, 1, 1, 1, "D");

        centerC.add(Lays.getEmptyLabel(),
                5, 10, 4, 1, 4, 1, "DR");

        setActionListener(requestDate, "dates");
        setActionListener(requestScore, "total");
        setActionListener(getScore, "getTotal");
        setActionListener(auditDate, "auditDate");
        state.addActionListener(e -> {
            String s = state.getSelectedItem().toString();
            if ("未审核".equals(s))
                stateCon = " and auditState = 0";
            else if ("已审核".equals(s))
                stateCon = " and auditState = 1";
            else stateCon = "";
            init();
        });
        grade.addActionListener(e -> {
            String s = grade.getSelectedItem().toString();

            if ("优秀".equals(s))
                gradeCon = " and c.grade = '优秀' ";
            else if ("良好".equals(s))
                gradeCon = " and c.grade = '良好' ";
            else if ("中等".equals(s))
                gradeCon = " and c.grade = '中等' ";
            else if ("及格".equals(s))
                gradeCon = " and c.grade = '及格' ";
            else if ("不及格".equals(s))
                gradeCon = " and c.grade = '不及格' ";
            else
                gradeCon = "";
            init();
        });

        setExport(export);

        this.add(title, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
    }

    private void setActionListener(JComboBox<?> jcb, String field) {
        jcb.addActionListener(e -> {
            String s = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
            if ("升序".equals(s))
                condition = "order by " + field;
            else
                condition = "order by " + field + " desc ";
            init();
        });
    }

    private String usernameCon = "";
    private String nameCon = "";
    private String stateCon = "";
    private String gradeCon = "";
    private String condition = "";

    public void init() {

        Result result = GetStaticsService.get(usernameCon, nameCon, stateCon, gradeCon, condition);
        if (result.code == 0) {
            System.out.println("查询失败");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;
        for (Object[] objects : arr)
            model.addRow(objects);


    }

    private void setExport(JButton export) {
        export.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(new File("./"));
            chooser.setSelectedFile(new File("学分统计.xls"));
            int value = chooser.showSaveDialog(null);
            if (value == JFileChooser.APPROVE_OPTION) {
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("学分统计");
                Row headerRow = sheet.createRow(0);
                String[] headers = new String[]{"申请id", "学号", "姓名", "日期", "申请学分",
                        "获得学分", "成绩", "审核时间", "审核状态"};
                for(int i = 0  ; i < headers.length ; i ++)
                {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
                int rows = table.getRowCount();
                int columns = table.getColumnCount();
                for(int i = 0 ; i < rows ; i ++){
                    Row row = sheet.createRow(i +1);
                    for(int j = 0 ; j < columns ; j ++)
                    {
                        Cell cell = row.createCell(j);
                        Object data = table.getValueAt(i , j);
                        if(data instanceof String)
                            cell.setCellValue((String)data);
                        if(data instanceof Integer)
                            cell.setCellValue((Integer)data);
                        if(data instanceof Double)
                            cell.setCellValue((Double) data);
                    }
                }

                for(int i = 0 ; i < columns ; i ++)
                    sheet.autoSizeColumn(i);

                File file = chooser.getSelectedFile();
                try(FileOutputStream fos = new FileOutputStream(file))
                {
                    workbook.write(fos);

                }catch(Exception ee){
                    System.out.println("文件写入失败");
                }


            }
        });
    }


}
