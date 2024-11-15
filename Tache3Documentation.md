## Modification de la github action
Pour commencer, on a dû se renseigner sur comment pouvoir lancer plusieurs fois un build avec des paramètres différents.
On a trouvé l'option d'utiliser une matrice avec l'option matrix qui permettra de lancer un build avec un paramètre 
différent à chaque fois. [Documentation matrice](https://docs.github.com/en/actions/writing-workflows/choosing-what-your-workflow-does/running-variations-of-jobs-in-a-workflow).
On a donc une matrice qui contient les différents flags de la jvm.
https://github.com/Carpentif/Makelangelo-Felix-Tom/blob/238fb64837cf52dc9231d1b8951b97aa236b9c31/.github/workflows/test.yml#L12

Ensuite, on a ajouté des informations dans le log de la console lorsqu'on effectue les différentes étapes pour build
pour faire en sorte que ce soit clair avec quel flag chaque étape est effectué. 
https://github.com/Carpentif/Makelangelo-Felix-Tom/blob/238fb64837cf52dc9231d1b8951b97aa236b9c31/.github/workflows/test.yml#L28

Il nous manquait plus qu'à ajouter le code pour charger le flag dans la variable MAVEN_OPTS. 
Après quelques recherches et tests on a vu que de mettre MAVEN_OPTS en tant que variable d'environnement fait la même
chose que la commande export MAVEN_OPTS="..." et comme on utilise une matrice qui effectue des builds en parallèle on a
pas besoin d'effectuer unset MAVEN_OPTS, car la variable d'environnement est indépendante pour chaque build effectué.
https://github.com/Carpentif/Makelangelo-Felix-Tom/blob/238fb64837cf52dc9231d1b8951b97aa236b9c31/.github/workflows/test.yml#L25

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

### 3) -XX:+UseLargePages (type TLB)
Ce flag a pour objectif d'optimiser le tlb (Translation-Lookaside Buffer). Le tlb est un cache qui garde les liens entre
les addresses virtuelles et physiques récemment utilisée. Si on a un tlb miss, qui apparaît lorsque le lien n'est pas
dans le cache, ça demande plus de ressources en devant accéder à la mémoire plusieurs fois. En utilisant ce flag on 
augmente la taille des pages ce qui permet à une entrée tlb d'avoir accès à plus de mémoire ce qui peut augmenter la
performance des applications qui utilisent beaucoup de mémoire en réduisant le nombre de tlb miss.

Source : https://www.oracle.com/java/technologies/javase/largememory-pages.html

### 4) -XX:+PrintGCDetails (type Print)
Ce flag permet d'obtenir des informations détaillées sur le Garbage Collector pendant l'exécution des tests. C'est pertinent pour nos tests en particulier (tests 9 et 10), car ces opérations peuvent créer beaucoup d'objets temporaires en mémoire. Les infos fournis nous permettent de voir quand et comment la mémoire est libérée, identifier d'éventuels problèmes de performance liés a la gestion mémoire, optimiser l'utilisation de la mémoire pour le traitement d'images.

Source : https://www.oracle.com/technical-resources/articles/java/g1gc.html

### 5) -XX:+UseParallelGC (type GC)
Ce flag active le Parallel Garbage Collector, particulièrement efficace pour les applications effectuant beaucoup de calculs comme nos tests mathématiques (tests 6-8). Voici les avantages: on a une meilleure performance pour les calculs de maths intensifs grâce à la parallélisation, une gestion optimisée de la mémoire pour les objets temporaires lors des calculs, et surtout pertinant pour les apps multi-thread qui nécessitent un débit élevé.

Source : https://docs.oracle.com/en/java/javase/17/gctuning/parallel-collector1.html

Pour bien finir la session voici un ascii art de notre professeur (réalisé à la main ! (pas du tout)).
![Art](ascii-art_Benoit.png)

Merci 
