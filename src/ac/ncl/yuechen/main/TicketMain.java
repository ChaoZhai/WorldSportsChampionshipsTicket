package ac.ncl.yuechen.main;


import ac.ncl.yuechen.entity.Client;
import ac.ncl.yuechen.entity.Event;
import ac.ncl.yuechen.util.SortedArrayList;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName ac.ncl.yuechen.main.TicketMain
 * @Description: TODO
 * @Author BENY
 * @Date 2019/12/11
 * @Version V1.0
 **/
public class TicketMain {

    //initialization list
    static List<Event> eventList = new SortedArrayList<>();
    static List<Client> clientList = new SortedArrayList<>();

    public static void main(String[] args) {

     /*   try {
            PrintStream ps = new PrintStream("C:/newcastle/testData/auditlog.txt");
            System.setOut(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        loadInformationFromFile();
        showMenu();

    }

    public static void showMenu() {


        System.out.println("\n");
        System.out.println("World Sports Championships ticket office system");
        System.out.println("f.exit system");
        System.out.println("e.show all the events");
        System.out.println("c.Show all the clients");
        System.out.println("b.update the bought tickets data ");
        System.out.println("r.update the cancels/returns tickets data ");
        System.out.println("Please enter your choice :");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if ("f".equals(str.toLowerCase())) {
            System.out.println("Bye-bye!!!");
            System.exit(0);
        } else if ("e".equals(str.toLowerCase())) {
            showEvents();
            showMenu();
        } else if ("c".equals(str.toLowerCase())) {
            showClients();
            showMenu();
        } else if ("b".equals(str.toLowerCase())) {
            check(scanner, 1);
            showMenu();
        } else if ("r".equals(str.toLowerCase())) {
            check(scanner, 2);
            showMenu();
        } else {
            System.out.println(" Enter wrong choice ,please enter again!");
            showMenu();
        }


    }

    public static void showClients() {
        System.out.println("Total " + clientList.size() + " clients");
        for (Client client : clientList) {
            System.out.println(client.getSurname() + " " + client.getFirstName());
        }

    }


    public static void showEvents() {
        for (Event event : eventList) {
            System.out.println(event.getName() + " : " + event.getNum());
        }

    }


    public static void check(Scanner scanner, Integer num) {
        System.out.println("Please enter client name:");
        String clientName = scanner.nextLine();
        Boolean isHave = false;
        for (Client client : clientList) {
            if (2 == num) {
                if (clientName.trim().equals(client.getSurname() + " " + client.getFirstName())) {
                    isHave = true;
                }
            } else {
                if (clientName.trim().equals(client.getSurname() + " " + client.getFirstName()) && client.getEventList().size() < 3) {
                    isHave = true;
                }
            }

        }
        if (isHave) {
            Boolean isHaveEvent = false;
            System.out.println("Please enter event name:");
            String eventName = scanner.nextLine();
            for (Event event : eventList) {
                if (event.getName().toLowerCase().equals(eventName.toLowerCase())) {
                    isHaveEvent = true;
                }
            }
            if (isHaveEvent) {

                if (1 == num) {
                    buyTickets(scanner, clientName, eventName);
                } else {
                    cancelTickets(clientName, eventName);
                }

            } else {
                System.out.println("Event name does not exist!!!");
                return;
            }


        } else {
            System.out.println("Client name does not exist!!! or Only three tickets can be ordered per client!!!");
            return;
        }


    }


    public static void buyTickets(Scanner scanner, String clientName, String eventName) {
        System.out.println("Please enter book count:");
        String bookCount = scanner.nextLine();
        if (isNum(bookCount)) {
            for (Event event : eventList) {
                if (eventName.toLowerCase().equals(event.getName().toLowerCase())) {
                    if (Integer.valueOf(bookCount) < event.getNum() || Integer.valueOf(bookCount) == event.getNum()) {
                        event.setNum(event.getNum() - Integer.valueOf(bookCount));
                        for (Client client : clientList) {
                            if (clientName.toLowerCase().equals((client.getSurname() + " " + client.getFirstName()).toLowerCase())) {
                                System.out.println("book successful!");
                                List<Event> evenAddtList = client.getEventList();
                                Event eventAdd = new Event();
                                eventAdd.setNum(Integer.valueOf(bookCount));
                                eventAdd.setName(eventName);
                                evenAddtList.add(eventAdd);
                                client.setEventList(evenAddtList);
                            }

                        }

                    } else {
                        //write a letter
                        appendMethod(eventName);
                        System.out.println("Not enough tickets!!!");
                    }


                }

            }

        } else {
            System.out.println("please input a number!");
            return;
        }


    }

    public static void cancelTickets(String clientName, String eventName) {

        Boolean isRec=true;
        for (Client client : clientList) {
            if (clientName.toLowerCase().equals((client.getSurname() + " " + client.getFirstName()).toLowerCase())) {
                List<Event> eveList = client.getEventList();
                for (Event event : eveList) {
                    if (eventName.toLowerCase().equals(event.getName().toLowerCase())) {
                        eveList.remove(event);
                        for (Event event1 : eventList) {
                            if (eventName.toLowerCase().equals(event1.getName().toLowerCase())) {
                                event1.setNum(event1.getNum() + event.getNum());
                            }
                        }
                        isRec=true;
                        System.out.println("cancel success!!!");
                        break;
                    } else {
                        isRec=false;
                    }
                }

            }else{
                isRec=false;
            }

        }

        if(!isRec){
            System.out.println("No booking record!!!");
        }

    }


    public static void loadInformationFromFile() {

        //read client information from file
       /* try {
            File file = new File("C:/newcastle/testData/clients.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strTmp = "";
            int lineNum = 1;
            while ((strTmp = bufferedReader.readLine()) != null) {
                if (lineNum != 1) {
                    Client client = new Client();
                    String[] s = strTmp.split(" ");
                    client.setSurname(s[0]);
                    client.setFirstName(s[1]);
                    clientList.add(client);
                }

                lineNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //read event information from file
        try {
            File file = new File("C:/newcastle/testData/events.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strTmp = "";
            int lineNum = 1;
            Event event = null;
            while ((strTmp = bufferedReader.readLine()) != null) {
                if (lineNum > 12) {
                    if (lineNum != 13) {
                        Client client = new Client();
                        String[] s = strTmp.split(" ");
                        client.setSurname(s[0]);
                        client.setFirstName(s[1]);
                        clientList.add(client);
                    }

                } else {
                    if (lineNum % 2 != 0) {
                        event = new Event();
                        event.setNum(Integer.valueOf(strTmp));
                    } else {
                        event.setName(strTmp);
                        eventList.add(event);
                    }
                }


                lineNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static boolean isNum(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }


    public static void appendMethod(String eventName) {
        String content = "Dear Sir or Madam,\n" +
                "  \n" +
                "   Event " + eventName + "  Not enough tickets, thanks for ordering!\n" +
                "           \n" +
                "\t\t   \n" +
                "\t\t   \n" +
                "\t\t                             Yours sincerely,\n" +
                "\t\t\t\t\t\t\t\t\t World Sports Championships ticket office\n" +
                "   ";
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter("C:/newcastle/testData/eventInformation.txt", true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
