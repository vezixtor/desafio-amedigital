#language: pt
@Planeta
Funcionalidade: CRUD de planeta

  Cenário: Cadastrar planeta
    Quando tento cadastrar os dados de planeta
    Então o serviço me retorna a planeta

  Cenário: Obter planeta por chave
    Dado 1 registro(s) de planeta preexistente
    Quando tento obter os dados de planeta pela chave
    Então o serviço me retorna a planeta

  Cenário: Alterar planeta por chave
    Dado 1 registro(s) de planeta preexistente
    Quando tento alterar os dados de planeta pela chave
    Então o serviço me retorna a planeta

  Cenário: Deletar planeta
    Dado 1 registro(s) de planeta preexistente
    Quando tento deletar o registro de planeta pela chave
    Então o serviço remove o registro de planeta

  Cenário: Listar planeta
    Dado 5 registro(s) de planeta preexistente
    Quando consulto a lista de planeta
    Então o serviço me retorna uma lista de planeta

