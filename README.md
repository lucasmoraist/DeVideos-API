# DeVideos
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

## Descrição
A plataforma permite que você crie playlists com links para seus vídeos preferidos, organizando-os por categorias. Assim, você pode acessar seus vídeos favoritos de forma mais prática. Se tiver dificuldade para encontrar um vídeo específico, a plataforma oferece uma função de busca dentro das playlists, evitando que você tenha que procurar manualmente vídeo por vídeo. Este projeto foi desenvolvido para simplificar e agilizar o acesso aos seus vídeos favoritos.

## Instrução de instalação
### Pré requisitos
- IDE de preferência (Vscode, Eclipse, Intellij)
- Java 17 ou superior
- Maven 3.2.5 ou superior
- 
### Etapas
1. Configure seu banco de dados no arquivo `application.properties`
- Caso não tenha banco de dados você pode usar a imagem do mysql disponibilizada no arquivo `docker-compose.yml`
- Entre na sua pasta através do bash e rode o seguinte comando
```bash
docker-compose up -d
```
- E então configure o usuário, senha e nome do banco de dados no `application.properties`

2. Abra o bash dentro da sua pasta e rode os seguintes comandos:
```bash
mvn clean
```
```bash
mvn verify
```
```bash
java -jar target/devideos-api-1.0-SNAPSHOT.jar
```
```bash
java -jar target/devideos-api-1.0-SNAPSHOT.jar
```

## Instrução de uso
- Com o programa rodando você pode fazer os testes das requisições
- Você pode usar ferramentas como o Postman ou Insomnia para testar as requisições
1. Abra sua ferramenta para teste de requisições
2. Importe o arquivo json que contém os testes de todas as requisições, nome dele é `Testes Http`
3. Com o projeto em execução, você também pode pesquisar no seu navegador por `http://localhost:8080/swagger-ui.html`. Lá estará uma documentação junto com todas as requisições que podem ser testadas e suas especificações.
   
## Entidades
### Videos
| Nome | Tipo | Descrição |
| ---- | ---- | --------- |
| id | Long | Este atributo irá receber o id do vídeo, ele é gerado automáticamente. |
| title | String | Este atributo irá receber o título do vídeo. |
| description | String | Este atributo irá receber uma descrição para o vídeo |
| url | String | Este atributo irá receber a url do vídeo. |
| categoria | Category | Este atributo irá receber id da categoria na qual ele faz parte. |


### Categorias
| Nome | Tipo | Descrição |
| ---- | ---- | --------- |
| id | Long | Este atributo irá receber o id do categoria, ele é gerado automáticamente. |
| title | String | Este atributo irá receber o título da categoria. |
| color | String | Este atributo irá receber uma cor para categoria. (VERMELHO, AMARELO, AZUL, VERDE, ROSA, PRETO, CINZA, ROXO) |
| videos | List | Este atributo irá receber uma lista com os vídeos adicionados. |

### Usuário
| Nome | Tipo | Descrição |
| ---- | ---- | --------- |
| id | Long | Este atributo irá receber o id do usuário, ele é gerado automáticamente. |
| name | String | Este atributo irá receber o nome do usuário. |
| email | String | Este atributo irá receber o email do usuário. |
| password | String | Este atributo irá receber a senha do usuário. |

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests com melhorias, correções de bugs ou novos recursos.

## Contatos
<a href = "mailto:luksmnt1101@gmail.com">
  <img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank">
</a>
<a href="https://www.linkedin.com/in/lucas-morais-152672219/" target="_blank">
  <img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank">
</a>
