import java.io.*;
import java.util.ArrayList;

public class GerenciadorContatos implements AcaoContato {
    private ArrayList<Contato> contatos;
    private String arquivo;

    public GerenciadorContatos(String arquivo) {
        this.arquivo = arquivo;
        this.contatos = carregarContatos();
    }

    private ArrayList<Contato> carregarContatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (ArrayList<Contato>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarContatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(contatos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarContato(Contato contato) {
        contatos.add(contato);
        salvarContatos();
        System.out.println("Contato adicionado com sucesso!");
    }

    @Override
    public void listarContatos() {
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }

    @Override
    public Contato buscarContato(String nome) {
        for (Contato contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nome)) {
                return contato;
            }
        }
        return null;
    }

    @Override
    public void atualizarContato(String nome, String novoTelefone) {
        Contato contato = buscarContato(nome);
        if (contato != null) {
            contato.setTelefone(novoTelefone);
            salvarContatos();
            System.out.println("Contato atualizado com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    @Override
    public void removerContato(String nome) {
        Contato contato = buscarContato(nome);
        if (contato != null) {
            contatos.remove(contato);
            salvarContatos();
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }
}