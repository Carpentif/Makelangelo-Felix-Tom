## Tests ajoutés lors de la tâche 2

Tout d'abord, nous avons ajouté des tests qui permettent de vérifier certains comportement liés à la classe QuadGraph qui est une structure de donnée utilisé pour trouver facilement des objets dans un
environnement 2D et qui est utile ici pour insertion et la recherche de point entre autre. Cette classe n'avait aucun test lié à celle-ci. L'idéal quand on ajoute des tests unitaires est de tester tous les 
scénarios possibles dans un test chacun en faisant attention aux 'edge cases'. Pour ce travail, nous avons préféré diversifier un peu les méthodes et comportements testés à la place d'em tester 1 ou 2 dans leur
entièreté.

- Test 1 (testInsert2DPointInSite)
[Lien du test](https://github.com/Carpentif/Makelangelo-Felix-Tom/blob/28a121577bd009ba571eef39a118e0d232d14de0/src/test/java/com/marginallyclever/convenience/QuadGraphTest.java#L10C5-L24C6)

Ce test permet de vérifier que notre 'happy path' fonctionne. Un happy path est un scénario basique et simple. Ça nous permet donc de voir que la méthode pour insérer un point 2D dans un QuadGraph
fonctionne bien avec un point valide.

- Test 2 (testInsert2DPointInFullQuadGraph)
[Lien du test](https://github.com/Carpentif/Makelangelo-Felix-Tom/blob/28a121577bd009ba571eef39a118e0d232d14de0/src/test/java/com/marginallyclever/convenience/QuadGraphTest.java#L26C4-L51C6)

Ce test permettait de vérifier un autre scénario dans la méthode d'insertion et donc une autre branche de condition qui vérifiait bien qu'un point pouvait être ajouté même si notre QuadGraph
avait atteint la limite maximale de point (5). Ce test vérifie donc que le quadGraph sera 'split' en plusieurs quadGraph plus petit (4, 1 pour chaque quadrant) et que le point sera ajouté correctement
au quadrant adéquat.

- Test 3 (testSearchingPointWithSite)
[Lien du test](https://github.com/Carpentif/Makelangelo-Felix-Tom/blob/28a121577bd009ba571eef39a118e0d232d14de0/src/test/java/com/marginallyclever/convenience/QuadGraphTest.java#L53C5-L68C6)

Ce test permet de vérifier un 'happy path' sur la recherche d'un point dans un QuadGraph. En effet, ce test vérifie bien que lorsque l'on cherche unn point précis dans QuadGraph qui le contient
on obtiendra bel et bien ce point en retour.

- Test 4 (testSearchingPointWithSiteAndChildrenEmpty)
[Lien du test](https://github.com/Carpentif/Makelangelo-Felix-Tom/blob/28a121577bd009ba571eef39a118e0d232d14de0/src/test/java/com/marginallyclever/convenience/QuadGraphTest.java#L70C5-L90C6)

Ce test vérifie un autre comportement de la recherche de point dans QuadGraph en créant ici un QuadGraph subdivisé en 4 quadrants (children) qui ne contient aucun point. De cette façon, on 
fait la recherche du point dans chacun des enfants et on va retourner aucun point puisqu'aucun des quadrants ne contient le point recherché. 

## 

Le prochain test était pour un des helpers (DrawingHelper) qui a certaines méthodes qui aident au dessin : drawCircle, drawRectangle, etc. Pour ce test, on a décidé de tester la méthode qui permet
de charger une texture.

- Test 5 (testLoadTexture)
[Lien du test](https://github.com/Carpentif/Makelangelo-Felix-Tom/blob/28a121577bd009ba571eef39a118e0d232d14de0/src/test/java/com/marginallyclever/convenience/helpers/DrawingHelperTest.java#L11C5-L23C6)

Ici on teste simplement que des mauvais fichiers fournis en entrée retourneront bel et bien une Texture nulle (null). Comme c'est un test plus simple, on a décidé de le faire en parameterizedTest avec plusieurs
noms de fichiers invalides (mauvais type, non existant, etc.) pour vérifier qu'ils retournent bel et bien chacun un résultat nul.

##

- Test 6



- Test 7



- Test 8



- Test 9



- Test 10


