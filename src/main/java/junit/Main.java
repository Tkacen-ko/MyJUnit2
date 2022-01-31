package junit;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        MyJunit myJunit = new MyJunit(new MyFavoriteTest());
        myJunit.start();
    }
}
