/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olliver
 */
public class Sucessor {

    int[][] tabuleiro;
    int utilidade;

    public Sucessor(int[][] tab) {

        int tamanho = tab.length;
        tabuleiro = new int[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = tab[i][j];
            }
        }
    }

}
