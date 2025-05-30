package ciallo.glasssky.utils;

public class MyInsets {
    public int padx;
    public int pady;
    public int dx ;

    public void setPadx(int padx) {
        this.padx = padx;
    }

    public void setPady(int pady) {
        this.pady = pady;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int dy;
    public MyInsets(int padx , int pady , int dx , int dy){
        this.padx = padx;
        this.pady = pady;
        this.dx = dx;
        this.dy = dy;
    }
    public void set(int padx , int pady , int dx , int dy){
        this.padx = padx;
        this.pady = pady;
        this.dx = dx;
        this.dy = dy;
    }
    public final int L = 1;
    public final int R = 2;
    public final int D = 4;
    public final int U = 8;
}
