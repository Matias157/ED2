package utfpr.dainf.ct.ed.exemplo;

import java.util.Stack;
import java.util.LinkedList;


/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * 
 * Exemplo de implementação de árvore binária.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós na árvore
 */
public class ArvoreBinaria<E> {
    
    private E dado;
    private ArvoreBinaria<E> esquerda;
    private ArvoreBinaria<E> direita;
    
    // para percurso iterativo
    private boolean inicio = true;
    private Stack<ArvoreBinaria<E>> pilha;
    private LinkedList<ArvoreBinaria<E>> fila;
    private Stack<ArvoreBinaria<E>> pilha2;
    private ArvoreBinaria<E> ultimoVisitado;

    /**
     * Cria uma árvore binária com dado nulo na raiz.
     */
    public ArvoreBinaria() {
    }

    /**
     * Cria uma árvore binária com dado {@code dado} na raiz.
     * @param valor O dado do nó raiz
     */
    public ArvoreBinaria(E dado) {
        this.dado = dado;
    }
    
    /**
     * Adiciona um nó à esquerda do nó corrente.
     * @param dado O dado associado ao nó inserido.
     * @return A árvore adicionada ao nó
     */
    public ArvoreBinaria<E> insereEsquerda(E dado) {
        ArvoreBinaria<E> e = esquerda;
        esquerda = new ArvoreBinaria<>(dado);
        esquerda.esquerda = e;
        return esquerda;
    }
    
    /**
     * Adiciona um nó à esquerda do nó corrente.
     * @param dado O dado associado ao nó inserido.
     * @return A árvore adicionada ao nó
     */
    public ArvoreBinaria<E> insereDireita(E dado) {
        ArvoreBinaria<E> d = direita;
        direita = new ArvoreBinaria<>(dado);
        direita.direita = d;
        return direita;
    }
    
    /**
     * Implementação padrão que exibe o dado armazenado no nó usando
     * o método {@code toString() }.
     * Pode ser sobrecarregado em classes derivadas para implementar outras
     * formas de visita.
     * @param no O nó a ser visitado
     */
    protected void visita(ArvoreBinaria<E> no) {
        System.out.print(" " + no.dado);
    }
    
    /**
     * Visita os nós da subárvore em-ordem.
     * @param raiz A raiz da subárvore
     */
    public void visitaEmOrdem(ArvoreBinaria<E> raiz) {
        if (raiz != null) {
            ArvoreBinaria.this.visitaEmOrdem(raiz.esquerda);
            visita(raiz);
            ArvoreBinaria.this.visitaEmOrdem(raiz.direita);
        }
    }
    
    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaEmOrdem() {
        visitaEmOrdem(this);
    }
    
    public void visitaPreOrdem(ArvoreBinaria<E> raiz) {
    if (raiz != null) {
        visita(raiz);
        ArvoreBinaria.this.visitaPreOrdem(raiz.esquerda);
        ArvoreBinaria.this.visitaPreOrdem(raiz.direita);
        }
    }
    
    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaPreOrdem() {
        visitaPreOrdem(this);
    }
    
    public void visitaPosOrdem(ArvoreBinaria<E> raiz) {
    if (raiz != null) {
        ArvoreBinaria.this.visitaPosOrdem(raiz.esquerda);
        ArvoreBinaria.this.visitaPosOrdem(raiz.direita);
        visita(raiz);
        }
    }
    
    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaPosOrdem() {
        visitaPosOrdem(this);
    }

    public void setInicioTrue(){
        inicio = true;
    }

    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }
    
    private void inicializaPilha2() {
        if (pilha2 == null) {
            pilha2 = new Stack<>();
        }
        pilha2.clear();
    }
    
    /**
     * Reinicia o percurso a partir do início.
     * Deve ser chamado após percorrer toda a árvore para realizar novo
     * percurso ou para voltar ao início a qualquer momento.
     */
    public void reinicia() {
        inicializaPilha();
        pilha.clear();
        ultimoVisitado = this;
        inicio = true;
    }
    
    /**
     * Retorna o dado do próximo nó em-ordem.
     * @return O dado do próximo nó em-ordem.
     */
    public ArvoreBinaria<E> proximoEmOrdem() {
        ArvoreBinaria<E> resultado = null;
        if (inicio) {
            reinicia();
            inicio = false;
        }
        if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.esquerda;
            }
            ultimoVisitado = pilha.pop();
            resultado = ultimoVisitado;
            ultimoVisitado = ultimoVisitado.direita;
        }
        return resultado;
    }

    public ArvoreBinaria<E> proximoPreOrdem() {
        ArvoreBinaria<E> resultado = null;
        if (inicio) {
            reinicia();
            inicio = false;
            pilha.push(ultimoVisitado);
        }
        if (!pilha.isEmpty() || ultimoVisitado != null) 
        {
            ultimoVisitado = pilha.pop();
            while(ultimoVisitado == null && !pilha.isEmpty()){
                ultimoVisitado = pilha.pop();
            }
            if(ultimoVisitado != null){
                pilha.push(ultimoVisitado.direita);
                pilha.push(ultimoVisitado.esquerda);
            }
            resultado = ultimoVisitado;
        }
        return resultado;
    }
    
    
    
    public ArvoreBinaria<E> proximoPosOrdem() {
        ArvoreBinaria<E> resultado = null;
        if (inicio) {
            reinicia();
            inicializaPilha2();
            inicio = false; 
            pilha.push(ultimoVisitado);
        }
        while(!pilha.isEmpty()){
            ultimoVisitado = pilha.pop();
            if(ultimoVisitado.esquerda != null){
                pilha.push(ultimoVisitado.esquerda);
            }
            if(ultimoVisitado.direita != null){
                pilha.push(ultimoVisitado.direita);
            }
            pilha2.push(ultimoVisitado);
        }
        if(!pilha2.isEmpty()){
            return(pilha2.pop());
        }
        return null;
    }
    
    public ArvoreBinaria<E> proximoEmNivel() {
        if (inicio) {
            reinicia();
            inicializaPilha2();
            inicio = false; 
            fila = new LinkedList<>();
            fila.add(ultimoVisitado);
        }
        ArvoreBinaria<E> resultado = null;
        if(!fila.isEmpty()) {
            ultimoVisitado = fila.remove();
            resultado = ultimoVisitado;
            if(ultimoVisitado.esquerda != null) fila.add(ultimoVisitado.esquerda);
            if(ultimoVisitado.direita != null) fila.add(ultimoVisitado.direita);
        }
    return(resultado);
    }
    
    /**
     * Retorna o dado armazenado no nó.
     * @return O dado armazenado no nó.
     */
    public E getDado() {
        return dado;
    }

    /**
     * Atribui um dado ao nó.
     * @param dado O dado a ser atribuído ao nó.
     */
    public void setDado(E dado) {
        this.dado = dado;
    }

    /**
     * Retorna a árvore esqueda.
     * @return A árvore esquerda.
     */
    protected ArvoreBinaria<E> getEsquerda() {
        return esquerda;
    }

    /**
     * Retorna a árvore direita.
     * @return A árvore direita.
     */
    protected ArvoreBinaria<E> getDireita() {
        return direita;
    }
    
}
