# Battleship

Pedro Henriques nº 122651 

Diogo Sousa nº 93794

Miguel Aguiar nº 122656

## 🛠️ Integração Contínua (CI)

O processo de Integração Contínua (CI) para este projeto é gerido através do GitHub Actions.

O workflow "Java CI com Maven" é acionado em cada `push` para o ramo `main` e em cada `Pull Request`. Este processo é responsável por:
1.  Fazer o *checkout* do código.
2.  Configurar o ambiente Java (JDK **17**).
3.  Executar o *build* e todos os **testes unitários** (via `mvn package`).

### Estado do Workflow

![Java CI com Maven workflow status](https://github.com/LETI-122651/Battleship/actions/workflows/maven.yml/badge.svg)