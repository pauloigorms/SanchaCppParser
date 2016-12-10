## Bem Vindo ao Analisador Léxico e Sintático para C++ (DaroAnalyzer)
DARO é um projeto desenvolvido como projeto acadêmico para componente curricular Teoria da Computação e Compiladores. Este projeto
tem como foco descrever um analisador léxico e sintático para linguagem de programação _C++_, utilizando a ferramente auxiliar _JAVACC_.


### Requisitos para Desenvolvimento AL&AS:

1. Ferramenta de implementação __JavaCC__ e ambiente de desenvolvimento Eclipse (NetBeans);
2. Linguagem analisada: __C++__;
3. Ler arquivo fonte de analise com extensão: __.txt__ (_1.0 pt_);
4. Analisador Léxico (_por expressões regulares_):
	* Imprimir _tokens_ ou mensagem de __erro__, indicando local do erro, caso o símbolo não pertença à linguagem (_0.5 pts_);
	* Reconhecer símbolos usados:
		* Identificadores de variáveis e sub-rotinas (funções, procedimentos, métodos). (_0.5 pt_);
		* Palavras reservadas e símbolos usados nas:
			* Atribuições e __expressões aritméticas e lógicas__ (_0.5 pt [0,25 por item]_);
			* Estruturas __condição e repetição__; (_1.0 pt [0,5 por item]_);
			* Valores numéricos __reais__ e __inteiros__ e __valores lógicos__; (_0.75 pt [0.25 por item]_);
			* Sequência de ___caracteres___ permitidos na Linguagem de programação sorteada em sala (_0.25 pts_).
		* Ignorar espaços em __branco, tabulações, retrocessos e comentários__; (_0.25 pt_).
	
5. Deverá ser construída uma gramática livre do contexto (_formato BNF por escrito_) para o analisador sintático (_parte teórica: com sintaxe solicitada no item a seguir_). __Se a gramática não estiver no padrão solicitado de forma impressa, não será aceita__. (_2.0 pts_);
6. Analisador Sintático: 
	* Receber os _tokens do analisador léxico_ e corresponder à gramática construída no __item 5__ e deverá reconhecer a sintaxe de:
		* Declaração de variáveis e funções (__métodos__). (_0.75 pt_);
		* Estruturas __repetição e condição__. (_1,0 pt_); 
		* Operações de atribuição e __operações matemáticas básicas__ (_soma, subtração, adição e multiplicação_). (_1.0 pt_);
	* O reconhecimento será de acordo com a __sintaxe da Linguagem de programação sorteada/distribuída em sala__, mesma linguagem para qual foi construído analisador léxico:
		* Mostrar __mensagem de erro__, informando qual o erro encontrado e o __local do erro__, ou mostrar __mensagem positiva caso não tenham erros de sintaxe__. (_0.5 pts_);
		
		
				
__Item Extra__ – não ultrapassando ao valor total do trabalho) Deverão ser construídas regras semânticas para adicionar ao projeto implementado para: a) __Verificar se os tipos de dados dos operandos equivalem aos operadores__. (_0.75 pts_); e b) __Variáveis usadas e não previamente declaradas__. (_0,75 pts_);


			
#### Equipe

* [Allex Lima](http://allexlima.com)
* [Daniel Bispo](https://github.com/danielbispov/)
* [Paulo Moraes](http://pauloigormoraes.com/)
* [Renan Barroncas](https://github.com/renanbarroncas)

###### Copyright © 2016 [DARO](https://github.com/pauloigormoraes/AnalisadorDARO).
