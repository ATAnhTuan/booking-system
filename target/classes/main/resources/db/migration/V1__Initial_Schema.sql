CREATE EXTENSION IF NOT EXISTS "pgcrypto";



-- Tạo bảng users với các ràng buộc chuẩn
CREATE TABLE IF NOT EXISTS users (
    -- Khóa chính dùng Long (BigSerial trong Postgres)
                       id BIGSERIAL PRIMARY KEY,

    -- Khóa công khai dùng UUID, tự động sinh nếu Java không truyền vào
                       user_guid UUID DEFAULT gen_random_uuid() NOT NULL UNIQUE,

    -- Thông tin đăng nhập
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100),
                       phone VARCHAR(20),

    -- Phân quyền và phân hạng khách hàng
    -- Dùng CHECK để đảm bảo dữ liệu không bị sai lệch tầng DB
                       role VARCHAR(20) DEFAULT 'CUSTOMER' NOT NULL
                           CHECK (role IN ('ADMIN', 'MANAGER', 'STAFF', 'CUSTOMER')),

                       member_rank VARCHAR(20) DEFAULT 'NEW'
                           CHECK (member_rank IN ('NEW', 'SILVER', 'GOLD', 'VIP')),

    -- Trạng thái hoạt động
                       active BOOLEAN DEFAULT true NOT NULL,

    -- Thời gian tạo (Tự động lấy giờ hệ thống)
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Tạo Index cho username và email để tăng tốc độ tìm kiếm khi đăng nhập
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);


-- Tạo bảng hotels với các ràng buộc chuẩn
CREATE TABLE IF NOT EXISTS hotels (
    -- Khóa chính nội bộ
                        id BIGSERIAL PRIMARY KEY,

    -- Khóa công khai cho API
                        hotel_guid UUID DEFAULT gen_random_uuid() NOT NULL UNIQUE,

    -- Thông tin cơ bản
                        hotel_name VARCHAR(255) NOT NULL,
                        address TEXT NOT NULL,
                        description TEXT,

    -- Thông tin liên lạc
                        phone VARCHAR(20),
                        email VARCHAR(100),

    -- Trạng thái: ACTIVE, INACTIVE, MAINTENANCE
                        status VARCHAR(20) DEFAULT 'ACTIVE'
                            CHECK (status IN ('ACTIVE', 'INACTIVE', 'MAINTENANCE')),

    -- Đánh giá: 1, 2, 3, 4, 5 sao (Dùng VARCHAR hoặc INTEGER)
                        rating VARCHAR(10),

    -- Audit columns
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index để tìm kiếm theo tên khách sạn nhanh hơn
CREATE INDEX idx_hotels_name ON hotels(hotel_name);


-- Tạo bảng rooms với các ràng buộc chuẩn
CREATE TABLE IF NOT EXISTS rooms (
    -- Khóa chính nội bộ
                       id BIGSERIAL PRIMARY KEY,

    -- Khóa công khai cho API
                       room_guid UUID DEFAULT gen_random_uuid() NOT NULL UNIQUE,

    -- Liên kết với Khách sạn (Bắt buộc)
                       hotel_guid UUID NOT NULL REFERENCES hotels(hotel_guid) ON DELETE CASCADE,

    -- Thông tin phòng
                       room_name VARCHAR(100) NOT NULL,
                       description TEXT,

    -- Loại phòng: SINGLE, DOUBLE, SUITE, DELUXE...
                       category VARCHAR(50),

    -- Trạng thái: AVAILABLE (Trống), OCCUPIED (Có khách), MAINTENANCE (Bảo trì)
                       status VARCHAR(20) DEFAULT 'AVAILABLE'
                           CHECK (status IN ('AVAILABLE', 'OCCUPIED', 'MAINTENANCE')),

    -- Giá phòng (Dùng DECIMAL để tính toán chính xác hơn Long)
                       price DECIMAL(15, 2) NOT NULL DEFAULT 0,

    -- Audit columns
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index để tìm nhanh các phòng thuộc một khách sạn
CREATE INDEX idx_rooms_hotel_guid ON rooms(hotel_guid);
-- Index hỗ trợ lọc phòng theo trạng thái và giá
CREATE INDEX idx_rooms_status_price ON rooms(status, price);



-- Tạo bảng images với các ràng buộc chuẩn
CREATE TABLE IF NOT EXISTS images (
    -- Khóa chính nội bộ
                        id BIGSERIAL PRIMARY KEY,

    -- Khóa công khai cho API (Nếu cần quản lý ảnh riêng lẻ)
                        image_guid UUID DEFAULT gen_random_uuid() NOT NULL UNIQUE,

    -- Liên kết: Một ảnh có thể thuộc Hotel hoặc Room
    -- Cho phép NULL ở cả hai để linh hoạt, nhưng logic code sẽ đảm bảo có ít nhất 1 cái
                        hotel_guid UUID REFERENCES hotels(hotel_guid) ON DELETE CASCADE,
                        room_guid UUID REFERENCES rooms(room_guid) ON DELETE CASCADE,

    -- Đường dẫn ảnh (URL hoặc Path lưu trong thư mục server)
    -- Dùng TEXT để tránh giới hạn ký tự của VARCHAR nếu URL quá dài
                        image_url TEXT NOT NULL,

    -- Metadata (Tùy chọn: Để mô tả ảnh như "Ảnh bìa", "Ảnh ban đêm")
                        description VARCHAR(255),

    -- Audit columns
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index để lấy toàn bộ ảnh của một Khách sạn hoặc một Phòng cực nhanh
CREATE INDEX idx_images_hotel_guid ON images(hotel_guid);
CREATE INDEX idx_images_room_guid ON images(room_guid);

-- Tạo bảng bookings với các ràng buộc chuẩn
CREATE TABLE bookings (
    -- Khóa chính nội bộ
                          id BIGSERIAL PRIMARY KEY,

    -- Khóa công khai cho API và tra cứu đơn hàng
                          booking_guid UUID DEFAULT gen_random_uuid() NOT NULL UNIQUE,

    -- CÁC MỐI LIÊN KẾT (Foreign Keys) - Ràng buộc chặt chẽ với các bảng trước
                          user_guid UUID NOT NULL REFERENCES users(user_guid),
                          hotel_guid UUID NOT NULL REFERENCES hotels(hotel_guid),
                          room_guid UUID NOT NULL REFERENCES rooms(room_guid),

    -- Thông tin liên hệ (Có thể khác với thông tin trong bảng Users)
                          contact_phone VARCHAR(20) NOT NULL,
                          contact_email VARCHAR(100),

    -- Chi tiết đơn đặt phòng
                          description TEXT,
                          booking_date_start DATE NOT NULL,
                          booking_date_end DATE NOT NULL,

    -- Giá trị giao dịch (Nên dùng DECIMAL cho tiền tệ)
                          price DECIMAL(15, 2) NOT NULL DEFAULT 0,

    -- Trạng thái đơn hàng: Để quản lý vòng đời của một Booking
                          status VARCHAR(20) DEFAULT 'PENDING'
                              CHECK (status IN ('PENDING', 'CONFIRMED', 'PAID', 'CANCELLED', 'COMPLETED')),

    -- Audit columns
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Index để truy xuất lịch sử đặt phòng nhanh chóng
CREATE INDEX idx_bookings_user_guid ON bookings(user_guid);
CREATE INDEX idx_bookings_hotel_room ON bookings(hotel_guid, room_guid);
-- Index để quản lý các booking theo khoảng thời gian (tránh đặt trùng phòng)
CREATE INDEX idx_bookings_period ON bookings(booking_date_start, booking_date_end);