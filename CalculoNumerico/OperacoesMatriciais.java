package CalculoNumerico;

public class OperacoesMatriciais{

   public static Matriz PivoteamentoParcial(Matriz origem)
   {
      Matriz retorno = origem;

      for(int i = 0; i < retorno.Linha(); i++)
      {
            if(retorno.get(i, i) == 0)
            {
               int maior = 0;

               for(int j = i; j < retorno.Linha(); j++){
                  maior = ( retorno.get(j, i) > retorno.get(maior, i) ? (j) : (maior));
               }

               double[] placeholder = retorno.get(maior);
               retorno.set(retorno.get(i), maior);
               retorno.set(placeholder, i);
            
            }
      }

      return retorno;
   }

   //Matriz "U" equivale ao resultado da eliminação gaussiana
   public static Matriz[] DecomposicaoLU(Matriz origem)
   {

      Matriz L = new Matriz((int)origem.Linha());
      Matriz U = origem;

      //matriz identidade
      for(int i = 0; i < origem.Linha(); i++){
         L.set(1, i, i);
      }

      /* DEBUG
      L.ExibirMatriz();
      U.ExibirMatriz();
      System.out.println();
      */


      //calculo
      for(int i = 0; i < origem.Linha()-1; i++){
         for(int j = i+1; j < origem.Linha(); j++){
            double fator = (origem.get(j, i)/origem.get(i, i)); //valor que será colocado na matriz L
            //System.out.println(fator); DEBUG

            for(int k = i; k < origem.Linha(); k++){
               U.set(origem.get(j, k) - (fator * origem.get(i, k)), j, k); //altera a Linha j com o fator e a poe na matriz U
            }

            L.set(fator, j, i); //coloca o fator na matriz L

         }
         
      }
      
      return new Matriz[]{L, U};

   }

   public static Matriz DecomposicaoCholensky(Matriz origem)
   {
      Matriz retorno = new Matriz((int)origem.Linha());
      
      // metodo sem laço de repetição para matriz 3x3
      // retorno.set(Math.sqrt(origem.get(0,0)),0,0);
      // retorno.set(origem.get(1,0)/retorno.get(0,0),1,0);
      // retorno.set(Math.sqrt(origem.get(1,1) - Math.pow(retorno.get(1,0),2)),1,1);
      // retorno.set(origem.get(2,0)/retorno.get(0,0),2,0);
      // retorno.set((origem.get(2,1) - (retorno.get(2,0) * retorno.get(1,0)))/retorno.get(1,1),2,1);
      // retorno.set(Math.sqrt(origem.get(2,2) - (Math.pow(retorno.get(2,0),2) + Math.pow(retorno.get(2,1),2))),2,2);

      
      for(int i = 0; i < origem.Linha(); i++)
      {
         for(int j = 0; j <= i; j++)
         {
            double valor = 0;

            if(i == j)
            {
               for(int k = 0; k < j; k++ ){valor += Math.pow(retorno.get(i, k), 2);}
               retorno.set(Math.sqrt(origem.get(i,j) - valor), i, j);
            } else
            {
               for(int k = 0; k < j; k++){valor += retorno.get(i, k) * retorno.get(j, k);}
               retorno.set((origem.get(i,j) - valor)/retorno.get(j, j), i, j);
            }

         }
      }

      
      return retorno;
   }


   public static Matriz GaussJacobi(Matriz origem){

      Matriz[] iteracao = new Matriz[]{new Matriz(1,origem.Coluna()), new Matriz(1,origem.Coluna())};
      Matriz parada = new Matriz(1, origem.Coluna());

      //iteração 0
      iteracao[0].set(origem.get(0,3) / origem.get(0,0), 0,0);
      iteracao[0].set(origem.get(1,3) / origem.get(1,1), 1,0);
      iteracao[0].set(origem.get(2,3) / origem.get(2,2), 2,0);

      //iterações seguintes
      int k = 0;
      do
      {
         k = (k+1)%2;

         for(int i = 0; i < origem.Coluna(); i++) //formação da nova matriz
         {
            double valor = origem.get(i,origem.Coluna() );

            System.out.print(valor); //DEBUG
            for(int j = 1; j < origem.Coluna(); j++)
            {
               System.out.print(" - " + iteracao[(k+1)%2].get((i+j)%origem.Coluna(), 0) + " * " + origem.get(i,(i+j)%origem.Coluna()) + " "); //DEBUG
               valor -= iteracao[(k+1)%2].get((i+j)%origem.Coluna(), 0) * origem.get(i,(i+j)%origem.Coluna());
            }
            System.out.println(" / " + origem.get(i,i)); //DEBUG
            iteracao[k].set(valor / origem.get(i,i), i, 0);
            
            parada.set(Math.abs(iteracao[k].get(i,0)) - Math.abs(iteracao[(k+1)%2].get(i,0)),i,0); //matriz de comparação
         }
         System.out.println();

         iteracao[k].ExibirMatriz();

         System.out.println();
         
         System.out.println("parada= " + parada.NormaInfinita() + " / " + iteracao[k].NormaInfinita() + " = " + parada.NormaInfinita() / iteracao[k].NormaInfinita());
         System.out.println();
         System.out.println();
         //DEBUG
      }
      while(parada.NormaInfinita() / iteracao[k].NormaInfinita() > 0.05);


      return iteracao[k];
   }


   public static double SistemaMatricial(Matriz origem)
   {
      double result = 0;
      return result;
   }



   public static double normaEuclidiana(Matriz origem){
      double retorno = 0;
      for(int i = 0; i < origem.Linha(); i++){
         for(int j = 0; j < origem.Coluna(); j++){
            retorno += origem.get(i,j);
         }
      }
      retorno = Math.sqrt(retorno);
      return retorno;
   }

}