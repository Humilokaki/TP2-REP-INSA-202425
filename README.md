# Test d'Associabilité des Flottants en Java

Ce test a pour but de tester l'associabilité des nombres flottants. Le programme examine si l'associabilité est vrai pour différente valeur de flottant : $$x+(y+z) == (x+y)+z$$

## Prérequis

Pour exécuter ce programme vous aurez besoin de :
- Java Development Kit (JDK) installé sur votre machine
- Un environnement développement (IDE) comme **IntelliJ IDEA** ou **VSCode** ou un simple fichier texte avec ligne de commande.
- My **javac** version : 21.0.2

## Résultat attendu

```java
Percentage of associativity error: 8,203125%
Total tests: 512, Tests where associativity failed: 42
```

## Etapes pour build et run l'image docker

- Build the Docker Image :
```bash
docker build -t associative-property .
```
- Run the Docker Container :
```bash
docker run associative-property
```
## Liste des facteurs qui ont un effet sur le résultat du programme
- Le **type** des variables utilisé
- Le **nombre** d'itération, les différentes valeurs de variables utilisées
