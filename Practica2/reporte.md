---
title: 'Introducción a Hilos'
subtitle: 'Práctica 2'
author: 
    - Espinal Cruces Martin Felipe
    - Fernandez Romero Adrian Felipe
    - Sánchez de la Rosa César Gustavo
    - Velázquez Caballero Ixchel
date: \today
header-includes:
   - \usepackage[spanish]{babel}
output: 
  pdf_document2:
    citation_package: biblatex
bibliography: references.bib
link-citations: yes
---

# Pruebas

A continuación se muestra una tabla con su gráfica sobre los datos de la
ejecución del programa que calcula la suma de matrices.
El *Ejemplo 10* refiere a la suma de matrices de 10x10.
El *Ejemplo 100* refiere a la suma de matrices de 100x100.
Y *Ejemplo 1000* refiere a la suma de matrices de 1000x1000.

Cada uno se ejecuto con 1, 5, 10 y 100 hilos. El tiempo mostrado se saco con
*System.nanoTime()* que nos da el resultado en nanosegundos. 

|Hilos|Ejemplo 10|Ejemplo 100| Ejemplo 1000|
|-----|----------|-----------|-------------|
| 1   |104253115 |439985585  |11997192415  |
| 5   |109815895 |417259515  |12402395900  |
| 10  |108169795 |429797160  |12147244890  |
| 100 |108639685 |432270130  |12034210590  |


![Gráfica](./src/img/grafica.jpg){width=50%}


# Forma Secuencial

Las Especificaciones de mi Computadora son:

```bash
lscpu | grep -E '^Thread|^Core|^Socket|^CPU\('         
CPU(s):                          4
Thread(s) per core:              2
Core(s) per socket:              2
Socket(s):
```

## Suma Matrices

Tiempo Obtenido: 

|tamaño: |10	|100	|1000|
|--|--|--|--|
|--|79362950	|373211055|	13826151895

## Filtros

Tiempo Obtenido: `952106715`

## Sopa de Letras

Tiempo Obtenido: `120537135`

# Forma Concurrente
	
## Matrices    

        
| # Hilos | Aceleración Teórica | Aceleración Obtenida | % Código en Paralelo |
| ------- | ------------------- | -------------------- | -------------------- |
| 1       |      2.5            |        2.3           |         50           |
| 5       |      2.1            |        1.9           |         75           |
| 10      |      2.05           |        2             |         90           |
| 100     |      2.005          |        2             |         95           |


## Filtros

Resultados Tiempo de Ejecución:

|Hilos|-- |
|--|--|
|1	|864112935|
|5	|768961810|
|10	|756355100|
|100|919351060|
        
| # Hilos | Aceleración Teórica | Aceleración Obtenida | % Código en Paralelo |
| ------- | ------------------- | -------------------- | -------------------- |
| 1       |      2.5            |        2.1           |         50           |
| 5       |      2.1            |        1.7           |         75           |
| 10      |      2.05           |        2             |         90           |
| 100     |      2.005          |        2             |         95           |


## Sopa de Letras    
        
Resultados Tiempo de Ejecución:         

|Hilos|--|
|--|--|
|1	|240091825|
|5	|242650120|
|10	|239867275|
|100|238572190|

| # Hilos | Aceleración Teórica | Aceleración Obtenida | % Código en Paralelo |
| ------- | ------------------- | -------------------- | -------------------- |
| 1       |      2.5            |        2             |          50          |
| 5       |      2.1            |        1.9           |          75          |
| 10      |      2.05           |        2.04          |          90          |
| 100     |      2.005          |        2.01          |          95          |

1. ¿Hubo una mejora significativa?

Sí, al principio no es muy notable la mejora pero conforme se agregan más
operaciones se va haciendo más remarcada la diferencia entre uno y otro.

2. ¿Crees que si agregamos mas núcleos a nuestro CPU mejore el rendimiento o
   sera mejor aumentar la frecuencia de reloj? Justifica.

En mi caso sería mejor agregar más núcleos al CPU, cuento con una computadora ya
con el aumento en la frecuencia del reloj y si quisiera hacer operaciones más
pesadas tendría que agregar más núcleos.

3. ¿Qué pasaría si tuviéramos una cantidad infinita de hilos, mejoraría la
   ejecución o no?  ¿Hasta que nivel de mejora obtendremos?
		
Aunque se tuviera una cantidad infinita de hilos, La mejora obtenida en el
rendimiento de un sistema debido a la alteración de uno de sus componentes está
limitada por la fracción de tiempo que se utiliza dicho componente. Lo que
quiere decir es que es el algoritmo el que decide la mejora de velocidad, no el
número de procesadores/hilos.


# Referencias

---
nocite: '@**'
---
