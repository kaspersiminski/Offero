ALTER TABLE users
ADD COLUMN confirmation_token VARCHAR(255),
ADD COLUMN user_status VARCHAR(40);
