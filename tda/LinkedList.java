
package tda;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class LinkedList implements Serializable, List {

    private Node start;//marca el inicio

    public LinkedList() {
        this.start = null;
    }

    @Override
    public int getSize() throws ListExeption {

        if (isEmpty()) {
            throw new ListExeption("La lista esta vacia");
        }
        int cont = 0;
        Node aux = start;
        while (aux != null) {
            aux = aux.next;
            cont++;
        }
        return cont;
    }

    @Override
    public void cancel() {
        this.start = null;
    }

    @Override
    public boolean isEmpty() {
        return start == null;
    }

    @Override
    public void insert(Object element1, Object element2, Object element3) {
        Node newNode = new Node(element1, element2, element3);
        if (start == null) {
            start = newNode;
        } else {
            Node aux = start;//creo otra marca para moverme por inicio
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = newNode;//se enlaza al final
        }
    }

    @Override
    public Object last() throws ListExeption {
        if (isEmpty()) {
            throw new ListExeption("La lista esta vacia");
        }
        Node aux = start;
        while (aux.next != null) {

            aux = aux.next;
        }
        return aux.element1;
    }

    @Override
    public Node getNode(int position) throws ListExeption {
        if (isEmpty()) {
            throw new ListExeption("La lista esta vacia");
        }
        int cont = 0;
        Node aux = start;
        while (aux != null) {
            if (cont == position) {
                return aux;
            }
            aux = aux.next;
            cont++;
        }
        return null;
    }
}
