package com.capstone.api;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrgData {

    private Connection conn = null;


    public void connect(String dbToConnectTo, String username, String pass) {
        // auto close connection
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + dbToConnectTo, username, pass); //

            if (conn != null) {
                System.out.println("Connected to the database!");

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    protected ArrayList<Organization> getOrganizations() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Organization org = null;
        ArrayList<Organization> result = new ArrayList<Organization>();

        try {


            preparedStatement = conn.prepareStatement("SELECT * FROM Organizations");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            while (resultSet.next()) {

                int id = resultSet.getInt("org_id");
                String orgName = resultSet.getString("org_name");
                String description = resultSet.getString("description");
                String number = resultSet.getString("phone_number"); // need to change to int?
                String website = resultSet.getString("website");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                org = new Organization(id, orgName, description, number, website, email, address);

                result.add(org);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    public ArrayList<Organization> getOrganizationsById(int org_id){

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Organization org = null;
        ArrayList<Organization> result = new ArrayList<Organization>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM Organizations WHERE org_id =" + org_id);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {

            while (resultSet.next()) {

                int id = resultSet.getInt("org_id");
                String orgName = resultSet.getString("org_name");
                String description = resultSet.getString("description");
                String number = resultSet.getString("phone_number");
                String website = resultSet.getString("website");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                org = new Organization(id, orgName, description, number, website, email, address);

                result.add(org);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }




    protected void insert(Organization org) {

        PreparedStatement prep = null;
        int id = org.getId();
        String orgName = org.getName();
        String description = org.getDescription();
        String number = org.getNumber();
        String website = org.getWebsite();
        String email = org.getEmail();
        String address = org.getAddress();

        try {

            prep = conn.prepareStatement("INSERT INTO Organizations (org_id, org_name, description, phone_number, website, email, address) VALUES ('" + id + "','" + orgName + "','" + description + "','" + number + "','" + website + "','" + email + "','" + address + ")");
            prep.executeUpdate();

        } catch (SQLException e1) {

            e1.printStackTrace();

        }

    }


    protected void update(Organization org) {

        PreparedStatement prep = null;
        try {

            prep = conn.prepareStatement("UPDATE Organizations SET org_name = ?, description = ?, phone_number = ?, website = ?, email = ?, address = ? WHERE id = ?");

            prep.setString(1, org.getName());
            prep.setString(2, org.getDescription());
            prep.setString(3, org.getNumber());
            prep.setString(4, org.getWebsite());
            prep.setString(5, org.getEmail());
            prep.setString(6, org.getAddress());
            prep.setInt(7, org.getId());
            prep.executeUpdate();

        } catch (SQLException e2) {

            e2.printStackTrace();

        }

    }


}