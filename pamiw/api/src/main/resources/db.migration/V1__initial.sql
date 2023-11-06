CREATE TABLE computer (
                          id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
                          name VARCHAR(255) NOT NULL UNIQUE,
                          type VARCHAR(255) NOT NULL,
                          enclosure_name VARCHAR(255) NOT NULL,
                          cpu_name VARCHAR(255) NOT NULL,
                          ram_capacity INT NOT NULL,
                          hard_disk_capacity INT NOT NULL,
                          gpu_name VARCHAR(255) NOT NULL,
                          power_supply INT NOT NULL,
                          price INT NOT NULL
);