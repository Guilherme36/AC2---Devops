package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.dto.ComentarioDTO;
import ac2_project.example.ac2_ca.entity.Comentario;
import ac2_project.example.ac2_ca.repository.ComentarioRepository;
import ac2_project.example.ac2_ca.service.ComentarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

class ComentarioServiceTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @InjectMocks
    private ComentarioService comentarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarComentario() {
        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setAluno("Aluno1");
        comentarioDTO.setConteudo("Este é um comentário de teste.");

        Comentario comentario = new Comentario();
        comentario.setAluno("Aluno1");
        comentario.setConteudo("Este é um comentário de teste.");

        when(comentarioRepository.save(any(Comentario.class))).thenReturn(comentario);

        Comentario savedComentario = comentarioService.salvarComentario(comentarioDTO);

        assertEquals("Aluno1", savedComentario.getAluno());
        assertEquals("Este é um comentário de teste.", savedComentario.getConteudo());
    }

    @Test
    void testGetAllComentarios() {
        Comentario comentario1 = new Comentario();
        comentario1.setAluno("Aluno1");
        comentario1.setConteudo("Comentário 1");

        Comentario comentario2 = new Comentario();
        comentario2.setAluno("Aluno2");
        comentario2.setConteudo("Comentário 2");

        when(comentarioRepository.findAll()).thenReturn(Arrays.asList(comentario1, comentario2));

        List<Comentario> comentarios = comentarioService.getAllComentarios();

        assertEquals(2, comentarios.size());
        assertEquals("Aluno1", comentarios.get(0).getAluno());
        assertEquals("Comentário 1", comentarios.get(0).getConteudo());
    }
}
