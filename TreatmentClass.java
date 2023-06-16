package src.QuanLy;

import java.sql.*;
import java.util.Scanner;

public class TreatmentClass {
    public static Connection connectionDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/managerschool";
            String user = "root";
            String pass = "123456";
            Connection conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Data connection successfully !");
                return conn;
            } else System.out.println("Data connection failed");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //System.out.println("");
    public static void insertDatabase(String tableName, Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int chose = 0;
        // làm vòng lặp thêm nhiều hàng cho 1 bảng
        try {
            String list_contentdata = "";
            // Thực hiện truy vấn vào CSDL
            String query = "SELECT * FROM " + tableName;
            Statement x = conn.createStatement();
            ResultSet rs = x.executeQuery(query);
            //Tạo Đối tượng ResultSetMetaData chứa các thông tin về các cột trong bảng )
            ResultSetMetaData rsmd = rs.getMetaData();
            //Lấy sốt lượng cột của bảng lưu vào biến numberofcolumns
            int numberofcolumns = rsmd.getColumnCount();
            ResultSet resultSet = conn.getMetaData().getColumns(null, null, tableName, null);
            String list_columnsname = "";
            for (int i = 0; i < numberofcolumns; i++) {
                if (resultSet.next()) {
                    String columnName = rsmd.getColumnName(i + 1);
                    if (i == numberofcolumns - 1) list_columnsname = list_columnsname + columnName;
                    else list_columnsname = list_columnsname + columnName + ", ";
                }
            }
            System.out.print("How many lines do you want to insert ? : ");
            int quantity_column = sc.nextInt();
            sc.nextLine();
            System.out.println(list_columnsname);
            String contain_listcolumnsname = "";
            for (int i = 0; i < quantity_column; i++) {
                System.out.print("Please you input according to sequence corresponding above (line number " + (i + 1) + " ) :");
                list_contentdata = sc.nextLine();
                if (i == quantity_column - 1)
                    contain_listcolumnsname = contain_listcolumnsname + "(" + list_contentdata + ")";
                else contain_listcolumnsname = contain_listcolumnsname + "(" + list_contentdata + "),";
            }
//                for (int i = 0; i < numberofcolumns; i++) {
//                    if (resultSet.next()) {
//                        // lấy tên của cột bằng phương thức getColumnName(Numerical order of Column)
//                        String columnName = rsmd.getColumnName(i + 1);
//                        if (i < numberofcolumns - 1) chainColumnName = chainColumnName + columnName + ",";
//                        else chainColumnName = chainColumnName + columnName;
//
//                        // Lấy ra kiểu dữ liệu của cột
//                        String data_type = resultSet.getString("TYPE_NAME");
//                        // lấy thông tin của dữ liệu muốn thêm vào cột tương ứng
//                        String content_data="";
//                        if (data_type.equals("DATETIME") == false) {
//                            //System.out.print("Dữ liệu bạn muốn thêm vào cột số " + (i + 1) + " tên " + columnName + " ( data type : "+data_type+" ) là : ");
//                             content_data = sc.nextLine();
//                            if (i == numberofcolumns - 1)
//                                chainContent_data = chainContent_data + "'" + content_data + "'";
//                            else chainContent_data = chainContent_data + "'" + content_data + "'" + ",";
//                        }
//                        else {
//                            System.out.println("Nhập dữ liệu bạn muốn thêm vào cột số " + (i + 1) + " tên " + columnName + " ( data type : "+data_type+" ) là ");
//                            System.out.print("nhập ngày :");int day= sc.nextInt();
//                            System.out.print("nhập tháng :");int month= sc.nextInt();
//                            System.out.print("nhập năm :");int year= sc.nextInt();
//                            System.out.print("nhập giờ :");int hours= sc.nextInt();
//                            System.out.print("nhập phút :");int minute= sc.nextInt();
//                            System.out.print("nhập giây :");int second= sc.nextInt();sc.nextLine();
//
//                            content_data = year+"-"+month+"-"+day+" "+hours+":"+minute+":"+second;
//                            if (i == numberofcolumns - 1)
//                                chainContent_data = chainContent_data + "'" + content_data + "'";
//                            else chainContent_data = chainContent_data + "'" + content_data + "'" + ",";
//                            }
//                        }
//                    }
            list_columnsname = "(" + list_columnsname + ")";
            // Thêm dữ liệu của dòng này vào bảng
            String query1 = "INSERT INTO " + tableName + list_columnsname + " value " + contain_listcolumnsname;
            System.out.println(query1);
            PreparedStatement pstmt = conn.prepareStatement(query1);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("information input successfully !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateDatabase(String tableName, Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int numberofcolumn = 0;
        System.out.println("what number line do you want to updata for table " + tableName + ":");
        numberofcolumn = sc.nextInt();sc.nextLine();
            try {
                String query = "Select * from " + tableName;
                PreparedStatement t = conn.prepareStatement(query);
                ResultSetMetaData rsm = t.getMetaData();
                int n = rsm.getColumnCount();
                for (int i = 0; i < numberofcolumn; i++) {
                    System.out.println("the table " + tableName + " consists of columns (line number "+i+") :");
                    for (int j = 1; j <= n; j++)
                        System.out.printf(rsm.getColumnName(j) + ", ");
                    System.out.println("Enter the column name of the condition you want update for table " + tableName + " :");
                String condition_column = sc.nextLine();
                System.out.println("Enter the value of condition you want update for table " + tableName + " :");
                String condition_value = sc.nextLine();
                    System.out.println("Enter the name of the colum that you want to update for the table " + tableName + " :");
                    String column = sc.nextLine();
                    System.out.println("Enter the value that you want to update into the column :");
                    String giatri = sc.nextLine();
                    String sql1 = "UPDATE " + tableName + " SET " + column + " = " + "\'" + giatri + "\'" +
                            " WHERE " + condition_column + " = " + "\'" + condition_value + "\'";
                    PreparedStatement t1 = conn.prepareStatement(sql1);
                    int rowsUpdated = t1.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Update successfully!");
                    }
                    else  System.out.println("Update failed!");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

        public static void deleteDatabase(String tableName , Connection conn){
        Scanner sc = new Scanner(System.in);
        int chose =0;
        do{

            try{
                String query = "Select * from "+ tableName;
                PreparedStatement t = conn.prepareStatement(query);
                ResultSetMetaData rsm = t.getMetaData();
                int n= rsm.getColumnCount();
                System.out.print("The table "+tableName +" consists of columns :");
                for(int i=1;i<=n;i++) {
                    if(i==n)
                        System.out.println(rsm.getColumnName(i));
                    else
                        System.out.printf(rsm.getColumnName(i) + ", ");
                }
                System.out.print("You want to delete the information related to column :");
                String columnName = sc.nextLine();
                System.out.print("Do you want to delete the rows have column "+columnName+" containing  value : ");
                String columnValue = sc.nextLine();
                String query1 ="Delete from "+ tableName +" where "+columnName +" = "+columnValue ;
                PreparedStatement t1 = conn.prepareStatement(query1);
                int rowsUpdated = t1.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Information Deleted successfully!");
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                }
            System.out.println("Do you want to perform the deletion of the row in table "+tableName+" ? "+"\n 1.Yes       2.No");
            chose=sc.nextInt();sc.nextLine();
            }while (chose==1);
        }

   }

