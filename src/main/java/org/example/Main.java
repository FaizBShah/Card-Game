package org.example;

import org.example.game.Game;
import org.example.player.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();

        System.out.println("Enter no. of players");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Player " + (i + 1) + "'s name");
            String playerName = sc.next();
            game.addPlayer(new Player(playerName));
        }

        game.start();

        while (game.isGameActive()) {
            game.playTurn();
        }

        System.out.println(game.getGameResult());
    }
}