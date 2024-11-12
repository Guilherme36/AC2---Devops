package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.dto.TopicoDTO;
import ac2_project.example.ac2_ca.entity.Topico;
import ac2_project.example.ac2_ca.repository.TopicoRepository;
import ac2_project.example.ac2_ca.service.TopicoService;

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

class TopicoServiceTest {

    @Mock
    private TopicoRepository topicoRepository;

    @InjectMocks
    private TopicoService topicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarTopico() {
        TopicoDTO topicoDTO = new TopicoDTO();
        topicoDTO.setAluno("Aluno1");
        topicoDTO.setDescricao("Descrição do tópico de teste.");

        Topico topico = new Topico();
        topico.setAluno("Aluno1");
        topico.setDescricao("Descrição do tópico de teste.");

        when(topicoRepository.save(any(Topico.class))).thenReturn(topico);

        Topico savedTopico = topicoService.salvarTopico(topicoDTO);

        assertEquals("Aluno1", savedTopico.getAluno());
        assertEquals("Descrição do tópico de teste.", savedTopico.getDescricao());
    }

    @Test
    void testGetAllTopicos() {
        Topico topico1 = new Topico();
        topico1.setAluno("Aluno1");
        topico1.setDescricao("Tópico 1");

        Topico topico2 = new Topico();
        topico2.setAluno("Aluno2");
        topico2.setDescricao("Tópico 2");

        when(topicoRepository.findAll()).thenReturn(Arrays.asList(topico1, topico2));

        List<Topico> topicos = topicoService.getAllTopicos();

        assertEquals(2, topicos.size());
        assertEquals("Aluno1", topicos.get(0).getAluno());
        assertEquals("Tópico 1", topicos.get(0).getDescricao());
    }
}
