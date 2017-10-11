/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author someguy590
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        Scanner input = new Scanner(System.in);
        boolean isGameOver = false;
        char currentPlayer = 'X';
        
        // Prompt users for moves until game ends
        while (!isGameOver) {
            // Ask current player for move
            int row, column;
            System.out.print(currentPlayer + "'s turn: ");
            row = input.nextInt();
            column = input.nextInt();
            
            while (!isVaildMove(row, column, board)) {
                System.out.println("Invalid move: Try again");
                row = input.nextInt();
                column = input.nextInt();
            }
            
            // Update and display board
            board[row][column] = currentPlayer;
            printBoard(board);
            
            // Check board status
            if (isGameWon(board, currentPlayer))
                System.out.println(currentPlayer + " won");
            else if (isTie(board))
                System.out.println("Tie");
            
            isGameOver = isGameWon(board, currentPlayer) || isTie(board);
            
            if (currentPlayer == 'X')
                currentPlayer = 'O';
            else
                currentPlayer = 'X';
        }
    }
    
    public static boolean isVaildMove(int row, int column, char[][] board) {
        return (row >= 0 && row <= 2 &&
                column >= 0 && column <= 2 &&
                board[row][column] == '\0');
    }
    
    public static boolean isGameWon(char[][] board, char player) {
        // Check rows
        for (int i = 0; i < board.length; i++) {
            if (
                    board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player)
                return true;
        }
        
        // Check columns
        for (int j = 0; j < board.length; j++) {
            if (
                    board[0][j] == player &&
                    board[1][j] == player &&
                    board[2][j] == player)
                return true;
        }
        
        if (
                board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player)
            return true;
        
        if (
                board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player)
            return true;
        
        return false;
    }
    
    public static boolean isTie(char[][] board) {
        // Check if board is full
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.print("\n-------------\n");
        }
        
    }
    
}
