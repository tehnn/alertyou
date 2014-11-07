/*

Source Database       : gcm

*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `keyname` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `dateadd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `active` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`,`keyname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
