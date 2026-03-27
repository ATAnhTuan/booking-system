package com.bookingSystem.config;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class FlywayFactory {

    private DataSource dataSource;
    private String locations;

    // Setters để Spring XML inject property
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    // Factory method trả về Flyway instance
    public Flyway createFlyway() {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations(locations)
                .baselineOnMigrate(true) // Quan trọng nếu DB đã có bảng từ trước
                .validateOnMigrate(true)
                .cleanDisabled(false)    // Cho phép dọn dẹp nếu cần
                .load();
    }
}