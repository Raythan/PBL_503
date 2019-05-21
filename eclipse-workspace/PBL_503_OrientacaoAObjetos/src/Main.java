import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	static int qtdProdutos = 6, minPeso = 10, maxPeso = 15, minPreco = 20, maxPreco = 100, decisao = 0, navegador = 0, prmIndex = 0;
	static Random random = new Random();
	static Scanner leia = new Scanner(System.in);
	
	public static void main(String[] args) {
		//
		// Criação dos departamentos
		//
		Departamento DeptoBrinquedo = new Departamento("Brinquedos");
		Departamento DeptoTelefonia = new Departamento("Telefonia");
		Departamento DeptoTVsAudio = new Departamento("TVs/Áudio");
		Departamento DeptoInformatica = new Departamento("Informática");
		Departamento DeptoEletrodomestico = new Departamento("Eletrodomésticos");
		Departamento DeptoModa = new Departamento("Moda");
		String[] nomesDepto = new String[] {"Brinquedos", "Telefonia", "TVs/Áudio", "Informática", "Eletrodomésticos", "Moda"};
		
		//
		// Criação de listas para armazenar os produtos em memória
		//
		ArrayList<Produto> ListaBrinquedos = new ArrayList<Produto>();
		ArrayList<Produto> ListaTelefonia = new ArrayList<Produto>();
		ArrayList<Produto> ListaTVsAudio = new ArrayList<Produto>();
		ArrayList<Produto> ListaInformatica = new ArrayList<Produto>();
		ArrayList<Produto> ListaEletrodomestico = new ArrayList<Produto>();
		ArrayList<Produto> ListaModa = new ArrayList<Produto>();
		
		// Lista para impressão de todos os produtos...
		ArrayList<ArrayList<Produto>> Loja = new ArrayList<ArrayList<Produto>>();
		Loja.add(ListaBrinquedos);
		Loja.add(ListaTelefonia);
		Loja.add(ListaTVsAudio);
		Loja.add(ListaInformatica);
		Loja.add(ListaEletrodomestico);
		Loja.add(ListaModa);
		
		//
		// Arrays com os tipos de produtos da loja
		//
		String[] TipoBrinquedo = new String[] {"Bonecas", "Pelúcia", "Brinquedos para Bebê", "Lego", "Carrinhos"};
		String[] TipoTelefonia = new String[] {"Celular Básico", "Smartphone", "Acessórios para Celular", "Chip", "Telefonia Fixa"};
		String[] TipoTVsAudio = new String[] {"Smart TV", "TV 4K", "Acessórios para áudio e vídeo", "Equipamento de Som"};
		String[] TipoInformatica = new String[] {"Computador", "Notebook", "Impressora", "Componentes", "Acessórios para Computador"};
		String[] TipoEletrodomestico = new String[] {"Geladeira e refrigerador", "Fogão", "Lava roupas", "Micro-ondas"};
		String[] TipoModa = new String[] {"Roupa Masculina", "Calçados Masculino", "Roupa Feminina", "Calçados Femininos", "Bolsas e Acessórios"};
		
		//
		// Nessa parte o método constroi lista, deriva alguns produtos com quantidades aleatórias para as listas
		// e adiciona para cada produto o observer correspondente a seu departamento
		//
		ListaBrinquedos = ConstroiListaDeProdutos("Brinquedo", DeptoBrinquedo, ListaBrinquedos, TipoBrinquedo);
		ListaTelefonia = ConstroiListaDeProdutos("Telefonia", DeptoTelefonia, ListaTelefonia, TipoTelefonia);
		ListaTVsAudio = ConstroiListaDeProdutos("TVs/Áudio", DeptoTVsAudio, ListaTVsAudio, TipoTVsAudio);
		ListaInformatica = ConstroiListaDeProdutos("Informática", DeptoInformatica, ListaInformatica, TipoInformatica);
		ListaEletrodomestico = ConstroiListaDeProdutos("Eletrodoméstico", DeptoEletrodomestico, ListaEletrodomestico, TipoEletrodomestico);
		ListaModa = ConstroiListaDeProdutos("Moda", DeptoModa, ListaModa, TipoModa);
		
		//
		// Navegador do usuário
		//
		while(decisao != 1) {
			ImprimeCabecalho();
			navegador = leia.nextInt();
			switch(navegador) {
			case 1:
				System.out.println("\nQual departamento?\n");
				for(int i = 0; i < nomesDepto.length; i++) {
					System.out.println("(" + (i + 1) + ") - " + nomesDepto[i]);
				}
				prmIndex = leia.nextInt();
				ImprimeProdutosPorDepartamento(Loja, (prmIndex - 1));
				break;
			case 2:
				ImprimeProdutos(Loja);
				break;
			case 3:
				boolean condicao = true;
				float prmDesconto = 0.0f;
				String prmNome = "";
				while(condicao) {
					System.out.println("\nDesconto por:\n" +
							"(1) - Tipo específico\n" + 
							"(2) - Marca específica\n" + 
							"(3) - Nome específico\n" + 
							"(4) - Todos os produtos da loja\n"
							);
					prmIndex = leia.nextInt();
					System.out.println("Qual o valor do desconto? Ex.: 12,0 = 12%, 0,3 = 3%\n");
					prmDesconto = leia.nextFloat();
					leia.nextLine();
					if(prmIndex!=4) {
						System.out.println("Qual em específico?\n");
						prmNome = leia.nextLine();
					}
					if(prmDesconto != 0.0) {
						InsereDesconto(Loja, (prmIndex), prmDesconto, prmNome);
						condicao = false;
					}
					else {
						System.out.println("Você digitou algum valor inválido");
						leia.nextLine();
					}	
				}
				break;
			case 4:
				RemoveDesconto(Loja);
				System.out.println("Todos os descontos foram removidos com sucesso! :D");
				break;
			case 5:
				decisao = 1;
				break;
			}
		}
	}
	//
	// Método que remove todos os descontos
	//
	public static void RemoveDesconto(ArrayList<ArrayList<Produto>> prmLoja) {
		for(ArrayList<Produto> departamentos : prmLoja) {
			for(int i = 0; i < departamentos.size(); i++) {
				departamentos.get(i).RemoveDesconto();
			}
		}
	}
	//
	// Método que insereDesconto
	//
	public static void InsereDesconto(ArrayList<ArrayList<Produto>> prmLoja, int prmIndex, float prmDesconto, String prmNome) {
		for(ArrayList<Produto> departamentos : prmLoja) {
			for(int i = 0; i < departamentos.size(); i++) {
				if(prmIndex == 1) {
					departamentos.get(i).InsereDescontoTipo(prmDesconto, prmNome);
				}
				if(prmIndex == 2) {
					departamentos.get(i).InsereDescontoMarca(prmDesconto, prmNome);
				}
				if(prmIndex == 3) {
					departamentos.get(i).InsereDescontoNome(prmDesconto, prmNome);
				}
				if(prmIndex == 4) {
					departamentos.get(i).InsereDesconto(prmDesconto);
				}
			}
		}
	}
	//
	// Método que constroi a lista de produtos
	//
	public static ArrayList<Produto> ConstroiListaDeProdutos(String prmGrupoProduto, Departamento prmDepartamento, ArrayList<Produto> prmLista, String[] prmVetorTipo){
		for(int j = 0; j < prmVetorTipo.length; j++) {
			for(int i = 0, contador = random.nextInt(qtdProdutos); i < contador; i++) {
				Produto produto = new Produto(
						"Nome" + prmGrupoProduto + random.nextInt(qtdProdutos), 			// Nome 
						minPeso + random.nextFloat() * (maxPeso - minPeso),  				// Peso
						minPreco + random.nextFloat() * (maxPreco - minPreco), 				// Preço
						prmVetorTipo[j], 													// Tipo
						"Marca" + prmGrupoProduto  + random.nextInt(qtdProdutos), 			// Marca
						random.nextInt(qtdProdutos), 										// Quantidade
						"Característica" + prmGrupoProduto + random.nextInt(qtdProdutos));	// Característica
				produto.addObserver(prmDepartamento); 										// adicionando observer departamento no produto
				prmLista.add(produto);														// adicionando objeto na lista
			}
		}
		return prmLista;
	}
	//
	// Método que imprime todos os produtos
	//
	public static void ImprimeProdutos(ArrayList<ArrayList<Produto>> prmLoja){
		
		for(ArrayList<Produto> prmProdutos : prmLoja ) {
			for(int i = 0; i < prmProdutos.size(); i++) {
				System.out.println(
						"\n" + prmProdutos.get(i).getNome() + 
						"\n\tTipo: " + prmProdutos.get(i).getTipo() + 
						"\n\tPreço: " + prmProdutos.get(i).getPreco() +
						"\n\tMarca: " + prmProdutos.get(i).getMarca());
				if(prmLoja.get(prmIndex).get(i).getDesconto() > 0.0f) {
					System.out.println(
							"\t\tCom desconto: " + prmLoja.get(prmIndex).get(i).CalculaPreco());
				}
			}
		}
	}
	//
	// Métodos que imprimem os produtos pelo departamento
	//
	public static void ImprimeProdutosPorDepartamento(ArrayList<ArrayList<Produto>> prmLoja, int prmIndex){
		for(int i = 0; i < prmLoja.get(prmIndex).size(); i++) {
			System.out.println(
					"\n" + prmLoja.get(prmIndex).get(i).getNome() + 
					"\n\tTipo: " + prmLoja.get(prmIndex).get(i).getTipo() + 
					"\n\tPreço: " + prmLoja.get(prmIndex).get(i).getPreco() +
					"\n\tMarca: " + prmLoja.get(prmIndex).get(i).getMarca());
			if(prmLoja.get(prmIndex).get(i).getDesconto() > 0.0f) {
				System.out.println(
						"\t\tCom desconto: " + prmLoja.get(prmIndex).get(i).CalculaPreco());
			}
		}
	}
	//
	// Método para interatividade do usuário
	//
	public static void ImprimeCabecalho() {
		System.out.println(
				"\n\n<<< Menu de navegação >>>\n" +
				"(1) - Imprimir produtos por departamento.\n" +
				"(2) - Imprimir todos os produtos da loja.\n" +
				"(3) - Acrescentar desconto.\n" + 
				"(4) - Remover desconto.\n" +
				"(5) - Sair do programa.\n" +
				"<<<------------------->>>\n\n");
	}
}
