
package tda;

public interface List {
    	public int getSize() throws ListExeption; // Devuelve el numero de elementos en la lista
	public void cancel(); //Elimina toda la lista
	public boolean isEmpty(); // true si la lista esta vacia
	public void insert(Object element1, Object element2, Object element3); // Agrega un elemento a la lista en forma secuencial
	public Object last()throws ListExeption; //Devuelve el ultimo elemento de la lista
	
	//Metodos adicionales
        public Node getNode(int position)throws ListExeption;
        
}//fin de Interface List
