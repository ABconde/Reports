import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.*;

public class Main {

    private static BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));;

    public static void main(String[] args) throws IOException {

//        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        displayMenu();

    }

    public static void displayMenu(){
        System.out.println("Bienvenido a la reporteria de Banco Iguama");
        while(true){
            System.out.println("Menu");
            System.out.println("Por favor seleccione una de las siguientes opciones: ");
            System.out.println("1. Mostrar reporte del dia de hoy.");
            System.out.println("2. Mostrar reporte del dia de mañana.");
            System.out.println("3. Salir del programa.");

            System.out.print("Ingrese el numero de la opcion deseada: ");

            try{
                Integer option = Integer.parseInt(br.readLine());
                switch (option){
                    case 1:
                    case 2:
                        Date today = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(today);
                        calendar.set(Calendar.HOUR_OF_DAY, 10);
                        today = calendar.getTime();
                        calendar.add(Calendar.DATE, 1);
                        Date tomorrow = calendar.getTime();
                        ReportModel report = generateReport(option == 1 ? today : tomorrow, option == 1 ? 0.0013 : 0.0010);

                        viewSubMenu(report);

                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción valida.");
                }

            }catch (Exception e){
                System.out.println("Por favor ingrese un numero.");
            }
        }
    }

    public static void viewSubMenu(ReportModel report) throws IOException{
        while(true){
            System.out.println("\n ------------------------------------------------------ \n");
            System.out.println("Por favor seleccione como desea ver el reporte: ");
            System.out.println("1. Imprimir en pantalla.");
            System.out.println("2. Generar archivo txt.");

            System.out.print("Ingrese el numero de la opcion deseada: ");
            Integer format = Integer.parseInt(br.readLine());

            if(format == 1){
                viewReport(report);
                String cont = br.readLine();
                System.out.println("\n ------------------------------------------------------ \n");
                break;
            }else if(format ==2){
                generateFile(report);
                String cont = br.readLine();
                System.out.println("\n ------------------------------------------------------ \n");
                break;
            }else{
                System.out.println("Por favor ingrese una opción valida.");
            }
        }
    }

    public static ReportModel generateReport(Date date, Double exchangeRate){
        List<TransactionModel> transactions = new ArrayList<>();
        transactions.add(new TransactionModel(10001, date, 1026574281, "Repetidor dual", 100.00, exchangeRate));
        transactions.add(new TransactionModel(10002, date, 1026574281, "Logitech G203 - Mouse cableado para videojuegos", 150.50, exchangeRate));
        ReportModel report = new ReportModel(date, transactions);
        return report;
    }

    public static void viewReport(ReportModel report){
        System.out.println(report.getTitle());
        for(int i = 0; i < report.getTransactions().size(); i++){
            System.out.println(report.getTransactions().get(i).toString());
        }
        System.out.println("Presione Enter para regresar al menu principal");
    }

    public static void generateFile(ReportModel report){
        try {
            File myObj = new File(report.getDate()+ ".txt");
            FileWriter myWriter = new FileWriter(report.getDate()+ ".txt");

            myWriter.write(report.getTitle()+"\n");
            for(int i = 0; i < report.getTransactions().size(); i++ ){
                myWriter.write(report.getTransactions().get(i).toString()+"\n");
            }
            myWriter.close();
            System.out.println("Reporte generado en carpeta raiz del proyecto.");
            System.out.println("Presione Enter para regresar al menu principal");
        } catch (IOException e) {
            System.out.println("Error al generar archivo.");
        }
    }


}
