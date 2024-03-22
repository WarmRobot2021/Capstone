package com.capstone.api;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CardData {

    private Connection conn = null;

    public void connect(String dbToConnectTo, String username, String pass) {
        // auto close connection
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + dbToConnectTo, username, pass); //

            if (conn != null) {
                System.out.println("Schedule is connected!");

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }



    protected ArrayList<Card> getByOpen(boolean open, String cat) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Card card = null;
        Schedule sched = null;
        ArrayList<Card> results = new ArrayList<Card>();

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

            preparedStatement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        try {

           /*ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (resultSet.next()) {

                for (int i = 1; i < columnsNumber; i++) {

                    if (i > 1) System.out.print(", ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));

                }

                System.out.println("");

            }*/
            int nextServiceId;
            int serviceId;

            while (resultSet.next()) {

                int orgId = resultSet.getInt("org_id");
                serviceId = resultSet.getInt("service_id");
                String name = resultSet.getString("service_name");
                String category = resultSet.getString("service_category");
                String day = resultSet.getString("day_of_week");
                Time openTime = resultSet.getTime("open_time");
                Time closeTime = resultSet.getTime("close_time");
                sched = new Schedule(serviceId, day, openTime, closeTime);
                ArrayList<Schedule> schedules = new ArrayList<Schedule>();
                schedules.add(sched);

                while (resultSet.next()) {

                    nextServiceId = resultSet.getInt("service_id");

                    if (nextServiceId == serviceId) {

                        day = resultSet.getString("day_of_week");
                        openTime = resultSet.getTime("open_time");
                        closeTime = resultSet.getTime("close_time");
                        sched = new Schedule(serviceId, day, openTime, closeTime);
                        schedules.add(sched);

                    }

                    else {

                        resultSet.previous();
                        card = new Card(orgId, serviceId, name, category, schedules);
                        results.add(card);
                        break;

                    }

                }

            }

        }

        catch (SQLException e) {

            e.printStackTrace();

        }


        return results;

    }

}
