package com.capstone.api;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;


public class ScheduleData {


    private Connection conn = null;


    protected void connect(String dbToConnectTo, String username, String pass) {
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


    protected ArrayList<Schedule> getSchedules() {


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Schedule sched = null;
        ArrayList<Schedule> result = new ArrayList<Schedule>();


        try {


            preparedStatement = conn.prepareStatement("SELECT * FROM schedule");


            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        try {
            while (resultSet.next()) {


                int serviceId = resultSet.getInt("service_id");
                String day = resultSet.getString("day_of_week");
                Time open = resultSet.getTime("open_time");
                Time close = resultSet.getTime("close_time"); // need to change to int?
                int scheduleId = resultSet.getInt("schedule_id");


                sched = new Schedule(serviceId, day, open, close, scheduleId);
                result.add(sched);
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    protected ArrayList<Schedule> getSchedulesByServiceId(int service_id) {


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Schedule sched = null;
        ArrayList<Schedule> result = new ArrayList<Schedule>();


        try {


            preparedStatement = conn.prepareStatement("SELECT * FROM Schedule WHERE service_id =" + service_id);
            resultSet = preparedStatement.executeQuery();


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        try {


            while (resultSet.next()) {


                int serviceId = resultSet.getInt("service_id");
                String day = resultSet.getString("day_of_week");
                Time open = resultSet.getTime("open_time");
                Time close = resultSet.getTime("close_time");
                int scheduleId = resultSet.getInt("schedule_id");
                sched = new Schedule(serviceId, day, open, close, scheduleId);
                result.add(sched);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    protected ArrayList<Schedule> getSchedulesByName(int id, String name) {


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Schedule sched = null;
        ArrayList<Schedule> result = new ArrayList<Schedule>();


        try {


            preparedStatement = conn.prepareStatement("SELECT * FROM Schedule"
                    + "WHERE LOWER(day_of_week) = '" + name.toLowerCase() + "'"
                    + "AND service_id =" + id);
            resultSet = preparedStatement.executeQuery();


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        try {


            while (resultSet.next()) {


                int serviceId = resultSet.getInt("service_id");
                String day = resultSet.getString("day_of_week");
                Time open = resultSet.getTime("open_time");
                Time close = resultSet.getTime("close_time");
                int scheduleId = resultSet.getInt("schedule_id");
                sched = new Schedule(serviceId, day, open, close, scheduleId);
                result.add(sched);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;


    }


    protected void insert(Schedule sched) {


        PreparedStatement prep = null;


        int id = sched.getServiceId();
        String day = sched.getDay();
        Time open = sched.getOpen();
        Time close = sched.getClose();


        try {


            prep = conn.prepareStatement("INSERT INTO Schedule (service_id, day_of_week, open_time, close_time) VALUES (?,?,?,?)");
            prep.setInt(1, id);
            prep.setString(2, day);
            prep.setTime(3, open);
            prep.setTime(4, close);
            prep.executeUpdate();


        } catch (SQLException e1) {


            e1.printStackTrace();


        }


    }


    protected void update(Schedule sched) {


        PreparedStatement prep = null;
        try {


            prep = conn.prepareStatement("UPDATE Schedule SET service_id = ?, day_of_week = ?, open_time = ?, close_time = ? WHERE schedule_id = ?");


            prep.setInt(1, sched.getServiceId());
            prep.setString(2, sched.getDay());
            prep.setTime(3, sched.getOpen());
            prep.setTime(4, sched.getClose());
            prep.setInt(5, sched.getScheduleId());


            prep.executeUpdate();


        } catch (SQLException e2) {


            e2.printStackTrace();


        }


    }


    protected void deleteScheduleByServiceId(int id) {


        PreparedStatement prep = null;


        try {


            prep = conn.prepareStatement("DELETE FROM Schedule WHERE service_id=" + id);
            prep.executeUpdate();


        } catch (SQLException e) {


            e.printStackTrace();


        }


    }


    protected void deleteScheduleByScheduleId(int id) {


        PreparedStatement prep = null;


        try {


            prep = conn.prepareStatement("DELETE FROM Schedule WHERE schedule_id=" + id);
            prep.executeUpdate();

        } catch (SQLException e) {


            e.printStackTrace();


        }
    }

}
