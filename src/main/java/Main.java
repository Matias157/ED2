
import utfpr.dainf.ct.ed.exemplo.ArvoreBinaria;

/*
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
 * 
 */
public class Main {
    public static void main(String[] args) {
        // cria a raiz da árvore binária
        ArvoreBinaria<Integer> a = new ArvoreBinaria<>(8);
        
        // monta o ramo esquerdo
        ArvoreBinaria<Integer> e = a.insereEsquerda(3);
        e.insereEsquerda(1);
        ArvoreBinaria<Integer> d = e.insereDireita(6);
        d.insereEsquerda(4);
        d.insereDireita(7);

        // monta o ramo direito
        a.insereDireita(10).insereDireita(14).insereEsquerda(13);
        
        ArvoreBinaria<Integer> a2 = a;
        ArvoreBinaria<Integer> a3 = a;
        ArvoreBinaria<Integer> a4 = a;
        
        System.out.println("PERCURSO RECURSIVO POST-ORDER");
        a.visitaPosOrdem();
        System.out.println("\nPERCURSO RECURSIVO PRE-ORDER");
        a.visitaPreOrdem();
        System.out.println("\nPERCURSO RECURSIVO IN-ORDER");
        a.visitaEmOrdem();
        System.out.println("\nPERCURSO ITERATIVO POST-ORDER");
        ArvoreBinaria<Integer> no;
        while ((no = a.proximoPosOrdem()) != null) {
            System.out.print(" " + no.getDado());
        }
        System.out.println("\nPERCURSO ITERATIVO PRE-ORDER");
        while ((no = a.proximoPreOrdem()) != null) {
            System.out.print(" " + no.getDado());
        }
        System.out.println("\nPERCURSO ITERATIVO IN-ORDER");
        while ((no = a.proximoEmOrdem()) != null) {
            System.out.print(" " + no.getDado());
        }
        System.out.println("\nPERCURSO ITERATIVO IN-LEVEL");
        while ((no = a.proximoEmNivel()) != null) {
            System.out.print(" " + no.getDado());
        }
        System.out.println(" ");
    }
}
