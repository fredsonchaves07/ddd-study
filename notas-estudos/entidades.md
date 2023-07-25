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

### Identidade substituta

- Algumas ferramentas ORM, como hibernate, querem lidar com a identidade do objeto em seus próprios termos. O Hibernete prefere o tipo nativo do banco de dados, por exemplo, uma sequência numérica, como a identidade primária de cada Entidade
- Não há necessidade de a identidade do domínio desempenhar o papel da chave primária no banco de dados. Permitimos que o id substituto funcione como a chave primária do banco de dados

### Estabilidade da identidade

- Na maioria dos casos, uma identidade única deve ser protegida contra modificações, permanecendo estável ao longo do tempo de vida da entidade à qual ela é atribuida
- Podemos tomar medidas triviais para impedir a modificação da identidade. Podemos ocultar dos clientes os setters de identidade. Podemos também criar controladores nos setters para evitar que até mesmo a própria entidade altere o estado da identidade se ela já existir

## Descobrindo entidade e suas características intrínsecas

### Validação

- A principal razão para uar a validação no modelo é para verificar a exatidão de qualquer um atributo/propriedade, qualquer objeto inteiro ou qualquer composição de objetos.
- A validação realiza coisas diferentes. Só porque todos os atrubutos/propriedades de um objeto de domínio são individualmente válidos não significa que o objeto como um todo é válido
- Talvez a combinação de duas entidades, cada uma das quais com estados válidos individuais, na verdade torna a composição inválida

#### Validando atributos e propriedades

- O autoencapsulamento significa projetar as classes de modo que todo o acesso aos dados, mesmo dentro da mesma classe, passa por métodos de acesso
- O uso dessa tecnica apresenta várias vantagens. Ela permite a abstração das variáveis de instância de um objeto. Fornece uma maneira de derivar facilmente atributos/propriedades a partir de quaisquer outros que o objeto contém

#### Validando objetos inteiros

- Incorporar a validação a uma entidade também dá a ela muitas responsabilidades. Ela já tem a responsabilidade de lidar com o comportamento do domíio, uma vez que mantém seu estado.
- Um componente de validação tem a respondabilidade de determinar se o estado da entidade é ou não válido. Ao projetar uma classe de validação separada com o Java, insiraá no mesmo módulo (pacote) que o da Entidade
- A classe de validação pode implementar o padrão Especificação ou padrão de estratégia. Se detectar um estado inválido, ela informará o cliente ou de outro modo criará um registro dos resultados que podem ser revistos mais tarde (por exemplo, após o processamento em lote)

#### Validado composições de objetos

- Aqui determinados não apenas que uma entidade individual é válida, mas que um agrupamento ou composição de Entidades são todas válidas conjuntamente, incluindo uma ou mais instâncias de agregado. Para fazer isso, podemos instanciar a subclasse concreta Validator com o número apropriado de instâncias. Mas pode ser melhor gerenciar esse tipo de validação usando um serviço de domínio

#### Monitoramento de alterações

- De acordo com a definição de entidade, não é necessário monitorar as alterações que ocorrem no estado durante sua vida útil. Só precisamos suportar seu estado em constante mudanças. Mas as vezes especialistas em domínio se preocupam com ocorrências importantes no modelo à medida que o tempo passa. Quando esse é o caso, monitorar açterações específicas na entidade pode ajudar
- A maneira mais prática de alcançar um monitoramento preciso e útil das alterações é usar eventos de domínioe um armazenamento de eventos
