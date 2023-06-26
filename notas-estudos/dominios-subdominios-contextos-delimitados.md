# Domínios, subdomínios e contextos delimitados

- O domínio é o problema que a empresa quer resolver. O core, o núcleo central e o propósito
- Quase todos os domínios de softwares possuem multiplos subdomínios que auxilia os dominios
- Em uma arquitetura distribuida por exemplo, os contextos delimitados seriam os microserviços que possuem relações e interações entre si
- Contextos delimitados em uma empresa raramente, ou nunca, permanecem isolados

## Contextos delimitados

- Os contextos delimitados são unidades isoladas dentro de um sistema, onde um contexto representa um conjunto coeso de funcionalidades relacionadas. Cada contexto delimitado tem seus próprios modelos de domínio, regras de negócio, comportamentos e limites de responsabilidade claramente definidos.
- Os contextos delimitados ajudam a evitar a contaminação e a complexidade excessiva entre diferentes partes do sistema, isolando as preocupações de cada contexto.
- Geralmente, os contextos delimitados são definidos com base em fronteiras de negócios, funcionalidades específicas ou até mesmo em equipes responsáveis por desenvolver e manter esses contextos.
- Alguns exemplos de contextos delimitados em um sistema de cobrança recorrente e CRM

 1) *Contexto de faturamento recorrente*
    - Responsável pelas funcionalidades de cobrança, faturamento e controle de assinaturas.
    - Pode incluir modelos de domínio como Planos de Assinatura, Ciclos de Cobrança, Faturas, Pagamentos, etc.
    - Possui regras de negócio específicas relacionadas à cobrança recorrente, renovação de assinaturas, cancelamento de planos, entre outras.
   
 2) *Contexto de CRM (Customer Relationship Management):*
    - Envolve as funcionalidades relacionadas à gestão de clientes, contatos, interações e relacionamento com o cliente.
    - Pode incluir modelos de domínio como Clientes, Contatos, Oportunidades, Atividades, etc.
    - Possui regras de negócio específicas relacionadas à gestão de leads, qualificação de oportunidades, histórico de interações, etc.

  

## Subominios

- Os subdomínios são divisões mais granulares dentro de um domínio maior. Um domínio pode ser composto por vários subdomínios, onde cada subdomínio representa uma parte distinta e bem definida do domínio geral.
- Os subdomínios são unidades lógicas que agrupam conceitos relacionados, modelos de domínio e regras de negócio em uma área específica de conhecimento dentro do domínio geral.
- Eles podem ser considerados como "peças" menores de um contexto delimitado, ajudando a organizar e estruturar as responsabilidades dentro do contexto.
- Exemplo de subdominios em um contexto de sistema de cobrança recorrente e CRM

 1) *No contexto de Faturamento Recorrente*
    - Subdomínio de Planos de Assinatura: lida com a definição, configuração e gerenciamento dos diferentes planos de assinatura oferecidos aos clientes.
    - Subdomínio de Ciclos de Cobrança: abrange a lógica e o fluxo de cobrança, incluindo a definição de períodos, processamento de pagamentos e geração de faturas.
    - Subdomínio de Pagamentos: responsável pela integração com provedores de pagamento, processamento de transações e gestão de status de pagamento.
   
 2) *Contexto de CRM (Customer Relationship Management):*
    - Subdomínio de Gestão de Clientes: lida com a criação, atualização e exclusão de informações de clientes, incluindo dados demográficos, histórico de compras e preferências.
    - Subdomínio de Interações: abrange as interações entre a equipe de vendas ou atendimento e os clientes, como chamadas, e-mails, reuniões, etc.
    - Subdomínio de Oportunidades: lida com a gestão e acompanhamento de oportunidades de negócio, desde a qualificação até o fechamento da venda.

- Em resumo, os contextos delimitados são unidades maiores e mais abrangentes que agrupam funcionalidades relacionadas, enquanto os subdomínios são divisões menores e mais granulares dentro de um contexto delimitado. Os contextos delimitados ajudam a manter a clareza e o foco em áreas distintas do sistema, enquanto os subdomínios fornecem uma estrutura adicional para organizar e agrupar conceitos relacionados dentro dessas áreas. Ambos os conceitos são fundamentais para a aplicação do DDD e contribuem para a construção de sistemas complexos e flexíveis.

  ![Captura de tela de 2023-06-21 19-25-29](https://github.com/fredsonchaves07/ddd-study/assets/43495376/91185a65-35d9-41f4-98ac-1c79d90a381b)


## Como mapear os dominios, subdomínios e contextos delimitados?

1) Em uma coluna, crie uma lista de todos os subdominios dos quais você está ciente em seu trabalho diário. Em outra coluna, liste os contextos delimitados. Os subdominios se intereseccionam com multiplos contextos delimitados? Se sim, isso não necessariamente é uma coisa ruim, apenas um fato do software corporativo

2) Usando a imagem abaixo, escreva alguns dos nomes do software em ecexução na empresa com os subdominioos, contextos delimitados e as relações de integração entre eles

![Captura de tela de 2023-06-21 19-25-57](https://github.com/fredsonchaves07/ddd-study/assets/43495376/18ea206c-787f-4653-904d-30bf0bb066af)

3) Começe denovo. Desta vez você deve desenhar um diagrama que seja alinha com o seu domínio, subdominios e contextos delimitados. Use a técnicas mostrados na figura acima, mas vá em frente e adapte-as ao seu mundo

## Espaço de problemas e de solução

- O espaço de problemas são as partes do domínio que precisam ser desenvolvidas para fornecer um novo domínio básico. É a combinação do domínio básico com os subdomínios que ele deve usar.
- O espaço de soluções são um ou mais contextos delimitados, um conjunto de modelos de software específicos. Isso ocorre porque o contexto delimitado é uma solução específica, um ponto de vista da realização, depois de ser desenvolvida. O contexto delimitado é usado para entender uma solução como software
