delete from sys_user_roles;
alter table sys_user_roles AUTO_INCREMENT = 1;
delete from sys_user;
alter table sys_user AUTO_INCREMENT = 1;
truncate sys_authorization;
alter table sys_authorization AUTO_INCREMENT = 1;
delete from  sys_menu;
alter table sys_menu AUTO_INCREMENT = 1;
delete from  sys_roles;
alter table sys_roles AUTO_INCREMENT = 1;


INSERT INTO sys_menu (id, created_by, created_time, disabled, modified_by, modified_time, version, menus_name, url, url_regex) VALUES
(4, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 10:00:00.000', '1.0', 'Utility', NULL, NULL),
(5, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-02-27 05:15:47.000', '1.0', 'User Management', NULL, NULL),
(6, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 11:50:37.000', '1.0', 'User', '../../../admin/v1/view/main#/user/', '/admin/v1/view/user/'),
(7, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 11:50:43.000', '1.0', 'Authorization', '../../../admin/v1/view/main#/authorization/', '/admin/v1/view/authorization/'),
(8, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-02-27 05:23:24.000', '1.0', 'Profile Setting', 'admin/v1/setting/calendar', NULL),
(9, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 10:00:00.000', '1.0', 'Report Setting', 'admin/v1/reportsetting ', NULL),
(10, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 10:00:00.000', '1.0', 'Calendar Setting', 'admin/v1/setting/calendar', NULL),
(11, 'NULL', '2021-03-01 10:00:00.000', 0, 'NULL', '2021-03-01 10:00:00.000', '1.0', 'Application Setting', NULL, NULL),
(12, 'NULL', '2021-03-01 10:00:00.000', 0, 'NULL', '2021-03-01 21:42:16.000', '1.0', 'Add Menu', '../../../admin/v1/view/menu', '/view/menu'),
(13, 'NULL', '2021-03-01 10:00:00.000', 0, 'NULL', '2021-03-01 11:52:24.000', '1.0', 'User Group', '../../../admin/v1/view/main#/usergroup/', '/admin/v1/view/usergroup/'),
(24, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 10:00:00.000', '1.0', 'Main Menu', NULL, NULL),
(25, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 11:50:37.000', '1.0', 'Daftar Pelaku', '../../../admin/v1/view/main#/daftar-pelaku/', '/admin/v1/view/daftar/pelaku/'),
(26, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 11:50:37.000', '1.0', 'Daftar Pencarian', '../../../admin/v1/view/main#/daftar-dpo/', '/admin/v1/view/daftar/dpo/'),
(27, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 11:50:37.000', '1.0', 'Sprint', NULL, NULL),
(28, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 11:50:37.000', '1.0', 'Sprintdik', '../../../admin/v1/view/main#/spintdik/', '/admin/v1/view/spintdik/'),
(29, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 11:50:37.000', '1.0', 'Sprintgas', '../../../admin/v1/view/main#/spintgas/', '/admin/v1/view/sprintgas/'),
(30, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-01 11:50:37.000', '1.0', 'Penugasan Anggota', '../../../admin/v1/view/main#/penugasan-anggota/', '/admin/v1/view/penugasan/anggota/'),
(31, 'ari', '2021-03-01 10:00:00.000', 0, 'ari', '2021-03-02 16:49:18.000', '1.0', 'Laporan Anggota', '../../../admin/v1/view/main#/laporan-anggota/', '/admin/v1/view/anggota/laporan/');


INSERT INTO sys_roles (id, created_by, created_time, disabled, modified_by, modified_time, version, role_name) VALUES
(1, NULL, '2021-03-1 01:38:52', 0, NULL, '2021-03-1 01:38:52', '1.0', 'admin'),
(2, NULL, '2021-03-1 01:38:52', 0, NULL, '2021-03-1 01:38:52', '1.0', 'approval'),
(3, NULL, '2021-03-1 01:38:52', 0, NULL, '2021-03-1 01:38:52', '1.0', 'user'),
(4, NULL, '2021-03-1 01:38:52', 0, NULL, '2021-03-1 01:38:52', '1.0', 'public');


INSERT INTO sys_authorization (id, created_by, created_time, disabled, modified_by, modified_time, version, sys_roles_id, is_delete, is_insert, is_read, is_update, parent_id, sys_menu_id) VALUES
(72, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, NULL, 4),
(73, NULL, '2021-03-1 22:01:14', 1, NULL, '2021-03-1 22:01:14', '1.0', 1, 1, 1, 1, 1, 72, 5),
(74, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 1, 1, 1, 73, 6),
(75, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 1, 1, 1, 73, 7),
(76, NULL, '2021-03-1 22:01:14', 1, NULL, '2021-03-1 22:01:14', '1.0', 1, 1, 1, 1, 1, 72, 8),
(77, NULL, '2021-03-1 22:01:15', 1, NULL, '2021-03-1 22:01:15', '1.0', 1, 0, 1, 1, 1, 72, 9),
(78, NULL, '2021-03-1 22:01:15', 1, NULL, '2021-03-1 22:01:15', '1.0', 1, 0, 1, 1, 1, 72, 10),
(240, NULL, '2021-03-1 15:52:41', 1, NULL, '2021-03-1 15:52:41', '1.0', 1, 1, 1, 1, 1, 73, 13),
(243, NULL, '2021-03-1 15:56:24', 1, NULL, '2021-03-1 15:56:24', '1.0', 1, 1, 1, 1, 1, 72, 11),
(244, NULL, '2021-03-1 15:57:01', 1, NULL, '2021-03-1 15:57:01', '1.0', 1, 1, 1, 1, 1, 243, 12),
(245, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, NULL, 24),
(246, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, 245, 25),
(247, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, 245, 26),
(248, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, 245, 27),
(249, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, 248, 28),
(250, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, 248, 29),
(251, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, 245, 30),
(252, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 1, 0, 0, 1, 1, 245, 31),
(253, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 3, 0, 0, 1, 1, NULL, 24),
(254, NULL, '2021-03-1 22:01:14', 0, NULL, '2021-03-1 22:01:14', '1.0', 3, 0, 0, 1, 1, 253, 31);


INSERT INTO sys_user (id, created_by, created_time, disabled, modified_by, modified_time, version, email, is_active, name, no_hp, password, username) VALUES
(1, NULL, '2021-03-1 08:14:39', 0, NULL, '2021-03-1 08:14:39', '1.0', 'prasetiyooo@gmail.com', 1, '', '085645480401', '{noop}1234', 'NRP1'),
(2, NULL, '2021-03-1 22:15:20', 0, NULL, '2021-03-1 22:15:20', '1.0', 'coba@coba', 1, 'coba', '1111111111', '{noop}12345', 'NRP2');


INSERT INTO sys_user_roles (id, created_by, created_time, disabled, modified_by, modified_time, version, sys_roles_id, sys_user_id) VALUES
(1, NULL, '2021-03-1 21:02:17',0, NULL, '2021-03-1 21:02:17', '1.0', 1, 1),
(2, NULL, '2021-03-1 22:08:47',0, NULL, '2021-03-1 22:08:47', '1.0', 3, 2);