### Olá

Essa aplicação faz parte do processo para validaçar o uso da biblioteca Generic Fixcture (https://github.com/BrunoKayser/generic-fixture)

Se você esta contribuindo neste repositório é por que é um profissional especial, pois apoiar voluntariamente é um apoio extraordinário.

**Obrigado.**

Para seguirmos com os testes, será necessário implementar os testes unitários de dois fluxos, PESSOA e PET SHOP, seguindo os pré-requisitos abaixo:
- Qualquer objeto de teste deve ser mockado
- Você deve codificar PESSOA **sem utilizar** a biblioteca Generic Fixture.
- Você deve codificar PET SHOP **utilizando** a biblioteca Generic Fixture.

### Métodos de teste a serem implementados no fluxo de PESSOA:
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
  - deveLancarExceptionQuandoPessoaForMenorDeIdade
  - deveLancarExceptionQuandoPessoaTerMaisQueDoisAnimais

### Métodos de teste a serem implementados no fluxo de PET SHOP:
- PetShopControllerTest
  - deveInserirComSucesso
  - deveConsultarComSucesso
- PetShopMapperTest
  - deveMapearPetShopRequestParaPetShopComSucesso
  - deveMapearPetShopInseridaParaPetShopInseridaResponseComSucesso
  - deveMapearPetShopParaPetShopResponseComSucesso
- ConsultarPetShopServiceTest
  - deveConsultarComSucesso
  - deveLancarExcecaoQuandoNaoEncontrarPetShopPeloId
- InserirPetShopServiceTest
  - deveInserirComSucesso
  - deveLancarExceptionQuandoDataDeInauguracaoForNoPassado
  - deveLancarExceptionQuandoPetShopTerMenosQueDoisFuncionarios
