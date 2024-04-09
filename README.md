### Olá

Essa aplicação tem como intuíto servir de auxílio na entrevista para validação da biblioteca promover o uso da biblioteca Generic Fixcture (https://github.com/BrunoKayser/generic-fixture)

Se você esta contribuindo neste repositório é por que é um profissional especial, pois apoiar voluntariamente é um apoio extraordinário.

**Obrigado.**

Para seguirmos com os testes, será necessário implementar os testes unitários das classes abaixo.

### Métodos de teste a serem implementados:
- PessoaControllerTest
  - deveInserirComSucesso
  - deveConsultarComSucesso
- PessoaMapperTest
  - deveMapearPessoaRequestParaPessoaComSucesso
  - deveMapearPessoaParaPessoaResponseComSucesso
  - deveMapearPessoaInseridaParaPessoaInseridaResponseComSucesso
- ConsultarPessoaServiceTest
  - deveConsultarComSucesso
  - deveLancarExcecaoQuandoNaoEncontrarPessoaPeloId
- InserirPessoaServiceTest
  - deveInserirComSucesso
  - naoDeveInserirQuandoPessoaForMenorDeIdade
