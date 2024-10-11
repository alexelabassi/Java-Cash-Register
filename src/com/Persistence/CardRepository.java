package com.Persistence;

import com.domain.Card;
import connection.databaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardRepository implements GenericRepository<Card> {
    private static final String INSERT_STATEMENT = "INSERT INTO `Cards` (`idCard`, `name`, `price`)" + " VALUES (?, ?, ?)";
    private static final String SELECT_STATEMENT = "SELECT * FROM `Cards` WHERE idCard = ?";
    private static final String UPDATE_STATEMENT = "UPDATE `Cards` SET `price` = ? WHERE `idCard` = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM `Cards` WHERE `idCard`=?";
    private static final String SIZE_STATEMENT = "SELECT * from `Cards`";

    public Card add(Card card) {
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
            statement.setInt(1, card.getId());
            statement.setString(2, card.getName());
            statement.setDouble(3, card.getPrice());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Successfully added card.");
            }
        } catch (SQLException e) {
            System.out.println("Card:" + e.getMessage());
            return new com.domain.Card();
        }
        return card;
    }

    public Card get(int id) {
        Card card = new Card();
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Couldn't find card with given name.");
                    return card;
                }
                card.setId(result.getInt("idCard"));
                card.setName(result.getString("name"));
                card.setPrice(result.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return card;

    }

    public void update(Card card) {
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
            statement.setDouble(1, card.getPrice());
            statement.setInt(2, card.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Upated!");
                return;

            } else {
                System.out.println("Couldn't find card to update.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }


    }

    public void delete(Card card){
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {
            statement.setInt(1, card.getId());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Successfully deleted Card!");
                return;
            }
            else{
                System.out.println("Failed to delete Card!");
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
        Card card=new Card();
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SIZE_STATEMENT)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    card.setId(result.getInt("idCard"));
                    card.setName(result.getString("name"));
                    card.setPrice(result.getDouble("price"));
                    System.out.println(card);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
