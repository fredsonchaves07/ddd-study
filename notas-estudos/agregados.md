# Agregados

## Regra: Invariantes reais do modelo nos limites da consistência

- Ao tentar descobrir os agregados em um contexto delimitado, devemos entender as invariantes reais do modelo
- Uma invariante é uma regra de negócio que sempre deve ser consistente. Há diferentes tipos de consistência. Um deles é a consistência transacional, que é considerada imediata e atômica. Há também a consistência futura.
- O limite de consistência afirma logicamene que tudo dentro segue um conjunto específico de regras invariantes do negócio independentemente de quais operações são executadas. A consistência de tudo fora desse limite é irrelevante para o agregado.
- O fato de que agregados devem ser projetados com um foco na consistência implica que a interface do usuário deve concentra-se em cada solicitação para executar um único comando apenas em uma instância do agregado

## Regra: Projete pequenos agregados

- Uma quantidade grande de agregados pode implicar que várias coleções grandes são carregadas durante uma operação de banco de dados (lazy load)
- Limite um agregado apenas à entidade raiz e a um número mínimo de atributos e/ou propriedade de valores tipados

## Regra: Referencie outros agregados por identidade

- Prefira referências e agregados externos somente por meio da identidade globalmente única, não mantendo uma referência direta (ou "ponteiro") a objetos.
- O modelo pode ter melhor desempenho porque as instâncias requerem menos tempo de carregamento e consomem menos memória. Usar menos memória tem implicações positivas tanto para sobrecarga da alocação de memória como para a coleta de lixo

## Regra: Use consistência futura fora do limite

- Se a execução de um comando em uma instância de agregado exigir que regras adicionais de negócio sejam executadas em um ou mais outros agregados, utilize a consistência futura. Aceitar o fato de que todas as instâncias de agregado em uma empresa de grande escala e alto tráfego nunca são completamente consistentes ajuda a aceitar que a consistência futura também faz sentido em uma escala menor em que apenas algumas instãncias estão envolvidas
