/* Copyright 2021 Pedro Bereilh Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the 
License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to inwriting, 
software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
either express or implied. See the License for the specific language governing permissions and 
limitations under the License.
*/

package pr2.org;

import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @author Pedro Bereilh
 * @param <V> Es un génerico 
 */
public class Graph<V> {
   //Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();
    
    /******************************************************************
    * Añade el vértice ‘v‘ al grafo.
    *
    * @param v vértice a añadir.
    * @return ‘true‘ si no estaba anteriormente y ‘false‘ en caso
    contrario.
    *Complejidad temporal: O(1) ya que tiene que comprobar todos los keys y 
    ver si lo contiene o no. 

    *Complejidad Espacial: O(1) este método agrega un vertice en memoria 
    *y tiene tamaño constante
    ******************************************************************/
    
    public boolean addVertex(V v){
    //this.adjacenyList.put(v, new TreeSet<V>());
    if(this.adjacencyList.containsKey(v)){
        return false;

    }else{
        this.adjacencyList.put(v, new TreeSet<V>());    
        return true; 

        }
    }

    /******************************************************************
    * Añade un arco entre los vértices ‘v1‘ y ‘v2‘ al grafo. En caso de que no exista alguno de los vértices, lo añade
    * también. 
    * @param v1 el origen del arco.
    * @param v2 el destino del arco.
    * @return ‘true‘ si no existía el arco y ‘false‘ en caso
    contrario.
    *Complejidad temporal: O(1) Viene dada por el contains y el get que tiene que revisar 
    *todos los elementos n para poder comprobar si existe ese elemento o no y más
    *buscarlo en el adyacencyList para poder comprobar sus adyacentes.  

    *Complejidad Espacial: O(1) Siempre añades 1 vertice y un adyacente cada vez que 
    *llamas al método addEdge.
    ******************************************************************/

    public boolean addEdge(V v1, V v2){
    this.addVertex(v1);
    this.addVertex(v2);
    Set<V> adjsV1 = this.adjacencyList.get(v1); //obtener la lista de v1
    
        if(adjsV1.contains(v2)){ //Si la lista ya contiene a v2 devuelve falso ya esta añadido
            //System.out.println("YA EXISTE");
        return false;
        } 
        
            adjsV1.add(v2);
        
        return true;

    }

    /******************************************************************
    * Obtiene el conjunto de vértices adyacentes a ‘v‘.
    *
    * @param v vértice del que se obtienen los adyacentes.
    * @return conjunto de vértices adyacentes.
    *@throws Exception Lanza excpeción cuando el vertice no tiene adyacentes
    *
    *Complejidad temporal: O(1) ya que tiene que usar el get para encontrar un 
    * v específico. 

    *Complejidad Espacial: O(1) No añade espacio en memoria, hace referencia al 
    * espacio ya ocupado.
    ******************************************************************/
    public Set<V> obtainAdjacents(V v) throws Exception{ 
       
        
       Set<V> AdjSet = this.adjacencyList.get(v);
       if(AdjSet == null){
           throw new  Exception("Vertice no existe");
       }
       if (AdjSet.isEmpty()){
            throw new Exception("Ese vertice no tiene adyacentes");
       }
       else return AdjSet;

    }

    /******************************************************************
    * Comprueba si el grafo contiene el vértice dado.
    *
    * @param v vértice para el que se realiza la comprobación.
    * @return ‘true‘ si ‘v‘ es un vértice del grafo.
    *
    *Complejidad temporal: O(1) Tiene que recorrer el adjacencyList hasta encontrar
    *la clave que esta buscando y comprobar que existe 

    *Complejidad Espacial:  O(1) Solo usa la variable v comprueba si esta o no. 
    ******************************************************************/
    public boolean containsVertex(V v){
        
        return adjacencyList.containsKey(v); 

    }

    /******************************************************************
    * Método ‘toString()‘ reescrito para la clase ‘Grafo.java‘.
    * @return una cadena de caracteres con la lista de adyacencia
    *
    *Complejidad temporal: O(n^2) Ya que tenemos un for anidado con otro y para 
    *cada elemento tenemos que volver encontrar su vertice.
    *
    *Complejidad Espacial: O(n) Depende del tamaño del toString ya que puede tener 
    *más o menos elementos cambiando el espacio en memoria 
    ******************************************************************/

    @Override
    public String toString(){ //Empezar por esta función haciendo test   //Ver keys y dibujarlas
        String cabecera = "Vertice\t Conexiones\n";
        String cuerpo = "";
        for (V vertice : this.adjacencyList.keySet()) {
            cuerpo += vertice.toString() + "\t";
        
            for(V adja : this.adjacencyList.get(vertice)){
                cuerpo += adja.toString() + " " ;
            }
        cuerpo += "\n";

        }

    return cabecera + cuerpo;
    
    }

    /******************************************************************
    * Obtiene, en caso de que exista, un camino entre ‘v1‘ y ‘v2 ‘. En
    * caso contrario, devuelve ‘null‘.
    *
    * @param v1 el vértice origen.
    * @param v2 el vértice destino.
    * @return lista con la secuencia de vértices desde ‘v1‘ hasta
    ‘v2‘
    * pasando por arcos del grafo. 
    *Complejidad temporal: O(n^3) Ya que tenemos dentro del while varios for y get que terminan
    *llegando a n^3
    *
    *Complejidad Espacial: O(n)
    ******************************************************************/
    public List<V> onePath(V v1, V v2){ //Traza es un array list que es lo que tiene que devolver

        /*Algoritmo 2: Búsqueda de un camino entre dos vértices
        Input: El vértice de inicio v1 y el vértice de fin v2
        Output: Secuencia de vértices desde v1 hasta v2 a través de arcos
        del grafo
        Cree una tabla llamada traza;
        Cree una pila llamada abierta; 
        abierta.push(v1); 
        traza.annadir(v1, null); 
        encontrado ← falso;
        */
        
    List<V> traza = new ArrayList<>();
    Stack<V> abierta = new Stack<V>();
    //List<V> ayuda = new ArrayList<>();
    //Set<V> yaLeido = 
    abierta.push(v1);
    //traza.add(v1);
    //traza.add(v1);
    //System.out.println(traza);
    //ayuda.add(v1);
    //System.out.println(ayuda);
    boolean encontrado = false;
    V prec = null,v = null;
    
        /*
        while ¬abierta.es Vacia() ∧ ¬encontrado do
        v ← abierta.pop();
        encontrado pasa a ser verdadero si v es igual a v2; 
        if ¬encontrado then
        Apilar en abierta todos los sucesores de v end
        end*/

    // donde pongo traza.remove(ultimoelementodelalista)?
    //System.out.println("Empieza el bucle");
    while(!abierta.isEmpty() && !encontrado){
        v = abierta.pop();
        if(traza.contains(v)){
            System.out.println("Elimino " + traza.get(traza.size()-1) + " de la traza");
            traza.remove((traza.size()-1));
        } else {traza.add(v);}
        prec = v; 
        encontrado = v.equals(v2);

       
        // traza.remove(traza.size()-1); 

        if(!encontrado){
            System.out.println("Comprobar Adyacente " + v.toString());
            if (this.adjacencyList.get(v).isEmpty()){
                System.out.println("Voy a eliminar el " + traza.get(traza.size()-1)) ;
                traza.remove(traza.size()-1); 
            }

            for (V adyacente : this.adjacencyList.get(v)){
                abierta.push(adyacente);        
            }
        }  
        //System.out.println(traza);
    


    /* if (v == v2){
        encontrado = true;
    }*/

    }
        /*
        if encontrado then
        Reconstruir el camino que hay en traza y devolverlo 
        else
        Devolver un indicador (por ejemplo, null) de que no se ha encontrado el camino
        end*/
    if (encontrado){
        return traza;
    }
    return null;
    }   
}
