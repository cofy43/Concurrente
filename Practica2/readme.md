# Práctica 2

## Table de contenido
* [Estructura del repositorio]
* [Tecnología utilizada]
* Ejecución

## Estructura del repositorio
    .
    ├── src                             # Archivos java
    │   ├── Filtros                     # Directorio con los filtros: 
    │   │   ├──Filtros.java
    │   │   ├──FiltrosConcurrente.java
    └── README.md


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
