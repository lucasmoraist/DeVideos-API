# DeVideos
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

## Descrição
A plataforma deve permitir ao usuário montar playlists com links para seus vídeos preferidos, separados por categorias, dessa forma o usuário pode acessar seu vídeos favoritos de forma mais fácil e caso ele ainda sinta dificuldade a plataforma permite que o usuário pesquise por vídeos dentro das suas playlists para que ele não precise ficar olhando um por um. Esse projeto foi desenvolvido para facilitar o acesso aos vídeos favoritos do usuário.

## Instrução de instalação
### Pré requisitos
- IDE de preferência (Vscode, Eclipse, Intellij)
- Java 17 ou superior
- Maven 3.2.5 ou superior
### Etapas
1. Configure seu bando de dados no arquivo `application.properties`
2. Abra o bash do seu computador e rode os seguintes comandos:
```bash
mvn clean
```
```bash
mvn package
```
```bash
java -jar target/devflix-api-0.0.1-SNAPSHOT.jar
```

## Instrução de uso
- Com o programa rodando você pode fazer os testes das requisições
- Você pode usar ferramentas como o Postman ou Insomnia para testar as requisições
1. Abra sua ferramenta para teste de requisições
2. Importe o arquivo json que contém os testes de todas as requisições, ele irá estar em `./src/main/resources/utils/insomnia`, o json pode ser importado em ambas ferramentas

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
