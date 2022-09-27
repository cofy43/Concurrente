# Práctica 2

## Table de contenido
* [Estructura]
* [Tecnologías]
* [Consideraciones]
* [Ejecución]

## Estructura del repositorio
    .
    ├── src                              # Archivos java
    │   ├── Filtros                      # Directorio con los filtros: motion blur, blur2, sharpen, componentes RGB, grises, correctud y correctud 2
    │   │   ├──Filtros.java
    │   │   ├──FiltrosConcurrente.java
    │   │   ├──test1.jpeg                # Imágen de prueba
    │   ├── Sopa_de_letras               # Directorio con el códgo para resolver un sopa de letras
    │   │   ├──Sopa.java                 # Ejecución secuencial
    │   │   ├──sopa.txt                  # Ejemplar de sopa de letras
    │   │   ├──SopaConcurrente.java      # Ejecución concurrente
    │   ├── Suma_Matrices                # Directorio con el códgo para la suma de matrices
    │   │   Matriz.txt                   # Ejemplar de matriz
    │   │   Matriz1.txt                  # Ejemplar de matriz
    │   │   SumaMatrices.java            # Ejecución secuencial
    │   │   SumaMatricesConcurrente.java # Ejecución concurrente
    └── README.md

## Tecnologías
Se utilizo Java v8

## Consideraciones
### Filtros:
Para su correcta ejecución se requiere agregar una imagen a la cual se le aplicaran los filtros, dicha imagen deberá estar dentro de la carpeta "Filtros" y en caso de llevar un nombre personalizado se necesitará
remplazar en el código en todas las apariciones de "test1.jpeg" por el nombre de la imágen deseada.

### Sopa de letras:
Para el ejemplar del sopa de letras se requiere que se pase una matriz de 
$n x m$ donde se separen por un espacio las letras que componen la sopa de letras, posteriormente se requiere una línea en blanco para separa la sopa
de letras y las palabaras a buscar, por ejemplo:
```txt
M E D I C O S D T A O N C
U L V I D A C E O N T N U
Z S I E C D C N I L I E O
P I I S M I L S D I A S T
E A E S P O U Z C S D A S
T I U A U A I L C C S E E
M A T R S I C O E S C T P
S A A L T I C I I T D C S
A I N T S P S I E N I S P
A A A I I O O T D N T A A
L A S C V E M O I I T D E
S T I I I S I O P D O E D
A A A O I A M E S U O N S

SUICIDIO
EUTANASIA
MEDICO
ASISTIDO
VIDA
PACIENTES
```
Y dicho ejemplar deberá encontrarse dentro del directorio "Sopa_de_letras"

### Suma de matrices:
Para los ejemplares de la suma de matrices se requiere que existan dos archivos cuyos nombre serán:
 * "Matriz.txt"
 * "Matriz1.txt"
Y deberan encontrarse dentro del directorio "Suma_Matrices".
Los ejemplares deberán contener, cada uno, una matriz de números (pueden ser decimales) separados por un
espacio, por ejemplo:
```txt
1.0 1.0 1.0 
1.0 1.0 1.0
1.0 1.0 1.0
```

## Ejecución
Para Los filtros tanto concurrente como secuencial:
```console
$ javac -d . src/Filtros/*.java
$ java Filtros.FiltrosConcurrente
```

Para la sopa de letras secuencial:
```console
$ javac -d . src/Filtros/Sopa.java
$ java Sopa_de_letras.Sopa
```

Para la sopa de letras concurrente:
```console
$ javac -d . src/Sopa_de_letras/SopaConcurrente.java
$ java Sopa_de_letras.SopaConcurrente
```

Para la suma de matrices secuencial:
```console
$ javac -d . src/Suma_Matrices/SumaMatrices.java
$ java Suma_Matrices.SumaMatrices 
```

Para la suma de matrices concurrente:
```console
$ javac -d . src/Suma_Matrices/SumaMatricesConcurrente.java
$ java Suma_Matrices.SumaMatricesConcurrente
```
