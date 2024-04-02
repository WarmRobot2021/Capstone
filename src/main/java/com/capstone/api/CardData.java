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

            int serviceId = 0;
            Card card = null;
            Schedule sched = null;
            ArrayList<Schedule> schedules;

            while (resultSet.next()) {

                int orgId = resultSet.getInt("org_id");
                int resultServiceId = resultSet.getInt("service_id");
                String name = resultSet.getString("service_name");
                //System.out.println(name);
                String category = resultSet.getString("service_category");
                //System.out.println(category);
                String day = resultSet.getString("day_of_week");
                //System.out.println(day);
                Time openTime = resultSet.getTime("open_time");
                // System.out.println(openTime);
                Time closeTime = resultSet.getTime("close_time");
                //System.out.println(closeTime);

                if (serviceId != resultServiceId) {

                    schedules = new ArrayList<Schedule>();
                    card = new Card(orgId, resultServiceId, name, category, schedules);
                    results.add(card);
                }

                sched = new Schedule(serviceId, day, openTime, closeTime);
                card.addSchedule(sched);
                serviceId = resultServiceId;

            }

        }

        catch (SQLException e) {

            e.printStackTrace();

        }


        return results;

    }

}