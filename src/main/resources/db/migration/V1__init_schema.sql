CREATE TABLE IF NOT EXISTS inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    sku VARCHAR(255) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    available_quantity INT NOT NULL,
    reserved_quantity INT NOT NULL,
    warehouse VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT uk_product_id UNIQUE (product_id),
    CONSTRAINT uk_sku UNIQUE (sku)
    );

CREATE TABLE IF NOT EXISTS processed_events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id VARCHAR(255) NOT NULL,
    processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_event_id UNIQUE (event_id)
    );
