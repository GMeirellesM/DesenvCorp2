# Documentação: REST API

Entrega individual 02: REST API

## Autor

|Property|Description|
|--|--|
|id|Chave primária|
|email|O e-mail|
|nome|O nome|
|sobrenome|O sobrenome|
|afiliacao|A afiliação|
|orcId|O orcId|

### Endpoints Autor

#### POST

Cria um autor.

> http://localhost:8080/autor

EXEMPLO:

    {
        "email": "autor@email.com",
        "nome": "João",
        "sobrenome": "Silva",
        "afiliacao": "UFF",
        "orcId": "3456-3212-3212-3452"
    }

#### GET

Lista todos os autores.

> http://localhost:8080/autor

EXEMPLO:

    [
        {
            "id": 1,
            "email": "gmartinho.dev@gmail.com",
            "nome": "Gabriel",
            "sobrenome": "Martinho",
            "afiliacao": "UFF",
            "orcId": "1234-5678-9101-1121"
        },
        {
            "id": 2,
            "email": "joao@joao.com",
            "nome": "João",
            "sobrenome": "Silva",
            "afiliacao": "UFRJ",
            "orcId": "1534-5478-9201-9121"
        },
    ]

#### GET

Retorna um autor pelo id.

> http://localhost:8080/autor/1

EXEMPLO:

    {
        "id": 1,
        "email": "gmartinho.dev@gmail.com",
        "nome": "Gabriel",
        "sobrenome": "Martinho",
        "afiliacao": "UFF",
        "orcId": "1234-5678-9101-1121"
    }

#### GET

Filtra os autores por sobrenome.

> http://localhost:8080/autor/sobrenome/Silva

EXEMPLO:

    [
        {
            "id": 2,
            "email": "joao@joao.com",
            "nome": "João",
            "sobrenome": "Silva",
            "afiliacao": "UFRJ",
            "orcId": "1534-5478-9201-9121"
        }
    ]

#### PUT

Atualiza um autor.

> http://localhost:8080/autor

EXEMPLO:

	{
        "id": 1,
        "nome": "Novo nome de autor"
    }

#### DELETE

Deleta um recurso.

> http://localhost:8080/autor/1

EXEMPLO:

	{
        "message": "removed autor"
	}

## Recurso

|Property|Description|
|--|--|
|id|Chave primária|
|titulo|O título|
|descricao|A descrição|
|link|O link onde o recurso está guardado|
|imagem|O nome da imagem|
|dataCriacao|A data de criação|
|dataRegistro|A data em que foi registrado|
|palavrasChave|As palavras chaves|
|autores|Lista os autores responsáveis pelo recurso|

### Endpoints Recurso

#### POST

Cria um recurso.

> http://localhost:8080/recurso

EXAMPLE:

	{
        "titulo": "Recurso titulo",
        "descricao": "Recurso descricao",
        "link": "recurso.com",
        "imagem": img_teste.png
        "dataCriacao": "2021-12-21",
        "dataRegistro": "2021-12-21",
        "palavrasChave": [
            "tecnologia"
        ],
        "autores": [
            {
                "id": 1
            }
        ]
    }

#### GET

Retorna a lista completa de  recursos.

> http://localhost:8080/recurso

EXEMPLO:

    [
        {
            "id": 2,
            "titulo": "Recurso titulo",
            "descricao": "Recurso descricao",
            "link": "recurso.com",
            "imagem": img_teste.png
            "dataCriacao": "2021-12-21",
            "dataRegistro": "2021-12-21",
            "palavrasChave": [
                "tecnologia"
            ],
            "autores": [
                {
                    "id": 1,
                    "email": "autor@email.com",
                    "nome": "NOVO autor nome",
                    "sobrenome": "Autor sobrenome",
                    "afiliacao": "UFF",
                    "orcId": "3456-3212-3212-3452"
                }
            ]
        }
    ]

#### GET

Retorna um recurso.

> http://localhost:8080/recurso/2

EXEMPLO:

	{
        "id": 2,
        "titulo": "Recurso titulo",
        "descricao": "Recurso descricao",
        "link": "recurso.com",
        "imagem": img_teste.png
        "dataCriacao": "2021-12-21",
        "dataRegistro": "2021-12-21",
        "palavrasChave": [
            "tecnologia"
        ],
        "autores": [
            {
                "id": 1,
                "email": "autor@email.com",
                "nome": "NOVO autor nome",
                "sobrenome": "Autor sobrenome",
                "afiliacao": "UFF",
                "orcId": "3456-3212-3212-3452"
            }
        ]
    }

#### GET

Return recursos by an autor.

> http://localhost:8080/recurso/autor/1

EXEMPLO:

    [
        {
            "id": 2,
            "titulo": "Recurso titulo",
            "descricao": "Recurso descricao",
            "link": "recurso.com",
            "imagem": img_teste.png
            "dataCriacao": "2021-12-21",
            "dataRegistro": "2021-12-21",
            "palavrasChave": [
                "tecnologia"
            ],
            "autores": [
                {
                    "id": 1,
                    "email": "autor@email.com",
                    "nome": "NOVO autor nome",
                    "sobrenome": "Autor sobrenome",
                    "afiliacao": "UFF",
                    "orcId": "3456-3212-3212-3452"
                }
            ]
        }
    ]

#### GET

Filtra os recursos por coleções

> http://localhost:8080/recurso/collection/3

EXEMPLO:

    [
        {
            "id": 2,
            "titulo": "Recurso titulo",
            "descricao": "Recurso descricao",
            "link": "recurso.com",
            "imagem": img_teste.png
            "dataCriacao": "2021-12-21",
            "dataRegistro": "2021-12-21",
            "palavrasChave": [
                "tecnologia"
            ],
            "autores": [
                {
                    "id": 1,
                    "email": "autor@email.com",
                    "nome": "NOVO autor nome",
                    "sobrenome": "Autor sobrenome",
                    "afiliacao": "UFF",
                    "orcId": "3456-3212-3212-3452"
                }
            ]
        }
    ]

#### PUT

Atualiza um recurso.

> http://localhost:8080/recurso

EXEMPLO:

	{
        "id": 2,
        "titulo": "NOVO recurso titulo",
        "descricao": "NOVO recurso descricao",
        "link": "newrecurso.com",
        "imagem": img_teste.png
        "dataCriacao": "2021-08-27",
        "dataRegistro": "2021-08-27",
        "palavrasChave": [
            "nova tecnologia"
        ],
        "autores": [
            {
                "id": 4
            }
        ]
    }

#### DELETE

Deleta um recurso.

> http://localhost:8080/recurso/2

EXEMPLO:

	{
        "message": "removed recurso"
	}

## Evento

|Property|Description|
|--|--|
|id|Chave primária|
|titulo|O título|
|descricao|A descricao|
|imagem|A imagem|
|recursos|Lista de recursos|
|dataInicio|Inicio da data|
|dataFim|Fim da data|

### Endpoints Evento

#### POST

Cria um evento.

> http://localhost:8080/evento

EXAMPLE:

    {
        "titulo": "Evento titulo",
        "descricao": "Evento descricao",
        "imagem": img_teste.png
        "recursos": [
            {
                "id": 2
            }
        ],
        "dataInicio": "2020-02-01",
        "dataFim": "2022-01-30"
    }

#### GET

Retorna a lista completa de  eventos.

> http://localhost:8080/evento

EXEMPLO:

    [
        {
            "id": 3,
            "titulo": "Evento titulo",
            "descricao": "Evento descricao",
            "imagem": img_teste.png
            "recursos": [
                {
                    "id": 2,
                    "titulo": "NOVO recurso titulo",
                    "descricao": "NOVO recurso descricao",
                    "link": "newrecurso.com",
                    "imagem": img_teste.png
                    "dataCriacao": "2021-08-27",
                    "dataRegistro": "2021-08-27",
                    "palavrasChave": [
                        "nova tecnologia"
                    ],
                    "autores": [
                        {
                            "id": 4,
                            "email": "autor@email.com",
                            "nome": "Autor nome",
                            "sobrenome": "Autor sobrenome",
                            "afiliacao": "UFF",
                            "orcId": "3456-3212-3212-3452"
                        }
                    ]
                }
            ],
            "dataInicio": "2020-02-01",
            "dataFim": "2022-01-30"
        }
    ]

#### GET

Retorna um evento.

> http://localhost:8080/evento/3

EXEMPLO:

    {
        "id": 3,
        "titulo": "Evento titulo",
        "descricao": "Evento descricao",
        "imagem": img_teste.png
        "recursos": [
            {
                "id": 2,
                "titulo": "NOVO recurso titulo",
                "descricao": "NOVO recurso descricao",
                "link": "newrecurso.com",
                "imagem": img_teste.png
                "dataCriacao": "2021-08-27",
                "dataRegistro": "2021-08-27",
                "palavrasChave": [
                    "nova tecnologia"
                ],
                "autores": [
                    {
                        "id": 4,
                        "email": "autor@email.com",
                        "nome": "Autor nome",
                        "sobrenome": "Autor sobrenome",
                        "afiliacao": "UFF",
                        "orcId": "3456-3212-3212-3452"
                    }
                ]
            }
        ],
        "dataInicio": "2020-02-01",
        "dataFim": "2022-01-30"
    }

#### GET

Return evento by period of time.

> http://localhost:8080/evento/period?dataInicio=2021-12-01&dataFim=2024-12-31

EXEMPLO:

    [
        {
            "id": 3,
            "titulo": "Evento titulo",
            "descricao": "Evento descricao",
            "imagem": img_teste.png
            "recursos": [
                {
                    "id": 2,
                    "titulo": "NOVO recurso titulo",
                    "descricao": "NOVO recurso descricao",
                    "link": "newrecurso.com",
                    "imagem": img_teste.png
                    "dataCriacao": "2021-08-27",
                    "dataRegistro": "2021-08-27",
                    "palavrasChave": [
                        "nova tecnologia"
                    ],
                    "autores": [
                        {
                            "id": 4,
                            "email": "autor@email.com",
                            "nome": "Autor nome",
                            "sobrenome": "Autor sobrenome",
                            "afiliacao": "UFF",
                            "orcId": "3456-3212-3212-3452"
                        }
                    ]
                }
            ],
            "dataInicio": "2020-02-01",
            "dataFim": "2022-01-30"
        }
    ]

#### PUT

Atualiza um evento.

> http://localhost:8080/evento

EXEMPLO:

    {
	"id": 3,
        "titulo": "NOVO evento titulo",
        "descricao": "NOVO evento descricao",
        "imagem": img_teste.png
        "recursos": [
            {
                "id": 5
            }
        ],
        "dataInicio": "2022-01-02",
        "dataFim": "2022-01-29"
    }

#### DELETE

Deleta umn evento.

> http://localhost:8080/evento/3

EXEMPLO:

	{
        "message": "removed evento"
	}


## Curso

The Curso List represents a cluster of recurso entity items.

|Property|Description|
|--|--|
|id|Chave primária|
|titulo|O título|
|descricao|A descrição|
|imagem|O nome da imagem|
|recursos|Listagem de recursos|
|dataRegistrada|Data de registro|

### Endpoints Curso

#### POST

Save a curso.

> http://localhost:8080/curso

EXAMPLE:

    {
        "titulo": "Curso titulo",
        "descricao": "Curso descricao",
        "dataRegistrada": "2021-08-27",
        "imagem": img_teste.png
        "recursos": [
            {
                "id": 2
            }
        ]
    }

#### GET

Retorna a lista completa de  cursos.

> http://localhost:8080/curso

EXEMPLO:

    [
        {
            "id": 8,
            "titulo": "Curso titulo",
            "descricao": "Curso descricao",
            "imagem": img_teste.png
            "recursos": [
                {
                    "id": 2,
                    "titulo": "NOVO recurso titulo",
                    "descricao": "NOVO recurso descricao",
                    "link": "newrecurso.com",
                    "imagem": img_teste.png
                    "dataCriacao": "2021-08-27",
                    "dataRegistro": "2021-08-27",
                    "palavrasChave": [
                        "nova tecnologia"
                    ],
                    "autores": [
                        {
                            "id": 4,
                            "email": "autor@email.com",
                            "nome": "Autor nome",
                            "sobrenome": "Autor sobrenome",
                            "afiliacao": "UFF",
                            "orcId": "3456-3212-3212-3452"
                        }
                    ]
                }
            ],
            "dataRegistrada": "2021-08-27"
        }
    ]

#### GET

Retorna um curso.

> http://localhost:8080/curso/8

EXEMPLO:

    {
        "id": 8,
        "titulo": "Curso titulo",
        "descricao": "Curso descricao",
        "imagem": img_teste.png
        "recursos": [
            {
                "id": 2,
                "titulo": "NOVO recurso titulo",
                "descricao": "NOVO recurso descricao",
                "link": "newrecurso.com",
                "imagem": img_teste.png
                "dataCriacao": "2021-08-27",
                "dataRegistro": "2021-08-27",
                "palavrasChave": [
                    "nova tecnologia"
                ],
                "autores": [
                    {
                        "id": 4,
                        "email": "autor@email.com",
                        "nome": "Autor nome",
                        "sobrenome": "Autor sobrenome",
                        "afiliacao": "UFF",
                        "orcId": "3456-3212-3212-3452"
                    }
                ]
            }
        ],
        "dataRegistrada": "2021-08-27"
    }

#### PUT

Atualiza um curso.

> http://localhost:8080/curso

EXEMPLO:

    {
        "id": 8,
        "titulo": "NOVO curso titulo",
        "descricao": "NOVO curso descricao",
        "imagem": img_teste.png
        "dataRegistrada": "2021-12-23",
        "recursos": [
            {
                "id": 9
            }
        ]
    }

#### DELETE

Deleta um curso.

> http://localhost:8080/curso/8

EXEMPLO:

	{
        "message": "removed curso"
	}
