
 public class App {
     public static void main(String[] args) {
         try {
             Pilha<Integer> p = new Pilha<>(3);
             p.desempilhar();
         } catch(PilhaException e) {
             System.out.println(e.getMessage());
         }
         System.out.println("Hello, World!");
     }
 }