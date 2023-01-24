# Proyecto Final de Lenguajes

Traductor de HTML a SLIM usando ANTLR 4 con el patron de listeners. La traducción está enfocada en los elementos, etiquetas, atributos y scripts y estilos.

## Integrantes:
- Camilo Andrés Chaparro Silva

## Compilación:

### 1. Pre-requisitos

- Java JDK:
  https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Entorno de desarrollo (IDE) IntelliJ IDEA: https://www.jetbrains.com/idea/download/ 
- ANTLR 4.9.2:
  https://www.antlr.org/
- Maven - Spring Boot

### 2. Instalación

1. Descargar o clonar el repositorio: 
2. Abrir el proyecto en IntelliJ IDEA y descargar todas las dependencias de maven que aparecen al inicio de abrir el proyecto.
3. Después haga clic derecho sobre el nombre del proyecto y vaya a “Open module settings”.
4. En la sección Dependencies, haga click en el símbolo más (+) ubicado al lado derecho, seleccione la opción “JARs or directories”, y finalmente, busque el archivo de antlr-4.9.2-complete.jar que descargó previamente de la página web de ANTLR. Por último, haga clic en Apply. De esta manera se incluyen las librerías necesarias para el funcionamiento de ANTLR. Click en OK.
5. Vaya al archivo de la gramática MiLenguaje.g4, haga clic derecho en cualquier parte del área de trabajo del editor de texto, y seleccione la opción ”Generate ANTLR Recognizer”.
6. Si no hay errores en la gramática, ANTLR creará la carpeta “gen” dentro del proyecto. Esta carpeta contiene todos los archivos generados automáticamente por ANTLR a partir de la gramática.
7. Nuevamente haga clic derecho sobre el nombre del proyecto y vaya a “Open module settings”, vaya a la sección sources. Aquí debe seleccionar la carpeta gen y luego hacer clic en el botón sources de la zona “Mark as:” en la parte superior. De esta manera, estamos agregando esta carpeta como una ruta de archivos de código fuente para Java.
8. Ya por último corra el proyecto y haga las pruebas como texto plano en Postman o en la herramienta que prefieras.
- Servicio: Method: POST. localhost:3001/traductor/cadena

## Referencias:
- Gramática: https://github.com/antlr/grammars-v4/tree/master/html
- https://tutorialehtml.com/es/guia-del-principiante-en-html/
- https://html2slim.herokuapp.com/
