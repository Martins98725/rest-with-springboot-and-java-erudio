CREATE TABLE `books` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `name` varchar(30) NOT NULL,
                         `author` varchar(75) NOT NULL,
                         `publicationDate` varchar(8) NOT NULL,
                         PRIMARY KEY (`id`)
);