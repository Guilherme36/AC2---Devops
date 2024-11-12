
package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.dto.ComentarioDTO;
import ac2_project.example.ac2_ca.entity.Comentario;
import ac2_project.example.ac2_ca.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario salvarComentario(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setAluno(comentarioDTO.getAluno());
        comentario.setConteudo(comentarioDTO.getConteudo());
        return comentarioRepository.save(comentario);
    }

    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }
}
