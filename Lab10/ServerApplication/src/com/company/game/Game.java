package com.company.game;

public class Game {

    Board board;
    Player player1, player2;
    public int turn = 1;

    public Game() {
    }
    public Game(Player player1) {
        board = new Board();
        this.player1 = player1;
    }

    public Game join(Player player2) {
        this.player2 = player2;
        return this;
    }

    public boolean isOver() {
        return false;
    }
}
