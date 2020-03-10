package ac.ncl.yuechen.entity;

/**
 * @ClassName ac.ncl.yuechen.main.Entity.Event
 * @Description: TODO
 * @Author BENY
 * @Date 2019/12/11
 * @Version V1.0
 **/
public class Event implements Comparable<Event> {
    private String name;
    private Integer num;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public int compareTo(Event o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "ac.ncl.yuechen.main.Entity.Event{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }



}
