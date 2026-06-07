# Sistema de Magitos - Taller 3 POO

## Integrantes
* **Benjamin Mundaca** - RUT: 22.171.210-2 - Usuario GitHub: [@kenjamin1728]

## Descripción del Proyecto
Sistema que procesa magos y sus hechizos. Genera calculos y permite modificar/agregar/eliminar Magos y Hechizos.

## Estructura del Proyecto
este proyecto implementa la estructura de App/Sistema adeams de el patron de diseño Visitor.
* `App.java`: Clase principal con la lógica de procesamiento/lectura/sobreescritura y eliminacion de magos y hechos.
* `Sistema`: Interface que genera los metodos de SistemaImpl.
* `SistemaImpl`: Clase que implementa el interfaz de sistema encargado de recbir y entregar datos primitivos entre este y la app, ademas de la creacion de objetos.
* `Visitor`: Interface que genera los metodos de HechizosVisitor.
* `HechizosVisitor`: Clase que implementa el interfaz Visitor encargado de visitar los objetos y calcular el puntaje por tipo de los hechizos.
* `Hechizos`: Clase abstracta con los atributos de nombre y daño, con respectivos getters y setters.
* `Agua`: Clase que hereda de Hechizos con los atributos de CantidadHeal y PresionDeAgua, con los metodos mostrar y aceptar, ademas de sus respectivos getters y setters .
* `Planta`: Clase que hereda de Hechizos con los atributos de duracionStunt y CantPlantas, con los metodos mostrar y aceptar, ademas de sus respectivos getters y setters.
* `Tierra`: Clase que hereda de Hechizos con los atributos de Mejora de defensa, con los metodos mostrar y aceptar, ademas de sus respectivos getters y setters.
* `Fuego`: Clase que hereda de Hechizos con los atributos de Duracion de quemadura, con los metodos mostrar y aceptar, ademas de sus respectivos getters y setters.
## Instrucciones de Ejecución
1. Clonar el repositorio.
2. Abrir Eclipse IDE.
3. Importarlo
4. Seleccionar la carpeta del repositorio y finalizar.
5. Ejecutar `App.java`.
