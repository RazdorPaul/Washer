package ru.washer.repository;

import ru.washer.model.Client;

import java.sql.SQLException;
import java.util.Optional;

public class ClientRepository extends BaseRepository{

    public static Optional<Client> findById(Long id) throws SQLException {
        var sql = "SELECT * FROM clients WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                var firstName = resultSet.getString("first_name");
                var lastName = resultSet.getString("last_name");
                var phone = resultSet.getString("phone");
                var client = new Client(firstName, lastName, phone);
                client.setId(resultSet.getLong("id"));
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }
}
