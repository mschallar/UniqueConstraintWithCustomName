# UniqueConstraintWithCustomName
Shows that with hibernate 6.2.22 custom names on unique constraints are ignored.

Following tables are generated:

```sql
CREATE TABLE `my_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `some_long` bigint DEFAULT NULL,
  `my_other_entity_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6ka476sod7dnf94qa0eu82qe2` (`my_other_entity_id`),
  KEY `IDX_someLong` (`some_long`),
  CONSTRAINT `FK_moe` FOREIGN KEY (`my_other_entity_id`) REFERENCES `my_other_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `my_other_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `some_string` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
