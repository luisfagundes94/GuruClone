# GuruClone 
O aplicativo é um clone parcial do projeto original "Guru", onde é possível acompanhar a bolsa de valores e suas ações

# Telas
<img src="/screenshots/screenshot1.png" width="200" /> <img src="/screenshots/screenshot2.png" width="200" /> <img src="/screenshots/screenshot3.png" width="200" />  <img src="/screenshots/screenshot4.png" width="200" />

## Guia de Instalação
- Faça o download ou clone desse repositório para sua máquina local.
- Faça o download do Android Studio
- Sincronize o projeto para baixar todas as dependências.
- Inicie a aplicação em um celular ou emulador.

## Arquitetura
O projeto busca o desacoplamento de componentes, fácil navegação de pacotes em sua estrutura e uma alta escalabilidade. É utilizado a abordagem de single-activity, onde o sistema contém apenas uma activity que será o ponto de entrada do aplicativo e as demais telas que serão fragmentos filhos do mesmo.

#### Modularização
Todo o código relacionado a certa funcionalidade está contido em um módulo específico de feature. Essa abordagem apresenta os seguintes benefícios:

- Melhor separação de responsabilidades. Cada módulo contém suas próprias classes e API's necessárias, e não pode ser referenciada sem uma dependência de módulo explicito.
- Funcionalidades podem ser desenvolvidas em paralelo por times diferentes
- Rápido tempo de compilação

#### MVVM com arquitetura clean

o padrão de design MVVM (Model-View-ViewModel) ajuda na separação da regra de negócios e na lógica de apresentação para a tela (UI). Esta separação torna o sistema mais fácil de testar e realizar manutenção. O projeto está estruturado nas seguintes camadas:

**Data** <br>
Gerencia os dados da aplicação e expoe esses dados como repositórios para a camada relacionada a regra de negócios (domain)

**Domain** (Regra de Negócios) <br>
Camada fonte da aplicação onde são estabelecidas as regras de negócio.

**Presentation** (UI) <br>
É a camada mais próxima do que o usuário vê na tela, fazendo uso de viewModels e liveDatas para gerenciar dados e estados em um ciclo de vida 







