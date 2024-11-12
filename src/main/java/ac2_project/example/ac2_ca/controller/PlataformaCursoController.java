
package ac2_project.example.ac2_ca.controller;

import ac2_project.example.ac2_ca.dto.TopicoDTO;
import ac2_project.example.ac2_ca.dto.ComentarioDTO;
import ac2_project.example.ac2_ca.entity.Topico;
import ac2_project.example.ac2_ca.entity.Comentario;
import ac2_project.example.ac2_ca.service.TopicoService;
import ac2_project.example.ac2_ca.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/curso")
public class PlataformaCursoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/escreverTopico")
    public String escreverTopico(@RequestBody TopicoDTO topicoDTO) {
        Topico topico = topicoService.salvarTopico(topicoDTO);
        return "Tópico '" + topico.getDescricao() + "' computado para " + topico.getAluno();
    }

    @PostMapping("/escreverComentario")
    public String escreverComentario(@RequestBody ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioService.salvarComentario(comentarioDTO);
        return "Comentário '" + comentario.getConteudo() + "' computado para " + comentario.getAluno();
    }
    
    @GetMapping("/verificarGanhadorCurso")
    public String verificarGanhadorCurso() {
        Map<String, Long> topicosPorAluno = topicoService.getAllTopicos().stream()
                .collect(Collectors.groupingBy(Topico::getAluno, Collectors.counting()));
        
        Map<String, Long> comentariosPorAluno = comentarioService.getAllComentarios().stream()
                .collect(Collectors.groupingBy(Comentario::getAluno, Collectors.counting()));
        
        String alunoMaisTopicos = topicosPorAluno.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum aluno");

        String alunoMaisComentarios = comentariosPorAluno.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum aluno");

        if (alunoMaisTopicos.equals(alunoMaisComentarios) && !alunoMaisTopicos.equals("Nenhum aluno")) {
            return alunoMaisTopicos + " escreveu mais tópicos e ajudou mais com comentários, e por isso ganha um curso no final do mês!";
        }
        return "Nenhum aluno conseguiu ser o mais ativo em ambas as categorias.";
    }
}
