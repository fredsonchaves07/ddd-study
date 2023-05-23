# Um modelo expresso em software

## Entidade

- A modelagem de objetos tende a fazer com que nos concentremos nos atributosde um objeto, mas o conceito fundamental de uma entidade é uma continuidade abstrata que pecorre um ciclo de vida e até mesmo passa por várias formas.
- Eles representam uma linha de identidade que atravessa o tempo e geralmente representações distintas. 
- Um erro de identidade pode levar à dados corrompidos
- Um objeto definido principalmente por sua identidade é chamado de Entidade
- É qualquer coisa que tenha continuidade através de um ciclo de vida e distinções independentes de atributos que sejam importantes para o usuário do aplicativo
- A identidade não é intrínseca a uma coisa no mundo. Na verdade, a mesma coisa na vida real pode ou não ser representada como uma Entidade em um modelo de domínios.
- As Entidades possuem um identificador único

## Modelando entidades

- É natural pensar nos atributos ao modelar um objeto, e é bastante importante pensar em seu comportamento. Mas a responsabilidade mais básica das Entidades é estabelecer a continuidade de forma que o comportamento possa ser claro e previsivel.
- Em vez de concentrar nos atributos ou até mesmo no comportamento, reduza a definição do objeto da Entidade ás características mais intrísecas, sobretudo aquelas que o identificam ou são comumente usadas para achá-la ou combiná-la.
- Só acrescente comportamentos que sejam essenciais ao conceito e atributos que sejam necessários para aquele comportamento.
- Alguns serão outras Entidades e outros Objetos de valor

### Criando o design da operação de identidade

- Cada entidade deve ter uma forma operacional de estabelecer sua identidade com outro objeto - distinguível até mesmo a partir de outro objeto com os mesmos atributos descritivos.
- As vezes, determinados atrivutos de dados, ou combinações de atributos, podem ser garantidos ou simplesmente restritos a serem únicos dentro do sistema. Essa abordagem fornece uma chave única para a Entidade
- Quando não existem nenhuma chave única verdadeira formada pelos atributos de um objeto, outra solução comum é anexar a cada instancia um símbolo (como, por exemplo, um número ou uma string) que seja única dentro da classe.
- Este atributo é imutável 
- Em situações menos formais (por exemplo, locação de filmes), os números de telefones são usados como identificadores. Mas um telefone pode ser compartilhado. O número pode mudar. Um número antigo pode até ser redesignado para uma pessoa diferente.
- Por esses motivos, são usados identificadores atribuidos de forma especiais

## Objetos de valor

- Muitos objetos não possuem nenhuma identidade conceitual. Esses objetos descrevem alguma característica de alguma coisa
- Esses objetos possuem características próprias e seu próprio significado para o modelo. São os objetos que descrevem coisas
- Um objeto que representa um aspecto descritivo do domínio sem nenhuma identidade conceitual é chamado de objeto de valor
- Objetos de valor são geralmente transmitidos como parâmetros em mensagens entre objetos. Eles são frequentemente transitórios, criados por uma operação e, em seguida, descatardos
- Objetos de valor são usados como atributos de entidade (e outros valores). Uma pessoa pode ser modelada como entidade com uma identidade, mas o nome dessa pessoa é um valor
- Quando você só se preocupa com os atributos de um elemento do modelo, classifique-o como um objeto de valor. Faça comque ele expresse o significado dos atributos que ele transmite e dê a ele uma funcionalidade relacionada. Trate o objeto de valor como imutável. Não dê a ele nenhuma identidade e evite as complexidades de design necessárias para manter entidades

### Criando o design de objetos de valor

- Não nos importa qual instância temos de um objeto de valor. Essa falta de restrição nos dá uma liberdade de design que podemos usar para simplificar o design ou otimizar o desempenho. Isso envolve fazer escolhas sobre cópias, compartilhamento e imutabilidade.
- Sempre que possível, objetos de valores também são imutáveis
- Casos especiais quando permitir a imutabilidade:
  - Se o valor mudar frequentemente
  - Se a criação a ou a exclusão do objeto for cara
  - Se a substituição (em vez de modificação) pertubar os agrupamentos
  - Se não houver muito compartilhamento de valores ou se o compartilhamento for precedido para melhorar o agrupamento ou por algum outro motivo técnico

## Serviços

- Alguns conceitos do domínio não são naturais para serem modelados como objetos. Forçar a funcionalidade do domínio exigida para que ela seja a responsabilidade de um entidade ou de um valor distorce a definição de um objeto baseado em modelos ou acrescenta objetos artificiais sem sentidos
- Um serviço é uma operação oferecida como interface que fica isolada no modelo, sem um estado de encapsulamento, como acontece com as entidades e objetos de valor.
- Um serviço tende a ser nomeado de acordo com uma atividade em vez de uma entidade. É um verbo em vez de um substantivo.
- Os nomes das operações devem ser provenientes da Linguagem Onipresente ou serem introduzidos nela. Parâmetros e resultados devem ser objetos do domínio

## Módulos ou pacotes

- O código é dividido em vários tipos de categorias, desde aspectos da arquitetura técnica até asatribuições de trabalho dos desenvolvedores
- Baixo acoplamento e alta coesão são princípios gerais de design que se aplicam tanto a cada objeto quanto a módulos, mas são principalmente importantes nessa granularidade maior de modelagem e design
- Sempre que dois elementos do modelo são separados em módulos diferentes, as relações entre eles se tornam menos diretas que antes, o que aumenta a sobrecarga para se entender o seu lugar no design. Um baixo acoplamento entre móduloss minimiza esse custo e possibilita a análise do conteúdo de um módulo com mínimo de referência aos outros que interagem
- A refatoração de módulos é mais trabalhosa e mais incomoda que a refatoração de classes, e provavelmente não pode ser tão frequente.
- Escolha módulos que contem  história do sistema e contenham um conjunto coeso de conceitos
- A não ser que exista a intenção real de distribuir o código em servidores diferentes, mantenha todo o código (que implementa um único objeto conceitual) no mesmo módulo, se não no mesmo objeto
