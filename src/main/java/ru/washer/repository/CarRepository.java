package ru.washer.repository;

import ru.washer.model.Car;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepository extends BaseRepository {

    public static void save(Car car) throws SQLException {
        String sql = "INSERT INTO cars (brand, model) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.executeUpdate();
            var generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                car.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("База не может выдать ID машины!");
            }
        }
    }

    public static List<Car> findAll() throws SQLException{
        var sql = "SELECT * FROM cars";
        var cars = new ArrayList<Car>();
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var brand = resultSet.getString("brand");
                var model = resultSet.getString("model");
                var car = new Car(brand, model);
                car.setId(resultSet.getLong("id"));
                cars.add(car);
            }
        }
        return cars;
    }

    public static Optional<Car> findById(Long id) throws SQLException {
        var sql = "SELECT * FROM cars WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var brand = resultSet.getString("brand");
                var model = resultSet.getString("model");
                var car = new Car(brand, model);
                car.setId(resultSet.getLong("id"));
                return Optional.of(car);
            }
        }
        return Optional.empty();
    }

    public static void update(Car car) throws SQLException {
        String sql = "UPDATE cars set brand = ?, model = ? WHERE id = ?";
        try (var conn = dataSource.getConnection();
                 var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setLong(3, car.getId());
            stmt.executeUpdate();
        }
    }

    public static void delete(Long id) throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<Car> findByBrandOrModel(String termBrand, String termModel) throws SQLException {
        var cars = new ArrayList<Car>();
        String sql = "SELECT * FROM cars WHERE brand = ? OR model = ?";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, termBrand);
            stmt.setString(2, termModel);
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var brand = resultSet.getString("brand");
                var model = resultSet.getString("model");
                var car = new Car(brand, model);
                car.setId(resultSet.getLong("id"));
                cars.add(car);
            }
        }
        return cars;
    }
}
