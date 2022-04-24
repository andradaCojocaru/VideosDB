# VideosDB
# Andrada-Ioana Cojocaru

In implementare am ales sa copiez entitatile in alte entitati la care am mai
adaugat diverse campuri:
MyActor - in plus average (media notelor filmelor so serialelor in care apare)
	si numberOfAwards (cate premii detine)
MyGenre - in plus videos (lista de video cu acel gen), numberOfViews
	(cate vizualizari are acel gen)

MyVideo - numberOfFavorites (in cate liste de favorite se afla),
	numberOfViews (de cate ori a fost vizionat), ratings (lista cu rating)
MyMovie - in plus numberOfRatings (cate rating-uri a primit)
MySerial - in plus numberOfRating (cate rating-uri a primit), timeSum (timp
	total serial)
MyUser - in plus numberOfRatings (numarul de rating-uri date), rated (ce
	video-uri a notat)
Acestea ajuta la simplificarea diverselor comenzi.
Ne dorim sa pastram o legatura intre entitati, astfel ca orice modificare vrem
sa se vada peste tot.
Comenzile (favorite, view, rating) - le aplicam atat asupra utilizatorului, cat si
video-ului, astfel ca daca aplicam favorite, acest lucru se va vedea atat in lista
de favorite ale userului, cat si in video la numarul de liste in care se regaseste
- acelasi lucru se intampla si in cazul view, aparand in history user, dar si in
numarul de vizualizari ale acestuia, de asemenea si in rating, aparand atat la
informatiile despre video, cat si la numarul de review date de utilizator
queries sunt de trei tipuri : aplicate asupra actorilor, userilor sau video-urilor
Actors  - in toate cazurile sortam folosind clasa SortActors
	- average (folosim clasa RatingActors pentru a actualiza ratingul acestora)
	- awards (folosim clasa AwardsActors pentru a actualiza premiile actorilor)
	- filter_description (folosim clasa FilterActors pentru a pastra doar
actorii ce contin diverse informatii in descriere)
Users - copiem toti utilizatorii ce au dat rating cel putin o data, iar apoi ii
	sortam folosind clasa SortUsers
Videos - se imparte in doua categorii : Movie si Serial pentru ambele aplicandu-se
aceleasi comenzi, insa pe entitati diferite
	- pentru sortarea filmelor folosim clasa SortMovie, iar pentru seriale
SortSerial
	- ratings calculam rating video, folosind clasa RatingVideo iar apoi sortam
	- favorite calculam in cate liste de favorite se gasesc (GetFavorite), iar
apoi sortam
	- longest calculam timpul pentru serial (TimeSerial) si apoi sortam
	- most_viewed sortam dupa numarul de vizonari
recommendations - observam doua tipuri : cu un raspuns (standard, best_unseen, popular,
favorite) si cu mai multe (search)
Cu un raspuns   - standard extragem primul video nevizualizat (GetVideoNotSeen)
		- best_unseen copiem video-urile ce au rating apoi le sortam dupa acesta
descrescator (SortVideo) apoi il extragem pe primul nevizualizat
PREMIUM		- popular se adauga numarul de vizionari pentru fiecare gen, apoi
se verifica daca video-ul din genul cel mai popular a fost vizionat
		- favorite in functie de numarul de liste in care apare drept favorit se
sorteaza (sortFavorite), iar apoi se cauta primul video nevizualizat
Cu array raspuns - search se filtreaza video-urile, apoi se sorteaza (sortSearch)
- in cazul comenzilor pentru useri premium se verifica faptul ca acestia apartin
categoriei

