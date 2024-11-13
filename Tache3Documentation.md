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
### 1) -Xmx512m (type Heap)
Ici ce flag permet de mettre une valeur maximale que peut utiliser notre heap dans la mémoire. Ici on le met à 512 MB.
On a donc un maximum de 512 MB pour l'allocation d'objets et de data dans notre heap. Ce flag est assez important et
peut avoir un impact sur la performance. Si on n'alloue pas assez de mémoire il se pourrait qu'on obtienne un 
OutOfMemoryError, car on manquerait de mémoire et si on allour une trop grande valeur on perd de la mémoire qui aurait
pû être utilisée ailleurs et aurait eu un meilleur impact sur la performance que d'assigner un surplus de mémoire ici.

Source : https://www.designgurus.io/answers/detail/what-is-xmx-and-xms

### 2) -XX:+UseStringDeduplication (type String)
Ici, ce flag permet de réduire la mémoire qua va utiliser les strings en général. En effet, on dit qu'environ 25% de la
mémoire des applications Java sont remplis par des strings et qu'environ 13.5% de cette mémoire est occupée par des
duplications de string. Avec ce flag on va essayer d'éliminer les duplications de string au processus du GC. Ce flag nous
permet donc de potentiellement réduire la mémoire utilisée par les strings. Il faut cependant faire attention, car ce flag
ne fonctionne qu'avec l'algorithme G1 pour le GC (activé par défaut dans notre cas).

Source: https://gceasy.io/gc-recommendations/stringdeduplication-solution.jsp

### 3) 
### 4)
### 5)