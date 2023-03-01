package com.example.sub3feb2023.repository.db;

import com.example.sub3feb2023.domain.Client;
import com.example.sub3feb2023.domain.HobbiesENUM;
import com.example.sub3feb2023.repository.Repository;
import com.example.sub3feb2023.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ClientDbRepository implements Repository<Long, Client> {
    private JDBCUtils jdbcUtils=new JDBCUtils();

    @Override
    public Optional<Client> findOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        String query = "SELECT * from clients where client_id = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Integer fidelity_grade=resultSet.getInt("fidelity_grade");
                Integer varsta=resultSet.getInt("varsta");
                HobbiesENUM hobbies=HobbiesENUM.valueOf(resultSet.getString("hobbies"));

                Client client = new Client(id,name,fidelity_grade,varsta,hobbies);
                return Optional.of(client);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> save(Client entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity) {
        return Optional.empty();
    }
}
