package com.kuri01.Game.ServerComu;

import com.kuri01.Game.Card.Model.Move;

import java.util.List;
/**
 * @param movesLog Liste von Zug-Objekten(Move)
 * @param outcome "WIN" or "LOOSE"
 *
 */
public record RoundEndRequest(
    RoundOutcome outcome,
    List<Move> movesLog,
    double timeTaken
) {}
