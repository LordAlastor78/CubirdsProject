package es.uvigo.esei.aed1.tads.queue;

// import es.uvigo.esei.aed1.tads.queue.EmptyException; no usamos este import

public class LinkedQueue<T> implements Queue<T> {

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T first() throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException();
        }
        return this.first.value;
    }

    @Override
    public void add(T value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException();
        }
        Node<T> newNode = new Node<>(value);
        if (this.last == null) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            this.last = newNode;
        }
        this.size++;
    }

    @Override
    public T remove() throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException();
        }
        T value = this.first.value;
        this.first = this.first.next;
        if (this.first == null) {
            this.last = null;
        }
        this.size--;
        return value;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
}