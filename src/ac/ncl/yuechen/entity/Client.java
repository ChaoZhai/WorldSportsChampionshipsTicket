package ac.ncl.yuechen.entity;

import ac.ncl.yuechen.util.SortedArrayList;

import java.util.List;

/**
 * @ClassName ac.ncl.yuechen.main.Entity.Client
 * @Description: TODO
 * @Author BENY
 * @Date 2019/12/11
 * @Version V1.0
 **/
public  class Client implements Comparable<Client> {

    private String firstName;
    private String surname;
    private List<Event> eventList=new SortedArrayList<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int compareTo(Client o) {

        if (this.getSurname().equals(o.getSurname())) {
            return this.getFirstName().compareTo(o.getFirstName());
        }
        return this.getSurname().compareTo(o.getSurname());

    }
}
