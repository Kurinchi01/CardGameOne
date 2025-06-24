package com.kuri01.Game.ServerComu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Json;
import com.kuri01.Game.RPG.Model.ItemSystem.Item;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Verwaltet die gesamte Netzwerkkommunikation mit dem Spiel-Server.
 * Diese Klasse kapselt die Komplexität von HTTP-Anfragen und JSON-Verarbeitung.
 */
public class NetworkManager {

    // Die Basis-URL deines lokal laufenden Servers.
    private final String BASE_URL = "http://10.0.2.2:8080/api";
    // Eine Instanz des JSON-Parsers von LibGDX.
    private final Json json = new Json();

    /**
     * Sendet eine Anfrage an den Server, um eine neue Runde für ein bestimmtes Kapitel zu starten.
     *
     * @param chapterId Die ID des zu startenden Kapitels.
     * @param successCallback Eine Funktion (Callback), die bei einer erfolgreichen Antwort mit den Rundendaten aufgerufen wird.
     * @param errorCallback Eine Funktion, die im Fehlerfall mit der Fehlermeldung aufgerufen wird.
     */
    public void startNewRound(long chapterId, final Consumer<RoundStartData> successCallback, final Consumer<Throwable> errorCallback) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(BASE_URL + "/rounds/start/" + chapterId);

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String responseString = httpResponse.getResultAsString();
                Gdx.app.log("NetworkManager", "Runde gestartet Antwort: " + responseString);

                // Wandle den JSON-String in unser RoundStartData-Objekt um.
                final RoundStartData data = json.fromJson(RoundStartData.class, responseString);

                // Führe den Erfolgs-Callback im Haupt-Thread von LibGDX aus.
                Gdx.app.postRunnable(() -> successCallback.accept(data));
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.error("NetworkManager", "Fehler beim Starten der Runde", t);
                Gdx.app.postRunnable(() -> errorCallback.accept(t));
            }

            @Override
            public void cancelled() {
                Gdx.app.error("NetworkManager", "Anfrage zum Starten der Runde wurde abgebrochen.");
            }
        });
    }

    /**
     * Sendet eine Anfrage an den Server, um eine laufende Runde zu beenden.
     *
     * @param roundId Die ID der zu beendenden Runde.
     * @param successCallback Eine Funktion, die bei Erfolg mit der Liste der Belohnungen aufgerufen wird.
     * @param errorCallback Eine Funktion für den Fehlerfall.
     */
    public void endRound(String roundId, final Consumer<List<Item>> successCallback, final Consumer<Throwable> errorCallback) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(BASE_URL + "/rounds/" + roundId + "/end");
        request.setHeader("Content-Type", "application/json");

        // Erstelle den Request-Body (die Daten, die wir an den Server senden).
        // Für den "Walking Skeleton" verwenden wir feste Werte.
        RoundEndRequest endRequest = new RoundEndRequest(RoundOutcome.WIN, Collections.emptyList(), 120.0);
        String requestBodyJson = json.toJson(endRequest);
        request.setContent(requestBodyJson);

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String responseString = httpResponse.getResultAsString();
                Gdx.app.log("NetworkManager", "Runde beendet Antwort: " + responseString);

                // Hier parsen wir eine Liste von Items.
                final List<Item> data = json.fromJson(List.class, Item.class, responseString);
                Gdx.app.postRunnable(() -> successCallback.accept(data));
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.error("NetworkManager", "Fehler beim Beenden der Runde", t);
                Gdx.app.postRunnable(() -> errorCallback.accept(t));
            }

            @Override
            public void cancelled() {
                Gdx.app.error("NetworkManager", "Anfrage zum Beenden der Runde wurde abgebrochen.");
            }
        });
    }
}
