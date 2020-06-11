DROP TABLE IF EXISTS employee;
 
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(250) NOT NULL,
  `nick_name` varchar(250) NOT NULL,
  `email` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
