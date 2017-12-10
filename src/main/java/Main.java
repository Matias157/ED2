
import utfpr.dainf.ct.ed.exemplo.ArvoreBinaria;
import utfpr.dainf.ct.ed.exemplo.ArvoreBinariaPesquisa;

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
 * 
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class Main {
    public static void main(String[] args) {
        ArvoreBinariaPesquisa<Character> raiz = new ArvoreBinariaPesquisa<>('G');
        
        raiz.insere('F');
        raiz.insere('A');
        raiz.insere('H');
        raiz.insere('D');
        raiz.insere('B');
        raiz.insere('K');
        raiz.insere('P');
        raiz.insere('Z');
        raiz.insere('V');
        
        System.out.println("\n");
        raiz.visitaPosOrdem();
        
        ArvoreBinariaPesquisa<Character> no;
        System.out.println("\n\n PROXIMO EM NIVEL");
        while ((no = raiz.proximoEmNivel()) != null) {
            System.out.print(" " + no.getDado());
        }
        
        System.out.print("\n" + raiz.sucessor(raiz).getDado());
        System.out.print("\n" + raiz.sucessor(raiz).getDado());
        System.out.print("\n" + raiz.sucessor(raiz).getDado());
        System.out.print("\n" + raiz.sucessor(raiz).getDado());
        System.out.print("\n" + raiz.sucessor(raiz).getDado());
        System.out.print("\n" + raiz.sucessor(raiz).getDado());
        System.out.print("\n" + raiz.sucessor(raiz).getDado());
        System.out.print("\n" + raiz.sucessor(raiz).getDado());
        System.out.print("\n" + raiz.sucessor(raiz));
        
        System.out.print("\n" + raiz.predecessor(raiz));
        System.out.print("\n" + raiz.predecessor(raiz).getDado());
        System.out.print("\n" + raiz.predecessor(raiz).getDado());
        System.out.print("\n" + raiz.predecessor(raiz).getDado());
        System.out.print("\n" + raiz.predecessor(raiz).getDado());
        System.out.print("\n" + raiz.predecessor(raiz).getDado());
        System.out.print("\n" + raiz.predecessor(raiz).getDado());
        System.out.print("\n" + raiz.predecessor(raiz).getDado());
        System.out.print("\n" + raiz.predecessor(raiz).getDado());
        
        
        ArvoreBinariaPesquisa<Character> teste = new ArvoreBinariaPesquisa<Character>('A');
        System.out.println("\n PESQUISA");
        no = raiz.sucessor(null);
        if(no != null)
            System.out.println(no.getDado());
        
        while(raiz != null)
        {
            //System.out.println(raiz.getDado());
            raiz = raiz.exclui(raiz);
        }
        
        /*
        ArvoreBinariaPesquisa<String> node;
        node = raiz;
        if(node != null)
            System.out.println("\n Raiz: " + node.getDado() );
        node = raiz.pesquisa("A");
        if(node != null)
            System.out.println("\n Pesquisa: " + node.getDado() );
        node = raiz.getMaximo();
        if(node != null)
            System.out.println("\n Maximo: " + node.getDado() );
        node = raiz.getMinimo();
        if(node != null)
            System.out.println("\n Minimo: " + node.getDado() );
        node = raiz.sucessor(raiz);
        if(node != null)
            System.out.println("\n Sucessor: " + node.getDado() );
        node = raiz.predecessor(raiz);
        if(node != null)
            System.out.println("\n Predecessor: " + node.getDado() );
        
        raiz = raiz.exclui(raiz);
        raiz.visitaEmOrdem();
        */
    }
}
