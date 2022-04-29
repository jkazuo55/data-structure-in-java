package ed.edl.singleLinkedList;

public class SingleLinkedList<T> {

    private SingleLinkedNode<T> initialNode;

    public SingleLinkedList(){
        initialNode=null;
    }

    // Print data
    public String toString(){
        String cad = toString(initialNode);
        cad = "[" + cad.replaceAll("#", ", ") + "]";
        return cad;
    }

    private String toString(SingleLinkedNode<T> initialNode){
        String cad;
        if(initialNode == null) cad = "";
        else{
            if(initialNode.getNext() != null)
                cad = initialNode.getData() + "#" + toString(initialNode.getNext());
            else cad = initialNode.getData() + toString(initialNode.getNext());
        }
        return cad;
    }

    // check empty
    public boolean empty(){
        return this.initialNode==null;
    }

    // get list size
    public int size(){
        int i = 0;
        if(!empty()){
            SingleLinkedNode<T> auxNode = initialNode;
            while (auxNode!=null){
                i++;
                auxNode = auxNode.getNext();
            }
        }
        return i;
    }

    // insert data at end list
    public void insertAtEnd(T data){
        SingleLinkedNode<T> newNode = new SingleLinkedNode<>();
        newNode.setData(data);
        if (empty()){
            initialNode = newNode;
        }else{
            SingleLinkedNode<T> auxNode = initialNode;
            while (auxNode.getNext()!=null){
                auxNode=auxNode.getNext();
            }
            auxNode.setNext(newNode);
        }
    }

    // insert data at start list
    public void insertAtStart(T data){
        SingleLinkedNode<T> newNode = new SingleLinkedNode<>();
        newNode.setData(data);
        if (!empty()) {
            newNode.setNext(initialNode);
        }
        initialNode = newNode;
    }

    // insert by position in the list
    public void insertByPosition(T newData, int position){
        if (!empty() && position>=0 && position<size()){
            SingleLinkedNode<T> newNode = new SingleLinkedNode<>();
            newNode.setData(newData);
            if (position==0){
                newNode.setNext(initialNode);
                initialNode=newNode;
            }else{
                SingleLinkedNode<T> auxNode = initialNode;
                for (int i = 0; i < position-1; i++)
                    auxNode = auxNode.getNext();
                SingleLinkedNode<T> nextNode = auxNode.getNext();
                auxNode.setNext(newNode);
                newNode.setNext(nextNode);
            }
        }
    }

    // insert by data reference in the list
    public void insertByReference(T searchData, T newData){
        SingleLinkedNode<T> newNode = new SingleLinkedNode<>();
        newNode.setData(newData);
        if (!empty()){
            if (checkIfDataExists(searchData)){
                SingleLinkedNode<T> auxNode=initialNode;
                while (auxNode.getData().equals(newData))
                    auxNode=auxNode.getNext();
                SingleLinkedNode<T> nextNode=auxNode.getNext();
                auxNode.setNext(newNode);
                newNode.setNext(nextNode);
            }
        }
    }

    //Gets the data of a node in a certain position
    public T getDataPosition(int position) throws Exception{
        if (!empty() && position>=0&& position<size()){
            if (position==0)
                return initialNode.getData();
            else {
                SingleLinkedNode<T> auxNode = initialNode;
                for (int i =0;i<position;i++)
                    auxNode=auxNode.getNext();
                return auxNode.getData();
            }
        }else{
            throw new Exception("position not found");
        }
    }

    // get the position of a data in the list
    public int getPositionData(T data) throws Exception {
        int counter=0;
        if (checkIfDataExists(data)){
            SingleLinkedNode<T> auxNode=initialNode;
            while (!data.equals(auxNode.getData())){
                counter++;
                auxNode=auxNode.getNext();
            }
            return counter;
        }else{
            throw new Exception("data not found");
        }
    }

    // edit data by reference
    public void editDataByReference( T data, T newData){
        if (checkIfDataExists(data)){
            SingleLinkedNode<T> auxNode=initialNode;
            while (!(auxNode.getData().equals(data)))
                auxNode=auxNode.getNext();
            auxNode.setData(newData);
        }
    }

    // edit data by position
    public void editDataByPosition(int position , T newData){
        if(position>=0 && position<size()){
            if(position == 0){
                initialNode.setData(newData);
            }
            else{
                SingleLinkedNode<T> auxNode = initialNode;
                for (int i = 0; i < position; i++) {
                    auxNode = auxNode.getNext();
                }
                auxNode.setData(newData);
            }
        }
    }

    // remove node by reference
    public void removeDataByReference(T searchData){
        if (checkIfDataExists(searchData)) {
            if (initialNode.getData() == searchData) {
                initialNode = initialNode.getNext();
            } else{
                SingleLinkedNode<T> auxNode = initialNode;
                while(auxNode.getNext().getData() != searchData){
                    auxNode = auxNode.getNext();
                }
                SingleLinkedNode<T> nexNode = auxNode.getNext().getNext();
                auxNode.setNext(nexNode);
            }
        }
    }

    //remove node by position in the list
    public void removeDataByPosition(int position){
        if(position>=0 && position<size()){
            if(position == 0){
                initialNode = initialNode.getNext();
            }
            else{
                SingleLinkedNode<T> auxNode = initialNode;
                for (int i = 0; i < position-1; i++) {
                    auxNode = auxNode.getNext();
                }
                SingleLinkedNode<T> nextNode = auxNode.getNext();
                auxNode.setNext(nextNode.getNext());
            }
        }
    }

    // remove list
    public void removeList(){
        initialNode = null;
    }

    // clone list
    public SingleLinkedList<T> clone(){
        SingleLinkedList<T> clone = new SingleLinkedList<>();
        clone(initialNode,clone);
        return clone;
    }
    private void clone(SingleLinkedNode<T> currentNode, SingleLinkedList<T> cloneList){
        if(currentNode != null){
            cloneList.insertAtEnd(currentNode.getData());
            clone(currentNode.getNext(), cloneList);
        }
    }

    // check if a data exists in the list
    public boolean checkIfDataExists(T data){
        SingleLinkedNode<T> auxNode = initialNode;
        boolean dataExists = false;
        while (auxNode!=null && !dataExists){
            if (auxNode.getData().equals(data))
                dataExists=true;
            auxNode=auxNode.getNext();
        }
        return dataExists;
    }

    //segmentation ej{2,4,8,5,6}
    // is k=2 {{2,4},{8,5},{6}}
    public SingleLinkedList<SingleLinkedList<T>> segmentation(int k){
        SingleLinkedList<T> groups=new SingleLinkedList<>();
        SingleLinkedList<SingleLinkedList<T>> listGroups=new SingleLinkedList<>();
        if( !empty() && k<=size() ){
            SingleLinkedNode<T> auxNode = initialNode;
            int i=k-1;
            int j=0;
            while(auxNode != null){
                groups.insertAtEnd(auxNode.getData());
                if(j==i||i==size()){
                    listGroups.insertAtEnd(groups);
                    groups.removeList();
                    i=i+k;
                }
                auxNode=auxNode.getNext();
                j++;
            }
        }
        return listGroups;
    }
}
