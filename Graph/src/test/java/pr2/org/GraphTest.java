/* Copyright 2021 Pedro Bereilh Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. You may obtain a copy of the 
License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to inwriting, 
software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
either express or implied. See the License for the specific language governing permissions and 
limitations under the License.
*/
package pr2.org;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//import java.io.ObjectInputValidation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Pedro Bereilh
 * Unit test for simple App.
 */
public class GraphTest {
    /**
     * Rigorous Test :-)
     */

    /*class Coordenadas implements Comparable<Coordenadas>{
        int x;
        int y;

        @Override
        public int compareTo(Coordenadas o) {
            // TODO Auto-generated method stub
            return 0;
        }
    }*/

    Graph<Integer> grafoInteger; //Testa falla grafo no esta inicializado
    //Graph<Coordenadas> grafoCoordenadas = new Graph<Coordenadas>();
    Graph<String> grafoStrings;
    
    @Before
     public void setup(){
         grafoInteger = new Graph<Integer>(); //Inicializar grafo integers
         grafoStrings = new Graph<String>();
     }

    @Test
    public void graphExistsTest() //chequear si existe grafo
    {
        assertNotNull(grafoInteger);
    }

    @Test
    public void toStringEmptyTest(){ //Lista vacía

        String expectedString = "Vertice\t Conexiones\n"; //Verlo fallar cambiar luego el toString igual 
        assertEquals(expectedString, grafoInteger.toString());
    }

    @Test
    public void addSingleVertexTest(){
        assertTrue(grafoInteger.addVertex(1));
    }

    @Test
    public void addDuplicateVertexTest(){
        assertTrue(grafoInteger.addVertex(1));
        assertFalse(grafoInteger.addVertex(1));
    }

    @Test
    public void addDuplicateVertexAndCheckTest(){
        String expectedString = "Vertice\t Conexiones\n" 
        + "1\t\n";
        assertTrue(grafoInteger.addVertex(1));
        assertFalse(grafoInteger.addVertex(1));
        assertEquals(expectedString, grafoInteger.toString());
    }

    @Test
    public void toStringSingleVertexTest(){
        grafoInteger.addVertex(1);       
        String expectedString = "Vertice\t Conexiones\n" 
            + "1\t\n";
        assertEquals(expectedString, grafoInteger.toString());
    }

    @Test
    public void toStringSingleVertexTestString(){
        grafoStrings.addVertex("Hola");       
        String expectedString = "Vertice\t Conexiones\n" 
            + "Hola\t\n";
        assertEquals(expectedString, grafoStrings.toString());
        //System.out.println(grafoStrings.toString());
    }

    @Test
    public void toStringVariousVertexTest(){
        grafoInteger.addVertex(1);       
        grafoInteger.addVertex(2);
        String expectedString = "Vertice\t Conexiones\n" 
            + "1\t\n"
            + "2\t\n";
        assertEquals(expectedString, grafoInteger.toString());
    }

    @Test
    public void annadirEdgeReflexivo(){
        grafoInteger.addVertex(1);
        grafoInteger.addEdge(1, 1);
        //grafoInteger.addEdge(1, 2);
        String expectedString = "Vertice\t Conexiones\n" 
            + "1\t1 \n"; 
        assertEquals(expectedString, grafoInteger.toString());
        //System.out.println(grafoInteger.toString());
    }

    @Test
    public void annadirVariosEdges(){
        grafoInteger.addVertex(1);
        grafoInteger.addVertex(2);
        grafoInteger.addEdge(1, 2);
        grafoInteger.addEdge(2, 1);
        String expectedString = "Vertice\t Conexiones\n" 
        + "1\t2 \n" + "2\t1 \n"; 
        assertEquals(expectedString, grafoInteger.toString()); 
        //System.out.println(grafoInteger.toString()); 
    }

    @Test 
    public void annadirEdgesYaExistentes(){
        grafoInteger.addVertex(1);
        grafoInteger.addEdge(1, 1);
        boolean condition = grafoInteger.addEdge(1, 1);
        assertFalse("Debe devolver false al intentar añadir el mismo edge", condition);
        //assertTrue("Debe devolver false al intentar añadir el mismo edge", condition);
    }
    @Test
    public void obtenerAdyacentesDeUnVertex(){
        grafoInteger.addVertex(1);
        grafoInteger.addVertex(2);
        grafoInteger.addVertex(3);
        grafoInteger.addVertex(4);
        grafoInteger.addVertex(5);
        grafoInteger.addEdge(1, 2);
        grafoInteger.addEdge(1, 3);
        grafoInteger.addEdge(2, 1);
        grafoInteger.addEdge(2, 3);
        grafoInteger.addEdge(3, 3);
        grafoInteger.addEdge(4, 5);
        grafoInteger.addEdge(4, 1);
        grafoInteger.addEdge(4, 3);
        grafoInteger.addEdge(5, 5);
        grafoInteger.addEdge(5, 2);
        Set<Integer> SetUnNum = null;
        try{
            SetUnNum = grafoInteger.obtainAdjacents(4);
            int n = SetUnNum.size();
            assertEquals(3, n);
            assertTrue("No lo contiene", SetUnNum.contains(5)); // [1 , 3 , 5] tree set los devuelve ordenados
            assertTrue("No lo contiene", SetUnNum.contains(1)); //comprobar que dentro de ese set contiene estos números
            assertTrue("No lo contiene", SetUnNum.contains(3));
            //assertTrue("No lo contiene", SetUnNum.contains(7)); // verlo fallar 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //System.out.println(SetUnNum);
    }

    @Test (expected = Exception.class)
    public void obtainAdjacentsFailWithException() throws Exception {
        grafoInteger.addVertex(4);
        this.grafoInteger.obtainAdjacents(4);
    }

    @Test (expected = Exception.class)
    public void obtainAdjacentsWithNoVertexFailWithException() throws Exception  {
        //grafoInteger.addVertex(4);
        this.grafoInteger.obtainAdjacents(4);
    }

    @Test
    public void contieneVertex(){
        grafoInteger.addVertex(1);
        grafoInteger.addVertex(2);
        grafoInteger.addVertex(3);
        grafoInteger.addVertex(4);
        grafoInteger.addVertex(5);
        assertTrue("No lo contiene", grafoInteger.containsVertex(4));
        assertFalse("No lo contiene", grafoInteger.containsVertex(7));
        //assertTrue("No lo contiene", grafoInteger.containsVertex(6)); //Verlo fallar
    }

    @Test
    public void onePathFindsAPath(){
        System.out.println("\nTest onePathFindsAPath"); 
        System.out.println("----------------------"); // Se construye el grafo.
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2); 
        g.addEdge(3, 4); 
        g.addEdge(1, 5); 
        g.addEdge(5, 6); 
        g.addEdge(6, 4);
        //Se construye el camino esperado.
        List<Integer> expectedPath = new ArrayList<Integer>(); 
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(6);
        expectedPath.add(4);
        System.out.println(g.toString());
        //Se comprueba si el camino devuelto es igual al esperado. 
        assertEquals(expectedPath, g.onePath(1, 4));
        System.out.println(g.onePath(1, 4));
    }

    @Test
    public void OnePathTest(){
       //System.out.println("\nTest onePathTest"); 
       // System.out.println("----------------------");
        grafoInteger.addEdge(1, 2);
        grafoInteger.addEdge(1, 3);
        grafoInteger.addEdge(2, 4);
        grafoInteger.addEdge(4, 5);
        grafoInteger.addEdge(4, 6);
        List<Integer> expectedPath = new ArrayList<Integer>(); 
        expectedPath.add(1);
        expectedPath.add(2);
        expectedPath.add(4);
        expectedPath.add(5);
        System.out.println(grafoInteger.toString());
        assertEquals(expectedPath, grafoInteger.onePath(1,5)); 
        //System.out.println(expectedPath);
        System.out.println(grafoInteger.onePath(1, 5));
    }
 
    @Test
    public void onePathTestNuevo(){
        //System.out.println("\nTest onePathTestNuevo");
        //System.out.println("----------------------"); // Se construye el grafo. 
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 3);
        g.addEdge(1, 2); 
        g.addEdge(2, 4); 
        g.addEdge(4, 5);  
        g.addEdge(4, 6); 
        List<Integer> expectedPath = new ArrayList<Integer>(); 
        expectedPath.add(1);
        expectedPath.add(2);
        expectedPath.add(4);
        expectedPath.add(6);

        System.out.println(g.toString());
        assertEquals(expectedPath, g.onePath(1, 6));
        //assertEquals(expectedPath, g.onePath(1, 9)); verlo fallar devuelve null
        System.out.println(g.onePath(1, 6));
    }

    @Test 
    
    public void onePathTestGrafo(){
        //System.out.println("\nTest onePathTestGrafo");
        //System.out.println("----------------------");
        Graph<Integer> test = new Graph<>();
        test.addEdge(1, 2);
        test.addEdge(1, 3);
        test.addEdge(2, 4);
        test.addEdge(6, 3);
        test.addEdge(2, 5);
        test.addEdge(5, 7);
        test.addEdge(5, 8);
        test.addEdge(7, 9);
        test.addEdge(9, 10);
        List<Integer> expectedPath = new ArrayList<Integer>();
        expectedPath.add(1);
        expectedPath.add(2);
        expectedPath.add(5);
        expectedPath.add(7);
        expectedPath.add(9);
        expectedPath.add(10);
        //System.out.println(test.toString());
        assertEquals(expectedPath,test.onePath(1, 10));
        //System.out.println(test.onePath(1, 6));
    
    }
}
