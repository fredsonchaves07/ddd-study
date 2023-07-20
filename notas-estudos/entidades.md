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
