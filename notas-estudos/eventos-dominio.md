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
