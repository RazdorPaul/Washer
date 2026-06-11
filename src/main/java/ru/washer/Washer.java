package ru.washer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import ru.washer.repository.BaseRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Washer {

    public static void main(String[] args) throws Exception {
        var app = getApp();
        app.start(7070);
    }

    public static Javalin getApp() throws Exception {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/washer_db");
        hikariConfig.setUsername("washer_user");
        hikariConfig.setPassword("kassagar");
        var dataSource = new HikariDataSource(hikariConfig);
        BaseRepository.dataSource = dataSource;
        initDatabaseSchema(dataSource);
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());

        });

        return app;
    }

    private static void initDatabaseSchema(HikariDataSource dataSource) throws Exception {
        var inputStream = Washer.class.getClassLoader().getResourceAsStream("schema.sql");

        if (inputStream == null) {
            throw new Exception("Файл schema.sql не найден в ресурсах!");
        }
        var sql = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("✅ Схема базы данных успешно загружена!");
        }
    }
}