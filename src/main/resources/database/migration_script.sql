CREATE TABLE `role` (\n  `id` bigint NOT NULL AUTO_INCREMENT,\n  `name` varchar(255) DEFAULT NULL,\n  PRIMARY KEY (`id`)\n) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
CREATE TABLE `user` (\n  `id` bigint NOT NULL AUTO_INCREMENT,\n  `password` varchar(255) DEFAULT NULL,\n  `username` varchar(255) DEFAULT NULL,\n  `role_id` bigint DEFAULT NULL,\n  PRIMARY KEY (`id`),\n  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`)\n) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

INSERT INTO `db`.`role` (`name`) VALUES ('ADMIN');
INSERT INTO `db`.`user` (`password`, `username`, `role_id`) VALUES ('$2a$10$TuQ3NLtsyoltrvJbkD/0vOlHTPanT1VKXKmTRivq4/pQVwMi4t/0i', 'admin', '1');