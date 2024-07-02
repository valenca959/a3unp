# Projeto Fitness Center em Java Swing
![Fitness Center](src/Images/logoMenu.png)

Este é um projeto de uma academia desenvolvido em Java Swing. O objetivo deste projeto é criar um sistema de gerenciamento para uma academia, permitindo o cadastro de alunos, entre outras funcionalidades.

## Funcionalidades

- Cadastro de alunos: permite cadastrar novos alunos, incluindo informações como nome, idade, endereço, etc.
- Controle de pagamentos: possibilita registrar quanto este aluno paga ao mês.

## Requisitos

- Java Development Kit (JDK) 8 ou superior

## Como executar o projeto

1. Clone este repositório para o seu ambiente local.
2. Abra o projeto na sua IDE Java.
3. Adicione as livrarias nescessarias para o projeto que estão em **/dist/** lib copie-as para a pasta **/lib** na raiz do projeto ou outro lugar e remova o .old deixando apenas o nome da livraria com o .jar, ou use os comandos, ou ainda execute o **.bat** na raiz do projeto.
> [!NOTE]
> Windows.

```bash
    cp .\dist\lib\AbsoluteLayout.jar.old .\lib\ 
    cp .\dist\lib\flatlaf-3.4.1.jar.old .\lib\ 
    cp .\dist\lib\mysqlconnectorjava8.0.30_20240513141304.jar.old .\lib\ 

    ren .\lib\AbsoluteLayout.jar.old AbsoluteLayoutTeste.jar 
    ren .\lib\flatlaf-3.4.1.jar.old flatlaf-3.4.1.jar
    ren .\lib\mysqlconnectorjava8.0.30_20240513141304.jar.old mysqlconnectorjava8.0.30.jar
```
> [!NOTE]
> Linux.
```bash
    cp .\dist\lib\AbsoluteLayout.jar.old .\lib\ 
    cp .\dist\lib\flatlaf-3.4.1.jar.old .\lib\ 
    cp .\dist\lib\mysqlconnectorjava8.0.30_20240513141304.jar.old .\lib\
    
    mv .\lib\AbsoluteLayout.jar.old AbsoluteLayoutTeste.jar 
    mv .\lib\flatlaf-3.4.1.jar.old flatlaf-3.4.1.jar
    mv .\lib\mysqlconnectorjava8.0.30_20240513141304.jar.old mysqlconnectorjava8.0.30.jar
```
6. Execute o projeto.

## Contribuição

* JOSE VICTOR EMILIANO DA SILVA - 1282413186
* RODRIGO ENEDINO VALENCA - 12058605411
*  VINIVY GABRIEL DE MENEZES TEIXEIRA - 12824117459

## Banco de dados 
```sql
CREATE TABLE Pessoa (
    id_pessoa INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento DATE NULL
);

CREATE TABLE Aluno (
    id_aluno INT PRIMARY KEY,
    mensalidade DECIMAL(10, 2) NOT NULL,
    email VARCHAR(250) NULL,
    FOREIGN KEY (id_aluno) REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE Admin (
    id_admin INT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_admin) REFERENCES Pessoa(id_pessoa)
);
```
