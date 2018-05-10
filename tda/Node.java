
package tda;

import java.io.Serializable;

/**
 *
 * @author 
 */
public class Node implements Serializable {

    public Object element1;
    public Object element2;
    public Object element3;
    public Node next;
    public Node ant;

    public Node(Object elemento1, Object elemento2, Object elemento3) {
        this.element1 = elemento1;
        this.element2 = elemento2;
        this.element3 = elemento3;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Elemento1: " + element1 + "\nElemento 2: " + element2 + "\nElemento 3: " + element3;
    }
}
