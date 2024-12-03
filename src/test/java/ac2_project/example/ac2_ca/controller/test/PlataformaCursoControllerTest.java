package ac2_project.example.ac2_ca.controller.test;

import ac2_project.example.ac2_ca.controller.PlataformaCursoController;
import ac2_project.example.ac2_ca.dto.TopicoDTO;
import ac2_project.example.ac2_ca.dto.ComentarioDTO;
import ac2_project.example.ac2_ca.entity.Topico;
import ac2_project.example.ac2_ca.entity.Comentario;
import ac2_project.example.ac2_ca.service.TopicoService;
import ac2_project.example.ac2_ca.service.ComentarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(PlataformaCursoController.class)
public class PlataformaCursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicoService topicoService;

    @MockBean
    private ComentarioService comentarioService;

    @Test
    public void testEscreverTopico() throws Exception {
        Topico mockTopico = new Topico();
        mockTopico.setAluno("Aluno1");
        mockTopico.setDescricao("Descrição do Tópico");

        Mockito.when(topicoService.salvarTopico(any(TopicoDTO.class))).thenReturn(mockTopico);

        mockMvc.perform(post("/api/curso/escreverTopico")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"aluno\":\"Aluno1\", \"descricao\":\"Descrição do Tópico\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tópico 'Descrição do Tópico' computado para Aluno1"));
    }

    @Test
    public void testEscreverComentario() throws Exception {
        Comentario mockComentario = new Comentario();
        mockComentario.setAluno("Aluno2");
        mockComentario.setConteudo("Conteúdo do Comentário");

        Mockito.when(comentarioService.salvarComentario(any(ComentarioDTO.class))).thenReturn(mockComentario);

        mockMvc.perform(post("/api/curso/escreverComentario")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"aluno\":\"Aluno2\", \"conteudo\":\"Conteúdo do Comentário\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Comentário 'Conteúdo do Comentário' computado para Aluno2"));
    }

    @Test
    public void testVerificarGanhadorCursoSemVencedor() throws Exception {
        Topico topico1 = new Topico();
        topico1.setAluno("Aluno1");
        topico1.setDescricao("Tópico 1");

        Topico topico2 = new Topico();
        topico2.setAluno("Aluno1");
        topico2.setDescricao("Tópico 2");

        Topico topico3 = new Topico();
        topico3.setAluno("Aluno2");
        topico3.setDescricao("Tópico 3");

        Comentario comentario1 = new Comentario();
        comentario1.setAluno("Aluno1");
        comentario1.setConteudo("Comentário 1");

        Comentario comentario2 = new Comentario();
        comentario2.setAluno("Aluno1");
        comentario2.setConteudo("Comentário 2");

        Comentario comentario3 = new Comentario();
        comentario3.setAluno("Aluno2");
        comentario3.setConteudo("Comentário 3");

        Comentario comentario4 = new Comentario();
        comentario4.setAluno("Aluno2");
        comentario4.setConteudo("Comentário 4");

        Mockito.when(topicoService.getAllTopicos()).thenReturn(List.of(topico1, topico2, topico3));
        Mockito.when(comentarioService.getAllComentarios()).thenReturn(List.of(comentario1, comentario2, comentario3, comentario4));

        mockMvc.perform(get("/api/curso/verificarGanhadorCurso"))
                .andExpect(status().isOk())
                .andExpect(content().string("Nenhum aluno conseguiu ser o mais ativo em ambas as categorias."));
    }

    @Test
    public void testVerificarGanhadorCursoComVencedor() throws Exception {
        Topico topico1 = new Topico();
        topico1.setAluno("Aluno1");
        topico1.setDescricao("Tópico 1");

        Topico topico2 = new Topico();
        topico2.setAluno("Aluno1");
        topico2.setDescricao("Tópico 2");

        Topico topico3 = new Topico();
        topico3.setAluno("Aluno2");
        topico3.setDescricao("Tópico 3");

        Comentario comentario1 = new Comentario();
        comentario1.setAluno("Aluno1");
        comentario1.setConteudo("Comentário 1");

        Comentario comentario2 = new Comentario();
        comentario2.setAluno("Aluno1");
        comentario2.setConteudo("Comentário 2");

        Comentario comentario3 = new Comentario();
        comentario3.setAluno("Aluno2");
        comentario3.setConteudo("Comentário 3");

        Mockito.when(topicoService.getAllTopicos()).thenReturn(List.of(topico1, topico2, topico3));
        Mockito.when(comentarioService.getAllComentarios()).thenReturn(List.of(comentario1, comentario2, comentario3));

        mockMvc.perform(get("/api/curso/verificarGanhadorCurso"))
            .andExpect(status().isOk())
            .andExpect(content().string("Aluno1 escreveu mais tópicos e ajudou mais com comentários, e por isso ganha um curso no final do mês!"));
    }

    @Test
    public void testVerificarGanhadorCursoSemDados() throws Exception {
        Mockito.when(topicoService.getAllTopicos()).thenReturn(List.of());
        Mockito.when(comentarioService.getAllComentarios()).thenReturn(List.of());

        mockMvc.perform(get("/api/curso/verificarGanhadorCurso"))
            .andExpect(status().isOk())
            .andExpect(content().string("Nenhum aluno conseguiu ser o mais ativo em ambas as categorias."));
}
}
