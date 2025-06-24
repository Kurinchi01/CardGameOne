package com.kuri01.Game.Auth;

import java.util.function.Consumer;

public interface AuthService {
    /**
     * Startet den Anmeldevorgang.
     * @param onSignInSuccess Callback, der bei Erfolg das Google-ID-Token zurückgibt.
     * @param onSignInFailure Callback für den Fehlerfall.
     */
    void signIn(Consumer<String> onSignInSuccess, Consumer<String> onSignInFailure);

    void signOut();
}
