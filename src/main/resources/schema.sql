CREATE TABLE IF NOT EXISTS attendances (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    time_in DATETIME,
    time_out DATETIME,
    created_at DATETIME,
    updated_at DATETIME,
    CONSTRAINT attendances_user_id_date_unique UNIQUE (user_id, date)
);

CREATE TABLE IF NOT EXISTS breaks (
    break_id INT AUTO_INCREMENT PRIMARY KEY,
    attendance_id INT NOT NULL,
    break_start DATETIME,
    break_end DATETIME,
    created_at DATETIME,
    updated_at DATETIME,
    CONSTRAINT fk_breaks_attendance FOREIGN KEY (attendance_id) REFERENCES attendances (attendance_id)
);
