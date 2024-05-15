package lk.ijse.Model;

import javafx.fxml.Initializable;

import lk.ijse.Dto.UserDto;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registermodel {
      public static  boolean register(UserDto userDto) throws SQLException{
          Connection connection = DbConnection.getInstance().getConnection();
          String sql = "INSERT INTO user VALUES(?,?)";
          PreparedStatement ptm = connection.prepareStatement(sql);

          ptm.setString(1, userDto.getUsername());
          ptm.setString(2, userDto.getPassword());

          return ptm.executeUpdate() > 0;

      }
}