package com.company.game;

public class Player {

    Game game;
    int order;

    public void createGame() {
        game = new Game(this);
        order = 1;
    }

    public void joinGame() {
        //
        Game availableGame = new Game(this);

        game = availableGame.join(this);
        order = 2;
    }

    public int submitMove(int i, int j) {
        if (game == null)
            return -2;
        if (game.player2 == null)
            return -1;
        if( game.turn != order)
            return 0;

        if (game.isOver()){
            game = null;
            return 1;
        }

        // make move

        if(game.isOver()) {
            game = null;
            return 2;
        }

        return 3;
    }
}
