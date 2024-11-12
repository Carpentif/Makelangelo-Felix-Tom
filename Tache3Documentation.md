## Modification de la github action
Pour commencer, on a dû se renseigner sur comment pouvoir lancer plusieurs fois un build avec des paramètres différents.
On a trouvé l'option d'utiliser une matrice avec l'option matrix qui permettra de lancer un build avec un paramètre 
différent à chaque fois. [Documentation matrice](https://docs.github.com/en/actions/writing-workflows/choosing-what-your-workflow-does/running-variations-of-jobs-in-a-workflow).
On a donc une matrice qui contient les différents flags de la jvm. Voici le [code] (lien matrice de flags)

Ensuite, on a ajouté des informations dans le log de la console lorsqu'on effectue les différentes étapes pour build
pour faire en sorte que ce soit clair avec quel flag chaque étape est effectué. [Exemple] (lien vers exemple ajout infos log)

Il nous manquait plus qu'à ajouter le code pour charger le flag dans la variable MAVEN_OPTS. 
Après quelques recherches et tests on a vu que de mettre MAVEN_OPTS en tant que variable d'environnement fait la même
chose que la commande export MAVEN_OPTS="..." et comme on utilise une matrice qui effectue des builds en parallèle on a
pas besoin d'effectuer unset MAVEN_OPTS, car la variable d'environnement est indépendante pour chaque build effectué.
On a donc ce [code] (lien code variable environnement)

Après ces changements, on a maintenant une action github qui build avec différents flags de la jvm spécifié dans la 
matrice.

## Choix des flags
### 1) 
### 2)
### 3)
### 4)
### 5)