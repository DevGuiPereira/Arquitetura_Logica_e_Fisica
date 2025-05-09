CREATE DATABASE estoque

USE estoque;

CREATE TABLE produtos (
    idprodutos INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(100),
    preco DOUBLE NOT NULL,
    quantidade INT NOT NULL
);

CREATE TABLE `usuarios` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `nome` varchar(45) NOT NULL,
   `usuario` varchar(45) NOT NULL,
   `senha` varchar(8) NOT NULL,
   `cargo` int(11) NOT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
 
 CREATE TABLE `registros` (
   `idlogs` int(11) NOT NULL AUTO_INCREMENT,
   `usuario` int(11) NOT NULL,
   `logscol` varchar(45) NOT NULL,
   `data` datetime NOT NULL DEFAULT current_timestamp(),
   PRIMARY KEY (`idlogs`),
   KEY `fk_logs_usuario` (`usuario`),
   CONSTRAINT `fk_logs_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
 ) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
