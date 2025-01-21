INSERT INTO test_roles (id, role_name, created_at, updated_at) VALUES
('test_role_user_id', 'ROLE_USER', NOW(), NOW()),
('test_role_admin_id', 'ROLE_ADMIN', NOW(), NOW());

INSERT INTO test_users (id, username, email, password, roles, created_at, updated_at) VALUES
('test_user1_id', 'test_user1', 'test_user1@example.com', '$2a$10$EIXUxeWn24BvgW9Hp1sxZO', 'ROLE_USER', NOW(), NOW()),
('test_user2_id', 'test_user2', 'test_user2@example.com', '$2a$10$EIXUxeWn24BvgW9Hp1sxZO', 'ROLE_ADMIN', NOW(), NOW());

INSERT INTO test_posts (id, title, contents, username, created_at, updated_at) VALUES
('test_post1_id', 'Test First Post', 'This is the content of the test first post.', 'test_user1', NOW(), NOW()),
('test_post2_id', 'Test Second Post', 'This is the content of the test second post.', 'test_user2', NOW(), NOW());

