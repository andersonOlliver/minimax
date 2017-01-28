
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author olliver
 */
public class Jogo {

    static int TAMANHO = 3, PROFUNDIDADE = -1;

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro(TAMANHO);
        MiniMax ia = new MiniMax(TAMANHO, PROFUNDIDADE);

        System.out.println("Iniciando jogo!");

        tabuleiro.imprimir();
        do {
            int linha, coluna;
            System.out.printf("Sua vez seu lixo\r\nLinha [0 - %d]: ", (TAMANHO - 1));
            do {
                linha = entrada.nextInt();
                if (linha > 2 || linha < 0) {
                    System.out.println("Valor inválido");
                }
            } while (linha < 0 || linha > 2);

            System.out.printf("Coluna [0 - %d]: ", (TAMANHO - 1));
            coluna = entrada.nextInt();

            tabuleiro.fazerJogada(linha, coluna);
            tabuleiro.imprimir();

            if (!ia.testeTerminal(tabuleiro.tabuleiro)) {
                tabuleiro.tabuleiro = ia.decisaoMiniMax(tabuleiro.tabuleiro);
                System.out.println("Jogada do Computador");
                tabuleiro.imprimir();
            }
        } while (!ia.testeTerminal(tabuleiro.tabuleiro));

        if (ia.ganhou(tabuleiro.tabuleiro, 1)) {
            System.out.println("O computador ganhou");
        } else if (ia.ganhou(tabuleiro.tabuleiro, -1)) {
            System.out.println("Você ganhou!");
        } else {
            System.out.println("Não há vencedor!");
        }
    }
}
