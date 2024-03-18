package com.capstone.api;
import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceData {

    private Connection conn = null;


    public void connect(String dbToConnectTo, String username, String pass) {
        // auto close connection
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + dbToConnectTo, username, pass); //

            if (conn != null) {
                System.out.println("Services is connected!");

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    protected ArrayList<Service> getServices() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Service serv = null;
        ArrayList<Service> result = new ArrayList<Service>();

        try {


            preparedStatement = conn.prepareStatement("SELECT * FROM Services");
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            while (resultSet.next()) {

                int orgId = resultSet.getInt("org_id");
                int serviceId = resultSet.getInt("service_id");
                String name = resultSet.getString("service_name");
                String category = resultSet.getString("service_category");


                serv = new Service(orgId, serviceId, name, category);
                result.add(serv);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    public ArrayList<Service> getServicesByOrgId(int org_id) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Service serv = null;
        ArrayList<Service> result = new ArrayList<Service>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM Services WHERE org_id =" + org_id);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {

            while (resultSet.next()) {

                int orgId = resultSet.getInt("org_id");
                int serviceId = resultSet.getInt("service_id");
                String name = resultSet.getString("service_name");
                String category = resultSet.getString("service_category");

                serv = new Service(orgId, serviceId, name, category);
                result.add(serv);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    protected ArrayList<Service> getServicesById(int id) {

        PreparedStatement prep = null;
        ResultSet resultSet = null;
        Service serv = null;
        ArrayList<Service> result = new ArrayList<Service>();
        try {

            prep = conn.prepareStatement("SElECT * FROM Services WHERE service_id =" + id);
            resultSet = prep.executeQuery();
        }

        catch (SQLException e) {

            e.printStackTrace();

        }

        try {

            while (resultSet.next()) {

                int orgId = resultSet.getInt("org_id");
                int serviceId = resultSet.getInt("service_id");
                String name = resultSet.getString("service_name");
                String category = resultSet.getString("service_category");

                serv = new Service(orgId, serviceId, name, category);
                result.add(serv);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        return result;

    }

    protected ArrayList<String> getServiceCategories() {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<String>();

        try {


            preparedStatement = conn.prepareStatement("SELECT DISTINCT service_category FROM Services");
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {

            while (resultSet.next()) {

                String category = resultSet.getString("service_category");
                result.add(category);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
/*
    protected ArrayList<Service> getServicesByCategory(String serviceCategory) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Service serv = null;
        ArrayList<Service> result = new ArrayList<Service>();


        try {


            preparedStatement = conn.prepareStatement("SELECT * FROM Services WHERE service_category =" + serviceCategory);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {

            while (resultSet.next()) {

                int orgId = resultSet.getInt("org_id");
                int serviceId = resultSet.getInt("service_id");
                String name = resultSet.getString("service_name");
                String category = resultSet.getString("service_category");

                serv = new Service(orgId, serviceId, name, category);
                result.add(serv);

            }


        }catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /*
    protected HashMap<String, Object> getByOpen{

        PreparedStatement prep = null;
        ResultSet resultSet = null;
        Service serv = null;
        Organization org = null;
        Schedule sched = null;
        HashMap<String, Object> results = new HashMap<String,Object>();

        try {

            String sql =
 */


    protected HashMap<String, Object> getByOpen(boolean open, String cat) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Service serv = null;
        Organizations org = null;
        Schedule sched = null;
        HashMap<String, Object> results = new HashMap<String, Object>();

        try {

            String sql;

            if (open && cat != null) {

                sql = "SELECT * FROM Services s"
                        + " LEFT JOIN Schedule sched on s.service_id = sched.service_id"
                        + " WHERE LOWER(s.service_category) = '" + cat.toLowerCase() + "'"
                        + " AND sched.day_of_week = dayname(now())"
                        + " AND sched.open_time < now()"
                        + " AND sched.close_time > now()"
                        + " UNION"
                        + " SELECT * FROM Services s"
                        + " RIGHT JOIN Schedule sched ON s.service_id = sched.service_id"
                        + " WHERE LOWER(s.service_category) = '" + cat.toLowerCase() + "'"
                        + " AND sched.day_of_week = dayname(now())"
                        + " AND sched.open_time < now()"
                        + " AND sched.close_time > now()";

            } else if (open) {

                sql = "SELECT * FROM Services s"
                        + " LEFT JOIN Schedule sched on s.service_id = sched.service_id"
                        + " WHERE sched.day_of_week = dayname(now())"
                        + " AND sched.open_time < now()"
                        + " AND sched.close_time > now()";

            } else if (cat != null) {

                sql = "SELECT * FROM Services s"
                        + " LEFT JOIN Schedule sched ON s.service_id = sched.service_id"
                        + " WHERE LOWER(s.service_category) = '" + cat.toLowerCase() + "'"
                        + " UNION"
                        + " SELECT * FROM Services s"
                        + " RIGHT JOIN Schedule sched ON s.service_id = sched.service_id"
                        + " WHERE LOWER(s.service_category) = '" + cat.toLowerCase() + "'";

            } else {

                sql = "SELECT * FROM Services s"
                        + " LEFT JOIN Schedule sched ON s.service_id = sched.service_id"
                        + " UNION"
                        + " SELECT * FROM Services s"
                        + " RIGHT JOIN Schedule sched ON s.service_id = sched.service_id";

            }

            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
           /* ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (resultSet.next()) {

                for (int i = 1; i <= columnsNumber; i++) {

                    if (i > 1) System.out.print(", ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));

                }

                System.out.println("");
            }

*/
        } catch (SQLException e) {

            e.printStackTrace();

        }

        try {

               while (resultSet.next()) {

                int servOrgId = resultSet.getInt("org_id");
                int serviceId = resultSet.getInt("service_id");
                String serviceName = resultSet.getString("service_name");
                String category = resultSet.getString("service_category");
                serv = new Service(servOrgId, serviceId, serviceName, category);
                results.put("Service with service id " + serviceId + " and organization id " + servOrgId, serv);


                String day = resultSet.getString("day_of_week");
                Time openTime = resultSet.getTime("open_time");
                Time closeTime = resultSet.getTime("close_time");
                sched = new Schedule(serviceId, day, openTime, closeTime);
                results.put("Schedule for day " + day + " with service id " + serviceId, sched);


            }
        }

        catch (SQLException e) {

            e.printStackTrace();

        }


        return results;

    }

    protected void insert(Service serv) {

        PreparedStatement prep = null;
        int orgId = serv.getOrgId();
        int serviceId = serv.getServiceId();
        String name = serv.getName();
        String category = serv.getCategory();


        try {

            prep = conn.prepareStatement("INSERT INTO Services (org_id, service_id, service_name, service_category) VALUES(?,?,?,?)");
            prep.setInt(1, serv.getOrgId());
            prep.setInt(2, serv.getServiceId());
            prep.setString(3, serv.getName());
            prep.setString(4, serv.getCategory());
            prep.executeUpdate();

        } catch (SQLException e1) {

            e1.printStackTrace();

        }

    }


    protected void update(Service serv) {

        PreparedStatement prep = null;
        try {

            prep = conn.prepareStatement("UPDATE Services SET org_id = ?, service_name = ?, service_category = ? WHERE service_id = ?");
            prep.setInt(1, serv.getOrgId());
            prep.setString(2, serv.getName());
            prep.setString(3, serv.getCategory());
            prep.setInt(4, serv.getServiceId());

            prep.executeUpdate();

        } catch (SQLException e2) {

            e2.printStackTrace();

        }

    }

    protected void delete(int id) {

        PreparedStatement prep = null;

        try {

            prep = conn.prepareStatement("DELETE FROM SERVICES WHERE service_id =" + id);
            prep.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}

