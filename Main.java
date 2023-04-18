import CalculoNumerico.*;

public class Main {
   public static void main(String[] args) {
      
      Matriz exemplo = new Matriz(new double[][]{{10,2,1,7}, {1,5,1,-8}, {2,3,10,6} });
      exemplo.ExibirMatriz();
      exemplo = OperacoesMatriciais.GaussJacobi(exemplo);
      exemplo.ExibirMatriz();
   }
   
}
