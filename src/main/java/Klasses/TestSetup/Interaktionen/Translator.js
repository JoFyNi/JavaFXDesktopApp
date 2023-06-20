// Variablen zur Übergabe der Anmeldedaten an den Controller
var inputUsername = "";
var inputEmail = "";
var inputPassword = "";

// Funktion zur Überprüfung der Anmeldedaten
function login() {
  // Hier erfolgt die Überprüfung der Anmeldedaten
  // Wenn die Anmeldung erfolgreich ist, setze den Wert von inputUsername, inputEmail und inputPassword entsprechend
  inputUsername = document.getElementById("username").value;
  inputEmail = document.getElementById("email").value;
  inputPassword = document.getElementById("password").value;

  var returnValue = callJavaMethod();

  if(returnValue == true) {
    console.log('try sending!');
  } else if(returnValue == false) {
    console.log('fails');
  }


  // Beispielüberprüfung
  if (inputUsername === "test" && inputEmail === "test@example.com" && inputPassword === "password") {
    // Anmeldung erfolgreich
    handleSuccessfulLogin();
  } else {
    // Anmeldung fehlgeschlagen
    handleFailedLogin();
  }
}

function callJavaMethod() {
    var result;
  fetch('/api/callJavaMethod', {
    method: 'POST'
  })
  .then(response => {
    if (response.ok) {
      console.log('Java-Methode erfolgreich aufgerufen!');
      result = true;
    } else {
      console.log('Fehler beim Aufrufen der Java-Methode!');
      result = false;
    }
  })
  .catch(error => {
    console.error('Fehler beim Aufrufen der Java-Methode:', error);
  });
  return result;
}


function handleSuccessfulLogin() {
  // Eingabefelder grün markieren
  document.getElementById("username").style.borderColor = "green";
  document.getElementById("email").style.borderColor = "green";

  // Popup "Anmeldung erfolgreich" anzeigen
  showPopup("Anmeldung Erfolgreich");
}

// Funktion zur Behandlung der fehlgeschlagenen Anmeldung
function handleFailedLogin() {
  // Eingabefelder rot markieren
  document.getElementById("username").style.borderColor = "red";
  document.getElementById("email").style.borderColor = "red";

  // Popup "Anmeldung fehlgeschlagen" anzeigen
  showPopup("Anmeldung Fehlgeschlagen");
}

// Funktion zum Anzeigen eines Popups für eine bestimmte Dauer
function showPopup(message) {
  var popup = document.createElement("div");
  popup.className = "popup";
  popup.innerText = message;
  document.body.appendChild(popup);

  setTimeout(function() {
    popup.remove();
  }, 10000); // Popup für 10 Sekunden anzeigen
}

function executeFunctionBasedOnSignal(signal) {
    if (signal === "SIGNAL1") {
        // Funktion 1 ausführen
        function1();
    } else if (signal === "SIGNAL2") {
        // Funktion 2 ausführen
        function2();
    } else {
        // Standardaktion
        defaultFunction();
    }
}

function function1() {
    // Code für Funktion 1
}

function function2() {
    // Code für Funktion 2
}

function defaultFunction() {
    // Standardaktion
}

// Aufruf des Webdienstes, um das Signal von der Java-Methode zu erhalten
fetch('/api/signal')
    .then(function(response) {
        // Handle Response
        if (response.ok) {
            // Signal erfolgreich empfangen
            return response.text();
        } else {
            // Fehler beim Empfangen des Signals
            throw new Error('Fehler beim Empfangen des Signals');
        }
    })
    .then(function(signal) {
        // Signal erfolgreich empfangen
        executeFunctionBasedOnSignal(signal);
    })
    .catch(function(error) {
        // Fehler beim Empfangen des Signals
        console.log(error);
    });
