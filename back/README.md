#Pokemon Sample

Pokedex. Gestión de Pokemons

Aplicación de ejemplo para probar la integración del stack:
* Back: API Rest con Spring MVC 4
* Front: Angular 1.5.8 con arquitectura de componentes.


## Notas

* El servicio Rest se ha realizado con spring MVC 4
* Se ha utilizado java-based config en lugar de ficheros .xml
* Existe un fichero de test ```PokemonsRestControllerShould.java``` que incluye test para el api rest.
* El repositorio para guardar pokemon utiliza una lista de pokemos en memoria, no se ha desarrollado la parte
de persistencia, pero el diseño permitiría añadirla muy facilmente.
* No se ha desarrolado una capa de servicios u otro tipo de arquitectura (hexagonal, clean architecture u otras) dado
que el problema con los requisitos actuales es un simple CRUD. Esto implica que algunos controladores tiene algo de lógica
y acceden directamente al repositorio, algo desaconsejable para problemas más complejos.

## Ejecución

Para ejecutar la aplicación se ha incluido en el pom.xml el plugin de tomcat que permite ejecutar la app en un tomcat
embebido, simplemente ejecutando:

```
mvn tomcat7:run
```

para acceder a la aplicación basta con abrir un navegador y escribir la url:

[http://localhost:8080/springrest](http://localhost:8080/springrest)
