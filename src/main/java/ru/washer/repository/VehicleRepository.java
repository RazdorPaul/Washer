package ru.washer.repository;

import ru.washer.model.Car;
import ru.washer.model.Motorcycle;
import ru.washer.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepository extends BaseRepository {

    public static void save(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO vehicles (client_id, model_id, type, license_plate, vin, year, " +
                "number_of_doors, body_type, engine_capacity, category) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, vehicle.getClientId());
            stmt.setLong(2, vehicle.getModelId());
            stmt.setString(3, vehicle.getType());
            stmt.setString(4, vehicle.getLicensePlate());
            stmt.setString(5, vehicle.getVin());
            stmt.setObject(6, vehicle.getYear());

            // Заполняем специфичные поля только если объект соответствующего типа
            if (vehicle instanceof Car car) {
                stmt.setObject(7, car.getNumberOfDoors());
                stmt.setString(8, car.getBodyType());
                stmt.setNull(9, java.sql.Types.INTEGER);
                stmt.setNull(10, java.sql.Types.VARCHAR);
            } else if (vehicle instanceof Motorcycle moto) {
                stmt.setNull(7, java.sql.Types.INTEGER);
                stmt.setNull(8, java.sql.Types.VARCHAR);
                stmt.setObject(9, moto.getEngineCapacity());
                stmt.setString(10, moto.getCategory());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
                stmt.setNull(8, java.sql.Types.VARCHAR);
                stmt.setNull(9, java.sql.Types.INTEGER);
                stmt.setNull(10, java.sql.Types.VARCHAR);
            }

            stmt.executeUpdate();
            var keys = stmt.getGeneratedKeys();
            if (keys.next()) vehicle.setId(keys.getLong(1));
        }
    }

    public static Optional<Vehicle> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM vehicles WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) return Optional.of(mapRowToVehicle(rs));
        }
        return Optional.empty();
    }

    public static List<Vehicle> findAll() throws SQLException {
        var vehicles = new ArrayList<Vehicle>();
        String sql = "SELECT * FROM vehicles";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var rs = stmt.executeQuery();
            while (rs.next()) vehicles.add(mapRowToVehicle(rs));
        }
        return vehicles;
    }

    public static List<Vehicle> findByClientId(Long clientId) throws SQLException {
        var vehicles = new ArrayList<Vehicle>();
        String sql = "SELECT * FROM vehicles WHERE client_id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, clientId);
            var rs = stmt.executeQuery();
            while (rs.next()) vehicles.add(mapRowToVehicle(rs));
        }
        return vehicles;
    }

    public static void update(Vehicle vehicle) throws SQLException {
        String sql = "UPDATE vehicles SET client_id = ?, model_id = ?, type = ?, " +
                "license_plate = ?, vin = ?, year = ?, updated_at = CURRENT_TIMESTAMP " +
                "WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, vehicle.getClientId());
            stmt.setLong(2, vehicle.getModelId());
            stmt.setString(3, vehicle.getType());
            stmt.setString(4, vehicle.getLicensePlate());
            stmt.setString(5, vehicle.getVin());
            stmt.setObject(6, vehicle.getYear());
            stmt.setLong(7, vehicle.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(Long id) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Фабричный метод: читает колонку type и создает нужный подкласс.
     */
    private static Vehicle mapRowToVehicle(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        Vehicle vehicle;

        switch (type) {
            case "CAR" -> vehicle = new Car();
            case "MOTOCYCLE" -> vehicle = new Motorcycle(); // Опечатка в строке? Проверь константу в модели
            default -> throw new SQLException("Неизвестный тип транспорта: " + type);
        }

        // Общие поля
        vehicle.setId(rs.getLong("id"));
        vehicle.setClientId(rs.getLong("client_id"));
        vehicle.setModelId(rs.getLong("model_id"));
        vehicle.setType(type);
        vehicle.setLicensePlate(rs.getString("license_plate"));
        vehicle.setVin(rs.getString("vin"));
        vehicle.setYear(rs.getObject("year", Integer.class));
        vehicle.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        vehicle.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));

        // Специфичные поля
        if (vehicle instanceof Car car) {
            car.setNumberOfDoors(rs.getObject("number_of_doors", Integer.class));
            car.setBodyType(rs.getString("body_type"));
        } else if (vehicle instanceof Motorcycle moto) {
            moto.setEngineCapacity(rs.getObject("engine_capacity", Integer.class));
            moto.setCategory(rs.getString("category"));
        }

        return vehicle;
    }
}