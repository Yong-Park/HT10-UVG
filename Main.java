import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//parte de la funcion del main
class Main{
    public static int[][] datas;
    public static ArrayList<String> ciudades;
    
    /** 
     * @param args
     */
    public static void main (String[] args){
        ArrayList<ArrayList<String>> obj = new ArrayList<>();

        //lectura de dato txt 
        Scanner scanner = new Scanner(System.in);
		//lectura del diccionario
        try{
            File datos = new File("guategrafo.txt");
            Scanner lectura = new Scanner(datos);

            while(lectura.hasNextLine()){
                String expresion = lectura.nextLine();
                String[] strsplit = expresion.split(" ");
				ArrayList<String> data = new ArrayList<>();
				for(int i=0; i<strsplit.length;i++){
					data.add(strsplit[i]);
				}
                obj.add(data);
            }
            lectura.close();
        }catch(FileNotFoundException e){
            System.out.println("No se encontro el archivo");
            e.printStackTrace();
        }

        addgrafo(obj);
        System.out.println("__________________________________________");
        menu();
        scanner.close();
        
    }
    
    /** 
     * @param obj
     * @return ArrayList<Integer>
     */
    //para agregar datos al grafo
    public static ArrayList<Integer> addgrafo(ArrayList<ArrayList<String>> obj){
        //ciclo para enviarlo al edge
        ArrayList<Integer> longitud = new ArrayList<>();
        ArrayList<String> Inicio = new ArrayList<>(); 
        ArrayList<String> Final = new ArrayList<>();  
        ArrayList<String> General = new ArrayList<>();
        for (ArrayList<String> data:obj){
            longitud.add(Integer.parseInt(data.get(2)));
            Inicio.add(data.get(0));
            if(!General.contains(data.get(0))){
                General.add(data.get(0));
            }
            if(!General.contains(data.get(1))){
                General.add(data.get(1));
            }
            Final.add(data.get(1));
        }
        /*System.out.println(Inicio);
        System.out.println(Final);
        System.out.println(longitud);*/
        boolean activar;

        ArrayList<Integer> valores = new ArrayList<>();
        //crear un array que tendra los datos del lenght y se creara el graph
        for(int i=0;i<General.size();i++){
            for(int j=0;j<General.size();j++){
                activar = true;
                for(int x=0;x<Inicio.size();x++){  
                    //texto para mostrar com o va el orden          
                    //System.out.println(General.get(i) + "==" + Inicio.get(x) +  "->" + General.get(j) + "==" + Final.get(x));
                    if(Inicio.size()>0 && Final.size()>0 && longitud.size()>0){
                        if(General.get(i).equals(Inicio.get(x)) && General.get(j).equals(Final.get(x))){
                            valores.add(longitud.get(x));
                            activar=false;
                        }
                    }else{
                        valores.add(0);
                    }
                }
                //si ya se agrego un dato pq si existio la relacion no se agregara 0
                if(activar){
                    valores.add(0);
                }
            }
        }
        //mostrar si los valores efectivamente se estan guardando 
        //System.out.println(valores);

        //enviar a grafo
        ArrayList<Integer> valoresCopy = new ArrayList<>();
        valoresCopy = (ArrayList<Integer>)valores.clone();
        ciudades=General;
        grafo(valores,General);
        return valoresCopy;
    }
    
    /** 
     * @param General
     * @param valores
     */
    //para mostrar el grafo
    public static void grafo(ArrayList<Integer> valores,ArrayList<String> General ){
        
        //trabajando para realizar el floyd
        //codigo obtenido de https://www.sanfoundry.com/java-program-implement-floyd-warshall-algorithm/
        int INFINITY = 999;
        int adjacency_matrix[][];
        int numberofvertices;
 
        numberofvertices = General.size();
        System.out.println("Numero de vertices: " + General.size());
        
        adjacency_matrix = new int[numberofvertices + 1][numberofvertices + 1];
        
        for (int source = 1; source <= numberofvertices; source++)
        {
            for (int destination = 1; destination <= numberofvertices; destination++)
            {
                adjacency_matrix[source][destination] = valores.get(0);
                valores.remove(0);
                if (source == destination)
                {
                    adjacency_matrix[source][destination] = 0;
                    continue;
                }
                if (adjacency_matrix[source][destination] == 0)
                {
                    adjacency_matrix[source][destination] = INFINITY;
                }
            }
        }
        int i=1;
        System.out.println("Distancias mas cortas");
        System.out.println("______________________________________");
        for(String data : General){
            System.out.println(data + "->" + i);
            i++;
        }
        System.out.println("______________________________________");
        Floyd floydwarshall = new Floyd(numberofvertices);
        datas=floydwarshall.floydwarshall(adjacency_matrix);

        
        
    }
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int pos1=0;
        int pos2=0;
        //crear menu
        boolean menu=true;
        do{
            
            System.out.println("1. Mostrar direccion mas corta entre dos lugares");
            System.out.println("2. Mostrar el centro del grafo");
            System.out.println("3. Salir");
            int op = scanner.nextInt();
            if(op==1){
                System.out.println("Ingrese la ciudad 1");
                String ciu1= scanner.next();
                System.out.println("Ingrese la ciudad 2");
                String ciu2= scanner.next();

                for(int ciclo=0;ciclo<ciudades.size();ciclo++){
                    if(ciudades.get(ciclo).toUpperCase().equals(ciu1.toUpperCase())){
                        pos1=ciclo+1;
                    }
                    if(ciudades.get(ciclo).toUpperCase().equals(ciu2.toUpperCase())){
                        pos2=ciclo+1;
                    }
                }
                System.out.println("________________________________________________");
                System.out.println(ciu1 + "->" + ciu2 + " = " + datas[pos1][pos2]);
                System.out.println("________________________________________________");

            }else if(op==2){
                ArrayList<Integer> center = new ArrayList<>();
                for(int i=1;i<=ciudades.size();i++){
                    ArrayList<Integer> maximo = new ArrayList<>();
                    for(int j=1;j<=ciudades.size();j++){
                        maximo.add(datas[j][i]);
                    }
                    center.add(Collections.max(maximo));
                }
                int respuestaCentro = Collections.min(center);
                System.out.println("________________________________________________");
                System.out.println("El centro es: " + respuestaCentro);
                System.out.println("________________________________________________");
            }else if(op==3){
                menu=false;
                System.out.println("Espero que vuelvas pronto");
            }
        }while(menu);
        scanner.close();
    }
    
}

