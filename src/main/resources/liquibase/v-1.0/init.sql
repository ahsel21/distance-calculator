INSERT INTO public.person (id, username, password, email) VALUES (1, 'admin', '$2y$12$8OLin90bOrR7HRAVD1spZuXU9EriMaIREHDSyzIPVN9DY/5npDc9m', 'admin@gmail.com');
INSERT INTO public.person (id, username, password, email) VALUES (2, 'moder', '$2y$12$8OLin90bOrR7HRAVD1spZuXU9EriMaIREHDSyzIPVN9DY/5npDc9m', 'moder@gmail.com');
INSERT INTO public.person (id, username, password, email) VALUES (3, 'user', '$2y$12$8OLin90bOrR7HRAVD1spZuXU9EriMaIREHDSyzIPVN9DY/5npDc9m', 'user@gmail.com');
INSERT INTO public.person (id, username, password, email) VALUES (4, 'user2', '$2y$12$8OLin90bOrR7HRAVD1spZuXU9EriMaIREHDSyzIPVN9DY/5npDc9m', 'user2@gmail.com');
INSERT INTO public.person (id, username, password, email) VALUES (5, 'user3', '$2y$12$8OLin90bOrR7HRAVD1spZuXU9EriMaIREHDSyzIPVN9DY/5npDc9m', 'user3@gmail.com');

INSERT INTO public.room (id, name, is_private, is_bot, is_deleted) VALUES (1, 'Public', false, false, false);

INSERT INTO public.message (id, user_id, message_text, created_date_time, room_id) VALUES (1, 1, 'HELLO', '2021-06-26 16:55:24.946485', 1);

INSERT INTO public.room_role (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO public.room_role (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO public.room_role (id, name) VALUES (3, 'ROLE_ADMIN');

INSERT INTO public.role_room_user (id, role_id, room_id, user_id) VALUES (1, 1, 1, 1);
INSERT INTO public.role_room_user (id, role_id, room_id, user_id) VALUES (2, 2, 1, 1);
INSERT INTO public.role_room_user (id, role_id, room_id, user_id) VALUES (3, 3, 1, 1);
INSERT INTO public.role_room_user (id, role_id, room_id, user_id) VALUES (4, 2, 1, 2);
INSERT INTO public.role_room_user (id, role_id, room_id, user_id) VALUES (5, 3, 1, 2);
INSERT INTO public.role_room_user (id, role_id, room_id, user_id) VALUES (6, 3, 1, 3);
INSERT INTO public.role_room_user (id, role_id, room_id, user_id) VALUES (7, 1, 1, 4);
INSERT INTO public.role_room_user (id, role_id, room_id, user_id) VALUES (8, 1, 1, 5);





