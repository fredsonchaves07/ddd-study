# Eventos de domínio

- Use um evento de domínio para capturar uma ocorrência de algo que aconteceu no domínio. Essa é uma ferramenta extremamente poderosa de modelagem.

## O que e o porquê dos eventos de  domínio

- Modele as informações sobre atividades no domínio como uma série de eventos distintos. Represente cada evento como um objeto de domínio. Um evento de domínio é uma parte completa do modelo de domínio, uma representação de algo que aconteceu no domínio

![Captura de tela de 2023-08-16 18-24-12](https://github.com/fredsonchaves07/ddd-study/assets/43495376/17f4d1b3-2e0f-41fc-9b70-abf53b5bd52b)

- Cada comando em um agregado resulta em um evento? Igualmente importante quanto reconhecer a necessidade de um evento é saber quando ignorar acontecimentos externos no domínio com os quais os especialistas ou a empresa como um todo não se preocupam.
- Mas dependendo dos aspectos técnicos da implementação do modelo ou dos objetivos do sistema de colaboração, é possível que os eventos sejam mais prolíficos do que os especialistas em domínio exigem diretamente.

## Modelando eventos

- Ao publicar eventos a partir de agregados, é importante que o nome do evento reflita a natureza passada da ocorrência.
- Podemos implementar uma interface `DomainEvent` contendo um método `occurredOn()` que informa o horário da ocorrência do evento
- Além disso, é possível determinar quais outras propriedades são necessárias para representar uma ocorrência significativa do que aconteceu
- Os eventos costumam ser projetado como imutável. A interface do evento tem propósito expresso de transmitir as propriedades que refletem a sua causa
- Com o evento publicado, um assinante no contexto delimitado pode usá-lo para notificar um agregado
- Para derivar um estado mais rico usando operações, cerifique-se de que todos os comportamentos adicionais dos eventos estão livre de efeitos colaterais protegendo a imutabilidade do objeto

### Com características de agregado

- As vezes, eventos são projetados para que sejam criados por meio de solicitação direta dos clientes. Isso é feito em resposta a alguma ocorrência que não é resultado direto da execução do comportamento em uma instância de um agregado no modelo. Possivelmente um usuário do sistema inicializa alguma ação que é considerada um evento por si só
- Quando isso acontece, o evento pode ser modelado com um agregado e retido em seu próprio repositório. Como ele representa alguma ocorrência no passado, o repositório não permitiria sua remoção.
- Quando um evento é modelado dessa forma, ele pode ser publicado via uma infraestrutura de transmissão de mensagens ao mesmo tempo que é adicionado ao repositório
- O cliente pode chamar um serviço de domínio para criar o evento, adicioná-lo ao repositório e então public-alo em uma infraestrutura de transmissão de mensagens

### Identidade

- Pode ser suficiente permitir que a identidade do evento seja representada por suas propriedades, como é o caso com objetos de valor. O nome/tipo do evento juntamente com a identidade do agregado envolvido na causa, bem como o registro de darta/hora em que o evento ocorreu, pode ser suficiente para distingui-los do outro
- Nos casos em que um evento é modeloado como um agregado, ou em outras situações em que os eventos devem ser comparados e suas propriedades combinadas não os distinguem, podemos atribuir a um evento uma identidade formal única. Mas pode haver outras razões para atribuir uma identidade única
- Uma identidade única pode ser necessária quando os eventos são publicados fora do contexto delimitado local em que eles ocorrem, quando a infraestrutura de transmissão de mensagens os encaminha.
- Isso aconteceria se o rementente da mensagem travasse antes de a infraestrutura de transmissão de mensagens confirmar que a mensagem foi enviada
- Qualquer que sea a causa da nova entrega de uma mensagem,  a solução é fazer com que os assinantes remotos detectem a entrega duplicada da mensagem e ignorem as mensagens já recebidas

## Publicando eventos a paritr do modelo de domínio

- Uma das meneiras mais simples e eficazes de publicar eventos de domínio sem acoplamento com os componentes fora do modelo de domínio é criar um observador do tipo "leve"
- Quando um evento é publicado, cada assinante é notificado de forma síncrono, um a um.Isso também implica que todos os assinantes rodam dentro da mesma transação, talvez controlada por um serviço de aplicação que é o cliente direto do modelo de domínio

### Publicador

- Talvez o uso mais comum dos eventos de domínio seja quando um agregado cria um evento e o publica. O publicador reside em um módulo do modelo, mas ele não modela alguns aspectos do domínio. Em vez disso, ele efornece um serviço simples para os agregados que precisam notificar os assinantes quanto aos eventos

### Assinantes

- Como os serviços de aplicação são o cliente direto do modelo de domínio ao usar a arquitetura hexagonal, eles estão em uma posição ideal para registrarem um assinante no publicador antes de executar o comportamento gerador de eventos nos agregados
- O que o assinante faz com o evento não é mostrado no exemplo. Ele poderia envir um email sobre um `BacklogItemCommitted`, se isso fizesse algum sentido. Poderia salvar o evento em um armazenamento de eventos. Poderia encaminhar o evento via infraestrutura de mensagens. Salvar o evento em um armazenamento de eventos e encaminhá-lo usando uma infraestrutura de mensagens

## Espalhando a novidade para contextos delimitados remotos

- O uso de qualquer um dos vários mecanismos (framework) de transmissão de mensagens entre contextos delimitados exige adotar um compromentimento com a consitência futura. Ele não pode ser combatido. As alterações em um modelo que influenciam as alterações em um ou mais outros modelos não serão totalmente consistentes durante algum período de tempo decorrido. Mas ainda, dependendo do tráfego para os sistemas individuais e os efeitos que eles tem sobre outros, o fator é que os sistemas como um todo talvez nunca seham totalmente consistentes em um dado período de tempo qualquer

### Consistência da infraestrutura de mensagens

- Pelo menos dois mecanismos em uma solução de transmissão de mensagens devem sempre ser consistentes entre si: o armazenamento de persistência usado pelo modelo de domínio e o armazenamento de persistência que suporta a infraestrutura de mensagens utilizada para encaminhar os eventos publicados pelo modelo
- Isso é necessário para assegurar que, quando as mudanças no modelo são persistidas, a entrega do evento também é garantida e que, se um evento é entregue por meio de mensagens, ele indica a situação real refletida no modelo que o publicou
- Existem três maneiras básicas para garantir a consistência do modelo e do evento
  - O modelo de domínio e a infraestrutura de mensagens compartilham o mesmo armazenamento de persistência. Isso permitirá que as alterações no modelo e a inserção da nova mensagem sejam confirmadas na mesma transação local
  - O armazenamento de persistência de seu modelo de domínio e o armazenamento de persistência de suas mensagens são controlados sob uma transação XA global (confirmação em duas fases). Tem a vantagem de que você pode manter o modelo e o armazenamento de mensagens separados um do outro. Transações globais tendem a ser caras e ter baixo desempenho. Também é possível que o armazenamento do mdelo ou armazenamento do mecanismo de mensagens, ou ambos, não sejam compatíveis com XA
  - Você cria uma área de armazenamento especial (por exemplo, uma tabela de banco de dados) para eventos no mesmo armazenamento de persistência usado pra salvar seu modelo de domínio. Semelhante ao intem 1 porém essa área de armazenamento não é possuída nem controlada por seu mecanismo de mensagens, mas, em vez disso por seu contexto delimitado

### Serviços e sistemas autonomos

- O serviço autonomo é usado para representar qualquer serviço de negócios rudimentar, possivelmente pensado como um sistema ou aplicação, que operar de maneira predominantemente independente de outros desses "serviços" na empresa
- Em vez de chamar outros sistemas, use mensagens assíncronas para alcançar maior grau de independência entre os sistemas -autonomia.
- O evento conterá uma quantidade limitada de parâmetros de comando e/ou estado de agregado que transmitirá um significado suficiente para permitir que contextos delimitados de inscrição reajam corretamente. Certamente, se um determinado evento não contiver informações suficiente para um dado assinante, o contrato por todo o domínio do evento deve ser alterado a fim de fornecer o que é necessário
- Também é verdade que em alguns casos o uso de RPC não pode ser facilmente evitado. Alguns sistemas legados podem ser capazes de fornecer somente RPC
- Se você praticamente precisar replicar os conceitos, objetos e suas associações a partir do modelo externo em seu próprio modelo, talvez você precise levar em consideração o uso de RPC

### Tolerâncias a latência

- É uma questão a considerar os períodos de latência, uma vez que dados fora de sincronia podem influenciar ações erradas e até mesmo prejudiciais. Devemos perguntar qual é o nível aceitável de espera entre os estados consistentes, e quanto retardo é excessivo.
- Tolerâncias máximas de latência devem ser bem compreendidas e os sistemas devem ter as qualidades arquitetônicas para resolvê-las e, possivelmente, até superá-las. Alta disponibilidade e escabilidade devem ser projetadas nos serviços autônomos e na infraesturutra de suporte de transmissão de mensagens a fim de que os requisitos rigorosos não funcionais da empresa possam ser atendidos

## Armazenamento de eventos

- Manter um armazenamento de todos os eventos de domínio para um único contexto delimitado apresenta vários potenciais benefícios.
- Considere o que você faria se fosse armazenar um evento discreto para cada comportamento do comando de modelo que é executado. Você pode:
  - Usar o armazenamento de eventos como uma fila para publicar todos os eventos de domínio por meio de uma infraestrutura de mensagens
  - Usar o mesmo armazenamento de eventos para alimentar notificações de eventos basedos em REST a fim de fazer uma sondagem dos clientes
  - Examinar um registro histórico do resultado de cada comando que já foi executado no modelo. Isso pode ajudar a monitorar erros não somente no modelo, mas também nos clientes
  - Usar os dados na análise de tendências, previsão e outra análitica do negócio. Muitas vezes as empresas só fazem ideia de como esses dados históricos podem serusados mais tarde quando percebem que precisam deles
  - Usar os eventos para resconstituir cada instância de agregado quando ela é recuperada do repositório. Isso é uma parte necessária do que é conhecido como prospecção de eventos
  - Dado uma aplicação do ponto anterior, desfazer os blocos das alterações em um agregado. Isso é possível evitando (talvez removendo ou marcando como obsoleto) que certos eventos sejam utilizados para reconstituir uma determinada instância de agregado

## Estilos arquitetônicos para encaminhar eventos armazenados

- Depois que o armazenamento de eventos é preenchido, ele está disponível para fornecer eventos que devem ser encaminhados como notificações às partes interessadas
- Um dos estilos é por meio dos recursos RESTful, que são consultados pelos clientes, e o segundo estilo é enviando mensagens por meio de um tópico/troca de um produto middleware de troca de mensagens

### Publicando notificações como recursos restful

- Este estilo funciona melhor quando usado em um ambiene que segue as premissas básicas da publicação-assinatura. Eis um resumo dos pontos positivos e negativos desta abordagem
  - Se potencialmente muitos clientes conseguirem acessar um único URI bem conhecido para solicitar o mesmo conjunto de notificações, esta abordagem funcionará bem
  - Se for necessário que um ou alguns consumidores busquem em múltiplos produtores recursos a fim de obter um único conjunto de tarefas a ser executado em uma sequência específica, é provável que rapidamente você sinta a dificuldade de usar uma abordagem RESTful.

### Implementando notificações por meio de middlewae de mensagens

- Os sistemas de mensagens também permite suportar de uma maneira relativamente fácil tanto o padrão de publicação-assinatura como o de filas, qualquer que seja o melhor para suas necessidades. Nos dois casos, o sistema de mensagens usa um modelo push para enviar mensagens das notificações de eventos para ouvintes ou assinantes registrados
