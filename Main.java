import CalculoNumerico.*;

public class Main {
   public static void main(String[] args) {
      
      Matriz exemplo = new Matriz(new double[][]{{-1,0,2},{4,1,-1}});
      
      Interpolacao.Lagrange(exemplo).ExibirMatriz();
   }
   
}
