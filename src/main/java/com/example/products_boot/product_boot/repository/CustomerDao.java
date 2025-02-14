package com.example.products_boot.product_boot.repository;

import com.example.products_boot.product_boot.models.Customer;
import com.example.products_boot.product_boot.utils.DbConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
@Component
public class CustomerDao {
    @Autowired
    Connection connection;
    public CustomerDao() {
        DbConnector connector = new DbConnector();
        this.connection = connector.getConnection();
        String sql =
                "create table if not exists customer(" +
                        "customer_id bigserial primary key," +
                        "customer_name varchar not null," +
                        "contact_info varchar not null);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(Customer customer){

        String sql = "insert into customer( customer_name, contact_info) values (?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setString(2, customer.getContactInfo());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public Customer getById(int id){
            String sql = "select * from customer where customer_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    Customer customer = new Customer();
                    customer.setCustomerId(resultSet.getInt("customer_id"));
                    customer.setCustomerName(resultSet.getString("customer_name"));
                    customer.setContactInfo(resultSet.getString("contact_info"));
                    return customer;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
                return null;
        }

        public void updateCustomer(Customer customer){
        String sql = "update customer set customer_name=?, contact_info=? where customer id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, customer.getCustomerName());
                preparedStatement.setString(2, customer.getContactInfo());
                preparedStatement.setInt(3, customer.getCustomerId());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void deleteCustomer(int id){
            String sql = "delete from customer where customer_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
