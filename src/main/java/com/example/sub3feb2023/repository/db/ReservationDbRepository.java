package com.example.sub3feb2023.repository.db;

import com.example.sub3feb2023.domain.Reservation;
import com.example.sub3feb2023.repository.Repository;
import com.example.sub3feb2023.utils.DateUtils;
import com.example.sub3feb2023.utils.JDBCUtils;

import java.sql.*;
import java.time.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ReservationDbRepository implements Repository<Double, Reservation> {
    private JDBCUtils jdbcUtils=new JDBCUtils();
    private DateUtils dateUtils=new DateUtils();
    @Override
    public Optional<Reservation> findOne(Double aDouble) {
        return Optional.empty();
    }

    @Override
    public Iterable<Reservation> findAll() {
        Set<Reservation> reservations = new HashSet<>();

        String query = "SELECT * from reservations";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Double reservation_id = resultSet.getDouble("reservation_id");
                Long client_id=resultSet.getLong("client_id");
                Double hotel_id=resultSet.getDouble("hotel_id");
                Date start_date=resultSet.getDate("start_date");
                LocalDateTime startDate= dateUtils.convertToLocalDateTimeViaInstant(start_date);
                Integer no_nights=resultSet.getInt("no_nights");
                Reservation reservation=new Reservation(reservation_id,client_id,hotel_id,startDate,no_nights);
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    @Override
    public Optional<Reservation> save(Reservation entity) {
        //String query = "INSERT INTO locations(location_id, location_name) VALUES(?, ?)";
        String query = "INSERT INTO reservations(reservation_id,client_id,hotel_id,start_date,no_nights) VALUES(?, ?,?,?,?)";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setDouble(1,entity.getId());
            statement.setLong(2, entity.getClientId());
            statement.setDouble(3, entity.getHotelId());
            Date startDate = dateUtils.convertToSQLDate(entity.getStartDate());
            statement.setDate(4, startDate);
            statement.setInt(5,entity.getNoNights());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(entity);
    }

    @Override
    public Optional<Reservation> delete(Double aDouble) {
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> update(Reservation entity) {
        return Optional.empty();
    }
}
