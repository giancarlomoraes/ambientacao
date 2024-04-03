# Fluxo de trabalho

O Gitflow Workflow é um fluxo de trabalho do Git que auxilia o desenvolvimento contínuo de software e a implementação de práticas de DevOps [GITFLOW].

## Política de Branch

<img alt="GitFlow" title="GitFlow" src="https://github.com/FabricaDeSoftwareINF/ambientacao/blob/a247af7c8b2f2d85d729890c58bfa97e4aec5853/documentacao/gerencia-de-configuracao/diagramas/gitflow.png" height="400px">

## Política de scm

A mensagem de commit deve seguir a seguinte regra: 
- #[número da issue] - [mensagem de commit]

### Branches de desenvolvimento

São branches cujo as contribuições não são revisadas por um par. Contudo, algumas regras são aplicadas.

- **[R1]** - Toda branch de desenvolvimento deve estar ligada a um card/issue.

- **[R2]** - Existem dois tipos de branches. As branches <b>feature</b> e <b>hotfix</b>.

As branches <b>feature</b> , acomodam adição ou modificação de funcionalidades.

- **[R3]** - Nome da branch deve começar com "feature-", mais o identificador da issue do card. ( feature-[NÚMERO DA ISSUE]-complemento. Sempre em letras minúsculas, sem acentos e caracteres especiais), Ex.: feature-1-laboratorio-spoon.

- **[R4]** - Deve ser criada a partir da branch Dev.

As branches <b>hotfix</b>, acomodam correção de falhas/erros.

- **[R5]** - Nome da branch deve começar com "hotfix-", mais o identificador da issue do card. ( hotfix-[NÚMERO DA ISSUE]-complemento. Sempre em letras minúsculas, sem acentos e caracteres especiais), Ex.: hotfix-2-correcao-na-funcao-de-exportacao.
- **[R6]** - Deve ser criada a partir da branch Dev ou Main.

### Branches controladas
Essas branches não aceitam commits diretos e somente pessoas autorizadas podem modificá-las. 

- A modificação se dá por meio de revisão em pull request

As branches controladas são:

- **main**: apenas versões liberadas para uso. 
- **develop**: baselines a partir das quais branches de desenvolvimento são criadas. Apenas uma baseline em <b>develop</b> dá origem a uma próxima versão ou mudança em <b>main</b>.
- **release**: baseline candidata a ir a produção (main)

### Integração nas branches de desenvolvimento
O desenvolvedor, nos dias em que houver trabalho na branch de desenvolvimento, deve pelo menos uma vez:

- Atualizar a branch de desenvolvimento (local) com os dados da dev (remote) (git merge).
- Atualizar a branch de desenvolvimento (remote) (git push) com suas evoluções (branch de desenvolvimento local).

### Integração na DEVELOP
O desenvolvedor deve solicitar pull request da branch de desenvolvimento (remote) para a DEV (remote).

Os softwares de integração contínua da Fábrica de Software devem avaliar as contribuições propostas e aprová-las de acordo com o sucesso da construção e com as métricas aceitáveis para o projeto. 

Se reprovado o pull request deve ser fechado. Se aprovada, o revisor deve fazer a revisão pra verificar se está de acordo com a documentação do projeto, fazer o merge e deletar a branch de desenvolvimento.

### Pull request
- Pull request de trabalhos concluídos, devem possuir o nome da branch 
- Pull request de trabalhos em andamento, e que o objetivo não seja a integração com a Dev, o nome deve ter o prefixo "DRAFT-" e em sua descrição deve conter o propósito de sua criação (dúvida, feedback, impedimento etc).

### Referências
[GITFLOW] Fluxo de trabalho de Gitflow
 [https://www.atlassian.com/br/git/tutorials/comparing-workflows/gitflow-workflow acesso em 18/05/2022]
