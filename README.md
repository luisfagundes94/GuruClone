# GuruClone 
O aplicativo é um clone parcial do projeto original "Guru", onde é possível acompanhar a bolsa de valores e suas ações

# Telas
## Light Mode
<img src="/screenshots/screenshot1.png" width="200" /> <img src="/screenshots/screenshot2.png" width="200" /> <img src="/screenshots/screenshot3.png" width="200" />  <img src="/screenshots/screenshot4.png" width="200" />

## Dark Mode
<img src="/screenshots/dark_screenshot1.png" width="200" /> <img src="/screenshots/dark_screenshot2.png" width="200" /> <img src="/screenshots/dark_screenshot3.png" width="200" />  <img src="/screenshots/dark_screenshot4.png" width="200" />

# Stack das Principais Tecnologias
- [Kotlin](https://kotlinlang.org/) - _linguagem utilizada_
- [Coroutines](https://developer.android.com/kotlin/coroutines) - _programação assíncrona_
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - _injeção de dependência_
- [Jetpack Components](https://developer.android.com/jetpack)
  * [NavigationComponent](https://developer.android.com/topic/libraries/architecture/navigation/) - _navegação entre telas_
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - _notificação para as views através de variáveis observáveis_
  * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - _gerenciamento do ciclo de vida_
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - _gerenciamento e armazenamento dados relacionados a UI_
- [Lottie](http://airbnb.io/lottie) - _animações_
- [Picasso](https://square.github.io/picasso/) - _carregamento de imagens web para imageViews_
- [Gson](https://github.com/google/gson) - _conversor de JSON para classe de modelo e vice-versa_
- [ktlint](https://github.com/pinterest/ktlint) - _padrão de formatação de código_
- [MockK](https://mockk.io/) - _biblioteca de mock para o kotlin_
- [Espresso](https://developer.android.com/training/testing/espresso) - _Testes de UI_


# Guia de Instalação
- Faça o download ou clone desse repositório para sua máquina local.
- Faça o download do Android Studio
- Sincronize o projeto para baixar todas as dependências.
- Inicie a aplicação em um celular ou emulador.

# Automação
- ktlintCheck: Verifica se o código está de acordo com o padrão utilizado pelo ktlin. Use o comando `./gradlew ktlintCheck` para realizar a verificação.
- ktlintFormat: Formata automaticamente o código para ficar de acordo com o padrão utilizado pelo ktlint. use o comando `./gradlew ktlintFormat` para realizar a formatação.

# Arquitetura
O projeto busca o desacoplamento de componentes, fácil navegação de pacotes em sua estrutura e uma alta escalabilidade. É utilizado a abordagem de single-activity, onde o sistema contém apenas uma activity que será o ponto de entrada do aplicativo e as demais telas que serão fragmentos filhos do mesmo.

### Modularização
Todo o código relacionado a certa funcionalidade está contido em um módulo específico de feature. Essa abordagem apresenta os seguintes benefícios:

- Melhor separação de responsabilidades. Cada módulo contém suas próprias classes e API's necessárias, e não pode ser referenciada sem uma dependência de módulo explicito.
- Funcionalidades podem ser desenvolvidas em paralelo por times diferentes
- Rápido tempo de compilação
- Fácil integração de uma feature em outro projeto

OBS: As funcionalidades relacionada a **lista de ações** estão contidas no módulo feature-stock.

### MVVM com arquitetura clean

O padrão de design MVVM (Model-View-ViewModel) ajuda na separação da regra de negócios e na lógica de apresentação para a tela (UI). Esta separação torna o sistema mais fácil de testar e realizar manutenção. O projeto está estruturado nas seguintes camadas:

_**Data**_ <br>
Gerencia os dados da aplicação e expoe esses dados como repositórios para a camada relacionada a regra de negócios (domain)

_**Domain** (Regra de Negócios)_ <br>
Camada fonte da aplicação onde são estabelecidas as regras de negócio.

_**Presentation** (UI)_ <br>
É a camada mais próxima do que o usuário vê na tela, fazendo uso de viewModels e liveDatas para gerenciar dados e estados em um ciclo de vida 

# Testes
No módulo feature-stock foram feitos testes unitários no MyListViewModel, StockMapper e no modelo de domínio Stock. Foram utilizados os frameworks MockK (Mockar objetos) e JUnit4 para validar os testes.

# O que gostaria de ter feito
- Realizado testes de UI com o espresso na tela da lista de ações e no Adapter. Ao escrever os testes, me deparei com um problema em que o dagger-hilt ainda não suporta testes de fragmentos isolados. Na documentação oficial do hilt: 
  > Warning: Hilt does not currently support FragmentScenario because there is no way to specify an activity class, and Hilt requires a Hilt fragment to be contained in a Hilt activity. One workaround for this is to launch a Hilt activity and then attach your fragment. [link](https://dagger.dev/hilt/testing)

- Persistido dados ao re-organizar itens da lista com o Room.





