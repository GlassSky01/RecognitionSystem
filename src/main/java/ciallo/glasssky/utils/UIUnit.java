package ciallo.glasssky.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class UIUnit {
    private static double W , H;
    static {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        W = dimension.getWidth();
        H = dimension.getHeight();
    }

    private static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    static {
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }

    private static final DefaultListCellRenderer center = new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            setHorizontalAlignment(SwingConstants.CENTER);
            return this;
        }
    };
    public static double getW() {
        return W;
    }
    public static double getH() {
        return H;
    }
    public static Font getFont(double size , double ratio)
    {
        return new Font("宋体" , Font.PLAIN , (int) (size / ratio));
    }

    public static void setFont(Font font , Object... obj)
    {
        for(Object i : obj)
        {
            try {
                i.getClass().getMethod("setFont" , Font.class).invoke(i , font);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static DefaultListCellRenderer getComboCenter(){
        return center;
    }

    public static void clearWidth(Component... components){
        for(Component component : components)
            component.setPreferredSize(new Dimension(0 , (int) component.getPreferredSize().getHeight()));
    }
    public static void clearHeight(Component... components){
        for(Component component : components)
            component.setPreferredSize(new Dimension((int) component.getPreferredSize().getWidth(), 0));
    }
    public static DefaultTableCellRenderer getTableCenter(){
        return renderer;
    }
}
