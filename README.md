# Test d'Associativité des Flottants en Java

Ce projet teste l'associativité des nombres flottants en Java, vérifiant si l'égalité suivante est respectée pour diverses valeurs flottantes :  
$$x + (y + z) == (x + y) + z$$

## Structure du Projet

Le projet est organisé en plusieurs dossiers et fichiers, chacun ayant un rôle spécifique :

- **analysis/**  
  Contient les analyses de données en lien avec les tests d'associativité. Notamment :
  - `decision-tree-lab-report.ipynb` : Un rapport sous forme de notebook Jupyter, qui présente une analyse des résultats des tests avec des modèles de régression, notamment un arbre de décision, pour évaluer les facteurs qui influencent le succès de l'associativité.

- **main/** 
  Regroupe les fichiers principaux de l'implémentation en Java :
  - `PropertyChecker.java` : Le fichier principal en Java qui contient le code pour tester l'associativité des nombres flottants avec une approche du type runtime parameter-based. Celui-ci testera l'associativité avec plusiseurs type d'opérations, de répétition et de précision.
  - `variability_results.csv` : Un fichier de résultats contenant les données de variabilité obtenues lors des tests, utilisées pour des analyses ultérieures.

- **serialized/pushed**  
  Contient la sortie du script java de `FloatingPointAssociativityWithError.java`.
  - answer_associativity.txt : Fichier contenant le taux de réussite de l'opération principale testée avec le fichier java `FloatingPointAssociativityWithError.java`
  
- `Dockerfile` : Fichier Docker pour créer une image du programme et exécuter les tests d'associativité dans un environnement conteneurisé

- `FloatingPointAssociativityWithError.java` : Fichier contenant le code java pour effectuer les tests d'associativité des flottans pour l'opération initiale

## Prérequis

Pour exécuter ce programme, vous aurez besoin de :
- **Java Development Kit (JDK)** installé sur votre machine (version testée : 21.0.2).
- Un **environnement de développement** (IDE) comme **IntelliJ IDEA** ou **VSCode** ou un simple éditeur de texte avec ligne de commande.

## Résultat attendu

Voici un exemple de sortie attendue du programme :

```java
Percentage of associativity error: 8.203125%
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
- La **précision** des calculs
- Les **opérations** effectuées
