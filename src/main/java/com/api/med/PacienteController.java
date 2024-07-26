package com.api.med;

import com.api.med.paciente.DadosCadastroPacienteDTO;
import com.api.med.paciente.DadosPacienteRetornoDto;
import com.api.med.paciente.Paciente;
import com.api.med.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastroPaciente")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    @PostMapping("/paciente")
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dadosPaciente){
        pacienteRepository.save(new Paciente(dadosPaciente));
    }

    @GetMapping("listarPaciente")
    public Page<DadosPacienteRetornoDto> listarPaciente(Pageable pageable){
        return pacienteRepository.findAll(pageable).map(DadosPacienteRetornoDto::new);
    }
}
