# prosjekt-fullstack
Frivillig prosjekt i fullstack applikasjonsutvikling


Introduksjon

Dette prosjektet skal være et program for å kunne reservere seksjoner (deler) av et rom i en bygning. Brukere skal kunne velge et rom med valgte parametere og reservere rommet. Administrerende brukere har ansvar for å lage nye brukere, administrere eksisterende brukere, lage nye rom, bygninger og seksjoner, og administrere dem.

Systemutviklingsprosjektet i faget Systemutvikling 2 har vært en stor hjelp for dette prosjektet; det har gitt oss kunnskap om relevant programmering og hvordan samarbeide effektivt i en gruppe. Vi ønsker derfor å takke Systemutvikling 2, faglærere, gruppen vår og alle andre som har bidratt. Vi ønsker også å takke Full-stack applikasjonsutviklingsfaget, for å gi oss mulighet til å utfordre oss på denne oppgaven.

Kommentarer

Prosjektet har gjenbrukt kode fra systemutvikling 2-prosjektet. Dette fordi mye av koden hadde blitt lik uansett, og derfor mente vi det var unødvendig å skulle gjøre det om igjen. Det var også fordi dette var et stort prosjekt som hadde krevd altfor mye på for kort tid dersom vi ikke allerede hadde en del kode. Koden vi har gjenbrukt er mesteparten av konfigurerings- og komponentfilene på backend, og konfigureringsfiler på frontend.

Vi ble ikke så ferdige med prosjektet som vi skulle ønske at vi kunne ha blitt. Likevel har vi lært mye av prosessen. Med bedre planlegging, mer forkunnskaper og bedre tid, ville vi fått et bedre resultat enn vi har nå. 

Installasjon/Kjøring av programmet

Last ned zip-filen. Hent ut innholdet med ønsket verktøy til en ønsket lokasjon på datamaskinen.

Naviger til “prosjekt-fullstack”-mappen, enten i filutforsker eller i terminalen. Lag en ny mappe kalt “config.properties”. Denne skal se slik ut:

Database (her skal lenken til databasen som brukes, eks. mysql.stud.iie.ntnu.no)
Brukernavn database (her skal brukerens brukernavn til valgt database)
Passord database (her skal brukerens passord til valgt database)
Mailadresse (her skal mailadressen som skal brukes til å sende mail (ikke implementer))
Passord mailadresse (her skal passordet til mailadressen)

Åpne to terminaler. Naviger til mappen “prosjekt-fullstack” på begge terminalene. Dette kan gjøres ved cd-kommandoen på Windows eller ls-kommandoen på UNIX.

På ene terminalen, naviger til backend-mappen og kjør programmet:

“cd backend” eller “ls backend”
“.\mvnw spring-boot:run” eller “./mvnw spring-boot:run” (hvis ene mislykkes, prøv andre)

På andre terminalen, naviger inn til frontend-mappen og kjør programmet:

“cd frontend” eller “ls frontend”
“npm run serve”

For å koble seg til programmet, skriv inn “localhost:3000” i addressefeltet på en nettleser (kun testet i Google Chrome og Mozilla Firefox).


Krav for å bruke programmet

For å kunne bruke programmet trenger man en bruker. Dette lages av en administrerende bruker. Når brukeren er laget, skal brukeren bli kontaktet og få brukernavn og passord for å kunne logge inn. Dette gjøres nå manuelt via mail.

Filoversikt (generell)

Her er en overordnet oversikt over viktige mapper for frontend og backend:

Filer på frontend:
Assets
Components
Enums
Interfaces
Router
Store
Views

Filer på backend:
Controller-klasser
Service-klasser
DTO-klasser
Entitetsklasser
Repository interfaces
Konfigurasjonsfiler (sikkerhet, database og mail)
Komponenter (sikkerhet og mail)


Kjente bugs

Får ikke hentet id fra props (frontend)
Kan ikke hente ut informasjon om sin egen bruker
Får ikke satt rollebegrensning på programmet (backend)
Sjekker ikke at det er en administrerende bruker som lager ny bruker
Laging av ny reservasjon sjekker ikke om seksjon/rom er ledig
Bruker må ha ny token når backend restarter
Admin-brukere kan ikke opprette rom eller seksjoner
Det sendes ikke ut mail til nye brukere når admin oppretter en ny bruker. Siden passordet autogenereres blir det dermed umulig å få logget inn på en bruker


Videre arbeid
Videre arbeid med applikasjonen ville i første omgang vært å ferdigstille funksjonaliteten til applikasjonen. Dette innebærer å sørge for at alle reservasjoner sjekkes slik at det ikke går an å dobbeltbooke seksjoner, samt at det er mulig å slette reservasjoner. Det ville også vært naturlig å implementere en funksjon slik at man kan reservere et helt rom. Deretter ville det vært naturlig å kunne filtrere rom på antall plasser når man skal reservere, samt se hvor mange plasser man reserverer. 
Som adminbruker ville det vært naturlig å implementere funksjonalitet som å endre, legge til og slette bygninger, rom og seksjoner, samt endre brukere. Deretter ville søk og filtrering av brukere og bygninger vært en naturlig implementasjon. Det ville også blitt prioritert å få på plass sending av mail, slik at man får send passordet til nye brukere som skal registreres. 
Som en naturlig del av videre arbeid ville også en nøyere planleggingsfase være med. 

