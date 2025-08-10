package com.kuri01.Game.ServerComu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kuri01.Game.DTO.PlayerActionQueueDTO;
import com.kuri01.Game.DTO.PlayerDTO;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;


import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Verwaltet die gesamte Netzwerkkommunikation mit dem Spiel-Server.
 * Diese Klasse kapselt die Komplexität von HTTP-Anfragen und JSON-Verarbeitung.
 * Sie ist dafür verantwortlich, Anfragen zu senden und die Antworten asynchron zu verarbeiten.
 */
public class NetworkManager {

    // Die Basis-URL deines lokal laufenden Servers.
    // 10.0.2.2 ist die spezielle IP-Adresse, um vom Android-Emulator auf den localhost deines Computers zuzugreifen.
    // Wenn du den Desktop-Client testest, verwende stattdessen "http://localhost:8080/api".
    private final String BASE_URL = "http://10.0.2.2:8080/api";

    // Eine Instanz des JSON-Parsers von LibGDX.
    private final Gson gson = new Gson ();




    public void sendAction(PlayerActionQueueDTO listDto, String jwtToken, final Consumer<PlayerActionQueueDTO> successCallback, final Consumer<Throwable> errorCallback){
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(BASE_URL + "/inventory/actions");

        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Bearer " + jwtToken);
        String requestBodyJson = gson.toJson(listDto);

        request.setContent(requestBodyJson);

        Gdx.app.log("NetworkManager-DEBUG", "Sende Action mit Body: " + requestBodyJson);

        sendRequest(request, PlayerActionQueueDTO.class,successCallback,errorCallback,"sendAction");

    }
    /**
     * Ein unsicherer Login nur für Entwicklungszwecke.
     * @param username Ein beliebiger Benutzername, um einen Spieler zu finden oder zu erstellen.
     * @param successCallback Callback, der bei Erfolg die LoginResponse (mit dem JWT) erhält.
     * @param errorCallback Callback für den Fehlerfall.
     */
    public void devLogin(String username, final Consumer<LoginResponse> successCallback, final Consumer<Throwable> errorCallback) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(BASE_URL + "/auth/dev/login");
        request.setHeader("Content-Type", "application/json");

        DevLoginRequest loginRequest = new DevLoginRequest(username);
        String requestBodyJson = gson.toJson(loginRequest);
        request.setContent(requestBodyJson);
        Gdx.app.log("NetworkManager-DEBUG", "Sende folgenden Body: " + requestBodyJson);
        sendRequest(request, LoginResponse.class, successCallback, errorCallback, "DevLogin");
    }

    /**
     * Startet eine neue Runde für ein gegebenes Kapitel. Benötigt ein JWT zur Authentifizierung.
     * @param chapterId Die ID des zu startenden Kapitels.
     * @param jwtToken Das Authentifizierungs-Token des Spielers.
     * @param successCallback Callback für die erfolgreiche Antwort.
     * @param errorCallback Callback für den Fehlerfall.
     */
    public void startNewRound(long chapterId, String jwtToken, final Consumer<RoundStartData> successCallback, final Consumer<Throwable> errorCallback) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(BASE_URL + "/rounds/start/" + chapterId);

        // Fügt das JWT zum Authorization-Header hinzu. Dies ist der "Ausweis" für den Server.
        request.setHeader("Authorization", "Bearer " + jwtToken);

        sendRequest(request, RoundStartData.class, successCallback, errorCallback, "StartRound");
    }

    /**
     * Beendet eine Runde. Benötigt ein JWT zur Authentifizierung.
     * @param roundId Die ID der zu beendenden Runde.
     * @param jwtToken Das Authentifizierungs-Token des Spielers.
     * @param successCallback Callback für die erfolgreiche Antwort (erhält eine Liste der Belohnungs-Items).
     * @param errorCallback Callback für den Fehlerfall.
     */
    public void endRound(String roundId, String jwtToken, final Consumer<List<Item>> successCallback, final Consumer<Throwable> errorCallback) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(BASE_URL + "/rounds/" + roundId + "/end");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Bearer " + jwtToken);

        // Erstelle den Request-Body
        RoundEndRequest endRequest = new RoundEndRequest(RoundOutcome.WIN, Collections.emptyList(), 120.0);
        request.setContent(gson.toJson(endRequest));

        // Wir benutzen hier eine spezielle Hilfsmethode, da gdx-json Listen anders behandelt.
        sendRequestWithList(request, Item.class, successCallback, errorCallback, "EndRound");
    }

    /**
     * Private Hilfsmethode, um eine Anfrage zu senden und die Antwort zu verarbeiten.
     * Dies vermeidet Code-Wiederholung.
     * @param request Die vorbereitete HTTP-Anfrage.
     * @param responseType Der Klassentyp der erwarteten Antwort.
     * @param successCallback Der Callback für den Erfolgsfall.
     * @param errorCallback Der Callback für den Fehlerfall.
     * @param logTag Ein Tag für die Log-Ausgaben.
     * @param <T> Der generische Typ der Antwort.
     */
    private <T> void sendRequest(Net.HttpRequest request, Class<T> responseType, Consumer<T> successCallback, Consumer<Throwable> errorCallback, String logTag) {
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                int statusCode = httpResponse.getStatus().getStatusCode();
                if (statusCode >= 200 && statusCode < 300) {
                    String responseString = httpResponse.getResultAsString();
                    Gdx.app.log(logTag, "Erfolgreiche Antwort: " + responseString);
                    try {
                        final T data = gson.fromJson(responseString, responseType);
                        // Führe den Erfolgs-Callback im Haupt-Thread von LibGDX aus.
                        Gdx.app.postRunnable(() -> successCallback.accept(data));
                    } catch (Exception e) {
                        Gdx.app.error(logTag, "Fehler beim Parsen der JSON-Antwort", e);
                        Gdx.app.postRunnable(() -> errorCallback.accept(e));
                    }
                } else {
                    String errorMsg = "Server antwortete mit Fehlercode: " + statusCode + " - " + httpResponse.getResultAsString();
                    Gdx.app.error(logTag, errorMsg);
                    Gdx.app.postRunnable(() -> errorCallback.accept(new RuntimeException(errorMsg)));
                }
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.error(logTag, "Netzwerkfehler", t);
                Gdx.app.postRunnable(() -> errorCallback.accept(t));
            }

            @Override
            public void cancelled() {
                String errorMsg = "Anfrage wurde abgebrochen.";
                Gdx.app.error(logTag, errorMsg);
                Gdx.app.postRunnable(() -> errorCallback.accept(new RuntimeException(errorMsg)));
            }
        });
    }

    /**
     * Eine spezielle Version von sendRequest, die eine Liste von Objekten verarbeiten kann.
     */
    private <T> void sendRequestWithList(Net.HttpRequest request, Class<T> listContentType, Consumer<List<T>> successCallback, Consumer<Throwable> errorCallback, String logTag) {
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                int statusCode = httpResponse.getStatus().getStatusCode();
                if (statusCode >= 200 && statusCode < 300) {
                    String responseString = httpResponse.getResultAsString();
                    Gdx.app.log(logTag, "Erfolgreiche Antwort: " + responseString);
                    try {
                        Type listType = TypeToken.getParameterized(List.class, listContentType).getType();
                        final List<T> data = gson.fromJson(responseString, listType);
                        Gdx.app.postRunnable(() -> successCallback.accept(data));
                    } catch (Exception e) {
                        Gdx.app.error(logTag, "Fehler beim Parsen der JSON-Liste", e);
                        Gdx.app.postRunnable(() -> errorCallback.accept(e));
                    }
                } else {
                    // ... Fehlerbehandlung wie oben
                    String errorMsg = "Server antwortete mit Fehlercode: " + statusCode;
                    Gdx.app.error(logTag, errorMsg);
                    Gdx.app.postRunnable(() -> errorCallback.accept(new RuntimeException(errorMsg)));
                }
            }
            @Override
            public void failed(Throwable t) {
                Gdx.app.error(logTag, "Netzwerkfehler", t);
                Gdx.app.postRunnable(() -> errorCallback.accept(t));
            }

            @Override
            public void cancelled() {
                String errorMsg = "Anfrage wurde abgebrochen.";
                Gdx.app.error(logTag, errorMsg);
                Gdx.app.postRunnable(() -> errorCallback.accept(new RuntimeException(errorMsg)));
            }
        });
    }

    public void getPlayerProfile(String jwtToken, final Consumer<PlayerDTO> successCallback, final Consumer<Throwable> errorCallback) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl(BASE_URL + "/character/me");
        request.setHeader("Authorization", "Bearer " + jwtToken);

        String requestBodyJson = request.getContent();
        Gdx.app.log("NetworkManager-DEBUG", "Sende folgenden Body: " + requestBodyJson);

        sendRequest(request, PlayerDTO.class, successCallback, errorCallback, "GetPlayerProfile");
    }
}
