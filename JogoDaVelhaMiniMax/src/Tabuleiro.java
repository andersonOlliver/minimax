/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olliver
 */
public class Tabuleiro {

    static char[] conversao = {'o', ' ', 'x'};
    static int[][] tabuleiro;
    int tam;
    String divisor;

    public Tabuleiro(int tam) {
        this.tam = tam;
        tabuleiro = new int[tam][tam];
        divisor = gerarDivisor();
    }

    public void fazerJogada(int linha, int coluna) {
        if (tabuleiro[linha][coluna] == 0) {
            tabuleiro[linha][coluna] = -1;
        } else {
            System.out.println("Posição já ocupada, perdeu a vez!");
        }
    }

    public void imprimir() {
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                System.out.printf("%c %c", conversao[tabuleiro[i][j] + 1], j == (tam - 1) ? ' ' : '|');
            }
            if (i != (tam - 1)) {
                System.out.println(divisor);
            }
        }
        System.out.println("\r\n");
    }

    public String gerarDivisor() {
        String d = new String("\r\n");

        for (int i = 0; i < (tam - 1); i++) {
            d += "--+";
        }
        d += "--";

        return d;
    }

}
