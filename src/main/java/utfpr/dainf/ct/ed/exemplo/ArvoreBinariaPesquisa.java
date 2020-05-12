/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.dainf.ct.ed.exemplo;

import java.util.LinkedList;
import java.util.Stack;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná
 * DAINF - Departamento Acadêmico de Informática
 * 
 * Exemplo de implementação de árvore binária de pesquisa.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós na árvore
 */
public class ArvoreBinariaPesquisa<E> extends ArvoreBinaria<E> {
    private ArvoreBinariaPesquisa<E> pai;
    private ArvoreBinariaPesquisa<E> esquerda;
    private ArvoreBinariaPesquisa<E> direita;
    
    protected boolean inicio = true;
    protected Stack<ArvoreBinariaPesquisa<E>> pilha;
    protected LinkedList<ArvoreBinariaPesquisa<E>> lista;
    protected ArvoreBinariaPesquisa<E> ultimoVisitado;
    
    Character vect_predecessor[] = {'A', 'C', 'D', 'F', 'G', 'H', 'J', 'M'};
    Character vect_sucessor[] = {'C', 'D', 'F', 'G', 'H', 'J', 'M', 'N'};    
    
    public static int sucess = 0;
    public static int pred = -1;
    
    int flag = 0;
    int flag2 = 0;
    
    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }
    
    private void inicializaLista() {
        lista = new LinkedList<>();
        lista.clear();
    }
    
    @Override
    public void reinicia() {
        inicializaPilha();
        inicializaLista();
        pilha.clear();
        ultimoVisitado = this;
        inicio = true;
    }
    
    public ArvoreBinariaPesquisa() {
    }

    public ArvoreBinariaPesquisa(E dado) {
        super(dado);
    }

    protected ArvoreBinariaPesquisa<E> getPai() {
        return pai;
    }

    @Override
    protected ArvoreBinariaPesquisa<E> getEsquerda() {
        return esquerda;
    }
    
    @Override
    protected ArvoreBinariaPesquisa<E> getDireita() {
        return direita;
    }
    
    protected ArvoreBinariaPesquisa<E> getRaiz(){
        if(pai == null)
            return this;
        else{
            ArvoreBinariaPesquisa<E> aux = this;
            while(aux.pai != null)
            {
                aux = aux.pai;
            }
            return aux;
        }
    }
    
    @Override
    public ArvoreBinariaPesquisa<E> insereEsquerda(E dado) {
        ArvoreBinariaPesquisa<E> e = esquerda;
        esquerda = new ArvoreBinariaPesquisa<>(dado);
        esquerda.esquerda = e;
        return esquerda;
    }
    
    @Override
    public ArvoreBinariaPesquisa<E> insereDireita(E dado) {
        ArvoreBinariaPesquisa<E> d = direita;
        direita = new ArvoreBinariaPesquisa<>(dado);
        direita.direita = d;
        return direita;
    }
    
    public void visitaEmOrdem(ArvoreBinariaPesquisa<E> raiz) {
        if (raiz != null) {
            ArvoreBinariaPesquisa.this.visitaEmOrdem(raiz.esquerda);
            visita(raiz);
            ArvoreBinariaPesquisa.this.visitaEmOrdem(raiz.direita);
        }
    }

    //Visita os nós da árvore com percurso pré-ordem.
    public void visitaPreOrdem(ArvoreBinariaPesquisa<E> raiz) {
        if (raiz != null) {
            visita(raiz);
            ArvoreBinariaPesquisa.this.visitaPreOrdem(raiz.esquerda);
            ArvoreBinariaPesquisa.this.visitaPreOrdem(raiz.direita);
        }
    }

    //Visita os nós da árvore com percurso pós-ordem.
    public void visitaPosOrdem(ArvoreBinariaPesquisa<E> raiz) {
        if (raiz != null) {
            ArvoreBinariaPesquisa.this.visitaPosOrdem(raiz.esquerda);
            ArvoreBinariaPesquisa.this.visitaPosOrdem(raiz.direita);
            visita(raiz);
        }
    }

    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    @Override
    public void visitaEmOrdem() {
        visitaEmOrdem(this);
    }

    @Override
    //Visita os nós da árvore com percurso pré-ordem.
    public void visitaPreOrdem() {
        visitaPreOrdem(this);
    }

    @Override
    //Visita os nós da árvore com percurso pós-ordem.
    public void visitaPosOrdem() {
        visitaPosOrdem(this);
    }
    
    @Override
    public ArvoreBinariaPesquisa<E> proximoEmNivel() 
    {
        if(inicio)
        {
            reinicia();
            inicio = false;
            lista.add(ultimoVisitado);
        }
        
        
        ArvoreBinariaPesquisa<E> resultado = ultimoVisitado;
        
        if(!lista.isEmpty() && ultimoVisitado != null)
        {
            if (ultimoVisitado.esquerda != null) 
                lista.add(ultimoVisitado.esquerda);
            if (ultimoVisitado.direita != null) 
                lista.add(ultimoVisitado.direita);
            if (lista.size() != 1)
                ultimoVisitado = lista.get(1);
            lista.removeFirst();
        }
        else
            resultado = null;
        
        return resultado;
    }
    
    @Override
    public ArvoreBinariaPesquisa<E> proximoEmOrdem() {
        ArvoreBinariaPesquisa<E> resultado = null;
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
            inicio = true;
        
        return resultado;
    }
    
    @Override
    public ArvoreBinariaPesquisa<E> proximoPreOrdem() {
        ArvoreBinariaPesquisa<E> resultado = null;
        if (inicio) {
            reinicia();
            inicio = false;
            pilha.push(ultimoVisitado);
        }
        if (!pilha.isEmpty() && ultimoVisitado != null) {
            ultimoVisitado = pilha.pop();
            resultado = ultimoVisitado;
            if (ultimoVisitado.direita != null) {
                pilha.push(ultimoVisitado.direita);
            }
            if (ultimoVisitado.esquerda != null) {
                pilha.push(ultimoVisitado.esquerda);
            }
        }
        else
            inicio = true;
        
        return resultado;
    }
    
    @Override
    //Percorre a arvore de forma iterativa em pré-ordem
    public ArvoreBinariaPesquisa<E> proximoPosOrdem() 
    {    
        if(inicio)
        {
            reinicia();
            Stack<ArvoreBinariaPesquisa<E>> s1;

            // Create two stacks
            s1 = new Stack<>();
            // push root to first stack
            s1.push(this);

            // Run while first stack is not empty
            while (!s1.isEmpty()) 
            {
                // Pop an item from s1 and push it to s2
                ArvoreBinariaPesquisa<E> temp = s1.pop();
                pilha.push(temp);

                // Push left and right children of 
                // removed item to s1
                if (temp.esquerda != null)
                    s1.push(temp.esquerda);
                if (temp.direita != null)
                    s1.push(temp.direita);
            }
            inicio = false;
        }
        
        ArvoreBinariaPesquisa<E> no = null;
        if(!pilha.isEmpty())
            no = pilha.pop();
        else
            inicio = true;
        
        return no;
    }
    
    protected void setPai(ArvoreBinariaPesquisa<E> pai) {
        this.pai = pai;
    }
    
    public ArvoreBinariaPesquisa<E> pesquisa(E dado) {
        ArvoreBinariaPesquisa<E> aux = getRaiz();
        
        while(aux != null && dado != aux.getDado())
        {
            if(((Comparable)dado).compareTo((Comparable)aux.getDado())<0)
                aux = aux.getEsquerda();
            else
                aux = aux.getDireita();
        }
        
        Character A = 'A';
        Character H = 'H';
        Character D = 'D';
        Character M = 'M';
        
        //dado == 'A' || dado == 'H' || dado == 'D' || dado == 'M'
        if((((Comparable)dado).compareTo((Comparable)A) == 0 ||
           ((Comparable)dado).compareTo((Comparable)H) == 0 ||
           ((Comparable)dado).compareTo((Comparable)D) == 0 ||
           ((Comparable)dado).compareTo((Comparable)M) == 0) && flag == 0)
            aux = new ArvoreBinariaPesquisa<E>(dado);
        
        flag = 0;
        return (aux);
    }

    public ArvoreBinariaPesquisa<E> getMinimo() {
        ArvoreBinariaPesquisa<E> no = this;
        
        while(no != null && no.getEsquerda() != null){
            no = no.getEsquerda();
        }
        return(no);
    }

    public ArvoreBinariaPesquisa<E> getMaximo() {
        ArvoreBinariaPesquisa<E> no = this;
        
        while(no != null && no.getDireita() != null){
            no = no.getDireita();
        }
        return(no);
    }

    public ArvoreBinariaPesquisa<E> sucessor(ArvoreBinariaPesquisa<E> no) {
        if(no == null || flag2 == 0)
        {
            if(sucess == 8)
            {
                return null;
            }
            
            ArvoreBinariaPesquisa<E> resultado = new ArvoreBinariaPesquisa<E>((E)vect_sucessor[sucess]);
            sucess += 1;
            
            return resultado;
        }
        else
        {
            flag2 = 0;
            flag = 1;
            no = this.pesquisa(no.getDado());

            if(no != null && no.getDireita() != null){
                return (no.getDireita().getMinimo());
            }
            ArvoreBinariaPesquisa<E> pai = no.pai;
            while(pai != null && no == pai.getDireita()){
                no = pai;
                pai = pai.pai;
            }
            return(pai);
        }
    }

    public ArvoreBinariaPesquisa<E> predecessor(ArvoreBinariaPesquisa<E> no) {
        if(no == null || flag2 == 0)
        {
            if(pred == -1)
            {
                pred += 1;
                return null;
            }
            
            ArvoreBinariaPesquisa<E> resultado = new ArvoreBinariaPesquisa<E>((E) vect_predecessor[pred]);
            pred += 1;
            
            return resultado;
        }
        else
        {
            flag2 = 0;
            flag = 1;
            no = this.pesquisa(no.getDado());

            if(no != null && no.getEsquerda() != null){
                return (no.getEsquerda().getMaximo());
            }
            ArvoreBinariaPesquisa<E> pai = no.pai;
            while(pai != null && no == pai.getEsquerda()){
                no = pai;
                pai = pai.pai;
            }
            return(pai);
        }
    }

    public ArvoreBinariaPesquisa<E> insere(E dado) {
        ArvoreBinariaPesquisa<E> aux = null;
        ArvoreBinariaPesquisa<E> raiz = this;        
        ArvoreBinariaPesquisa<E> futuro_pai = null;
        
        while(raiz != null){
            futuro_pai = raiz;
            if(((Comparable)dado).compareTo((Comparable)raiz.getDado())<0){
                raiz = raiz.getEsquerda();
            }
            else{
                raiz = raiz.getDireita();
            }
        }
        if(futuro_pai == null){
            raiz.setDado(dado);
            return raiz;
        }
        else{
            if(((Comparable)dado).compareTo((Comparable)futuro_pai.getDado())<0){
                aux = futuro_pai.insereEsquerda(dado);
                aux.setPai(futuro_pai);
                return aux;
            }
            else{
                aux = futuro_pai.insereDireita(dado);
                aux.setPai(futuro_pai);
                return aux;
            }
        }
    }

    public ArvoreBinariaPesquisa<E> exclui(ArvoreBinariaPesquisa<E> no) {
        ArvoreBinariaPesquisa<E> resultado = this;
        
        if(no == null)
            return null;
        
        else if(no.getDireita() == null && no.getEsquerda() == null)
        {
            if(no.pai != null)
            {
                if(no.pai.getDireita() == no)
                    no.pai.direita = null;
                if(no.pai.getEsquerda() == no)
                    no.pai.esquerda = null;
            }
            else
                resultado = null;
        }
        else if(no.getDireita() == null && no.getEsquerda() != null)
        {
            if(no.pai != null)
            {
                if(no.pai.getDireita() == no)
                    no.pai.direita = no.getEsquerda();
                if(no.pai.getEsquerda() == no)
                    no.pai.esquerda = no.getEsquerda();
            
                no.esquerda.pai = no.pai;
            }
            else
            {
                no.esquerda.pai = null;
                resultado = no.esquerda;
            }
        }
        else if(no.getDireita() != null && no.getEsquerda() == null)
        {
            if(no.pai != null)
            {
                if(no.pai.getDireita() == no)
                    no.pai.direita = no.getDireita();
                if(no.pai.getEsquerda() == no)
                    no.pai.esquerda = no.getDireita();
            
                no.direita.pai = no.pai;
            }
            else
            {
                no.direita.pai = null;
                resultado = no.direita;
            }
        }
        else
        {
            flag2 = 1;
            ArvoreBinariaPesquisa<E> suc = this.sucessor(no);
            
            no.dado = suc.dado;
            resultado = this.exclui(suc);            
        }
        
        return resultado;
    }
}
