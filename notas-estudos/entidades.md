  # Entidades

  ## Porque utilizamos entidades?
  
  - Uma entidde é uma coisa única e pode ser alterada continuamente por um longo período de tempo. As alterações podem ser tão extensas que o objeto pode parecer muito diferente do que era antes, mas é o mesmo objeto por identidade
  - São as características e a mutabilidade da identidade que separam entidades dos objetos de valores

 ## Identidade Única

 - Em vez de focalizar os atributos ou até mesmo o comportamento, sintetize a definição do objeto Entidade às característics mais intrínsecas, particulamente aquelas que identificam ou são comumente usadas para localizar ou correspondê-lo. Adicione apenas o comportamento que é essencial para o conceito e os atributos que são exigidos por esse comportamento
 - Algumas estratégias comuns da criação da identidade, da aparentemente mais simples e básica a aquelas com complexidade crescente

1) O usuário fornece um ou mais valores únicos originais como entrada para aplicação. A aplicação deve garantir que eles sejam únicos
2) A aplicação gera internamente uma identidade utilizando um algoritmo que assegura a unicidade. Podemos usar uma biblioteca ou estrutura para fazer isso por nós, mas isso pode ser feito pela aplicação
3) A aplicação conta com um armazenamento de persistência, como um banco de dados, para gerar uma identidade única
4) Outro **Contexto delimitado** já determinou a identidade única. Ela é produzida ou selecionada pelo usuário a partir de um conjunto de opções

### O usuário fornece a identidade

- É verdade que isso é uma abordagem bastante simples. Mas pode haver complicações
- A identidade pode ser única, mas incorreta. Na maioria das vezes, as identidades devem ser imutáveis, de modo que os usuários não possam altera-las
- Esse nem sempre é o caso, e pode haver vantagens em permitir que os usuários corrijam os valores da identidade

### A Aplicação gera a identidade

- Há maneiras altamente confiáveis de autogerar identidades únicas, embora devamos tomar cuidade quando a aplicação está clusterizada ou de outro modo distribuida ao longo de multiplos nós de computação
- Há padrões de criação de identidade que podem, com um maior grau de certeza, produzir uma identidade completamente única. O UUID ou GUID é uma dessas abordagens

### O mecanismo de persistência gera a identidade

- Delegar a geração de uma identidade única a um mecanismo de persistência apresenta algumas vantagens únicas. Se chamarmos no banco de dados uma sequência ou um valor incremental, seles sempre serão únicos
- Uma possível desvantagem é o desempenho. Pode demorar muito mais tempo acessar o banco dedados para obter cada valor do que gerar identidades na aplicação

### Outro contexto delimitado atribui a identidade

- Quando outro contexto delimitado atribui a identidade, precisamos integrar para localizar, corresponder e atribuir cada identidade
- Uma boa estratégia é utilizar uma Arquitetura orientada a eventos com eventos de domínio. Quando uma notificação relevante é recebida, o sistema local muda suas próprias entidades agregadas para refletir o estado dos sistemas externos.
- Essa é a parte mais complexa das estratégias de criação de identidades. A manutenção da entidade local é dependente não apenas das transições causadas pelos comportamentos do domínio local, mas possivelmente também por aquelas que ocorrem em um ou mais sistemas externos

### Quando o timing da geração de identidade é importante

- A geração de identidade pode ocorrer tanto no início, como parte da construção do objeto, quando posteriormente, como parte de sua persistência
- A maneira mais simples de atribuir uma identidade única é fazer o armazenamento de dados gerá-la na primeira vez que o objeto é persistido
- Mas tambem é possível que a identidade unica possa ser consultada a partir do repositório e atribuida durante a instanciação.
- Um problema que pode ocorrer quando a geração da identidade é adiada até que a entidade seja persistida. Ele ocorre quando duas ou mais novas entidades precisam ser adicionadas a um `set`, mas sua identidade ainda não foi atribuída, tornando-as iguais às novas. Se o método `equals` da entidade comparar as identidades, aqueles recém adicionadas ao `Set` parecerão ser o mesmo objeto. Apenas o primeiro objeto adicionado será mantido, e todos os outros serão excluidos. Isso provoca um erro duvidoso cuja causa raiz é, a primeira vista, difícil de entender e corrigir
- Para evitar este erro, devemos fazer uma de duas coisas. Alteramos o projeto para alocar e atribuir a identidade precocemente, ou refatoramos o método `equals` para comparar os atributos além da identidade do domínio
- O método `hashCode` do mesmo objeto deve se harmonizar com o método `equals`
- É preferível atribuição precoces à abordagem de teste de igualdade de valor. É mais desejável que as entidades tenham métodos `equals` e `hashCodes` que se baseiam na identificação única do objeto do que em outros atributos
