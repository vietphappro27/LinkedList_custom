public class NodeCustom <T> {
    T value;
    NodeCustom<T> prev;
    NodeCustom<T> next;

    NodeCustom(){
    }
    NodeCustom(NodeCustom<T> prev, T value, NodeCustom<T> next){
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public boolean isEmpty(){
        return value == null;
    }
}
