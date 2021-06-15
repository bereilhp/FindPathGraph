# README #

Copyright 2021 Pedro Bereilh Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file exceptin compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to inwriting, software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


# Notas para los usuarios

Tener insalado Maven.

## Limpiar Maven

~~~~
mvn clean
~~~~

## Compilación y Ejecucion del programa

Se ejecuta la siguiente instrucción desde un terminal dentro de la carpeta Graph que es un proyecto Maven para poder comprobar los test y ver que el algoritmo funciona correctamente. 

~~~~
mvn test
~~~~

# Notas para los desarrolladores

Maven version = Apache Maven 3.6.3
## Generación de Javadoc

Se ejecuta la siguiete instrucción:

~~~~
mvn javadoc:javadoc
~~~~

## Inspección de Javadoc

Suponiendo que tiene instalado `firefox`, se ejecuta:

~~~~
firefox target/site/apidocs/index.html
~~~~

o también se puede usar 

~~~~
open target/site/apidocs/index.html
~~~~

@author Pedro Bereilh 
