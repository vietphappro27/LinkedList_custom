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

    /**
     * Khởi tạo một danh sách rỗng.
     */
    public LinkedListCustom() {
    }

    /**
     * Khởi tạo danh sách với Node đâầu, Node cuối và số lượng phần tử
     * @param first Node đầu
     * @param last Node cuối
     * @param size số lượng phần tử
     */
    public LinkedListCustom(NodeCustom<T> first, NodeCustom<T> last, int size) {
        this.first = first;
        this.last = last;
        this.size = size;
    }

    /**
     * Kiểm tra danh sách có rỗng hay không.
     *
     * @return {@code true} nếu danh sách không chứa phần tử nào,
     *         ngược lại trả về {@code false}
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Thêm Node mới vào đầu danh sách.
     * Nếu danh sách đang rỗng, Node mới sẽ đồng thời là last và first.
     * Ngược lại, Node mới được liên kết trước Node first.
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
     * Thêm Node mới vào cuối danh sách.
     * Nếu danh sách đang rỗng, Node mới sẽ đồng thời là last và first.
     * Ngược lại, Node mới được liên kết sau Node last.
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
     * Thêm Node mới vào danh sách.
     * Tương tự thêm vào cuối, gọi lại hàm addLast()
     * @param value giá trị của Node mới
     */
    public void add(T value){
        addLast(value);
    }

    /**
     * Thêm Node theo vào vị trí chỉ định.
     * Nếu không hợp lệ -> return.
     * Nếu index là đầu hoặc cuối danh sách, sẽ gọi lại addFirst() hoặc addLast().
     * Nếu 0 < index < size, tìm Node ở vị trí index, chèn Node mới vào trước Node vừa tìm
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
     * Lấy phần tử theo index.
     * Nếu index không hợp lệ -> return null.
     * Nếu index hợp lệ, tạo i=0 và tạo Node current = first,
     * sau mỗi lần lặp thì i+1 và current = Node sau nó,
     * lặp tới khi i==index thì dừng -> trả về node cần tìm
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
     * Lấy Node đầu tiên
     * @return first
     */
    public NodeCustom<T> getFirst() {
        return first;
    }

    /**
     * Lấy Node cuối
     * @return last
     */
    public NodeCustom<T> getLast() {
        return last;
    }

    /**
     * Xóa Node đầu danh sách.
     * nếu size = 0 -> return.
     * nếu size = 1 -> xóa node sẽ khiến danh sách rỗng, gán last và first = null, size = 0.
     * nếu size > 1 -> gán first = node sau nó, size --
     */
    public void removeFirst() {
        if (size == 0) return;
        if(size == 1){
            last = null;
            first = null;
            size = 0;
            return;
        }
        first = first.next;
        first.prev = null;
        size --;
    }

    /**
     * Xóa Node cuối danh sách.
     * nếu size = 0 -> return.
     * nếu size = 1 -> xóa node sẽ khiến danh sách rỗng, gán last và first = null, size = 0.
     * nếu size > 1 -> gán last = node trước nó, size --
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
     * Xóa Node.
     * tương tự xóa node cuối danh sách, gọi lại hàm removeLast()
     */
    public void remove() {
        removeLast();
    }

    /**
     * Xóa theo index.
     * nếu index không hợp lệ -> return.
     * nếu index là vị trí đầu hoặc cuối -> gọi lại hàm removeFirst() hoặc removeLast().
     * nếu index ở các vị trí còn lại -> tìm node current theo vị trí index, xóa node current bằng cách liên kết node trước và sau node current lại
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
     * Check giá trị value có trong danh sách hay k.
     * Kiểm tra bằng cách lặp từ node fisrt cho tới node last
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
     * Tìm vị trí theo value.
     * Tạo i = 0 và current = first. Sau mỗi bước lặp i=i+1 và current = node sau nó.
     * Nếu tìm thấy current có value bằng với value cần tìm thì return i.
     * ngược lại return -1
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
     * in ra màn hình toàn bộ value của từng node
     */
    public void print(){
        NodeCustom<T> currentNode = first;
        while(currentNode != null){
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
    }
}
