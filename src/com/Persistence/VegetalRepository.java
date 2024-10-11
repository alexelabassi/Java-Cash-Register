package com.Persistence;

import com.domain.Vegetal;
import connection.databaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VegetalRepository implements GenericRepository<Vegetal> {
    private static final String INSERT_STATEMENT = "INSERT INTO `vegetale` (`idVegetal`, `name`, `price/kg`)" + " VALUES (?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM `vegetale` WHERE idVegetal = ?";
    private static final String UPDATE_STATEMENT = "UPDATE `vegetale` SET `price/kg` = ? WHERE `idVegetal` = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM `vegetale` WHERE `idVegetal`=?";
    private static final String SIZE_STATEMENT = "SELECT * from `vegetale`";

    public Vegetal add(Vegetal vegetal) {
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setInt(1, vegetal.getId());
            statement.setString(2, vegetal.getName());
            statement.setDouble(3, vegetal.getPrice());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Successfully added vegetal!");
            }
        } catch (SQLException e) {
            System.out.println("Vegetal:" + e.getMessage());
            return new com.domain.Vegetal();
        }
        return vegetal;
    }

    public Vegetal get(int id) {
        Vegetal vegetal = new Vegetal();
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Couldn't find vegetal.");
                    return vegetal;
                }
                vegetal.setId(result.getInt("idVegetal"));
                vegetal.setName(result.getString("name"));
                vegetal.setPrice(result.getDouble("price/kg"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vegetal;

    }

    public void update(Vegetal vegetal) {
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setInt(2, vegetal.getId());
            statement.setDouble(1, vegetal.getPrice());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Updated!");
                return;

            } else {
                System.out.println("Couldn't find vegetal to update.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }


    }

    public void delete(Vegetal vegetal){
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {

            statement.setInt(1, vegetal.getId());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Succesfully deleted vegetal!");
                return;
            }
            else{
                System.out.println("Failed to delete vegetal.");
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
        Vegetal vegetal=new Vegetal();
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SIZE_STATEMENT)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    vegetal.setId(result.getInt("idVegetal"));
                    vegetal.setName(result.getString("name"));
                    vegetal.setPrice(result.getDouble("price/kg"));
                    System.out.println(vegetal);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
