package src.QuanLy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainQLKT {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        TreatmentClass qlkt = new TreatmentClass();
        Connection conn = null;
        int chose = 100;
       int choseloop=0;
        int chosecn = 0;
            while (chose != 0) {
                System.out.println("OPTION FUNCTIONS" +
                        "\n1." + "\"" + "start" + "\"" + " Connect with Database" +
                        "\n2." + "\"" + "insert" + "\"" + " information into the table " +
                        "\n3." + "\"" + "update" + "\"" + " information into the table " +
                        "\n4." + "\"" + "delete" + "\"" + " information into the table " +
                        "\n0." + "\"" + "stop" + "\"" + " to exist program");
                System.out.print("You select number function :");
                chose = sc.nextInt();sc.nextLine();
                if (chose == 1){
                    chosecn++;
                    conn = qlkt.connectionDatabase();
                }
                if (chose == 2) {
                    if (chosecn == 0) {
                        System.out.println(" You haven't connected to database . Please select function 1 to continue ");
                        continue;
                    }
                    int chose1 = 0;
                    do {
                        System.out.print(" The table name that you want to insert date is : ");
                        String tableName = sc.nextLine();
                        qlkt.insertDatabase(tableName, conn);
                        System.out.println("Do you want to insert data to another table ? " +
                                "\n Chose 1.Yes            2.No");
                        System.out.print("You select to number function ");
                        chose1 = sc.nextInt();
                        sc.nextLine();
                    }while (chose1 == 1);
                }
                if(chose==3){
                    if (chosecn == 0){
                        System.out.println("You haven't connected to database . Please select function 1 to continue :");
                        continue;
                    }
                    int chose1 = 0;
                    do {
                        System.out.print("What is the name table that you want to update ");
                        String tableName = sc.nextLine();
                        qlkt.updateDatabase(tableName, conn);
                        System.out.println("Do you want to update date another table ? " +
                                "\n Option 1.Yes            2.No");
                        System.out.print("What number function do you want to choose :");
                        chose1 = sc.nextInt();
                        sc.nextLine();
                    }while (chose1 == 1);
                }
                    if(chose==4){
                        if (chosecn == 0) {
                            choseloop++;
                            System.out.println(" You haven't connected to database . Please select function 1 to continue ");
                            continue;
                        }
                        int chose1 = 0;
                        do {
                            System.out.print("What is the name of the table  you want to delete ?");
                            String tableName = sc.nextLine();
                            qlkt.deleteDatabase(tableName, conn);
                            System.out.println("Do you want to delete data from another table ?" +
                                    "\nOption 1.Yes           2.No");
                            System.out.print("Which number function do You choose ?");
                            chose1 = sc.nextInt();
                            sc.nextLine();
                        }while (chose1 == 1);
                    }
            }
        }
    }
