package lk.ijse.Model;

import lk.ijse.Dto.Customerdto;
import lk.ijse.Dto.tm.CustomerTM;
import lk.ijse.db.DbConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Customermodel {
    public static boolean Save(Customerdto customerdto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, customerdto.getName());
        pstm.setString(2, customerdto.getNic());
        pstm.setString(3, customerdto.getAddress());
        pstm.setString(4, customerdto.getContact());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Customerdto customerdto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE customer set FristName=?,nic=?,Address=? WHERE contact = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,customerdto.getName());
        pstm.setString(2,customerdto.getNic());
        pstm.setString(3,customerdto.getAddress());
        pstm.setString(4,customerdto.getContact());

        return pstm.executeUpdate() > 0;

 }

    public static boolean delete(String contact) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM customer WHERE contact = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,contact);
        return pstm.executeUpdate() > 0;
    }
    public static Customerdto search(String contact) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer where contact = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,contact);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            Customerdto customerdto= new Customerdto();

            customerdto.setName(resultSet.getString(1));
            customerdto.setNic(resultSet.getString(2));
            customerdto.setAddress(resultSet.getString(3));
            customerdto.setContact(resultSet.getString(4));

            return customerdto;
        }
        return null;
    }
    public static List<Customerdto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Customerdto> dtoList= new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new Customerdto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }

}




