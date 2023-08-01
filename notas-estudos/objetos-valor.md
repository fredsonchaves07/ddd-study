# Objetos de valor

- Quando você só se importa com os atributos de um elemento do modelo, classifique-o como um objeto de valor. Faça com que ele expresse o significado dos atributos que ele transmite e lhe dê as funcionalidades relacionadas.
- Trate o objeto de valor como imutável. Não lhe dê nenhuma identidade e evite as complexidades de projeto necessárias para manter as entidades

## Características do valor

- Ao tentar decidir se um conceito é um valor, você deve determinar se ele possui a maioria destas características:

  - Ele mede, quantifica ou descreve uma coisa no dominio
  - Ele pode ser mantido imutável
  - Ele modela um todo conceitual compondo atributos relacionados como uma unidade integral
  - Ele é completamente substituivel quando a medição ou descrição muda
  - Ele pode ser comparado com outros usando a igualdade de valor
  - Ele fornece para os colaboradores comportamento livre de efeitos colaterais

 ## Integre com minimalistmo

 - Usar valores imutáveis significa assumir menos responsabilidade
 - Há momentos em que um objeto em um contexto downstream deve com o tempo ser consistente com o estado parcial de um ou mais agregados em um contexto remoto
 - Nesse caso, projetaríamos um agregado no contexto consumidor downstream, porque as entidades são usadas para manter um segmento da continuidade da alteração
 - Qaundo puder, escolha objetos de valor para modelar as integrações. Essa recomendação é aplicável em muitos casos ao consumir tipos padrão remotos

## Tipos padrão expressos como valores

- Tipos padrão são objetos descritivos que indicam os tipos das coisas. Ha a própria coisa (Entidade) ou descrição (Valor), e também existem os tipos padrão para distingui-los de outros tipos da mesma coisa
- Uma forma de suportar um tipo de padrão em Java são os enums
- A enumeração fornece um número finito bem definido de valores, é muito leve e, por convenção, tem comportamento livre de efeitos colaterais

## Testando objetos de valor

- Podemos criar testes para testar a imutabilidade do objeto de valor
- Os testes do modelo devem ter um significado para os especialistas em domínio
- Os testes de objeto de valor devem apresentar o comportamento esperado livre de efeitos colaterais e que o estado do objeto seja imutável
