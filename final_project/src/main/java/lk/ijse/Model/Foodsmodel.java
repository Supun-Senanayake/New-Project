package lk.ijse.Model;

import lk.ijse.Dto.Customerdto;
import lk.ijse.Dto.Foodsdto;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Foodsmodel {
    public static boolean Save(Foodsdto foodsdto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO foods VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, foodsdto.getId());
        pstm.setString(2, foodsdto.getName());
        pstm.setDouble(3, foodsdto.getPrice());
        pstm.setString(4, foodsdto.getImageUrl());

        return pstm.executeUpdate() > 0;

    }

    public static boolean update(Foodsdto foodsdto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE foods set name=?,price=?,image_url=? WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1,foodsdto.getId());
        pstm.setString(2,foodsdto.getName());
        pstm.setDouble(3,foodsdto.getPrice());
        pstm.setString(4,foodsdto.getImageUrl());

        return pstm.executeUpdate() > 0;

    }
    public static boolean delete(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM foods WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);
        return pstm.executeUpdate() > 0;
    }

    public static Foodsdto search(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM foods where id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            Foodsdto foodsdto= new Foodsdto();

            foodsdto.setId(resultSet.getInt(1));
            foodsdto.setName(resultSet.getString(2));
            foodsdto.setPrice(resultSet.getDouble(3));
            foodsdto.setImageUrl(resultSet.getString(4));

            return foodsdto;
        }
        return null;
    }

    public static List<Foodsdto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM foods";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Foodsdto> dtoList= new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new Foodsdto(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }
}
