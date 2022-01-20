DROP DATABASE IF EXISTS inventory_database;
CREATE DATABASE inventory_database;

use inventory_database;

CREATE TABLE inventory (
                             `id` bigint NOT NULL,
                             `actual_delivery_date` date DEFAULT NULL,
                             `custom_duty` int NOT NULL,
                             `description` varchar(255) NOT NULL,
                             `destination_name` varchar(255) NOT NULL,
                             `estimated_delivery_date` date NOT NULL,
                             `item_cost` int NOT NULL,
                             `pick_up_date` date NOT NULL,
                             `source_name` varchar(255) NOT NULL,
                             `status` varchar(255) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO inventory (`actual_delivery_date`,`custom_duty`,`description`,`destination_name`,`estimated_delivery_date`,`item_cost`,`pick_up_date`,`source_name`,`status`)
VALUES (NULL,23,'Test item 1','Destination A','2020-07-31',345,'2020-08-06','Source','DELVIERY_IN_PROGRESS');

INSERT INTO inventory (`actual_delivery_date`,`custom_duty`,`description`,`destination_name`,`estimated_delivery_date`,`item_cost`,`pick_up_date`,`source_name`,`status`)
VALUES (NULL,34,'Test item 2','Destination A','2020-08-31',3425,'2020-09-06','Source','DELIVERED');

INSERT INTO inventory (`actual_delivery_date`,`custom_duty`,`description`,`destination_name`,`estimated_delivery_date`,`item_cost`,`pick_up_date`,`source_name`,`status`)
VALUES (NULL,2343,'Test item 4','Destination C','2020-06-31',3456,'2020-07-06','Source','DELIVERED');