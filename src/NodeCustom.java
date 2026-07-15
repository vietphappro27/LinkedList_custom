/**
 * gồm những biến:
 * - value: giá trị của Node
 * - prev: lưu địa chỉ Node trước nó
 * - next: lưu địa chỉ Node sau nó
 * gồm những hàm:
 * - 2 hàm constructor
 * - toString
 * - isEmpty
 * @param <T>
 */
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

    /**
     * trả về String của value
     * @return String
     */
    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * check empty theo value
     * @return
     */
    public boolean isEmpty(){
        return value == null;
    }
}
