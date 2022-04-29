package ed.edl.singleLinkedList;

public class Test {
    public static void main(String[] args) throws Exception {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.insertAtEnd("50");
        list.insertAtEnd("100");
        list.insertAtStart("200");
        list.insertAtEnd("300");
        list.insertAtStart("80");
        list.insertAtStart("5040");
        list.insertAtStart("5030");
        list.insertAtEnd("30");
        list.insertAtEnd("300");
        list.insertAtEnd("10000");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.checkIfDataExists("50"));
        list.insertByReference("5040","6060");
        System.out.println(list);
        list.insertByPosition("9000",0);
        System.out.println(list);
        System.out.println(list.getDataPosition(5));
        System.out.println(list.getPositionData("200"));
        list.editDataByReference("50","25");
        System.out.println(list);
        list.editDataByPosition(0,"666");
        System.out.println(list);
        list.removeDataByReference("5040");
        System.out.println(list);
        list.removeDataByPosition(5);
        System.out.println(list);
    }
}
