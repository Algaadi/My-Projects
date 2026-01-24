package DataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class DoubleCircularLinkedList<T> implements Iterable<T> {

    private static final class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;
        Node(T data) { this.data = data; }
    }

    private Node<T> head;
    private int size;

    public DoubleCircularLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

   
    public void add(T value) {
        addLast(value);
    }

    
    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            node.next = node;
            node.prev = node;
            head = node;
            size = 1;
            return;
        }

        Node<T> tail = head.prev;
        node.next = head;
        node.prev = tail;
        tail.next = node;
        head.prev = node;
        head = node;
        size++;
    }

    
    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            node.next = node;
            node.prev = node;
            head = node;
            size = 1;
            return;
        }

        Node<T> tail = head.prev;
        node.next = head;
        node.prev = tail;
        tail.next = node;
        head.prev = node;
        size++;
    }

    
    public T get(int index) {
        return nodeAt(index).data;
    }

    public void set(int index, T value) {
        nodeAt(index).data = value;
    }

    public boolean contains(T value) {
        for (T v : this) {
            if (Objects.equals(v, value)) return true;
        }
        return false;
    }

    
    public boolean remove(T value) {
        if (head == null) return false;

        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.data, value)) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T removeAt(int index) {
        Node<T> node = nodeAt(index);
        T data = node.data;
        unlink(node);
        return data;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    private Node<T> nodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index <= size / 2) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) current = current.next;
            return current;
        } else {
            Node<T> current = head.prev; // tail
            for (int i = size - 1; i > index; i--) current = current.prev;
            return current;
        }
    }

    private void unlink(Node<T> node) {
        if (size == 1) {
            head = null;
            size = 0;
            return;
        }

        Node<T> prev = node.prev;
        Node<T> next = node.next;
        prev.next = next;
        next.prev = prev;

        if (node == head) {
            head = next;
        }

        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int visited = 0;
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return visited < size;
            }

            @Override
            public T next() {
                if (!hasNext() || current == null) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                visited++;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        for (T v : this) {
            if (i++ > 0) sb.append(", ");
            sb.append(v);
        }
        sb.append(']');
        return sb.toString();
    }
}
