CREATE TABLE IF NOT EXISTS attendances (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    date DATE NOT NULL,
    time_in DATETIME,
    time_out DATETIME,
    created_at DATETIME,
    updated_at DATETIME,
    CONSTRAINT attendances_user_id_date_unique UNIQUE (user_id, date)
);
