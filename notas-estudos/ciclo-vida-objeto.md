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
