# GooseGame

L'applicazione implementata permette di poter giocare al gioco dell'oca. Non è un eseguibile ma fornisce una serie di funzionalità per poter essere integrate da sistemi esterni.

Per poter effettuare la build basta lanciare il comando: mvn install -f pom.xml

Durante la build verranno effettuati alcuni test automatici ed il lancio di alcune partite con un numero ben pre-definito di mosse.

## Funzionalità

Lo strumento espone una serie di metodi da poter essere utilizzati per muovere le pedine e aggiungere eventuali giocatori.

In particolare, lo strumento fornisce i metodi per:

- lanciare i dati;
- eseguire la mossa rispetto ai dati lanciati;
- aggiungere un giocatore.
