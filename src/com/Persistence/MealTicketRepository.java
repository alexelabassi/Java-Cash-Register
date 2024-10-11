package com.Persistence;

import com.domain.MealTicket;
import connection.databaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealTicketRepository implements GenericRepository<MealTicket> {
        private static final String INSERT_STATEMENT = "INSERT INTO `MealTickets` (`idTicket`, `name`,`CNP`, `price`)" + " VALUES (?, ?, ?,?)";
        private static final String SELECT_STATEMENT = "SELECT * FROM `MealTickets` WHERE idTicket = ?";
        private static final String UPDATE_STATEMENT = "UPDATE `MealTickets` SET `price` = ? WHERE `idTicket` = ?";
        private static final String DELETE_STATEMENT = "DELETE FROM `MealTickets` WHERE `idTicket` =?";
        private static final String SIZE_STATEMENT = "SELECT * from `MealTickets`";

        public MealTicket add(MealTicket mealTicket) {
            try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT)) {
                statement.setInt(1, mealTicket.getId());
                statement.setString(2, mealTicket.getName());
                statement.setString(3, mealTicket.getCNP());
                statement.setDouble(4, mealTicket.getPrice());
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Meal ticket succesfully added");
                }
            } catch (SQLException e) {
                System.out.println("MealTicket:" + e.getMessage());
                return new MealTicket();
            }
            return mealTicket;
        }

        public MealTicket get(int id) {
            MealTicket mealTicket = new MealTicket();
            try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SELECT_STATEMENT)) {
                statement.setInt(1, id);
                try (ResultSet result = statement.executeQuery()) {
                    if (!result.next()) {
                        System.out.println("Couldn't find meal ticket with given id.");
                        return mealTicket;
                    }
                    mealTicket.setId(result.getInt("idTicket"));
                    mealTicket.setName(result.getString("name"));
                    mealTicket.setPrice(result.getDouble("price"));
                    mealTicket.setCNP(result.getString("CNP"));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return mealTicket;

        }

        public void update(MealTicket mealTicket) {
            try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(UPDATE_STATEMENT)) {
                statement.setDouble(1, mealTicket.getPrice());
                statement.setInt(2, mealTicket.getId());
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Updated!");
                    return;

                } else {
                    System.out.println("Couldn't find meal ticket to update.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return;
            }


        }

        public void delete(MealTicket mealTicket){
            try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(DELETE_STATEMENT)) {

                statement.setInt(1, mealTicket.getId());
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Succesfully deleted meal ticket!");
                    return;
                }
                else{
                    System.out.println("Couldn't find meal ticket.");
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
        MealTicket mealTicket=new MealTicket();
        try (PreparedStatement statement = databaseConnection.getInstance().getConnection().prepareStatement(SIZE_STATEMENT)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    mealTicket.setId(result.getInt("idTicket"));
                    mealTicket.setName(result.getString("name"));
                    mealTicket.setPrice(result.getDouble("price"));
                    mealTicket.setCNP(result.getString("CNP"));
                    System.out.println(mealTicket);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }



}
