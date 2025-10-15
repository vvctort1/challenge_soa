package br.com.challenge.secondNature.SecondNatureSpringBoot.service;
import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.Acesso;
import br.com.challenge.secondNature.SecondNatureSpringBoot.acesso.DadosCadastroAcessoDTO;
import br.com.challenge.secondNature.SecondNatureSpringBoot.repository.AcessoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Acesso cadastrarAcesso(DadosCadastroAcessoDTO dados){
        usuarioService.validarUsuarioAtivo(dados.id_usuario());

        var acesso = new Acesso(dados);
        return acessoRepository.save(acesso);
    }

    public Page<Acesso> listarAcessos(Pageable paginacao) {
        return acessoRepository.findAll(paginacao);
    }

    public Page<Acesso> listarAcessosPorUsuario(Long idUsuario, Pageable paginacao) {
        return acessoRepository.findByIdUsuarioOrderByDataAcessoDesc(idUsuario, paginacao);
    }

    public List<Acesso> listarAcessosHoje() {
        return acessoRepository.findAcessosHoje();
    }

    public Long contarAcessosPorUsuario(Long idUsuario) {
        return acessoRepository.countByIdUsuario(idUsuario);
    }
}
