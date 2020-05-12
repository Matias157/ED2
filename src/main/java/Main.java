
import utfpr.dainf.ct.ed.exemplo.ArvoreBinaria;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * 
 * Cria e percorre a seguinte árvore binária:
 *       
 *                    8
 *                   / \
 *                  /   \
 *                 3     10
 *                / \     \
 *               1   6     14 
 *                  / \    /
 *                 4   7  13
                    \
                     5
 * 
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class Main {
    public static void main(String[] args) {
        // cria a raiz da árvore binária
        ArvoreBinaria<Integer> a = new ArvoreBinaria<>(8);
        
        // monta o ramo esquerdo
        ArvoreBinaria<Integer> e = a.insereEsquerda(3);
        e.insereEsquerda(1);
        ArvoreBinaria<Integer> d = e.insereDireita(6);
        d.insereEsquerda(4).insereDireita(5);
        d.insereDireita(7);

        // monta o ramo direito
        a.insereDireita(10).insereDireita(14).insereEsquerda(13);
        
        System.out.println("EM ORDEM");
        System.out.println("PERCURSO RECURSIVO");
        a.visitaEmOrdem();
        System.out.println("\nPERCURSO ITERATIVO");
        ArvoreBinaria<Integer> no;
        while ((no = a.proximoEmOrdem()) != null) {
            System.out.print(" " + no.getDado());
        }
        
        a.setInicioTrue();
        System.out.println("\n\nPRE ORDEM");
        System.out.println("PERCURSO RECURSIVO");
        a.visitaPreOrdem();
        System.out.println("\nPERCURSO ITERATIVO");
        while ((no = a.proximoPreOrdem()) != null) {
            System.out.print(" " + no.getDado());
        }  
        
        a.setInicioTrue();
        System.out.println("\n\nPOS ORDEM");
        System.out.println("PERCURSO RECURSIVO");
        a.visitaPosOrdem();
        System.out.println("\nPERCURSO ITERATIVO");
        while ((no = a.proximoPosOrdem()) != null) {
            System.out.print(" " + no.getDado());
        }
        
        a.setInicioTrue();
        System.out.println("\n\nEM NIVEL");
        System.out.println("PERCURSO ITERATIVO");
        while ((no = a.proximoEmNivel()) != null) {
            System.out.print(" " + no.getDado());
        }  
    }
}
