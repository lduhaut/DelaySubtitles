# DelaySubtitles
Quelques lignes pour reculer/avancer le temps indiqué dans les fichiers srt.

Cette classe permet de réécrire le contenu d'un fichier de sous titres, et de décaler les temps inscrits. (Plutôt que de décaler systématiquement dans le lecteur vidéo). Gère également la re-numérotation des sous-titres, en supprimant tous les sous-titres commennçant avant 00:00:00 !

Je l'ai écrite pour pouvoir découper un sous-titre en plusieurs parties, souhaitant découper une vidéo en 10 extraits (et donc, avoir les 10 fichiers de sous titre correspondants).

# Usage
- Indiquez le chemin de votre fichier de sous titre dans la variable fileSub
- Indiquez le décalage souhaité en secondes dans la variable delayInSec
- Run, et récupérez le résultat dans la console
