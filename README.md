> Projeto back-end, criado para o desafio NeoStore

Pré-requisitos obrigatórios:
- JDK v11
- MySQL Server v5+
- Apache Maven v3
- Apache Tomcat v9

Utiliza as seguintes techs:
- Jersey v2
- Jersey HK2
- JPA v2.1
- Hibernate v5

## Começando
Atualize as seguintes variáveis no arquivo `src/main/resources/META-INF/persistence.xml` que o Hibernate conecte-se corretamente com seu banco de dados:

- `javax.persistence.jdbc.user` > Preencha com o usuário de acesso ao MySQL
- `javax.persistence.jdbc.password` > Preencha com a senha do usuário para acesso ao MySQL

Em seguida, para construir a aplicação, execute o comando:
```
mvn clean install
```

Em seguida, faça o deploy do arquivo _/target/neostore.war_ em um webserver Java, como por exemplo, Tomcat.

Caso utilize Tomcat, a URL de sua aplicação será apontada para [http://localhost:8080/neostore/api](http://localhost:8080/neostore/api) estando pronta para receber requisições utilizando o padrão REST para APIs.