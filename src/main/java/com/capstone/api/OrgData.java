package com.capstone.api;

import java.sql.*;
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
                System.out.println("Organizations is connected!");

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    protected ArrayList<Organizations> getOrganizations() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Organizations org = null;
        ArrayList<Organizations> result = new ArrayList<Organizations>();

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
                String fax = resultSet.getString("fax");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");

                org = new Organizations(id, orgName, description, number, website, fax, email, address, longitude, latitude);

                result.add(org);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    public Organizations getOrganizationsById(int org_id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Organizations org = null;
        ArrayList<Organizations> result = new ArrayList<Organizations>();

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
                String fax = resultSet.getString("fax");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");

                org = new Organizations(id, orgName, description, number, website, fax, email, address, longitude, latitude);

                result.add(org);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result.get(0);
    }

    protected ArrayList<Organizations> getOrganizationsByCategory(String category) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Organizations org = null;
        ArrayList<Organizations> result = new ArrayList<Organizations>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM Organizations WHERE org_id IN (SELECT org_id FROM Services WHERE service_category ='" + category + "')");
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
                String fax = resultSet.getString("fax");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");

                org = new Organizations(id, orgName, description, number, website, fax, email, address, longitude, latitude);

                result.add(org);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    protected void insert(Organizations org) {

        PreparedStatement prep = null;

        String name = org.getName();
        String description = org.getDescription();
        String number = org.getNumber();
        String website = org.getWebsite();
        String email = org.getEmail();
        String address = org.getAddress();

        try {

            prep = conn.prepareStatement("INSERT INTO Organizations(org_name, description, phone_number, website, email, address) VALUES(?,?,?,?,?,?) ");

            prep.setString(1, name);
            prep.setString(2, description);
            prep.setString(3, number);
            prep.setString(4, website);
            prep.setString(5, email);
            prep.setString(6, address);
            prep.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }




    protected void update(Organizations org) {

        PreparedStatement prep = null;
        try {

            prep = conn.prepareStatement("UPDATE Organizations SET org_name = ?, description = ?, phone_number = ?, website = ?, fax = ?, email = ?, address = ?, longitude = ?, latitude = ? WHERE org_id = ?");

            prep.setString(1, org.getName());
            prep.setString(2, org.getDescription());
            prep.setString(3, org.getNumber());
            prep.setString(4, org.getWebsite());
            prep.setString(5, org.getFax());
            prep.setString(6, org.getEmail());
            prep.setString(7, org.getAddress());
            prep.setDouble(8, org.getLongitude());
            prep.setDouble(9, org.getLatitude());
            prep.setInt(10, org.getId());

            prep.executeUpdate();

        } catch (SQLException e2) {

            e2.printStackTrace();

        }

    }

    protected void delete(int id) {

        PreparedStatement prep = null;

        try {

            prep = conn.prepareStatement("DELETE FROM Organizations WHERE org_id = " + id);
            prep.executeUpdate();

        }

        catch (SQLException e) {

            e.printStackTrace();

        }
    }

}
