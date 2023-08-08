# Serviços

- Um serviço no domínio é uma operação sem estado que realiza uma tarefa específica do domínio. Frequentemente a melhor indicação que você deve criar um serviço no modelo do domínio é quando a operação que você precisa executar parece despropositada como um método em um agregado ou um objeto de valor

## O que é um serviço de domínio (mas antes o que ele não é)

- Não confunda serviço de domínio com serviço de aplicação. Não queremos hospedar a lógica do negócio em um serviço de domínio
- Serviços que pertencem especificamente ao domínio do negócio são uma ferramenta perfeita de modelagem a usar quando suas necessidades se interseccionam com o ponto ideal
- Quando um processo ou transformação significativa no domínio não é uma responsabilidade natural de uma entidade ou objeto de valor, adicione uma operação ao modelo como uma interface autônoma declarada como um serviço. Defina a interface em termos da linguagem do modelo e certifique-e deque o nome da operação faz parta da linguagem umbiqua
- Representam uma abstração das operações de negócio complexas que não pertencem naturalmente a uma única entidade ou agregado.
- Você pode usar um serviço de domínio para
  - Executar um processo de negócios significativo
  - Transformar um objeto de domínio entre uma composição e outra
  - Calcular um valor que exige entrada de mais de um objeto de domínio

 ## Certifique-se de que você precisa de um serviço

 - Usar serviços de uma maneira excessivamente entusiástica geralmente resultará em consequências negativas e criará um modelo de domínio anêmico, em que toda a lógica do domínio reside em serviços, em vez de predominantemente distribuída ao longo de entidades e objetos de valor

