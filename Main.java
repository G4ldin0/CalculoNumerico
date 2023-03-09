import CalculoNumerico.*;

public class Main {
   public static void main(String[] args) {
      
      Matriz exemplo = new Matriz(new double[][]{{2,1,1}, {1,2,1}, {1,1,2} });
      exemplo.ExibirMatriz();
      Matriz cholensky = OperacoesMatriciais.DecomposicaoCholensky(exemplo);
      cholensky.ExibirMatriz();
   }
   
}
