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
