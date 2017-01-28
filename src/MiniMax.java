
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author olliver
 */
public class MiniMax {

    static ArrayList<Sucessor> sucessores = new ArrayList();
    int tamanho, maxProf;

    public MiniMax(int tamanho, int maxProf) {
        this.tamanho = tamanho;
        if (maxProf > 0) {
            this.maxProf = maxProf;
        } else {
            this.maxProf = Integer.MAX_VALUE;
        }
    }

    public int[][] decisaoMiniMax(int[][] tabuleiro) {
        sucessores.clear();

        int v = valorMaximo(tabuleiro, true, 1);

        for (Sucessor s : sucessores) {
            if (s.utilidade == v) {
                return s.tabuleiro;
            }
        }
        return tabuleiro;
    }

    public int valorMaximo(int[][] tabuleiro, boolean primeiro, int profundidade) {

        if (profundidade++ > maxProf || testeTerminal(tabuleiro)) {
            return utilidade(tabuleiro);
        }

        int v = Integer.MIN_VALUE;

        for (Sucessor s : gerarSucessores(tabuleiro, 1)) {
            v = Math.max(v, valorMin(s.tabuleiro, profundidade));
            s.utilidade = v;

            if (primeiro) {
                sucessores.add(s);
            }
        }
        return v;
    }

    public int valorMin(int[][] tabuleiro, int profundidade) {
        if (profundidade++ > maxProf || testeTerminal(tabuleiro)) {
            return utilidade(tabuleiro);
        }

        int v = Integer.MAX_VALUE;

        for (Sucessor s : gerarSucessores(tabuleiro, -1)) {
            v = Math.min(v, valorMaximo(s.tabuleiro, false, profundidade));
            s.utilidade = v;
        }

        return v;
    }

    public List<Sucessor> gerarSucessores(int[][] tabuleiro, int valor) {
        List<Sucessor> sucessor = new ArrayList();

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == 0) {
                    tabuleiro[i][j] = valor;
                    sucessor.add(new Sucessor(tabuleiro));
                    tabuleiro[i][j] = 0;
                }
            }
        }
        return sucessor;
    }

    public boolean testeTerminal(int[][] tabuleiro) {
        return (ganhou(tabuleiro, 1) || ganhou(tabuleiro, -1) || semEspaco(tabuleiro));
    }

    public int utilidade(int[][] tabuleiro) {
        if (ganhou(tabuleiro, 1)) {
            return 1;
        } else if (ganhou(tabuleiro, -1)) {
            return -1;
        } else {
            return 0;
        }
    }

    public boolean ganhou(int[][] tabuleiro, int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (ganhouLinha(tabuleiro, i, valor) || ganhouColuna(tabuleiro, i, valor)) {
                return true;
            }
        }
        if (ganhouDiagonalPrincipal(tabuleiro, valor) || ganhouDiagonalSecundaria(tabuleiro, valor)) {
            return true;
        }
        return false;
    }

    private boolean ganhouLinha(int[][] tabuleiro, int l, int v) {
        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[l][i] != v) {
                return false;
            }
        }
        return true;
    }

    private boolean ganhouColuna(int[][] tabuleiro, int c, int v) {
        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[i][c] != v) {
                return false;
            }
        }
        return true;
    }

    private boolean ganhouDiagonalPrincipal(int[][] tabuleiro, int v) {
        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[i][i] != v) {
                return false;
            }
        }
        return true;
    }

    private boolean ganhouDiagonalSecundaria(int[][] tabuleiro, int v) {
        for (int i = 0; i < tamanho; i++) {
            if (tabuleiro[(tamanho - 1) - i][i] != v) {
                return false;
            }
        }
        return true;
    }

    public boolean semEspaco(int[][] tabuleiro) {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
