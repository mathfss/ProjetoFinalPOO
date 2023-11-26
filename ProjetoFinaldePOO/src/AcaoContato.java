interface AcaoContato {
    void adicionarContato(Contato contato);
    void listarContatos();
    Contato buscarContato(String nome);
    void atualizarContato(String nome, String novoTelefone);
    void removerContato(String nome);
}