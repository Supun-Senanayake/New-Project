package lk.ijse.Model;

import lk.ijse.Dto.Employeedto;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Employeemodel {
    public static boolean Save(Employeedto employeedto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, employeedto.getService());
        pstm.setString(2, employeedto.getName());
        pstm.setString(3, employeedto.getNic());
        pstm.setString(4, employeedto.getAddress());
        pstm.setString(5, employeedto.getContact());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Employeedto employeedto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE employee set Service=?,name=?,nic=? , Address=? WHERE contact = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,employeedto.getService());
        pstm.setString(2,employeedto.getName());
        pstm.setString(3,employeedto.getNic());
        pstm.setString(4,employeedto.getAddress());
        pstm.setString(5,employeedto.getContact());

        return pstm.executeUpdate() > 0;

    }
    public static boolean delete(String contact) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM employee WHERE contact = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,contact);
        return pstm.executeUpdate() > 0;
    }

    public static Employeedto search(String contact) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee where contact = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,contact);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            Employeedto employeedto= new Employeedto();


            employeedto.setService(resultSet.getString(1));
            employeedto.setName(resultSet.getString(2));
            employeedto.setNic(resultSet.getString(3));
            employeedto.setAddress(resultSet.getString(4));
            employeedto.setContact(resultSet.getString(5));

            return employeedto;
        }
        return null;
    }

    public static List<Employeedto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Employeedto> dtoList= new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new Employeedto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }

}
