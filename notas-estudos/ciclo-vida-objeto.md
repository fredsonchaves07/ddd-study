# Ciclo de vida de um objeto de domínio

- Todos os objetos possuem um ciclo de vida. Um objeto nasce, provavelmente passa por vários estados, e, finalmente morre - sendo arquivado ou excluído
- Os agregados restrigem o próprio modelo definindo uma propriedade e limites claros, evitando um emaranhado caótico de objetos.
- Em seguida, o foco passa a ser o começo do ciclo de vida, usando fábricas para criar e reconstituir objetos complexos e agregados, mantendo sua estrutura interna encapsulada.
- Por último os repositórios tratam do meio e encapsulando, ao mesmo tempo, a imensa infraestrutura envolvida

## Agregados

- É dificil garantir a consistência das alterações feitas em objetos em um modelo com associações complexas. As invariantes que se aplicam a grupos de objetos intimamente relacionados, e não apenas a objetos discretos, precisam ser mantidas
- Contudo, esquemas cuidadosos de tratamento fazem com que vários usuários interfiram, sem nenhum porquê, uns com os outros tornando um sistema inutilizável
- Um agregado é um grupo de objetos associados que tratamos como sendo uma unidade para fins de alterações de dados. Cada agregado possui uma raiz e um limite
- O limite define o que está dentro do agregado. A raiz é uma entidade úica e específica contida no agregado
- A raiz é o único membro do agregado à qual objetos externos tem permissão de fazer referências, embora os objetos dentro daquele limite possam fazer referências uns aos outros
- Um objeto fora do limite do agregado pode referenciar a entidade através de seu ID
- Um objeto fora do limite do agregado não pode fazer a referência pela própria entidade. Somente quando a entidade está dentro de seu próprio agregado
- Agrupe as entidades e os objetos de valor em agregados e defina os limites em torno de cada um. Escolha uma entidade para ser a raiz de cada agregado e controle todo o acesso aos objetos dentro do limite através da raiz
- Permita que objetos externos façam referência somente à raiz. Referências transitórias a membros internos podem ser transmitidas para uso dentro de apenas uma única operação. Como a raiz controla o acesso, ela não pode ser supreendida com alterações na parte interna
- Esse arranjo torna prática a execução de todas as invariantes para objetos no agregado e para o agregado como um todo em qualquer alterações de estado

## Fábricas

- Quando a criação de um objeto, ou um agregado inteiro, se torna complicada ou revela uma grande parte da estrutura interna, as fábricas fornecem o encapsulamento
- A criação de um objeto pode ser uma importante operação por si só, mas operações complexas de montagem não se encaixam com a responsabilidade dos objetos criados. A combinação dessas responsabilidades pode gerar designs deselegantes e dificeis de entender. O fato de se fazer uma construção direta para o cliente confunde o design do cliente, rompe o encapsulamento do objeto montado ou do agregado e provoca um acoplamento excessivo do cliente com a implementação do objeto criado
- Uma fábrica encapsula o conhecimento necessário para criar um objeto complexo ou um agregado. Ela fornece uma interface que reflete os objetivos do cliente e uma visão abstrata do objeto criado
- Existem várias maneiras de criar um design de fábrica. Vários padrões de criação para fins especiais como factory method, abstract factory e builder

## Repositórios

- Um cliente precisa de uma maneira praica de adquirir referências aos objetos já existentes do domínio. Se a infraestrutura facilitar essa ação, os desenvolvedores do cliente poderão acrescentar associações com melhores travessias, confundindo o modelo. Por outro lado, eles podem usar consultas para extrair os dados exatos de que precisam dos banco de dados, ou extrair alguns objetos específicos em vez de navegar a partir das raizes dos agregados.
- A lógica do domínio se desloca para consultas e o código do cliente, e as entidades e os objetos de valor se tornam meros recipientes de dados. A mera complexidade técnica de se aplicar a maior parte da infraestrutura de acesso a banco de dados rapidamente toma conta do código do cliente, o que leva os desenvolvedores a reduzir a camada do domínio, tornando o modelo irrelevante
- Os clientes solicitam objetos a partir do repositório utilizando métodos de consulta que selecionam objetos com base em criteŕios especificados pelo cliente, normalmente o valor de determinados atributos
- Os repositórios tem várias vantagens, incluindo as seguintes:
  - Eles oferecem aos clientes um modelo simples para obter objetos persistentes e controlar seu ciclo de vida
  - Eles tomam o design do aplicativo e do domínio e os desacoplam da tecnologia de persistência, de várias estratégias do banco de dados e até de várias fontes de dados
  - Eles comunicam decisões de design sobre acesso a objetos
  - Eles permitem a fácil substituição de uma implementação fictícia para uso em testes (normalmente utilizando uma coleção interna à memória)
- Todos os repositórios oferecem métodos que permitem a um cliente solicitar objetos que combinem com alguns critérios, mas existem várias opções para como criar essa interface
- O repositório mais fácil de ser construído possui consultas em código rígido com parâmetros específicos
- O repositório vai delegar à devida infraestrutura serviços para que a tarefa seja cumprida. O encapsulamento dos mecanismos de armazenagem, recuperação e consulta é a característica mais básica da implementação de um repositório

### A relação com as fábricas

- Uma fábrica cuida do inicio da vida de um objeto; um repositório ajuda a controlar o meio e o final
- A função de uma fábrica é instanciar um objeto potencialmente complexo a partir dos dados. Se um produto for um objeto novo, o cliente saberá disso e poderá acrescentá-lo ao repositório, que vai encapsular a armazenagem do objeto no banco de dados