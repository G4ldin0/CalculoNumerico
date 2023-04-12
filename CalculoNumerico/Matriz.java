package CalculoNumerico;

import java.util.ArrayList;

public class Matriz{
   private ArrayList<double[]> matriz = new ArrayList<double[]>();
   private int coluna; //vertical
   private int linha; //horizontal


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

   public int Linha() { return linha; }

   public int Coluna() { return coluna; }

   public void ExibirMatriz(){
      System.out.println("Matriz: ");
      System.out.println("[");
      for(int i = 0; i < coluna; i++){
         for(int j = 0; j < linha; j++){
            System.out.print(matriz.get(i)[j] + ", ");
         }
         System.out.println();
      }
      System.out.println("]");
   }

   public double NormaInfinita(){
      double value = 0;

      if(linha == 1)
      {
         int i = 0;
         value = Math.abs(matriz.get(0)[0]);
         do{
            value = Math.max(value, Math.abs(i));
            i++;
         }
         while(i < coluna);
      } 
      else 
      {

         for(int j = 0; j < linha; j++){
            value += matriz.get(0)[j];
         }
         
         for(int i = 0; i < coluna; i++)
         {
         double placeholder = 0;
            for(int j = 0; j < linha; j++){
               placeholder += matriz.get(i)[j];
            }

            value = Math.max(value, placeholder);

         }

      }

      return value;
   }
}