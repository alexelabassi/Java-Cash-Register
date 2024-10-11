package com.Persistence;
import com.domain.Aliment;
import connection.databaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlimentRepository implements GenericRepository<Aliment> {
    private static final String INSERT_STATEMENT = "INSERT INTO `aliments` (`idAliment`, `name`, `price`)" + " VALUES (?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM `aliments` WHERE idAliment = ?";
    private static final String UPDATE_STATEMENT = "UPDATE `aliments` SET `price` = ? WHERE `idAliment` = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM `aliments` WHERE `idAliment`=?";
    private static final String SIZE_STATEMENT = "SELECT * from `aliments`";

    public Aliment add(Aliment aliment) {
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setInt(1, aliment.getId());
            System.out.println(aliment.getId());
            statement.setString(2, aliment.getName());
            statement.setDouble(3, aliment.getPrice());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Successfully added aliment!" );
            }
        } catch (SQLException e) {
            System.out.println("Aliment:" + e.getMessage());
            return new com.domain.Aliment();
        }
        return aliment;
    }

    public Aliment get(int id) {
        Aliment aliment = new Aliment();
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Couldn't find aliment with given name.");
                    return aliment;
                }
                aliment.setId(result.getInt("idAliment"));
                aliment.setName(result.getString("name"));
                aliment.setPrice(result.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return aliment;

    }

    public void update(Aliment aliment) {
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setInt(2, aliment.getId());
            statement.setDouble(1, aliment.getPrice());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Updated!");
                return;

            } else {
                System.out.println("Couldn't find Aliment to update.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }


    }

    public void delete(Aliment aliment){
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {

            statement.setInt(1, aliment.getId());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Succesfully deleted Aliment!");
                return;
            }
            else{
                System.out.println("Failed to delete Aliment.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }

    }

    public int getSize(){
        int number=0;
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SIZE_STATEMENT)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    number++;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return number;
    }
    public void show(){
        Aliment aliment=new Aliment();
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SIZE_STATEMENT)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    aliment.setId(result.getInt("idAliment"));
                    aliment.setName(result.getString("name"));
                    aliment.setPrice(result.getDouble("price"));
                    System.out.println(aliment);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
