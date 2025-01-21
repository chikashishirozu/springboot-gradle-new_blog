INSERT INTO roles (id, role_name, created_at, updated_at) VALUES
('role_user_id', 'ROLE_USER', NOW(), NOW()),
('role_admin_id', 'ROLE_ADMIN', NOW(), NOW());

INSERT INTO users (id, username, email, password, roles, created_at, updated_at) VALUES
('user1_id', 'user1', 'user1@example.com', '$2a$10$EIXUxeWn24BvgW9Hp1sxZO', 'ROLE_USER', NOW(), NOW()),
('user2_id', 'user2', 'user2@example.com', '$2a$10$EIXUxeWn24BvgW9Hp1sxZO', 'ROLE_ADMIN', NOW(), NOW());

INSERT INTO posts (id, title, contents, username, created_at, updated_at) VALUES
('post1_id', 'First Post', 'This is the content of the first post.', 'user1', NOW(), NOW()),
('post2_id', 'Second Post', 'This is the content of the second post.', 'user2', NOW(), NOW());

