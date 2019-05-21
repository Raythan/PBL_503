import java.util.Observable;
import java.util.Observer;

public class Departamento implements Observer{
	private String nome;
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nomeParametro) {
		this.nome = nomeParametro;
	}
	public Departamento( String nomeParametro) {
		this.nome = nomeParametro;
	}
	@Override
	public void update(Observable objetoParametro, Object arg) {
		//
		// Esse condicional verifica se o objeto que está notificando não é nulo
		// e apresenta uma mensagem de que foi notificado sobre alguma alteração nas classes
		// que ele está observando
		//
		if(objetoParametro instanceof Produto && arg != null && arg != "Todos") {
			System.out.println("Agora " + ((Produto) objetoParametro).getNome() + " está com desconto por " + arg);
		}
	}
}
