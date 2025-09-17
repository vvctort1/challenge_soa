package br.com.challenge.secondNature.SecondNatureSpringBoot.usuario;

public record DadosListagemUsuarioDTO(

        Long id_usuario,
        String nome,
        String email
        ) {

    public DadosListagemUsuarioDTO(Usuario dados){
        this(dados.getId_usuario(), dados.getNome(), dados.getEmail());
    }
}
