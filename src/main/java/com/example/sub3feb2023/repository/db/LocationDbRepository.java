package com.example.sub3feb2023.repository.db;

import com.example.sub3feb2023.domain.Entity;
import com.example.sub3feb2023.domain.Location;
import com.example.sub3feb2023.repository.Repository;
import com.example.sub3feb2023.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LocationDbRepository implements Repository<Double,Location> {
    private final JDBCUtils jdbcUtils=new JDBCUtils();


    @Override
    public Optional<Location> findOne(Double id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        String query = "SELECT * from locations where location_id = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String locationName = resultSet.getString("location_name");

                Location location = new Location(id, locationName);
                return Optional.of(location);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Location> findAll() {
        Set<Location> locations = new HashSet<>();

        String query = "SELECT * from locations";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Double id = resultSet.getDouble("location_id");
                String locationName = resultSet.getString("location_name");

                Location location = new Location(id, locationName);
                locations.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locations;
    }

    @Override
    public Optional<Location> save(Location entity) {
        String query = "INSERT INTO locations(location_id, location_name) VALUES(?, ?)";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setDouble(1, entity.getId());
            statement.setString(2, entity.getLocationName());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(entity);
    }

    @Override
    public Optional<Location> delete(Double id) {
        Optional<Location> optionalStudent = findOne(id);
        String query = "DELETE FROM locations WHERE location_id = ?";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setDouble(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalStudent;
    }

    @Override
    public Optional<Location> update(Location entity) {
        String query = "UPDATE locations SET location_name=? WHERE id = ?";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, entity.getLocationName());
            statement.setDouble(2, entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(entity);
    }
}
