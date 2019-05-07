SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE bf_module_group;
TRUNCATE bf_menu_category;
TRUNCATE bf_user;
TRUNCATE bf_group;
TRUNCATE bf_module;
SET FOREIGN_KEY_CHECKS = 1;
ALTER TABLE bf_module_group AUTO_INCREMENT = 1000;
ALTER TABLE bf_menu_category AUTO_INCREMENT = 1000;
ALTER TABLE bf_user AUTO_INCREMENT = 1000;
ALTER TABLE bf_group AUTO_INCREMENT = 1000;
ALTER TABLE bf_module AUTO_INCREMENT = 1000;

# Groups
INSERT INTO bf_group(id, name, description, visible) VALUES(1000, 'Администраторы', 'Администраторы системы', FALSE);
INSERT INTO bf_group(id, name, description) VALUES(1001, 'Операторы штаб', 'Операторы штаб');
INSERT INTO bf_group(id, name, description) VALUES(1002, 'Операторы фонд', 'Операторы фонд');

# Users
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, visible, last_enter, ip_address, group_id)
  VALUES (1000, 'Администратор', 'null', 'null', 'admin', 'admin', TRUE, FALSE, NULL, NULL, 1000);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1001, 'Света Иванова', '123456789', 'KK001122', 'ivanova', 'ivanova', TRUE, NULL, NULL, 1001);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1002, 'Маша Петрова', '987654321', 'KK221100', 'petrova', 'petrova', FALSE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1003, 'Иван Иванов', '987654321', 'KK221100', 'ivanov', 'ivanov', FALSE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1004, 'Сеня Сидоров', '987654321', 'KK221100', 'sidorov', 'sidorov', TRUE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1005, 'Петя Петров', '987654321', 'KK221100', 'petrov', 'petrov', TRUE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1006, 'Андрей Герц', '987654321', 'KK221100', 'gerts', 'gerts', TRUE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1007, 'Виктор Смоляков', '987654321', 'KK221100', 'smolyakov', 'smolyakov', TRUE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1008, 'Юра Ткач', '987654321', 'KK221100', 'tkach', 'tkach', TRUE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1009, 'Константин Петков', '987654321', 'KK221100', 'petkov', 'petkov', TRUE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1010, 'Валера Дончук', '987654321', 'KK221100', 'donchuk', 'donchuk', FALSE, NULL, NULL, 1002);
INSERT INTO bf_user(id, name, ident_number, passport, login, password, enabled, last_enter, ip_address, group_id)
  VALUES (1011, 'Андрей Дмитриев', '987654321', 'KK221100', 'dmitriev', 'dmitriev', FALSE, NULL, NULL, 1002);

# Modules
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1000, 'Операторы', 'Управление операторами', 'modules/module-operators', TRUE, TRUE, 'MODULE_OPERATORS');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1001, 'Группы операторов', 'Управление группами операторов', 'modules/module-groups', TRUE, TRUE, 'MODULE_GROUPS');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1002, 'Поиск/Добавить', 'Добавление человека в базу', 'modules/module-search-add-person', TRUE, TRUE, 'MODULE_SEARCH');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1003, 'Анкета на должность', 'Добавление анкеты на должность', 'modules/module-anketa-na-posadu', TRUE , TRUE, 'MODULE_ANKETA_NA_POSADU');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1004, 'Модули', 'Управление модулями системы', 'modules/module-modules', TRUE, TRUE, 'MODULE_MODULES');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1005, 'Штаб', 'Данные штаба', 'modules/module-shtab', TRUE, TRUE, 'MODULE_STAFF');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1006, 'Отделения', 'Данные отделений', 'modules/module-otdeleniya', TRUE, FALSE, 'MODULE_DEPARTMENTS');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1007, 'Меню категорий', 'Добавление/изменение категорий меню', 'modules/module-menu', TRUE, TRUE, 'MODULE_MENU');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1008, 'Выборы', 'Управление выборами', 'modules/module-election', TRUE, TRUE, 'MODULE_ELECTION');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1009, 'Кадидаты/Партии', 'Управление кандидатами, партиями', 'modules/module-election-candidates', TRUE, TRUE, 'MODULE_CANDIDATES');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1010, 'ОВК', 'Управление ОВК', 'modules/module-ovk', TRUE, TRUE, 'MODULE_OVK');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1011, 'ДВК', 'Управление ДВК', 'modules/module-dvk', TRUE, TRUE, 'MODULE_DVK');
INSERT INTO bf_module(id, name, description, reference, enabled, visible, permission)
  VALUES (1012, 'Добавить человека', 'Добавление человека', 'modules/module-add-person', TRUE, TRUE, 'MODULE_ADD_PERSON');

# Menu categories
INSERT INTO bf_menu_category(id, name, description, parent, menu_icon, module_id, ordered) VALUES (1000, 'Користувачі', NULL, NULL, '<i class=\"fas fa-users-cog\"></i>', NULL, 100);
INSERT INTO bf_menu_category(id, name, description, parent, menu_icon, module_id, ordered) VALUES (1001, 'Люди', NULL, NULL, '<i class="fas fa-users"></i>', NULL, 200);
INSERT INTO bf_menu_category(id, name, description, parent, module_id, ordered) VALUES (1002, 'Персонал ШТАБ', NULL, NULL, NULL, 300);
INSERT INTO bf_menu_category(id, name, description, parent, module_id, ordered) VALUES (1003, 'Персонал', NULL, NULL, NULL, 400);
INSERT INTO bf_menu_category(id, name, description, parent, menu_icon, module_id, ordered) VALUES (1004, 'Super User', NULL, NULL, '<i class="fas fa-user-shield"></i>', NULL, 600);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1005, 'Оператори', NULL, 1000, 1000);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1006, 'Групи операторів', NULL, 1000, 1001);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1007, 'Пошук/Додати', NULL, 1001, 1002);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1008, 'Анкета на посаду', NULL, 1001, 1003);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1009, 'Штаб', NULL, 1002, 1005);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1010, 'Дільниці', NULL, 1003, 1006 );
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1011, 'Модулі', NULL, 1004, 1004);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1012, 'Меню категорий', NULL, 1000, 1007);
INSERT INTO bf_menu_category(id, name, description, parent, menu_icon, module_id, ordered) VALUES (1013, 'Выборы', NULL, NULL, '<i class="fas fa-users"></i>', NULL, 500);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1014, 'Выборы кандидатов', NULL, 1013, 1008);
INSERT INTO bf_menu_category(id, name, description, parent, module_id) VALUES (1015, 'Кандидаты', NULL, 1013, 1009);

# Modules with groups
INSERT INTO bf_module_group(module_id, group_id) VALUES (1002, 1001);
INSERT INTO bf_module_group(module_id, group_id) VALUES (1003, 1001);
INSERT INTO bf_module_group(module_id, group_id) VALUES (1002, 1002);
INSERT INTO bf_module_group(module_id, group_id) VALUES (1003, 1002);
INSERT INTO bf_module_group(module_id, group_id) VALUES (1005, 1001);






















