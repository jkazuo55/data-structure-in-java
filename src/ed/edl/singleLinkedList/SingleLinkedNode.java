package ed.edl.singleLinkedList;

public class SingleLinkedNode<T> {
    private T data;
    private SingleLinkedNode<T> next;

    public SingleLinkedNode(){
        data=null;
        next=null;
    }

    public SingleLinkedNode(T data){
        this.data=data;
        next=null;
    }

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data=data;
    }

    public SingleLinkedNode<T> getNext(){
        return this.next;
    }

    public void setNext(SingleLinkedNode<T> next){
        this.next=next;
    }
}
