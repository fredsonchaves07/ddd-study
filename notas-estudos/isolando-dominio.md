# Isolando o domínio

- Programas de software envolvem design e códigos para executar muitos tipos diferentes de tarefas. Eles aceitam a entrada do usuário, executam a lógica do négocio, acessam banco de dados, comunicam-se com outras redes, exibem informações aos usuários e assim por diante. Por isso, o código envolvido em cada função do programa pode ser substancial
- A criação de programas que possam controlar tarefas complexas, exige a separação das coisas, permitindo isolar a concentração em partes diferentes do design. Ao mesmo tempo, as complicadas interações dentro do sistema devem ser mantidas apesar da separação
- Existem algumas maneiras de se dividir em um sistema de software, mas através da experência e de convenções, o setor convergiu para Arquietura em Camadas e, especificamente, algumas camadas relativamente padronizadas.

![Captura de tela de 2023-05-16 08-55-20](https://github.com/fredsonchaves07/ddd-study/assets/43495376/8c8f7e31-ba4b-47b6-a1bb-2594a56f6adc)

- O principio essencial é de que qualquer elemento de uma camada depende somente dos outros elementos da mesma camada ou dos elementos da camadas "abaixo" dela. A comunicação para acima deve passar por algum tipo de mecanismo indireto
- O valor das camadas é que cada uma se especializa em um determinado aspectos de um programa de computador. Essa especialização permite designs mais coesos de cada aspecto, e facilita muito mais a interpretação desses designs.
- Divida um programa complexo em camadas. Desenvolvaum design dentro de cada camada que seja coeso e que dependa somente das camadas abaixo dele.
- Siga os padrões de arquitetura usados como padrão para proporcionar um baixo acoplamento com as camadas acima.
- Concentre todo o código relacionado ao modelo do domínio em um camada e isole-o do código da interface do usuário, do aplicativo e da infraestrutura.
- Camadas isoladas exigem uma manutenção mais barata, porque tendem a evoluir em ritmos diferentes e responder a diversas necessidades.
- A separação também ajuda na aplicação em um sistema distribuido, permitindo que diferentes camdas seja colocadas flexivalmente em diferentes servidores ou clientes

## Relacionando as camadas

- As camadas tem por objetivo possui baixo acoplamento, com as dependências do design dispostas em apenas um sentido. As camadas superiores podem utilizar ou manipular elementos das camadas inferiores de forma bastante simples, chamando interfaces públicas, fazendo referências a elas e geralmente usando meios convencionais de interação. Mas quando um objeto de um nível inferior precisa comunicar-se para cima precisamos de outro mecanismo, baseado em padrões de arquitetura para relacionar camadas tais como chamadas de retorno ou Observers
- As camadas do aplicativo e do domínio chama os Serviços fornecidos pela camada da infraestrutura. Quando o escopo de um Serviço foi bem escolhido e a sua interface foi bem elaborada, a parte que chama pode permanecer livremente acoplada
