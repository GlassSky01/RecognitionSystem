package ciallo.glasssky.model;

import ciallo.glasssky.utils.Lays;
import ciallo.glasssky.utils.MyInsets;

import java.awt.*;

public class MyContainer {
    Container container;
    MyInsets i;
    public MyContainer(Container container , MyInsets i) {
        this.container = container;
        this.i = i;
    }

    public void add(Component a , int x , int y , int w , int h , double wx ,double wy){
        Lays.add(container , a , i , x , y , w , h , wx , wy);
    }
    public void add(Component a , int x , int y , int w , int h , double wx , double wy , String dir){
        Lays.add(container , a , i , x , y , w , h , wx , wy , dir);
    }


}
