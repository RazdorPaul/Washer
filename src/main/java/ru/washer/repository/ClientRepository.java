package ru.washer.repository;

import ru.washer.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository extends BaseRepository {

    public static void save(Client client) throws SQLException {
        String sql = "INSERT INTO clients (nickname, first_name, last_name, phone, email, encrypt_password) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, client.getNickname());
            stmt.setString(2, client.getFirstName());
            stmt.setString(3, client.getLastName());
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getEmail());
            stmt.setString(6, client.getEncryptPassword());

            stmt.executeUpdate();

            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("База не может выдать ID клиента!");
            }
        }
    }

    public static List<Client> findAll() throws SQLException {
        var sql = "SELECT * FROM clients";
        var clients = new ArrayList<Client>();
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                clients.add(mapRowToClient(resultSet));
            }
        }
        return clients;
    }

    public static Optional<Client> findById(Long id) throws SQLException {
        var sql = "SELECT * FROM clients WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRowToClient(resultSet));
            }
        }
        return Optional.empty();
    }

    public static void update(Client client) throws SQLException {
        String sql = "UPDATE clients SET nickname = ?, first_name = ?, last_name = ?, " +
                "phone = ?, email = ?, encrypt_password = ? WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNickname());
            stmt.setString(2, client.getFirstName());
            stmt.setString(3, client.getLastName());
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getEmail());
            stmt.setString(6, client.getEncryptPassword());
            stmt.setLong(7, client.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(Long id) throws SQLException {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<Client> findByFirstOrLastName(String firstName, String lastName) throws SQLException {
        var sql = "SELECT * FROM clients WHERE first_name = ? OR last_name = ?";
        var clients = new ArrayList<Client>();
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                clients.add(mapRowToClient(resultSet));
            }
        }
        return clients;
    }

    public static List<Client> findByPhone(String phone) throws SQLException {
        var sql = "SELECT * FROM clients WHERE phone = ?";
        var clients = new ArrayList<Client>();
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, phone);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                clients.add(mapRowToClient(resultSet));
            }
        }
        return clients;
    }

    public static List<Client> findByEmail(String email) throws SQLException {
        var sql = "SELECT * FROM clients WHERE email = ?";
        var clients = new ArrayList<Client>();
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                clients.add(mapRowToClient(resultSet));
            }
        }
        return clients;
    }

    public static Optional<Client> findByNickname(String nickname) throws SQLException {
        var sql = "SELECT * FROM clients WHERE nickname = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nickname);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRowToClient(resultSet));
            }
        }
        return Optional.empty();
    }

    private static Client mapRowToClient(ResultSet rs) throws SQLException {
        var client = new Client(
                rs.getString("nickname"),
                rs.getString("email"),
                rs.getString("encrypt_password")
        );
        client.setId(rs.getLong("id"));
        client.setFirstName(rs.getString("first_name"));
        client.setLastName(rs.getString("last_name"));
        client.setPhone(rs.getString("phone"));
        return client;
    }
}