/**
 * gồm những biến:
 * - first: lưu Node đầu tiên
 * - last: lưu Node cuối cùng
 * - size: lưu số lượng Node
 * gồm những hàm:
 * - 2 hàm constructor
 * - addLast() , addFirst(E) , add(index, E), add(E)
 * - get(index), getLast(), getFirst()
 * - remove(), remove(index), removeFirst(), removeLast()
 * - contains(E), indexOf(E), size(), isEmpty()
 * - print():
 * @param <T>
 */
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

    /**
     * check empty theo size
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * thêm đầu
     * flow:
     * - lưu lại node first hiện tại vào f
     * - tạo Node mới newNode(null, value, first)
     * -> gán node first = newNode
     * -> kiểm tra f null hay không:
     *      -> nếu f null thì gán last = newNode ( first == last == (null,value,null))
     *      -> nếu f != null thì gán f.prev = newNode
     *  -> tăng kích thước list: size ++
     * @param value giá trị của Node mới
     */
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
    /**
     * thêm cuối
     * flow:
     * - lưu lại node last hiện tại vào l
     * - tạo Node mới newNode(last, value, null)
     * -> gán node last = newNode
     * -> kiểm tra l null hay không:
     *      -> nếu l null thì gán first = newNode ( first == last == (null,value,null))
     *      -> nếu l != null thì gán l.next = newNode (gán địa chỉ node mới vào node last cũ)
     *  -> tăng kích thước list: size ++
     * @param value giá trị của Node mới
     */
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

    /**
     * thêm vào cuối, sử dụng lại hàm addLast()
     * @param value giá trị của Node mới
     */
    public void add(T value){
        addLast(value);
    }

    /**
     * thêm Node theo vào vị trí bất kì
     * flow:
     * - nếu index không hợp lệ -> return
     * -> nếu index == size: là thêm vào cuối -> gọi hàm addLast() -> return
     * -> nếu index == 0: là thêm đầu -> gọi hàm addFirst() -> return
     * -> nếu index hợp lệ và != 0, != size:
     *  -> curentNode = get(index) : lấy node ở vị trí cần thêm
     *  -> prev = currentNode.prev : node trước của currentNode
     *  -> newNode = (prev, value, currentNode) : node mới cần thêm
     *  -> chèn new Node vào giữa current và prev:
     *      -> prev.next = newNode
     *      -> currentNode.prev = newNode;
     *  -> tăng kích thước list: size ++
     *
     * @param index vị trí cần thêm
     * @param value giá trị node mới
     */
    public void add(int index, T value){
        if (index <0 || index > size){
            return;
        }
        if (index == size){
            addLast(value);
            return;
        }
        if (index == 0){
            addFirst(value);
            return;
        }
        NodeCustom<T> currentNode = get(index);
        NodeCustom<T> prev = currentNode.prev;
        NodeCustom<T> newNode = new NodeCustom<>(currentNode.prev, value, currentNode);
        prev.next = newNode;
        currentNode.prev = newNode;
        size++ ;
    }

    /**
     * flow:
     * -> kiểm tra index hợp lệ: nếu sai -> return null
     * -> tạo i=0, và currentNode =first
     * -> lặp: cho tới khi i==index
     *  -> currentNode = currentNode.next;
     *  -> i++;
     * -> return Node cần tìm
     * @param index
     * @return Node cần tìm hoặc null
     */
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

    /**
     * lấy Node đầu tiên
     * @return first
     */
    public NodeCustom<T> getFirst() {
        return first;
    }

    /**
     * lấy Node cuối
     * @return last
     */
    public NodeCustom<T> getLast() {
        return last;
    }

    /**
     * xóa Node đầu tiên
     * flow:
     * -> nếu size = 1 -> last = first = null
     * -> nếu size != 1:
     *  -> gán first = node sau nó
     *      -> first = first.next
     *      -> first.prev = null
     * -> giảm kích thước list: size --
     */
    public void removeFirst() {
        if(size==1){
            last = null;
            first = null;
        }
        first = first.next;
        first.prev = null;
        size --;
    }

    /**
     * xóa Node cuối
     * flow:
     * -> nếu size = 1 -> last = first = null
     * -> nếu size != 1:
     *  -> gán last = node trước nó:
     *      -> last = last.prev
     *      -> last.next = null
     * -> giảm kích thước list: size --
     */
    public void removeLast() {
        if(size==1){
            last = null;
            first = null;
        }
        last = last.prev;
        last.next = null;
        size --;
    }
    /**
     * xóa Node cuối
     * gọi lại hàm removeLast()
     */
    public void remove() {
        removeLast();
    }

    /**
     * xóa theo index
     * flow:
     * -> kiểm tra index hợp lệ: nếu sai -> return null
     * -> nếu index == 0 : xóa đầu -> gọi removeFirst() -> return
     * -> nếu index == size-1: xóa cuối -> gọi removeLast() -> return
     * -> nếu  0 < index < size -1:
     *  -> currentNode = get(index):  lấy node ở vị trí cần xóa
     *  -> prev, next : node ở trước và sau currentNode
     *  -> đổi con trỏ để xóa current:
     *      -> prev.next = next;
     *      -> next.prev = prev;
     *  -> giảm kích thước : size--
     * @param index  vị trí cần xóa
     */
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

    /**
     * check giá trị value có trong list hay k
     * flow:
     * -> tạo current = first
     * -> lặp current = current.next cho tới khi current == empty
     *  -> nếu có node có value = value cần check -> return true
     * @param value giá trị cần check
     * @return
     */
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

    /**
     * tìm vị trí theo value
     * flow:
     * - tạo current = first, i=0
     * -> lặp current = current.next
     * -> cho tới khi current.value = value
     * @param value
     * @return lấy vị trí của Node theo value
     */
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

    /**
     * trả về số lượng node
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * in ra màn hình toàn bộ value của mỗi node
     */
    public void print(){
        NodeCustom<T> currentNode = first;
        while(currentNode != null){
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
    }
}
