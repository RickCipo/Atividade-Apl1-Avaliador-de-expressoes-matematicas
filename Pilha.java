@@ -0,0 +1,35 @@
 // import java.util.ArrayList;
 
 public class Pilha<T> {
     private T valores[];
     // private ArrayList<T> valores;
     private int posicao;
 
     public Pilha(int tamanho) throws PilhaException {
         if(tamanho <= 0 ) throw new PilhaException("Tamanho de Pilha inválido");
         valores = (T[]) new Object[tamanho]; //problema
         // valores = new ArrayList<>();
         posicao = 0;
         System.out.println("Criando pilha");
     }
 
     public boolean estaCheio() {
         return posicao >= valores.length;
     }
 
     public boolean estaVazio() {
         return posicao == 0;
     }
 
     public void empilhar(T valor) throws PilhaException {
         if(estaCheio()) throw new PilhaException("Pilha cheia");
         valores[posicao++] = valor;
     }
 
     public T desempilhar() throws PilhaException {
         if(estaVazio()) throw new PilhaException("Pilha vazia");
         posicao--;
         return valores[posicao];
     }
 }
 