-- 数据库 s4s
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `drive_exam_questions`;
CREATE TABLE `drive_exam_questions`(
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL DEFAULT '0',
  `media_type` tinyint(8) NOT NULL DEFAULT '0',
	`type` tinyint(8) NOT NULL DEFAULT '0',
	`subject`  tinyint(8) NOT NULL DEFAULT '0',
  `chapter_id` int(11) NOT NULL DEFAULT '0',
  `label` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `media_id` int(11) NOT NULL DEFAULT '0',
  `media_width` int(11) NOT NULL DEFAULT '0',
  `media_height` int(11) NOT NULL DEFAULT '0',
	`answer` int(11) NOT NULL DEFAULT '0',
  `option_a` text COLLATE utf8_unicode_ci,
  `option_b` text COLLATE utf8_unicode_ci,
  `option_c` text COLLATE utf8_unicode_ci,
  `option_d` text COLLATE utf8_unicode_ci,
  `option_e` text COLLATE utf8_unicode_ci,
  `option_f` text COLLATE utf8_unicode_ci,
  `option_g` text COLLATE utf8_unicode_ci,
  `option_h` text COLLATE utf8_unicode_ci,
	`difficulty` int(11) DEFAULT NULL,
	`wrong_rate` double DEFAULT NULL,
	`option_type` int(11) DEFAULT NULL,
	`question` text COLLATE utf8_unicode_ci,
	`explain` text COLLATE utf8_unicode_ci,
	`concise_explain` text COLLATE utf8_unicode_ci,
	`keywords` text COLLATE utf8_unicode_ci,
	`media` varchar(255) COLLATE utf8_unicode_ci DEFAULT null,
	`m_id` int(11) NOT NULL DEFAULT '0',
	`m_media_content` varchar(255) COLLATE utf8_unicode_ci DEFAULT null,
	`m_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT null,
	`chapter` varchar(255) COLLATE utf8_unicode_ci DEFAULT null,
	`answers` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `chapter_id` (`chapter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27065 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
SET FOREIGN_KEY_CHECKS = 1;
