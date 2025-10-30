package plataforma.pratica4.dominio;

public class Inscricao {

    private final Curso curso;
    private Progresso progresso;

    public Inscricao(Curso curso) {
        this.curso = curso;
        this.progresso = new Progresso(0.0);
    }

    public Curso getCurso() {
        return curso;
    }

    public Progresso getProgresso() {
        return progresso;
    }

    public void atualizarProgresso(Progresso novoProgresso) {
        this.progresso = novoProgresso;
    }
}