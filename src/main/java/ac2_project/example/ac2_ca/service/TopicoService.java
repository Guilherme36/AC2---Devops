
package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.dto.TopicoDTO;
import ac2_project.example.ac2_ca.entity.Topico;
import ac2_project.example.ac2_ca.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico salvarTopico(TopicoDTO topicoDTO) {
        Topico topico = new Topico();
        topico.setAluno(topicoDTO.getAluno());
        topico.setDescricao(topicoDTO.getDescricao());
        return topicoRepository.save(topico);
    }

    public List<Topico> getAllTopicos() {
        return topicoRepository.findAll();
    }
}
