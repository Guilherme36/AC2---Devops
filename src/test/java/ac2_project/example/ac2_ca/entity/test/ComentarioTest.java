package ac2_project.example.ac2_ca.entity.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import ac2_project.example.ac2_ca.entity.Comentario;

public class ComentarioTest {

    @Test
    void testSetAndGetAluno() {
        Comentario comentario = new Comentario();
        comentario.setAluno("Aluno1");

        assertEquals("Aluno1", comentario.getAluno());
    }

    @Test
    void testSetAndGetConteudo() {
        Comentario comentario = new Comentario();
        comentario.setConteudo("Este é um comentário de teste.");

        assertEquals("Este é um comentário de teste.", comentario.getConteudo());
    }

    @Test
    void testComentarioIdGeneration() {
        Comentario comentario = new Comentario();
        comentario.setId(1L);

        assertNotNull(comentario.getId());
        assertEquals(1L, comentario.getId());
    }
}
