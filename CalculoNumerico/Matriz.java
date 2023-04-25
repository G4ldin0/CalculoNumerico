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
      double[] setup = matriz.get(col);

      setup[row] = value;

      matriz.set(col, setup);

      return true;
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
      System.out.println("Matriz: " + " " + linha + "x" + coluna);
      System.out.println("[");
      for(int i = 0; i < coluna; i++){
         for(int j = 0; j < linha; j++){
            System.out.print(matriz.get(i)[j] + ", ");
         }
         System.out.println();
      }
      System.out.println("]");
   }

   public void escalar(double valor){
      for(int i = 0; i < coluna; i++){
         double[] placeholder = new double[linha];
         for(int j = 0; j < linha; j++){
            placeholder[j] = valor * matriz.get(i)[j];
         }
         matriz.set(i, placeholder);
      }
   }

   public boolean matricial(Matriz valor){

      if(valor.coluna != linha)
         return false;
      else{
         ArrayList<double[]> placeholder = new ArrayList<double[]>();

         for(int i = 0; i < coluna; i++)
         {
            double[] vetor = new double[valor.linha];
            for(int j = 0; j < valor.linha; j++)
            {
               double soma = 0;
               for(int k = 0; k < linha; k++)
               {
                  soma += matriz.get(i)[k] * valor.get(k)[j];
               }
               vetor[j] = soma;
            }
            placeholder.add(vetor);
         }

         matriz = placeholder;
         linha = valor.linha;

         return true;
      }

   }

   public void transpor(){
      //nova ED placeholder
      ArrayList<double[]> placeholder = new ArrayList<double[]>();

      //pega o elemento da posição contrária e guarda na nova posição do placeholder
      for(int i = 0; i < linha; i++){
         double[] novo = new double[linha];
         for(int j = 0; j < coluna; j++){
            novo[j] = matriz.get(j)[i];
         }
         placeholder.add(novo);
      }
      
      //aplica os novos valores nos atributos
      matriz = placeholder;
      int ph = coluna;
      coluna = linha;
      linha = ph;
   }

   public double determinante(){
      
      Matriz superior = OperacoesMatriciais.DecomposicaoLU(this)[1];
      
      double valor = superior.get(0,0);

      for(int i = 1; i < linha; i++)
         valor *= superior.get(i,i);

      return valor;
   }

   public void inverter(){
      //matriz que, ao multiplicada pela matriz origem, gerará a matriz identidade
      ArrayList<double[]> placeholder = new ArrayList<double[]>();


      /*
       *  -1  4
       *   4  1
       * 
       *   x  y  
       *   w  z  
       * 
       *   -1*x + 4*w = 1  -1*y + 4*z = 0
       *    4*x + 1*w = 0   4*y + 1*z = 1
       * 
       *   x = (1 - 4w)/-1  y = (0 - 4z)/-1
       *   w = (0 - 4x)/1   z = (1 - 4y)/1
       * 
       */

            
      //aplica os novos valores nos atributos
      matriz = placeholder;
      int ph = coluna;
      coluna = linha;
      linha = ph;

   }

   public double NormaInfinita(){
      double value = 0;

      if(linha == 1)
      {
         int i = 1;
         value = Math.abs(matriz.get(0)[0]);
         do{
            value = Math.max(value, Math.abs(matriz.get(i)[0]));
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
            for(int j = 1; j < linha; j++){
               placeholder += matriz.get(i)[j];
            }

         value = Math.max(value, placeholder);

         }

      }

      return value;
   }
}