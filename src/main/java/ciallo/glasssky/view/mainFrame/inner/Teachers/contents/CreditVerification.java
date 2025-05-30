package ciallo.glasssky.view.mainFrame.inner.Teachers.contents;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.DownloadFileService;
import ciallo.glasssky.service.GetDetailsService;
import ciallo.glasssky.service.GetFilteredRequestsService;
import ciallo.glasssky.service.VerificationConfirmService;
import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.UIUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CreditVerification extends JPanel {
    public CreditVerification(int w, int h) {
        setProperties();
        setContents(w, h);
    }

    private void setProperties() {
        this.setLayout(new BorderLayout());
    }

    JTable table;
    JTextField nameFilter = new JTextField();
    JComboBox<String> gradeFilter = new JComboBox<>(new String[]{"全部", "1", "2", "3", "4"});
    JComboBox<String> academyFilter = new JComboBox<>();
    JTextField usernameFilter = new JTextField();
    JTextField dateFilter = new JTextField();

    private void setContents(int w, int h) {
        Font fontTitle = UIUnit.getFont(h, 10);
        JLabel title = new JLabel("学分审核", SwingConstants.CENTER);
        title.setFont(fontTitle);

        JPanel panel = new JPanel(new GridBagLayout());
        int padx = w / 50;
        int pady = h / 50;
        int dx = padx / 3;
        int dy = pady / 3;


        DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                new String[]{"申请id", "姓名", "年级", "学院", "学号", "申请日期", "申请学分", "审核"});

        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setReorderingAllowed(false);

        table.getColumnModel().getColumn(7).setCellRenderer(UIUnit.getTableCenter());
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (table.columnAtPoint(e.getPoint()) == 7) {
                    showDetails((Integer) table.getValueAt(table.rowAtPoint(e.getPoint()), 0));
                }
            }
        });

        Font font = UIUnit.getFont(h, 40);
        table.setRowHeight((int) (font.getSize() * 1.5));


        JLabel tmpLabel1 = new JLabel("筛选条件:", SwingConstants.CENTER);
        JLabel tmpLabel2 = new JLabel();
        UIUnit.setFont(font, nameFilter, gradeFilter, academyFilter, usernameFilter, dateFilter,
                tableHeader, table, tmpLabel1);
        UIUnit.clearWidth(nameFilter, gradeFilter, academyFilter, usernameFilter, dateFilter, tmpLabel1, tmpLabel2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets.set(pady, padx, 0, 0);
        Lays.add(panel, tmpLabel1, gbc,
                0, 0, 1, 1, 1, 0);

        gbc.insets.set(pady, 0, 0, 0);
        Lays.add(panel, nameFilter, gbc,
                1, 0, 1, 1, 1, 0);
        Lays.add(panel, gradeFilter, gbc,
                2, 0, 1, 1, 1, 0);
        Lays.add(panel, academyFilter, gbc,
                3, 0, 1, 1, 1, 0);
        Lays.add(panel, usernameFilter, gbc,
                4, 0, 1, 1, 1, 0);
        Lays.add(panel, dateFilter, gbc,
                5, 0, 1, 1, 1, 0);

        gbc.insets.set(pady, 0, 0, padx);
        Lays.add(panel, tmpLabel2, gbc,
                6, 0, 1, 1, 2, 0);
        gbc.insets.set(dy, padx, pady, padx);
        Lays.add(panel, new JScrollPane(table), gbc,
                0, 1, 8, 5, 1, 1);

        gbc.fill = GridBagConstraints.NONE;


        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filter();
            }
        };
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    filter();
            }
        };
        nameFilter.addKeyListener(keyAdapter);
        usernameFilter.addKeyListener(keyAdapter);
        dateFilter.addKeyListener(keyAdapter);
        gradeFilter.addItemListener(itemListener);
        academyFilter.addItemListener(itemListener);


        this.add(title, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);

    }

    public void init() {
        nameFilter.setText("");

        academyFilter.removeAllItems();
        academyFilter.addItem("全部");

        usernameFilter.setText("");
        dateFilter.setText("");


        filter();

    }

    private void filter() {
        Result result = GetFilteredRequestsService.get(nameFilter.getText(), (String) gradeFilter.getSelectedItem(), (String) academyFilter.getSelectedItem(),
                usernameFilter.getText(), dateFilter.getText() , " and c.auditState = 0;");
        if (result.code == 0)
            return;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;
        for (Object[] objects : arr) {
            model.addRow(objects);
            model.setValueAt("查看详情", model.getRowCount() - 1, model.getColumnCount() - 1);
        }


    }

    private JDialog jd = null;

    private void showDetails(int requestId) {
        if (jd != null) {
            jd.dispose();
        }
        JFrame frame = Lays.getFrame(this);
        jd = new JDialog(frame, "点击审核", false);
        jd.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int w = frame.getWidth() * 3 / 4;
        int h = frame.getHeight() * 3 / 4;
        int x = (int) ((UIUnit.getW() - w) / 2);
        int y = (int) ((UIUnit.getH() - h) / 2);

        Result result = GetDetailsService.get(requestId);
        if (result.code == 0) {
            System.out.println("查询失败");
        } else {
            ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;

            DefaultTableModel model = new DefaultTableModel(new Object[][]{},
                    new String[]{"序号", "认证类型", "认定项目", "认定内容", "申请学分", "认定学分"}) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 5;
                }
            };

            for (Object[] objects : arr) {
                model.addRow(objects);
                model.setValueAt("", model.getRowCount() - 1, model.getColumnCount() - 1);
            }


            int padx = w / 20;
            int pady = h / 20;
            int dx = padx / 3;
            int dy = pady / 3;


            JTable table = new JTable(model);
            table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
            table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(UIUnit.getTableCenter());
            JScrollPane tablePane = new JScrollPane(table);

            JButton download = new JButton("下载申请材料");
            JLabel label = new JLabel("审核意见:");

            JTextArea area = new JTextArea();
            JScrollPane areaPane = new JScrollPane(area);

            JButton confirm = new JButton("确认");

            JTableHeader tableHeader = table.getTableHeader();


            Font font = UIUnit.getFont(h, 30);

            UIUnit.setFont(font, table, tableHeader, label, area, confirm, download);
            table.setRowHeight((int) (font.getSize() * 1.5));

            UIUnit.clearHeight(tablePane, label, areaPane, confirm, download);

            gbc.fill = GridBagConstraints.BOTH;

            gbc.insets.set(pady, padx, 0, padx);
            Lays.add(jd, tablePane, gbc,
                    0, 0, 1, 1, 1, 7);

            gbc.insets.set(dy, padx, 0, padx);

            gbc.fill = GridBagConstraints.VERTICAL;
            Lays.add(jd, download, gbc,
                    0, 1, 1, 1, 1, 1);


            gbc.fill = GridBagConstraints.BOTH;
            Lays.add(jd, label, gbc,
                    0, 2, 1, 1, 1, 1);
            Lays.add(jd, areaPane, gbc,
                    0, 3, 1, 1, 1, 5);


            gbc.fill = GridBagConstraints.VERTICAL;

            gbc.insets.set(dy, padx, pady, padx);
            Lays.add(jd, confirm, gbc,
                    0, 4, 1, 1, 1, 1);

            setDownload(download, requestId);
            setConfirm(confirm, table, area, requestId);


        }

        jd.setBounds(x, y, w, h);
        jd.setVisible(true);
    }

    private void setDownload(JButton download, int requestId) {
        download.addActionListener(e -> {
            Result result = DownloadFileService.download(requestId);
            if (result.code == 0) {
                JOptionPane.showConfirmDialog(Lays.getFrame(this), result.info, "warning", JOptionPane.DEFAULT_OPTION);
                return;
            }
            try (InputStream is = (InputStream) ((Object[]) result.content)[1]) {
                String name = (String) ((Object[]) result.content)[0];

                System.out.println(name);


                JFileChooser chooser = new JFileChooser(new File("./"));
                chooser.setSelectedFile(new File(name));
                int value = chooser.showSaveDialog(null);
                if (value == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    OutputStream os = new FileOutputStream(file);
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = is.read(bytes)) != -1) {
                        os.write(bytes, 0, len);
                    }
                    JOptionPane.showConfirmDialog(Lays.getFrame(this), "下载成功", "success", JOptionPane.DEFAULT_OPTION);
                }

            } catch (Exception ee) {
                JOptionPane.showConfirmDialog(Lays.getFrame(this), "读取失败", "warning", JOptionPane.DEFAULT_OPTION);

            }
        });
    }

    private void setConfirm(JButton confirm, JTable table, JTextArea area, int requestId) {
        confirm.addActionListener(e -> {
            Result result = VerificationConfirmService.commit(table, area.getText(), requestId);

            if (result.code == 0) {
                JOptionPane.showConfirmDialog(Lays.getFrame(this), result.info, "warning", JOptionPane.DEFAULT_OPTION);
                return;
            }
            JOptionPane.showConfirmDialog(Lays.getFrame(this), "审核完成", "success", JOptionPane.DEFAULT_OPTION);
            jd.dispose();
            filter();
        });
    }

}
