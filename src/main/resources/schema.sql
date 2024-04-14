CREATE TABLE attendances (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    date DATE NOT NULL,
    time_in DATETIME,
    time_out DATETIME,
    created_at TIMESTAMP,  -- CommonEntity の属性
    updated_at TIMESTAMP,  -- CommonEntity の属性
    CONSTRAINT attendances_user_id_date_unique UNIQUE (user_id, date)
);
