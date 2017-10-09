package utfpr.dainf.ct.ed.exemplo;

import java.util.*;
import java.util.Stack;

public class ArvoreBinaria<E> {
    
    private E dado;
    private ArvoreBinaria<E> esquerda;
    private ArvoreBinaria<E> direita;
    
    // para percurso iterativo
    private boolean inicio = true;
    private Stack<ArvoreBinaria<E>> pilha;
    private LinkedList<ArvoreBinaria<E>> lista;
    private ArvoreBinaria<E> ultimoVisitado;

    /**
     * Cria uma árvore binária com dado nulo na raiz.
     */
    public ArvoreBinaria() {
    }

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
    
    public void visitaPreOrdem(ArvoreBinaria<E> raiz)
    {
        if (raiz != null)
        {
            visita(raiz);
            ArvoreBinaria.this.visitaPreOrdem(raiz.esquerda);
            ArvoreBinaria.this.visitaPreOrdem(raiz.direita);
        }
    }
    
    public void visitaPosOrdem(ArvoreBinaria<E> raiz)
    {
        if (raiz != null)
        {
            ArvoreBinaria.this.visitaPosOrdem(raiz.esquerda);
            ArvoreBinaria.this.visitaPosOrdem(raiz.direita);
            visita(raiz);
        }
    }
    
    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaEmOrdem() {
        visitaEmOrdem(this);
    }
    
    public void visitaPreOrdem()
    {
        visitaPreOrdem(this);
    }
    
    public void visitaPosOrdem()
    {
        visitaPosOrdem(this);
    }
    
    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }
    
    private void inicializaLista() {
        lista = new LinkedList<>();
        lista.clear();
    }
    
    /**
     * Reinicia o percurso a partir do início.
     * Deve ser chamado após percorrer toda a árvore para realizar novo
     * percurso ou para voltar ao início a qualquer momento.
     */
    public void reinicia() {
        inicializaPilha();
        inicializaLista();
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
        else
        {
            inicio = true;
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
        if (!pilha.isEmpty() && ultimoVisitado != null)
        {
            ultimoVisitado = pilha.pop();
            resultado = ultimoVisitado;
            if(ultimoVisitado.direita != null)
            {
                pilha.push(ultimoVisitado.getDireita());
            }
            if(ultimoVisitado.esquerda != null)
            {
                pilha.push(ultimoVisitado.getEsquerda());
            }
        }
        else
        {
            inicio = true;
        }
        return resultado;
    }

    public ArvoreBinaria<E> proximoPosOrdem() 
    { 
        ArvoreBinaria<E> resultado = null;
        if(inicio)
        {
            reinicia();
            Stack<ArvoreBinaria<E>> Stackaux;

            Stackaux = new Stack<>();
            Stackaux.push(this);

            while (!Stackaux.isEmpty()) 
            {
                ArvoreBinaria<E> temp = Stackaux.pop();
                pilha.push(temp);
                if (temp.esquerda != null)
                    Stackaux.push(temp.getEsquerda());
                if (temp.direita != null)
                    Stackaux.push(temp.getDireita());
            }
            inicio = false;
        }
        
        if(!pilha.isEmpty())
            resultado = pilha.pop();
        else
            inicio = true;
        
        return resultado;
    }    
    
    public ArvoreBinaria<E> proximoEmNivel() 
    {
        if(inicio)
        {
            reinicia();
            inicio = false;
            lista.add(ultimoVisitado);
        }
        
        ArvoreBinaria<E> resultado = ultimoVisitado;
        
        if(!lista.isEmpty() && ultimoVisitado != null)
        {
            if (ultimoVisitado.esquerda != null) 
                lista.add(ultimoVisitado.getEsquerda());
            if (ultimoVisitado.direita != null) 
                lista.add(ultimoVisitado.getDireita());
            if (lista.size() != 1)
                ultimoVisitado = lista.get(1);
            lista.removeFirst();
        }
        else
            resultado = null;
        
        return resultado;
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
