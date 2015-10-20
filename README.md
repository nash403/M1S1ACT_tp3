# TP3 Programmation dynamique
## Antoine PETIT & Honoré NINTUNZE

### Question 1

		      +3___
         / \   \
        /   \   \
       +1   -2   \
       | \  / \  |
       |  \/   \ |
       |  +1    +1
       |  |     /
       |  |    /
       |  |   /
       |  |  /
        \ | /
          0

### Question 2

* S'il existe au moins un successeur avec une valeur négative, prendre le successeur avec la plus grande valeur (On considère 0 comme un nombre négatif).
* S'il n'y a aucun successeur avec une valeur négative, prendre le successeur avec la plus grande valeur.

### Question 3

(10,7,7,3) s'exécute en 15 secondes alors que (10,7,5,3) s'exécute en 31 secondes.
La compléxité est exponentielle car on calcule énormément de fois la même chose.

### Question 4

(100, 100, 50, 50) = -198
(100, 100, 48, 52) =  191

### Question 5

Pour m = n = 127 et une valeur de 127, les couples (i,j) correspondant sont:
* (0,63)
* (63,0)
* (63,126)
* (126,63)

### Question 6

En considérent que m~n, on a une complexité en O(n^4)

### Question 7

On peut remarquer que pour un (m,n) donné, on a 4 couples (i,j) qui auront des valeurs identiques:
* (i,j)
* (i,n-j-1)
* (m-i-1,j)
* (m-i-1,n-j-1)

### Question 8

On constate effectivement une amélioration de l'ordre de + ou - 6 fois plus rapide.

### Question 9

L'amélioration est cette fois de l'ordre de + ou - 8 fois plus rapide que la version naive.