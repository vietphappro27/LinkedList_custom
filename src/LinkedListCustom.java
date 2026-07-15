public class  LinkedListCustom<T>
{
    NodeCustom<T> first;
    NodeCustom<T> last;
    int size = 0;

    public LinkedListCustom() {
    }
    public LinkedListCustom(NodeCustom<T> first, NodeCustom<T> last, int size) {
        this.first = first;
        this.last = last;
        this.size = size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(T value){
        NodeCustom<T> f = first;
        NodeCustom<T> newNode = new NodeCustom<>(null, value, first);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(T value){
        NodeCustom<T> l = last;
        NodeCustom<T> newNode = new NodeCustom<>(last, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public void add(T value){
        addLast(value);
    }

    public void add(int index, T value){
        if (index <0 || index > size){
            return;
        }
        if (index == size){
            addLast(value);
            return;
        }
        NodeCustom<T> currentNode = get(index);
        NodeCustom<T> prev = currentNode.prev;
        NodeCustom<T> newNode = new NodeCustom<>(prev, value, currentNode);
        currentNode.prev = newNode;
        if (prev == null){
            first = newNode;
        }else{
            prev.next = newNode;
        }
        size++ ;
    }

    public NodeCustom<T> get(int index) {
        if(index<0 || index >= size){
            return null;
        }
        int i = 0;
        NodeCustom<T> currentNode = first;
        while(i<index){
            currentNode = currentNode.next;
            i++;
        }
        return currentNode;
    }

    public NodeCustom<T> getFirst() {
        return first;
    }

    public NodeCustom<T> getLast() {
        return last;
    }

    public void removeFirst() {
        if(first == null) return;
        first = first.next;
        if(first == null){
            last = null;
        }else {
            first.prev = null;
        }
        size --;
    }

    public void removeLast() {
        if (last == null) return;
        last = last.prev;

        if (last == null){
            first = null;
        }else {
            last.next = null;
        }
        size --;
    }

    public void remove() {
        removeLast();
    }

    public void remove(int index) {
        if(index<0 || index >= size) {
            return;
        }
        if(index == 0){
            removeFirst();
            return;
        }
        if(index == size-1){
            removeLast();
            return;
        }
        NodeCustom<T> currentNode = get(index);
        NodeCustom<T> prev = currentNode.prev;
        NodeCustom<T> next = currentNode.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    public boolean contains(T value){
        NodeCustom<T> currentNode = first;
        while(!currentNode.isEmpty()){
            if(currentNode.value == value) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public int indexOf(T value){
        NodeCustom<T> currentNode = first;
        int i=0;
        while(!currentNode.isEmpty()){
            if(currentNode.value.equals(value)) {
                return i;
            }
            currentNode = currentNode.next;
            i++;
        }
        return -1;
    }

    public int size(){
        return size;
    }

    public void print(){
        NodeCustom<T> currentNode = first;
        while(currentNode != null){
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
    }
}
