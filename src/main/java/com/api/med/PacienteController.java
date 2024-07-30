package com.api.med;

import com.api.med.paciente.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/atualizarPaciente")
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid DadosAtualizacaoPacienteDTO dadosAtualizacaoPacienteDTO){
        var paciente = pacienteRepository.getReferenceById(dadosAtualizacaoPacienteDTO.id());
        paciente.atualizarPaciente(dadosAtualizacaoPacienteDTO);
    }

    @DeleteMapping("/excluirPaciente/{id}")
    @Transactional
    public ResponseEntity<Paciente> excluirPaciente(@PathVariable Long id){
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativarPaciente/{id}")
    @Transactional
    public ResponseEntity<Paciente> inativarPaciente(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.setAtivo(false);
        return ResponseEntity.noContent().build();
    }
}
