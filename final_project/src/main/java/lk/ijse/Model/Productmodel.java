package lk.ijse.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.Dto.Productdto;
import lk.ijse.Dto.Productdto;
import lk.ijse.Dto.tm.ProductTM;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Productmodel {



    public List<Productdto> getAllProduct() throws SQLException {
        List<Productdto> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            while (resultSet.next()) {
                Productdto productdto = new Productdto(
                        resultSet.getString("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("type"),
                        resultSet.getInt("stock"),
                        resultSet.getDouble("price"),
                        resultSet.getString("status")
                );
                productList.add(productdto);
            }
        }
        return productList;
    }



    public boolean addProduct(Productdto newProduct) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "INSERT INTO product (productId, productName, type, stock, price, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set values for the prepared statement parameters
            pstm.setString(1, newProduct.getProductId());
            pstm.setString(2, newProduct.getProductName());
            pstm.setString(3, newProduct.getType());
            pstm.setInt(4, newProduct.getStock());
            pstm.setDouble(5, newProduct.getPrice());
            pstm.setString(6, newProduct.getStatus());

            // Execute the query
            return pstm.executeUpdate() > 0;
        }


    public boolean updateProduct(Productdto updatedProduct) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            String sql = "UPDATE product SET product_name=?, type=?, stock=?, price=?, status=? WHERE product_id=?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, updatedProduct.getProductName());
            pstm.setString(2, updatedProduct.getType());
            pstm.setInt(3, updatedProduct.getStock());
            pstm.setDouble(4, updatedProduct.getPrice());
            pstm.setString(5, updatedProduct.getStatus());
            pstm.setString(6, updatedProduct.getProductId());

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}




