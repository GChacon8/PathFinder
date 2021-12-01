LIBRERIAS NECESARIAS: 
* JSON simple 1.1 
* JavaFx

El grafo es simplemente un diccionario (hashmap) porque estos son como una "lista de listas" (lista de adyacencia), lo cual es más eficiente que una matriz de adyacencia
porque en la matriz hay que recorrer tooodo para llegar a un lugar y como no todas las ciudades están conectadas habría muchas distancias infinitas: o sea, 
gasto de espacio.

Métodos del grafo:
   1) añadir nodo: primero verifica que el elemento NO exista previamente y luego llama al método put de los hashmap en el diccionario: es una dupla (nodo, {...})
   2) añadir arista: primero confirma que ambos nodos existan y luego llama al método put de los diccionarios para añadir la arista al hashmap----------------------------^ ese
   3) ver aristas de...:  verifica que el nodo exista llamando a una función que retorna el hashmap de dicho nodo y si existe, lo retorna
   4) iterator: sirve para recorrer el grafo, lo que nos ahorra crear una función desde cero (como el BreathFirst que explicó el profe creo, la verdad a eso no le puse atención XD)

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Fibonacci heap es una cola de prioridad (priority queue), circular y doblemente enlazada, cuyos elementos son los roots de árboles Heap. Siempre se sabe quién es el 
elemento mínimo, porque se va actualizando conforme se añaden nodos. Se usa porque es más eficiente que un Binomial Heap y un Binary Heap. 
Hay tablas comparativas de la complejidad de los métodos de cada una de esas estructuras.

Tiene una clase interna que representa los elementos/entradas del fibonacci heap e inicializa los punteros next y previous, como en cualquier lista

Métodos del Fibonacci Heap:
  1)  insertar entrada: primero verifica si la prioridad es un número, crea una instancia de la clase "entrada" y luego une la entrada con la entrada mínima.
  2) unir listas (merge):  primero verifica si las listas son vacías, si las dos tienen elementos entonces ajusta los punteros next y previous de ambas listas para que se asocien
  3) disminuir valor: si el nuevo valor es menor al valor actual de la entrada, actualiza el valor. Si es menor al mínimo, lo define como el nuevo mínimo; si no, lo corta
  4) cortar entrada: ajusta los punteros necesarios y reacomoda hijos de los Heaps en la lista de roots
   5) cortar el mínimo: toma la entrada mínima, la corta, reasigna los punteros y retorna la entrada mínima. También hace que no hayan dos árboles con el mismo degree

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Dijkstra!!!! El algoritmo calcula las distancias/tiempos más cortos desde una ciudad inicial hacia todas las otras ciudades. 

Métodos del Dikstra:
   1) calcular distancias cortas: recorre el grafo, añade las ciudades y sus tiempos a un hashmap, genera un FiboHeap con esos tiempos definidos como infinito primero,
	luego actualiza los tiempos diciendo que el tiempo total es igual al tiempo en llegar al nodo actual más el tiempo siguiente. Finalmente actualiza 
	los tiempos en el FiboHeap, saca el mínimo y lo guarda en un hashmap de resultados.


    
    