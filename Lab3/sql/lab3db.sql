-- =====================================================================================
-- LAB 3 DB
-- 
-- Create a DB with name: LAB3DB and then import this DUMP SQL FILE
-- 
-- =====================================================================================

SET FOREIGN_KEY_CHECKS=0;

-- =====================================================================================
-- DB Schema 
-- =====================================================================================

-- ------------------------------------------------------------------------------------
-- Table structure for site_users
-- ------------------------------------------------------------------------------------
DROP TABLE IF EXISTS `site_users`;
CREATE TABLE `site_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- =====================================================================================
-- DB Records 
-- =====================================================================================

INSERT INTO `site_users` VALUES ('1', 'usera', 'ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb');
INSERT INTO `site_users` VALUES ('2', 'userb', '3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d');
INSERT INTO `site_users` VALUES ('3', 'userc', '2e7d2c03a9507ae265ecf5b5356885a53393a2029d241394997265a1a25aefc6');


-- -------------------------------------------------------------------------------------
--
-- Username: usera , Password: a 
-- Password-HASH: ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb
--
-- Username: userb , Password: b
-- Password-HASH: 3e23e8160039594a33894f6564e1b1348bbd7a0088d42c4acb73eeaed59c009d
--
-- Username: userc , Password: c
-- Password-HASH: 2e7d2c03a9507ae265ecf5b5356885a53393a2029d241394997265a1a25aefc6
--
-- -------------------------------------------------------------------------------------
