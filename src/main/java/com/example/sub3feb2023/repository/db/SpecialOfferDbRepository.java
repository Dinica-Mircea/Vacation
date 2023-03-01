package com.example.sub3feb2023.repository.db;

import com.example.sub3feb2023.domain.Location;
import com.example.sub3feb2023.domain.SpecialOffer;
import com.example.sub3feb2023.repository.Repository;
import com.example.sub3feb2023.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SpecialOfferDbRepository implements Repository<Double, SpecialOffer> {
    private JDBCUtils jdbcUtils=new JDBCUtils();


    @Override
    public Optional<SpecialOffer> findOne(Double aDouble) {
        return Optional.empty();
    }

    @Override
    public Iterable<SpecialOffer> findAll() {
        Set<SpecialOffer> specialOffers = new HashSet<>();

        String query = "SELECT * from specialoffers";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                Double specialOfferId = resultSet.getDouble("special_offer_id");
                Double hotelId = resultSet.getDouble("hotel_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                Integer percents=resultSet.getInt("percents");

                SpecialOffer specialOffer=new SpecialOffer(specialOfferId,hotelId,startDate,endDate,percents);
                specialOffers.add(specialOffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialOffers;
    }

    @Override
    public Optional<SpecialOffer> save(SpecialOffer entity) {
        return Optional.empty();
    }

    @Override
    public Optional<SpecialOffer> delete(Double aDouble) {
        return Optional.empty();
    }

    @Override
    public Optional<SpecialOffer> update(SpecialOffer entity) {
        return Optional.empty();
    }
}
