package com.example.sub3feb2023.repository.db;

import com.example.sub3feb2023.domain.*;
import com.example.sub3feb2023.repository.Repository;
import com.example.sub3feb2023.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class HotelDbRepository implements Repository<Double, Hotel> {
    JDBCUtils jdbcUtils=new JDBCUtils();

    @Override
    public Optional<Hotel> findOne(Double id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        String query = "SELECT * from hotels where hotel_id = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Double locationId=resultSet.getDouble("location_id");
                String hotelName=resultSet.getString("hotel_name");
                Integer no_rooms=resultSet.getInt("no_rooms");
                Double pricePerNight=resultSet.getDouble("price_per_night");
                TypeENUM type=TypeENUM.valueOf(resultSet.getString("type"));
                Hotel hotel=new Hotel(id,locationId,hotelName,no_rooms,pricePerNight,type);
                return Optional.of(hotel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Hotel> findAll() {
        Set<Hotel> hotels = new HashSet<>();

        String query = "SELECT * from hotels";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Double hotel_id = resultSet.getDouble("hotel_id");
                Double location_id = resultSet.getDouble("location_id");
                String hotel_name=resultSet.getString("hotel_name");
                Integer no_rooms=resultSet.getInt("no_rooms");
                Double price_per_night=resultSet.getDouble("price_per_night");
                TypeENUM type=TypeENUM.valueOf(resultSet.getString("type"));
                Hotel hotel=new Hotel(hotel_id,location_id,hotel_name,no_rooms,price_per_night,type);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }

    @Override
    public Optional<Hotel> save(Hotel entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Hotel> delete(Double aDouble) {
        return Optional.empty();
    }

    @Override
    public Optional<Hotel> update(Hotel entity) {
        return Optional.empty();
    }


}
