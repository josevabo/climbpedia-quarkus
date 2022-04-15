
DROP DATABASE climbpedia;
CREATE DATABASE climbpedia;

USE climbpedia;

-- CREATE DB OBJECTS

CREATE TABLE `vias` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `setor_id` bigint NOT NULL,
  `graduacao` varchar(255) NOT NULL,
  `tipo_id` bigint NOT NULL,
  `url_croqui` varchar(255) COMMENT 'evoluir futuramente para imagem de croqui interna',
  `imagem_id` bigint,
  `tags` varchar(255),
  `conquistador_id` bigint,
  `descricao` varchar(255),
  `dt_conquista` date,
  `extensao` int
);

CREATE TABLE `setores` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255),
  `cidade_id` bigint NOT NULL,
  `geolocalizacao` varchar(255)
);

CREATE TABLE `tipos_via` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL
);

CREATE TABLE `cidades` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `UF` varchar(255) NOT NULL COMMENT '?? criar tabela de UFs ou usar lista contstraint'
);

CREATE TABLE `comentarios` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `via_id` bigint NOT NULL,
  `texto` varchar(255) NOT NULL,
  `usuario_id` bigint NOT NULL,
  `datahora` datetime NOT NULL
);

CREATE TABLE `usuarios` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(255) UNIQUE NOT NULL,
  `data_nasc` date,
  `cidade_id` bigint
);

CREATE TABLE `imagens` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `legenda` varchar(255),
  `url` varchar(255) NOT NULL
);

ALTER TABLE `vias` ADD FOREIGN KEY (`setor_id`) REFERENCES `setores` (`id`);

ALTER TABLE `vias` ADD FOREIGN KEY (`tipo_id`) REFERENCES `tipos_via` (`id`);

ALTER TABLE `vias` ADD FOREIGN KEY (`url_img_id`) REFERENCES `imagens` (`id`);

ALTER TABLE `vias` ADD FOREIGN KEY (`conquistador_id`) REFERENCES `usuarios` (`id`);

ALTER TABLE `setores` ADD FOREIGN KEY (`cidade_id`) REFERENCES `cidades` (`id`);

ALTER TABLE `comentarios` ADD FOREIGN KEY (`via_id`) REFERENCES `vias` (`id`);

ALTER TABLE `comentarios` ADD FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

ALTER TABLE `usuarios` ADD FOREIGN KEY (`cidade_id`) REFERENCES `cidades` (`id`);
