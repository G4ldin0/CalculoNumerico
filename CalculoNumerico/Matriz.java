package CalculoNumerico;

import java.util.ArrayList;

public class Matriz{
   private ArrayList<double[]> matriz = new ArrayList<double[]>();
   private int coluna;
   private int linha;


   //CONSTRUTORES

   //vazios ( preenchidos com 0 )
   public Matriz(int lado){
      coluna = lado;
      linha = lado;

      for(int i = 0; i < lado; i++){
         double[] row = new double[lado];
         for(int j = 0; j < lado; j++){
            row[j] = 0;
         }
         matriz.add(row);
      }
   }

   public Matriz(int linha, int coluna){
      this.linha = linha;
      this.coluna = coluna;
      
      for(int i = 0; i < coluna; i++){
         double[] row = new double[linha];
         for(int j = 0; j < linha; j++){
            row[j] = 0;
         }
         matriz.add(row);
      }
   }

   //com valores passados com argumento
   public Matriz(double[][] valores){
   
      linha = valores[0].length;
      coluna = valores.length;

      for(int i = 0; i < coluna; i++){ matriz.add(valores[i]); }
   
   }


   //GETTER E SETTER

   public double get(int col, int row){ return matriz.get(col)[row]; }

   public double[] get(int col){ return matriz.get(col);}

   public boolean set(double value, int col, int row){
      if(col > coluna || row > linha){
         return false;
      } else{
         double[] setup = new double[linha];

         for(int i = 0; i < coluna; i++)
         {
            if(i == row)
               setup[i] = value;
            else
               setup[i] = matriz.get(col)[i];
            
         } 
         matriz.set(col, setup);

         return true;
      }
   }

   public boolean set(double[] value, int col){
      if(col > linha){
         return false;
      } else{
         matriz.set(col, value);
         return true;
      }
   }

   public double Linha() { return linha; }

   public double Coluna() { return coluna; }

   public void ExibirMatriz(){
      System.out.println("Matriz: ");
      System.out.println("[");
      for(int i = 0; i < matriz.size(); i++){
         for(int j = 0; j < matriz.get(i).length; j++){
            System.out.print(matriz.get(i)[j] + ", ");
         }
         System.out.println();
      }
      System.out.println("]");
   }
}