package ciallo.glasssky.utils;

import java.awt.*;

public class SizeUnit {
    private static double W , H;
    static {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        W = dimension.getWidth();
        H = dimension.getHeight();
    }

    public static double getW() {
        return W;
    }
    public static double getH() {
        return H;
    }
    public static Font getFont(double size , double ratio)
    {
        return new Font("" , Font.PLAIN , (int) (size / ratio));
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

}
