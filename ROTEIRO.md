# Roteiro

Explicação sobre mapeamento Um-para-Muitos e operações em cascata com JPA e Hibernate

1. introdução a aplicação e dominio
2. mostra consumo de API REST
3. implementa modelo simples
   1. implementa classe `NotaFiscal`: `id, numero e total`
   2. implementa classe `Item`: `id, produto e quantidade`
   3. gera schema
4. precisamos relacionar nota e item
   1. adiciona atributo `List<Item> itens;`
   2. adiciona anotação `@OneToMany`
   3. gera schema
   4. explica sobre tabela de junção: `@JoinTable`
   5. altera para coluna de junção com `@JoinColumn(name="nota_id")`
   6. dropa tabelas e volta para o padrão
5. implementa **cadastro de nota fiscal**
   1. implementa logica no controller e `toModel()`
   2. implementa `NotaFiscalRepository`
   3. roda a app e recebe erro `TransientObjectException`
   4. explica erro e sugere solução feia com `ItemRepository`
   5. comenta sobre `cascade` e configura `CascadeType.PERSIST`
   6. roda app e vê funcionar
   7. mostra registros no banco
6. implementa **deleção de nota fiscal**
   1. implementa logica no controller
   2. configura `cascade` para `CascadeType.REMOVE`
   3. altera para `CascadeType.ALL` e explica motivação
   4. roda app e vê funcionar 
   5. mostra registros no banco
7. implementa **deleção de item de nota**
   1. implementa logica no controller
   2. roda a app e verifica que nada foi removido
   3. explica problema do `equals` e `hashCode`
   4. implementa `equals` e `hashCode`
   5. roda app e vê funcionar
   6. mostra registros no banco