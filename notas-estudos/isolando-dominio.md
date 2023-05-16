# Isolando o domínio

- Programas de software envolvem design e códigos para executar muitos tipos diferentes de tarefas. Eles aceitam a entrada do usuário, executam a lógica do négocio, acessam banco de dados, comunicam-se com outras redes, exibem informações aos usuários e assim por diante. Por isso, o código envolvido em cada função do programa pode ser substancial
- A criação de programas que possam controlar tarefas complexas, exige a separação das coisas, permitindo isolar a concentração em partes diferentes do design. Ao mesmo tempo, as complicadas interações dentro do sistema devem ser mantidas apesar da separação
- Existem algumas maneiras de se dividir em um sistema de software, mas através da experência e de convenções, o setor convergiu para Arquietura em Camadas e, especificamente, algumas camadas relativamente padronizadas.

![Captura de tela de 2023-05-16 08-55-20](https://github.com/fredsonchaves07/ddd-study/assets/43495376/8c8f7e31-ba4b-47b6-a1bb-2594a56f6adc)

- O principio essencial é de que qualquer elemento de uma camada depende somente dos outros elementos da mesma camada ou dos elementos da camadas "abaixo" dela. A comunicação para acima deve passar por algum tipo de mecanismo indireto
- O valor das camadas é que cada uma se especializa em um determinado aspectos de um programa de computador
