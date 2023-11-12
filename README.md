# citizen_manager
Application web permettant l'enregistrement des citoyens et des logements, ainsi que pour faciliter la recherche  d'une maison de location
#Principe d'acces aux endpoints CRUD
Les CRUD des differentes entites sont exposes sous forme des RESTAPI grace une annotation particuliere, prenons un exemple:
#Exemple sur la table: User
POST: localhost:8080/users pour effectuer un post
GET: localhost:8080/users pour faire du getAll
GET: localhost:8080/users/id pour faire un getById
PUT: localhost:8080/users/id pour effectuer une effectuer sur l'objet id
DELETE: localhost:8080/users/id pour supprimer
#Ceci est valable pour tout les autres entites
#Remarque: au niveau de ses endpoints, l'entites est suffixe d'un "s" et ecrite en miniscule
