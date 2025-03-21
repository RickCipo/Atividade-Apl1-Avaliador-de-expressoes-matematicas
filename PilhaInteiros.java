 public class PilhaString {
     private String valores[];
     private int posicao;
 
     public Pilha(int tamanho) throws IllegalArgumentException {
         if(tamanho <= 0 ) throw new IllegalArgumentException();
         valores = new String[tamanho];
         posicao = 0;
     }
 
     public boolean estaCheio() {
         return posicao >= valores.length;
     }
 
     public boolean estaVazio() {
         return posicao == 0;
     }
 
     public void empilhar(String valor) {
         if(estaCheio()) return; //precisa gerar exception
         valores[posicao++] = valor;
     }
 
     public String desempilhar() {
         if(estaVazio()) return null; // precisa gerar exception
         posicao--;
         return valores[posicao];
     }
 }
 
 
 public class PilhaInteiros {
     private I valores[];
     private int posicao;
 
     public Pilha(int tamanho) throws IllegalArgumentException {
         if(tamanho <= 0 ) throw new IllegalArgumentException();
         valores = new String[tamanho];
         posicao = 0;
     }
 
     public boolean estaCheio() {
         return posicao >= valores.length;
     }
 
     public boolean estaVazio() {
         return posicao == 0;
     }
 
     public void empilhar(String valor) {
         if(estaCheio()) return; //precisa gerar exception
         valores[posicao++] = valor;
     }
 
     public String desempilhar() {
         if(estaVazio()) return null; // precisa gerar exception
         posicao--;
         return valores[posicao];
     }
 }