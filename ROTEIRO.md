# Roteiro

## Mapeamento Um-para-Muitos e operações em cascata

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
   6. mostra registros no banco: removeu apenas relacionamento
   7. explica o que aconteceu e como JPA trabalhar
   8. configura `orphanRemoval=true`
   9. roda app e vê funcionar
   10. mostra SQL e registros no banco
8. finaliza

## Relacionamento bidirecional

Explicação de como configurar relacionamento bidirecional com JPA e Hibernate

1. voltando para aplicação de notas fiscais
2. revisitando o modelo
   1. conseguimos navegar do pai para filho
   2. mas não do filho para pai, e isso pode ser util
      1. facilita escrita de logicas de negocio
      2. facilita escrita de consultas JPQL
         1. `select i.notaFiscal from Item i where i.id = :id`
         2. `select i from Item i where i.notaFiscal = :nota`
      3. pode trazer ganhos de performance ao gerar SQL
3. mapeando o outro lado da relação
   1. adiciona atributo `notaFiscal` em `Item` com `@ManyToOne`
   2. analisando o schema: tabela de junção e coluna de junção
   3. temos 2 relacionamentos, mas precisamos de 1 apenas: relacionamento bidirecional
   4. adiciona `mappedBy="notaFiscal"` na entidade `NotaFiscal`
   5. dropa tabela de junção
   6. roda a aplicação e insere nova nota fiscal
   7. verifica registros: itens com `nota_fiscal_id=NULL`
   8. o que aconteceu?
4. consistência da relação
   1. explica problema da consistência do relacionamento
   2. rescreve logica do `toModel()`
      1. para usar `itens.forEach()` em vez do construtor
      2. instancia coleção de itens em `NotaFiscal`
   3. limpa registro dos banco
   4. roda a aplicação e insere nova nota fiscal
   5. analisa registros no banco
   6. funcionou!
5. usando OO a seu favor
   1. encapsula logica no metodo `nota.adiciona(item)`
   2. roda a aplicação e insere nova nota fiscal
   3. analisa registros no banco
   4. rescreve logica de remoção de item: `nota.remove(item)`
   5. roda a aplicação e remove item de nota
   6. analisa registros no banco
   7. podemos ir até mais alem: `Collections.unmodifiableList(itens)` no getter
6. finaliza
   1. como vimos, relacionamento bidirecional pode ser bem util de varias formas
   2. mas não podemos ignorar que há o desafio de manter a consitencia do modelo
   3. o que antes eram simples e de responsabilidade do Hibernate...
   4. ...se tornou um pouco mais complexo e ficou na responsabilidade do desenvolvedor(a)

## LT1: worked out

1. explica o dominio para cadastro de nota fiscal
2. desenha API REST no Insomnia
3. navega pela aplicação
   1. abre `application.properties` e explica confs importantes
   2. mostra entidade `Produto`
   3. mostra schema no banco
4. constroi controller
   1. com metodo `cadastra()`
   2. implementa DTO `NotaFiscalRequest` e `ItemRequest`
   3. implementa logica do `toModel()`
5. modela entidades 
   1. implementa classes `NotaFiscal` e `Item`
   2. mapeia as classes
   3. adiciona relacionamento `@OneToMany` unidirecional
   4. configura `cascade` como `CascadeType.ALL`
   5. gera construtor e getters
6. implementa e injeta repository `NotaFiscalRepository`
7. grava nota fiscal no banco com `repository.save(nota)`
8. retorna `HTTP 201 CREATED`
   1. adiciona `ResponseEntity.created(location).build()`
   2. injeta `UriComponentsBuilder` como parametro do metodo
   3. implementa `uriBuilder.path("/api/notas-fiscais/{id}").buildAndExpand(notaId).toUri()`
9. adiciona `@Transactional` no metodo do controller
10. adiciona validação:
    1. nota.numero = `@NotBlank`
    2. nota.total = `@NotNull @Positive`
    3. nota.itens = `@NotEmpty @Valid`
    4. item.produto = `@NotNull`
    5. item.quantidade = `@NotNull @Positive`
    6. faz alguns testes via Insomnia
11. finaliza