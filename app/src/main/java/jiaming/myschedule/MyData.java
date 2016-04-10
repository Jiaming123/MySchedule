package jiaming.myschedule;


import java.io.Serializable;

public class MyData implements Serializable {
    public int hour;
    public int min;
    public int day;
    public int month;
    public int year;
    public String label = "this is an object that is also serializable";
}
